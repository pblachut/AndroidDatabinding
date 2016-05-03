package piotrek.databinding;

import android.databinding.BaseObservable;

public class BindableString extends BaseObservable {
    private String value;

    public BindableString(String newValue){
        value = newValue;
    }

    public String get() {
        return value;
    }

    public void set(String newValue) {
        if (equals(value, newValue) == false) {
            value = newValue;
            notifyChange();
        }
    }

    public static boolean equals(Object a, Object b) {
        if (a == null)
            return (b == null);
        else
            return a.equals(b);
    }

}
