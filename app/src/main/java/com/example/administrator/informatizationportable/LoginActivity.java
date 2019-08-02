package com.example.administrator.zyzywd;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zyzywd.database.DLQX;
import com.example.administrator.zyzywd.opratingcard.Function;

import org.litepal.LitePal;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class LoginActivity extends Activity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText zsxmEdit;
    private EditText passwdEdit;
    private CheckBox rememberId;
    private ImageView persionImg;
    CardView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getContext().getSharedPreferences("data", MODE_PRIVATE);
        zsxmEdit = findViewById(R.id.login_id);
        passwdEdit = findViewById(R.id.login_password);
        rememberId = findViewById(R.id.remember_id);
        loginBtn = findViewById(R.id.login_button);
        persionImg = findViewById(R.id.persion);
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!Function.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
        boolean isRemember = pref.getBoolean("is_remember_id", false);
        if (isRemember) {
            String rid = pref.getString("rid", "");
            DLQX dlqx = LitePal.where("rid like ?", rid).find(DLQX.class).get(0);
            zsxmEdit.setText(dlqx.getZSXM(), TextView.BufferType.EDITABLE);
            rememberId.setChecked(true);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LitePal.getDatabase();
                String zsxm = zsxmEdit.getText().toString();
                DLQX dlqx;
                List<DLQX> dlqxes = LitePal.where("zsxm like ?", zsxm).find(DLQX.class);
                for (int i = 0; i < dlqxes.size(); i++) {
                    dlqx = dlqxes.get(i);
                    if (dlqxes.get(i).getSBM().equals(passwdEdit.getText().toString())) {
                        editor = pref.edit();
                        if (rememberId.isChecked()) {
                            editor.putBoolean("is_remember_id", true);
                            editor.putString("rid", dlqx.getRID());
                            editor.putString("name", dlqx.getZSXM());
                        } else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码不对", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        passwdEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    String zsxm = zsxmEdit.getText().toString();
//                    DLQX dlqx;
//                    List<DLQX> dlqxes = LitePal.where("zsxm like ?", zsxm).find(DLQX.class);
//                    for (int i = 0; i < dlqxes.size(); i++) {
//                        dlqx = dlqxes.get(i);
//                        if (dlqxes.get(i).getSBM().equals(passwdEdit.getText().toString())){
//                            if(dlqx.getPIC().isEmpty()){
//                                File imgFile = new File("/sdcard/data/persion/" + dlqxes.get(i).getPIC());
//                                if (!dlqxes.get(i).getPIC().isEmpty() && imgFile.exists()) {
//                                    appraiserList.add(new ImageTextVertical(dlqxes.get(i).getZSXM(), imgFile));
//                                }else{
//                                    Glide.with(getBaseContext()).load(R.drawable.ic_appraiser_noimg).into(holder.itemImage);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        });
    }
}
