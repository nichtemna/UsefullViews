package com.nichtemna.views.ui.edittexts;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.nichtemna.Views.R;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class CheckWithPrefixEditText extends CheckEditText {
      private Context mContext;
    private String mPrefix = "";

    private TextWatcher textWather = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (getText().length() < mPrefix.length()) {
                setText(mPrefix);
            } else if (getText().length() >= mPrefix.length() && !getText().subSequence(0, mPrefix.length()).toString().equals(mPrefix)) {
                setText(mPrefix + s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public CheckWithPrefixEditText(Context context) {
        super(context);
        init(context);
    }

    public CheckWithPrefixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public CheckWithPrefixEditText(Context context, AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        addTextChangedListener(textWather);
        setText(mPrefix);
        setSelection(mPrefix.length());
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckWithPrefixEditText);
        try {
            int resourceId = a.getResourceId(R.styleable.CheckWithPrefixEditText_prefix_check, R.string.empty_string);
            mPrefix = getResources().getString(resourceId);
        } finally {
            a.recycle();
        }
    }

    /**
     * We do not let user put selection before TAG
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (getText().length() != 0 && selStart == selEnd && selStart == 0 && mPrefix != null) {
            {
                setSelection(mPrefix.length());
            }
            super.onSelectionChanged(selStart, selEnd);
        }
    }



}