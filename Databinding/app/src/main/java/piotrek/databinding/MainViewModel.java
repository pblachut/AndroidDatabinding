package piotrek.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainViewModel extends BaseObservable {
    private String name;

    public TrimmedTextWatcher getNameTextWatcher(){
        return new TrimmedTextWatcher() {
            @Override
            public void onTextChanged(String newValue) {
                setName(newValue);
            }
        };
    }

    public BindableType<String> description;

    public BindableType<Boolean> isSelected;

    public BindableType<Calendar> date;


    private IMainView view;

    @Bindable
    public String getName(){
        return name;
    }

    public void setName(String newName){

        if (!Objects.equals(name,newName)) {
            name = newName;

            notifyPropertyChanged(piotrek.databinding.BR.name);
        }
    }

    public void onButtonClick(View viewFromButton){
        String message = new StringBuilder("Name: ")
                .append(name)
                .append("\n")
                .append("Description: ")
                .append(description.get())
                .append("\n")
                .append("Is selected: ")
                .append(isSelected.get())
                .append("\n")
                .append("Date: ")
                .append(date.get().getTime())
                .toString();

        view.showMessage(message);
    }


    public MainViewModel(IMainView view){
        this.view = view;
        name = null;
        description = new BindableType<>(null);
        isSelected = new BindableType<>(true);
        date = new BindableType<>(Calendar.getInstance());
    }
}
