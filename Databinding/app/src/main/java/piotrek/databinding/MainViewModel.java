package piotrek.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

public class MainViewModel extends BaseObservable {
    private String name;
    public String subtitle;
    public ObservableField<String> description;

    public String getName(){
        return name;
    }

    public MainViewModel(){
        name = "Name of MainViewModel";
        subtitle = "Subtitle of MainViewModel";
        description = new ObservableField<>("Description of MainViewModel");
    }
}
