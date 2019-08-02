package com.example.administrator.zyzywd.zyzywd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.zyzywd.R;

public class ExamBottomNavigation extends AppCompatActivity {
    private TextView mTextMessage;
    public static Context mContext;
    private Fragment mFragmentContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(Html.fromHtml("<h2>技术准备</h2><br><p>Description</p>"));
                    return true;
                case R.id.navigation_dashboard:
                    Intent exam = new Intent(ExamBottomNavigation.this, ExamActivity.class);
                    startActivity(exam);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_bottom_navigation);

        mContext = this;
        SharedPreferences pref = this.getSharedPreferences("data", MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(pref.getString("question", ""));
        setSupportActionBar(toolbar);
        Button EndExam = findViewById(R.id.end_exam);
        EndExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ExamBottomNavigation.this)
                        .setTitle("")
                        .setMessage("你确定要结束问答吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("返回", null)
                        .show();
            }
        });
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.pingfenguize);
        mTextMessage.setText(Html.fromHtml("<h4>\n" +
                "\t评分规则：\n" +
                "</h4>\n" +
                "<p>\n" +
                "\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题目分为客观题和主观题，客观题10道答对8道以上即为合格，主观题答对两道即为合格。\n" +
                "</p>"));
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(ExamBottomNavigation.this)
                .setTitle("你确定要结束问答吗？")
                .setMessage("问答记录不会被保存，如要保存结束请点右上角")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("返回", null)
                .show();
    }
}
