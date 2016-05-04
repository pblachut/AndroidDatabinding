package piotrek.databinding.bindings;

import android.databinding.BindingAdapter;
import android.widget.EditText;

import piotrek.databinding.BindableType;
import piotrek.databinding.R;
import piotrek.databinding.TrimmedTextWatcher;

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
