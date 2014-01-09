package com.nichtemna.views.ui.edittexts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.nichtemna.Views.R;

import java.util.ArrayList;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class EnterCodeLinearLayout extends LinearLayout {
    private ArrayList<EditText> mEditTexts = new ArrayList<EditText>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnCodeFullyEnteredListener mCodeFullyEnteredListener;
    private TextWatcher mTextWatcher = new TextWatcher() {
        boolean empty;

        @Override
        public void beforeTextChanged(CharSequence pCharSequence, int i, int i2, int i3) {
            for (int j = 0; j < mEditTexts.size(); j++) {
                if (mEditTexts.get(j).hasFocus()) {
                    empty = mEditTexts.get(j).getText().length() == 0;
                }
            }
        }

        @Override
        public void onTextChanged(CharSequence pCharSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable pEditable) {
            //go back and  forth in row of edittexts
            for (int j = 0; j < mEditTexts.size(); j++) {
                if (mEditTexts.get(j).hasFocus() && mEditTexts.get(j).getText().length() == 1 && j != 3 && empty) {
                    mEditTexts.get(j).clearFocus();
                    mEditTexts.get(j + 1).requestFocus();
                } else if (mEditTexts.get(j).hasFocus() && mEditTexts.get(j).getText().length() == 1 && j == 3 && empty) {
                    mEditTexts.get(j).clearFocus();
                    mEditTexts.get(j).requestFocus();
                } else if (mEditTexts.get(j).hasFocus() && mEditTexts.get(j).getText().length() == 0 && j != 0 && !empty) {
                    mEditTexts.get(j).clearFocus();
                    mEditTexts.get(j - 1).requestFocus();
                }
            }

            if (getEnteredCode().length() == 4 && mCodeFullyEnteredListener != null) {
                mCodeFullyEnteredListener.onCodeFullyEntered(getEnteredCode());
            }
        }
    };

    public void setCodeFullyEnteredListener(OnCodeFullyEnteredListener pCodeFullyEnteredListener) {
        mCodeFullyEnteredListener = pCodeFullyEnteredListener;
    }

    public EnterCodeLinearLayout(Context context) {
        super(context);
        initLayout(context);
        init(context);
    }

    public EnterCodeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        init(context);
    }

    private void initLayout(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mLayoutInflater.inflate(R.layout.view_enter_code, this, true);

        mEditTexts.add((EditText) findViewById(R.id.viewEnterCodeEditText1));
        mEditTexts.add((EditText) findViewById(R.id.viewEnterCodeEditText2));
        mEditTexts.add((EditText) findViewById(R.id.viewEnterCodeEditText3));
        mEditTexts.add((EditText) findViewById(R.id.viewEnterCodeEditText4));

        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(mTextWatcher);
        }
    }

    private void init(Context pContext) {
        mContext = pContext;
    }

    public String getEnteredCode() {
        StringBuilder sb = new StringBuilder();
        for (EditText editText : mEditTexts) {
            sb.append(editText.getText());
        }
        return sb.toString();
    }

    public interface OnCodeFullyEnteredListener {
        void onCodeFullyEntered(String code);
    }

}
