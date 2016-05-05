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
    public String label;

    public BindableType<String> description;

    public BindableType<Boolean> isSelected;

    public BindableType<Calendar> date;


    private IMainView view;

    public void onButtonClick(View viewFromButton){
        String message = new StringBuilder("Description: ")
                .append(description.get())
                .append("\n")
                .append("Label: ")
                .append(label)
                .append("\n")
                .append("Is selected: ")
                .append(isSelected.get())
                .append("\n")
                .append("Date: ")
                .append(date.get().getTime())
                .toString();

        view.showMessage(message);
    }

    public void onUpdateViewModelValuesButtonClick(View viewFromButton){
        label += " updated";
        description.set(description.get() + " updated");
        isSelected.set(!isSelected.get());

        Calendar updatedDate = Calendar.getInstance();
        updatedDate.setTime(date.get().getTime());
        updatedDate.add(Calendar.DAY_OF_MONTH, 1);

        date.set(updatedDate);
    }


    public MainViewModel(IMainView view){
        this.view = view;
        label = "LABEL";
        description = new BindableType<>(null);
        isSelected = new BindableType<>(true);
        date = new BindableType<>(Calendar.getInstance());
    }
}
