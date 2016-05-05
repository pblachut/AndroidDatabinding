Android databinding
==========================================================

Databinding is a approach which connects data sources with data consumers and gives possibility to automatically update each other. Such approach was already developed for some time in other languages like C# (WPF) or Javascript (Angular, Knockout). Now finally it comes to Android.

How it worked before databinding
----------------------------------------------------------

Before databinding it was natural for Android that to set or get same value from control you should have reference to it firstly. 

```java
TextView label = (TextView) view.findViewById(R.id.textViewId);
label.setText("sample text");
String textFromLabel = label.getText();

Button button = (Button) view.findViewById(R.id.buttonId);
button.setOnClickListener(new View.OnClickListener() {
    public void onClick(View v) {
        // Perform action on click
    }
});
```

It introduces huge amount of code which was useless from application logic perspective. Of course there are external libraries like [Butter Knife](http://jakewharton.github.io/butterknife/) which helps a lot with this but it does not resolve an issue. Activities and fragments are still strictly bounded to resource layouts and have to carry all the references to controls.

Introducing of databinding gives possibility to implement MVVM pattern on Android. This pattern has been developed by Microsoft and it is variation of MVP pattern.

![](mvvm.png)

Main role in this pattern has view model object which mediates in exchange of data between view and the model. Comunication between view model and the view is made by using databinding. View has possibility to get current value of some data and view model can notify the view that data has changed. View can also pass ui events to view model that user has performed some action (e.g. text in text box has changed or button has been clicked).

How to setup project
-------------------------------------------------------------

Only thing that should be performed before databinding would be used in the android project is to setup gradle app file.

```gradle
android {
    dataBinding {
        enabled = true
    }
}
```

One way binding
-------------------------------------------------------

Firstly you should create view model file which would be used in android layout file. Important thing is that view model class should extend BaseObservable class.

```java
public class MainViewModel extends BaseObservable {
    
}
```
Later on you should be able to reference view model class in your layout file. Main tag in the layout file should be `<layout>..</layout>`. 

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable name="viewModel" type="piotrek.databinding.MainViewModel"/>
    </data>
    <LinearLayout...>
</layout>
```

After that it should be able to create instance of generated binding class. Binding class name bases on layout file name. In activity class instating binding class should be performed instead of standard setContentView(..) method. Generated binding class should have method to set object used in layout reference.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MainViewModel viewModel = new MainViewModel();
    MainViewBinding binding = DataBindingUtil.setContentView(this, R.layout.main_view);
    binding.setViewModel(viewModel);
}
```

Now view (layout file) and view model class is connected via code behind class (activity class) and later modifications would be done only in view and view model. First of all, all data that would be referenced in view should be defined in view model. One way binding (view can read values from the view model) can be achieved in 3 ways:

- public field of ObservableField<> type
- private field with public getter and setter

```java
public class MainViewModel extends BaseObservable {
    private String name;
    public ObservableField<String> description;

    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        if (!Objects.equals(name,newName)) {
            name = newName;
            notifyPropertyChanged(piotrek.databinding.BR.name);
        }
    }

    public MainViewModel(){
        name = "Name of MainViewModel";
        description = new ObservableField<>("Description of MainViewModel");
    }
}
```

All those fields can be referenced in layout file.

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable name="viewModel" type="piotrek.databinding.MainViewModel"/>
    </data>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".MainView">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.name}" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.description}" />
    </LinearLayout>
</layout>

```

When the view would be loaded then layout can automatically read values set in view model, without additional code in activity class. The same situation would be when view model would trigger change of the values. UI controls would be automatically updated.

How Andorid databinding works under the hood
--------------------------------------------------

Databinding firstly takes all layouts and looks inside of them binding expressions inside `@{...}` brachets. Then it tries to resolve those expressions into functions and based on them it will generate java code for propper set and get values in the layout controls. There is no magic behind. It is simply code generation similar to that which is in Butterknife or Dagger libraries. But this code generation makes creating layouts more easy. Activites or fragments classes are becoming only dummy linkers between view model and view objects. 

One time binding
-------------------------------------------------------

Specific type of one way binding is one time binding. It bases on the fact that value is loaded into view only one time. Later on view would not receive any updates of this value. Such bindings can be used to display some kind of labels or anather texts which are loaded only once.

To use such binding view model should have simple, not observable, public field. Attaching them into view is the same as in one way binding.

Two way binding
--------------------------------------------------

To achieve two way databinding it is needed to write some more additional code. Current Google implementation do not support out of the box automatic updates of view model triggered by view. 

To enable automatic updates of view model triggered by changing input of some control it is needed to create firstly binding adapter class. Binding adapter class should be specific for each control. It is caused because each control set their value in different way.

```java
public class EditTextBoxBinding {
    @BindingAdapter({"textBinding"})
    public static void bindEditText(EditText view, final BindableType<String> text) {
        if (text == null)
            throw new IllegalArgumentException("BindableType<String> object cannot be null");

        if (view.getTag(R.id.dataBinding) == null){
            view.setTag(R.id.dataBinding, true);
            view.addTextChangedListener(new TrimmedTextWatcher() {
                @Override
                public void onTextChanged(String newValue) {
                    text.set(newValue);
                }
            });
        }

        String textFromView = view.getText().toString();
        String textFromViewModel = text.get();

        if (!textFromView.equals(textFromViewModel)) {
            view.setText(textFromViewModel);
        }
    }
}
```

In the first part of the method it is checked if binding was already attached into control. If it wasn`t then text changed listener is added which updates the view model value. In the second part of the method two values, from the view and view model are compared. If they are different then it means that value in view model was changed and it must be updated on view.

Such defined binding can be used in layout view file. Of course before using it, `public BindableType<String> description` should be defined in view model.

```xml
<EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:textBinding="@{viewModel.description}"/>
```

After running such code, view model would automatically receiving updates if EditText control input would change. As it is shown `android:text=""` markup property is not used. All work is done by `app:textBinding=""`.

Important thing to mention is that `ObservableField<>` type cannot be used even if it does the same as `BindableType<>` type. The reason is that android databinding framework hides its observability to binding adapter classes and it was done intentionally. 

 
Summary
--------------------------------------------------

Android databinding is a step into good direction for eliminating useless parts of codes from the app logic perspective. It gives also possibility to implement MVVM pattern which is very popular in other frameworks and gives alternative for currently most used in Android MVP pattern. 

Of course it is still under development and the biggest drawback is lack of support two way bindings.