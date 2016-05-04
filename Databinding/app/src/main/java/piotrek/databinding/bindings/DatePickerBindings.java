package piotrek.databinding.bindings;

import android.databinding.BindingAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import piotrek.databinding.BindableType;
import piotrek.databinding.R;

public class DatePickerBindings {

    @BindingAdapter({"dateBinding"})
    public static void bindEditText(DatePicker view, final BindableType<Calendar> date) {

        if (date == null)
            throw new IllegalArgumentException("BindableType<Calendar> object cannot be null");

        if (view.getTag(R.id.dataBinding) == null){
            view.setTag(R.id.dataBinding, true);
            view.init(view.getYear(), view.getMonth(), view.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar currentValue = date.get();

                    currentValue.set(year, monthOfYear, dayOfMonth);

                    date.set(currentValue);
                }
            });
        }

        int year = view.getYear();
        int month = view.getMonth();
        int day = view.getDayOfMonth();

        Calendar dateFromView = Calendar.getInstance();
        dateFromView.set(year, month, day);

        Calendar dateFromViewModel = date.get();

        if (!dateFromView.equals(dateFromViewModel)) {
            view.updateDate(dateFromViewModel.get(Calendar.YEAR), dateFromViewModel.get(Calendar.MONTH), dateFromViewModel.get(Calendar.DAY_OF_MONTH));
        }
    }
}
