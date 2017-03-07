package com.example.viraj.vj_custompatternlock;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.viraj.vj_custompatternlock.SecurityPattern.PatternlockScreen;
import com.example.viraj.vj_custompatternlock.Utils.PreferenceKeys;
import com.example.viraj.vj_custompatternlock.Utils.Preferences;

/**
 * Created by Viraj Jage on 03-03-2017.
 */


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean securityFlag = Preferences.getPreferenceBoolean(SplashActivity.this, PreferenceKeys.isSecuritySet, false);
                if (securityFlag) {
                    Intent intent = new Intent(SplashActivity.this, PatternlockScreen.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);


    }
}
