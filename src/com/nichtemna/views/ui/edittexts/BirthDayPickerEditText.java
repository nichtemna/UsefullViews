package com.nichtemna.views.ui.edittexts;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.nichtemna.Views.R;
import com.nichtemna.views.utils.DateUtils;
import com.nichtemna.views.utils.Utils;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: temnanich
 * Date: icon_samples/20/13
 * Time: icon_paper_yellow:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class BirthDayPickerEditText extends EditText implements View.OnTouchListener, DatePickerDialog.OnDateSetListener {
    private Drawable imgCheckButton = getResources().getDrawable(R.drawable.icon_check_yellow_little);
    private Context mContext;
    private int mYear, mMonth, mDay;
    private Calendar mCalendar;

    public BirthDayPickerEditText(Context context) {
        super(context);
        init(context);
    }

    public BirthDayPickerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BirthDayPickerEditText(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        imgCheckButton.setBounds(0, 0, imgCheckButton.getIntrinsicWidth(), imgCheckButton.getIntrinsicHeight());
        setOnTouchListener(this);
        mCalendar = Calendar.getInstance();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        boolean valueToReturn = false;
        if (event.getAction() != MotionEvent.ACTION_UP) {
            valueToReturn = false;
        } else {
            DatePickerDialog dialog = new DatePickerDialog(mContext, this, mCalendar.get(Calendar.YEAR),  mCalendar.get(Calendar.MONTH),  mCalendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
            valueToReturn = true;
        }
        return valueToReturn;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                          int dayOfMonth) {
        setDate(year, monthOfYear, dayOfMonth);
    }

    public void setDate(int year, int monthOfYear,
                        int dayOfMonth) {
        //get birthday time in mills
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, monthOfYear, dayOfMonth);

        //set birthday as text
        setText(DateUtils.convertCalendarToString(mCalendar, DateUtils.DATE_FORMAT_DAY_MONTH_YEAR));

        if (Utils.isMatchAge(mCalendar.getTimeInMillis())) {
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCheckButton,
                    this.getCompoundDrawables()[3]);
        } else {
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null,
                    this.getCompoundDrawables()[3]);
        }
    }


    public String getDateServer() {
        return DateUtils.convertCalendarToString(mCalendar, DateUtils.DATE_FORMAT_YEAR_MONTH_DAY);
    }

    public boolean isMatchAge() {
        return Utils.isMatchAge(mCalendar.getTimeInMillis());
    }
}
