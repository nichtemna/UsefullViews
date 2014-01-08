package com.nichtemna.views.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.nichtemna.Views.R;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class EditTextActivity extends Activity {

    public static void start(Activity pActivity) {
        Intent intent = new Intent(pActivity, EditTextActivity.class);
        pActivity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
    }
}