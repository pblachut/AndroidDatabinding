package piotrek.databinding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class CheckboxBindings {

    @BindingAdapter({"checkedBinding"})
    public static void bindCheckBox(CheckBox view, final BindableBoolean isCheckedObject) {

        if (isCheckedObject == null)
            throw new IllegalArgumentException("ObservableBoolean object cannot be null");

        if (view.getTag(R.id.dataBinding) == null){
            view.setTag(R.id.dataBinding, true);
            view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    isCheckedObject.set(isChecked);
                }
            });
        }

        boolean viewValue = view.isChecked();
        boolean viewModelValue = isCheckedObject.get();

        if (viewValue != viewModelValue) {
            view.setChecked(viewModelValue);
        }
    }
}
