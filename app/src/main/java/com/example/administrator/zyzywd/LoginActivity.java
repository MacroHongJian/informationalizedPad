package com.example.administrator.zyzywd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private CheckBox rememberId;
    CardView loginShiShiZhe;
    CardView loginCaoZuoZhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.login_id);
        rememberId = findViewById(R.id.remember_id);
        loginShiShiZhe = (CardView) findViewById(R.id.login_shishizhe);
        loginCaoZuoZhe = (CardView) findViewById(R.id.login_caozuozhe);
        boolean isRemember = pref.getBoolean("is_remember_id", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            accountEdit.setText(account, TextView.BufferType.EDITABLE);
            rememberId.setChecked(true);
        }
        loginShiShiZhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                if (account.equals("333")) {
                    editor = pref.edit();
                    if (rememberId.isChecked()) {
                        editor.putBoolean("is_remember_id", true);
                        editor.putString("account", account);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginCaoZuoZhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                if (account.equals("333")) {
                    editor = pref.edit();
                    if (rememberId.isChecked()) {
                        editor.putBoolean("is_remember_id", true);
                        editor.putString("account", account);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
