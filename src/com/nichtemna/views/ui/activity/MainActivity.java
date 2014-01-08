package com.nichtemna.views.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nichtemna.Views.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activityMainButtonEditTexts).setOnClickListener(this);
    }

    @Override
    public void onClick(View pView) {
        switch (pView.getId()) {
            case R.id.activityMainButtonEditTexts:
                EditTextActivity.start(MainActivity.this);
                break;
        }
    }
}
