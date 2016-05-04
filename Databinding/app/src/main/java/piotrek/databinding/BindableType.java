package piotrek.databinding;

import android.databinding.BaseObservable;

import java.util.Objects;

public class BindableType<T> extends BaseObservable {
    /*
    * Content copied from ObservableField<>
    * types which bases on ObservableField<> hides their observability
    * see: https://code.google.com/p/android/issues/detail?id=187130
    */
    private T mValue;

    public BindableType(T value) {
        mValue = value;
    }

    public T get() {
        return mValue;
    }

    public void set(T value) {
        if (!Objects.equals(mValue, value)) {
            mValue = value;
            notifyChange();
        }
    }
}
