package com.nichtemna.views.ui.edittexts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class EditTextForPassword extends EditText {

    public EditTextForPassword(Context context) {
        super(context);
        init(context);
    }

    public EditTextForPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTextForPassword(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
    }

    /**
     * We do not let user put selection before TAG
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (getText().length() != 0 && selStart == selEnd && selStart == 0) {
            {
                setSelection(1);
            }
            super.onSelectionChanged(selStart, selEnd);
        }
    }
}
