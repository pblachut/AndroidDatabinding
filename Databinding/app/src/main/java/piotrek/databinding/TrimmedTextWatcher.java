package piotrek.databinding;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TrimmedTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String newValue = s.toString();

        if (newValue != null)
            newValue = newValue.trim();

        onTextChanged(newValue);
    }

    public abstract void onTextChanged(String newValue);
}
