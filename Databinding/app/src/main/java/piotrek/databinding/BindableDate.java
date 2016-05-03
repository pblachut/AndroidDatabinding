package piotrek.databinding;

import android.databinding.BaseObservable;

import java.util.Calendar;
import java.util.Date;

public class BindableDate extends BaseObservable {

    private Calendar date;

    public BindableDate(Calendar date){
        this.date = date;
    }

    public Calendar get(){
        return date;
    }

    public void set(Calendar newDate){
        if (!equals(newDate)){
            date = newDate;
            notifyChange();
        }
    }


    private boolean equals(Calendar newDate){
        if (date == null)
            return (newDate == null);
        else
            return date.equals(newDate);
    }
}
