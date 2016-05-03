package piotrek.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

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

    public BindableString description;

    public BindableBoolean isSelected;

    public BindableDate date;


    private IMainView view;

    @Bindable
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;

        notifyPropertyChanged(piotrek.databinding.BR.name);
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
        description = new BindableString(null);
        isSelected = new BindableBoolean(true);
        date = new BindableDate(Calendar.getInstance());
    }
}
