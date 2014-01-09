package com.nichtemna.views.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.nichtemna.Views.R;
import com.nichtemna.views.ui.edittexts.EnterCodeLinearLayout;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class EditTextActivity extends Activity implements EnterCodeLinearLayout.OnCodeFullyEnteredListener {

    public static void start(Activity pActivity) {
        Intent intent = new Intent(pActivity, EditTextActivity.class);
        pActivity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        ((EnterCodeLinearLayout) findViewById(R.id.activityEdittextsCodeLinearLayout)).setCodeFullyEnteredListener(this);
    }

    @Override
    public void onCodeFullyEntered(String code) {
        Toast.makeText(this, "Entered code is " + code, Toast.LENGTH_LONG).show();
    }
}