package piotrek.databinding;

import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextBoxBinding {

    @BindingAdapter({"textBinding"})
    public static void bindEditText(EditText view, final ObservableString text) {

        if (text == null)
            throw new IllegalArgumentException("ObservableString object cannot be null");

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
