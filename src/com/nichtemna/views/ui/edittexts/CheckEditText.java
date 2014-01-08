package com.nichtemna.views.ui.edittexts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.nichtemna.Views.R;
import com.nichtemna.views.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: temnanich
 * Date: icon_samples/20/13
 * Time: icon_paper_yellow:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class CheckEditText extends EditText implements View.OnKeyListener {
    protected String mRegex;
    private Context mContext;
    private Drawable imgCheckButton = getResources().getDrawable(R.drawable.icon_check_yellow_little);
    private boolean isMatch = false;

    public void setMatchListener(OnCheckEditTextMatchListener matchListener) {
        mMatchListener = matchListener;
    }

    private OnCheckEditTextMatchListener mMatchListener;
    private TextWatcher textWather = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            handleCheckImage(getEnteredText());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public CheckEditText(Context context) {
        super(context);
        init(context);
    }

    public CheckEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public CheckEditText(Context context, AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckEditText);
        try {
            int resourceId = a.getResourceId(R.styleable.CheckEditText_regex, R.string.regex_all);
            mRegex = getResources().getString(resourceId);
        } finally {
            a.recycle();
        }
    }

    private void init(Context context) {
        mContext = context;
        imgCheckButton.setBounds(0, 0, imgCheckButton.getIntrinsicWidth(), imgCheckButton.getIntrinsicHeight());
        addTextChangedListener(textWather);
        handleCheckImage(getEnteredText());
        setOnKeyListener(this);
    }

    public boolean isMatch() {
        return isMatch;
    }

    protected String getRegex() {
        return mRegex;
    }

    /**
     * On every editing we check if entered text is match regex
     *
     * @param text
     */
    private void handleCheckImage(String text) {
        if (Utils.isMatch(text, getRegex())) {
            if (mMatchListener != null) {
                mMatchListener.onCheckEditTextMatches();
            }
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCheckButton,
                    this.getCompoundDrawables()[3]);
            isMatch = true;
        } else {
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null,
                    this.getCompoundDrawables()[3]);
            isMatch = false;
        }
    }


    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_ENTER:
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                            mContext.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
                    return true;
            }
        }
        return false;
    }

    public String getEnteredText() {
        return getText().toString();
    }
}
