package com.example.viraj.vj_custompatternlock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.viraj.vj_custompatternlock.SecurityPattern.DrawSecurityPattern;
import com.example.viraj.vj_custompatternlock.Utils.PreferenceKeys;
import com.example.viraj.vj_custompatternlock.Utils.Preferences;


/**
 * Created by Viraj Jage on 03-03-2017.
 */

public class MainActivity extends AppCompatActivity {

    ToggleButton btnSetPattern;
    CheckBox chkHidePattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        TextView txtHidePattern = (TextView) findViewById(R.id.txtHidePattern);
        btnSetPattern = (ToggleButton) findViewById(R.id.btnAddPattern);
        chkHidePattern = (CheckBox) findViewById(R.id.chkStealthMode);
        txtHidePattern.setVisibility(View.INVISIBLE);
        chkHidePattern.setVisibility(View.INVISIBLE);
        chkHidePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkHidePattern.isChecked()) {
                    Preferences.addPreferenceBoolean(MainActivity.this, PreferenceKeys.isStealthMode, true);
                } else {
                    chkHidePattern.setChecked(false);
                    Preferences.addPreferenceBoolean(MainActivity.this, PreferenceKeys.isStealthMode, false);
                }
            }
        });
        boolean stealthMode = Preferences.getPreferenceBoolean(MainActivity.this, PreferenceKeys.isStealthMode, false);
        if (stealthMode) {
            chkHidePattern.setChecked(true);
        } else {
            chkHidePattern.setChecked(false);
        }
        boolean securityFlag = Preferences.getPreferenceBoolean(MainActivity.this, PreferenceKeys.isSecuritySet, false);
        if (securityFlag) {
            btnSetPattern.setChecked(true);
        } else {
            btnSetPattern.setChecked(false);
        }

        btnSetPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSetPattern.isChecked()) {
                    // btnSetPattern.setChecked(true);
                    Intent intent = new Intent(MainActivity.this, DrawSecurityPattern.class);
                    startActivity(intent);
                } else {
                    btnSetPattern.setChecked(false);
                    Preferences.addPreferenceBoolean(MainActivity.this, PreferenceKeys.isSecuritySet, false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
