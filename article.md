1. What is databinding
Databinding is a approach which connects data sources with data consumers and gives possibility to automatically update each other. Such approach was already developed for some time in other languages like C# (WPF) or Javascript (Angular, Knockout). Now finally it comes to Android.

2. How it worked before databinding
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
});```

It introduces huge amount of code which was useless from application logic perspective. Of course there are external libraries like [Butter Knife](http://jakewharton.github.io/butterknife/) which helps a lot with this but it does not resolve an issue. Activities and fragments are still strictly bounded to resource layouts and have to carry all the references to controls.

3. How it works in databinding
4. How to setup project
5. One way databinding
6. Two way databinding
7. Summary