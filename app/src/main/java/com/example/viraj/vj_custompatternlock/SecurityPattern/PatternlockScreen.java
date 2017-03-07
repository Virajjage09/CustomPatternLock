package com.example.viraj.vj_custompatternlock.SecurityPattern;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class PatternlockScreen extends AppCompatActivity {

    private MaterialLockView materialLockView;
    String setPattern;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patternlock_screen);
        context = PatternlockScreen.this;
        materialLockView = (MaterialLockView) findViewById(R.id.pattern);
        setPattern = Preferences.getPreference(PatternlockScreen.this, PreferenceKeys.strPatternNo,"");

       /* if(Preferences.getPreferenceBoolean(context,PreferenceKeys.isStealthMode,false)){
            materialLockView.setInStealthMode(true);
        }else{
            materialLockView.setInStealthMode(false);
        }*/
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                Log.e("SimplePattern", SimplePattern);
                if (!SimplePattern.equalsIgnoreCase(setPattern)) {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                    // materialLockView.clearPattern();
                    Toast.makeText(context, "Please draw correct pattern", Toast.LENGTH_SHORT).show();

                } else {

                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                    Intent intent = new Intent(PatternlockScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
    }
}
