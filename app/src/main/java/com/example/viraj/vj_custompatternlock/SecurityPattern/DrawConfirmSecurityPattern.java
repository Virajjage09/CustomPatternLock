package com.example.viraj.vj_custompatternlock.SecurityPattern;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amnix.materiallockview.MaterialLockView;
import com.example.viraj.vj_custompatternlock.MainActivity;
import com.example.viraj.vj_custompatternlock.R;

import com.example.viraj.vj_custompatternlock.Utils.PreferenceKeys;
import com.example.viraj.vj_custompatternlock.Utils.Preferences;

import java.util.List;


/**
 * Created by Viraj Jage on 03-03-2017.
 */

public class DrawConfirmSecurityPattern extends AppCompatActivity {

    private String CorrectPattern = "0000";
    private MaterialLockView materialLockView;
    TextView btnSetPattern, btnBack;
    String setPattern;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_confirm_security_pattern);

        materialLockView = (MaterialLockView) findViewById(R.id.pattern);
        btnSetPattern = (TextView) findViewById(R.id.btnSetPattern);
        btnSetPattern.setEnabled(false);
        btnBack = (TextView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        context = DrawConfirmSecurityPattern.this;
        btnSetPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Security Pattern Activated successfully.", Toast.LENGTH_SHORT).show();
                Preferences.addPreference(context,PreferenceKeys.strPatternNo,CorrectPattern);
                Preferences.addPreferenceBoolean(context,PreferenceKeys.isSecuritySet,true);
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        if (null != getIntent().getStringExtra("patternValue") || getIntent().getStringExtra("patternValue") != "") {
            setPattern = getIntent().getStringExtra("patternValue");
        }
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                Log.e("SimplePattern", SimplePattern);
                if (!SimplePattern.equalsIgnoreCase(setPattern)) {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                    btnSetPattern.setEnabled(false);
                    // materialLockView.clearPattern();
                    Snackbar snackbar = Snackbar
                            .make(materialLockView, "Please draw correct pattern", Snackbar.LENGTH_LONG);

                    snackbar.show();

                } else {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                    CorrectPattern = SimplePattern;
                    btnSetPattern.setEnabled(true);
                }
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
    }
}
