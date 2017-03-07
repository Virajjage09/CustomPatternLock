package com.example.viraj.vj_custompatternlock.SecurityPattern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amnix.materiallockview.MaterialLockView;
import com.example.viraj.vj_custompatternlock.R;

import java.util.List;

/**
 * Created by Viraj Jage on 03-03-2017.
 */

public class DrawSecurityPattern extends AppCompatActivity {

    private String CorrectPattern = "0000";
    private MaterialLockView materialLockView;
    TextView btnContinue,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_security_pattern);

        btnContinue = (TextView) findViewById(R.id.btnContinue);
        btnContinue.setEnabled(false);
        btnBack = (TextView) findViewById(R.id.btnBack);
        materialLockView = (MaterialLockView) findViewById(R.id.pattern);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawSecurityPattern.this,DrawConfirmSecurityPattern.class);
                intent.putExtra("patternValue",CorrectPattern);
                startActivity(intent);
            }
        });
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                Log.e("SimplePattern", SimplePattern);
                if (SimplePattern.length() < 4) {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                    btnContinue.setEnabled(false);
                    // materialLockView.clearPattern();

                } else {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                    CorrectPattern = SimplePattern;
                    btnContinue.setEnabled(true);

                }
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
    }

}
