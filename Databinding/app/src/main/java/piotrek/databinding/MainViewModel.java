package piotrek.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

public class MainViewModel extends BaseObservable {

    private String name;
    public ObservableField<String> description;

    @Bindable
    public String getName(){
        return name;
    }

    public MainViewModel(){
        name = "Name of MainViewModel";
        description = new ObservableField<>("Description of MainViewModel");
    }


}
