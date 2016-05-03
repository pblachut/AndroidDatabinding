package piotrek.databinding;

import android.databinding.BaseObservable;

public class BindableBoolean extends BaseObservable {

    /*
    * Content copied from ObservableBoolean
    * types which bases on ObservableField<> hides their observability
    * see: https://code.google.com/p/android/issues/detail?id=187130
    */

    private boolean mValue;

    public BindableBoolean(boolean value) {
        mValue = value;
    }

    public boolean get() {
        return mValue;
    }

    public void set(boolean value) {
        if (value != mValue) {
            mValue = value;
            notifyChange();
        }
    }
}
