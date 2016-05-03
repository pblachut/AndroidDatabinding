package piotrek.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;

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

    public ObservableString description;
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
                .toString();

        view.showMessage(message);
    }


    public MainViewModel(IMainView view){
        this.view = view;
        name = null;
        description = new ObservableString(null);
    }
}
