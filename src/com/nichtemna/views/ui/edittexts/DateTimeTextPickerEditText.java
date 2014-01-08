package com.nichtemna.views.ui.edittexts;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.nichtemna.views.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class DateTimeTextPickerEditText extends EditText implements View.OnTouchListener, DateTimePicker.ICustomDateTimeListener {
    private Context mContext;
    private Calendar mCalendar = null;

    public DateTimeTextPickerEditText(Context context) {
        super(context);
        init(context);
    }

    public DateTimeTextPickerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DateTimeTextPickerEditText(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        setOnTouchListener(this);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        setDate(calendar);
    }

    public void setDate(int year, int month, int day, int hour, int minute, int second) {

        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, day, hour, minute, second);
        setText(getDateUser(mCalendar));
    }


    public void setDate(Calendar calendar) {
        mCalendar = calendar;
        setText(getDateUser(mCalendar));
    }


    /**
     * Converts date to readable state   for user
     */

    public String getDateUser(Calendar calendar) {
        return new SimpleDateFormat(DateUtils.DATE_FORMAT_DAY_TIME, new Locale("ru","RU")).format(calendar.getTime());
    }

    /**
     * Converts date to readable state   for server
     */
    public String getDateServer() {
        return new SimpleDateFormat(DateUtils.DATE_FORMAT_FULL, new Locale("ru","RU")).format(mCalendar.getTime());
    }

    /**
     * Creates dialog for user to choose date and time
     *
     * @return
     */
    public DateTimePicker getDateTimePicker() {
        DateTimePicker custom = new DateTimePicker(mContext, this);
        custom.set24HourFormat(true);
        custom.setDate(mCalendar);
        return custom;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        boolean valueToReturn = false;
        if (event.getAction() != MotionEvent.ACTION_UP) {
            valueToReturn = false;
        } else {
            DateTimePicker dialog = getDateTimePicker();
            dialog.showDialog();
            valueToReturn = true;
        }
        return valueToReturn;
    }

    /**
     * On date set
     */
    @Override
    public void onSet(Dialog dialog, Calendar calendarSelected, Date dateSelected, int year, String monthFullName, String monthShortName, int monthNumber, int date, String weekDayFullName, String weekDayShortName, int hour24, int hour12, int min, int sec, String AM_PM) {
        setDate(calendarSelected);
    }

    @Override
    public void onCancel() {
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

}
