package com.example.administrator.zyzywd.zyzywd;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.DLQX;
import com.example.administrator.zyzywd.database.WDJL;
import com.example.administrator.zyzywd.database.WDXM;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class ExamHistory extends AppCompatActivity {

    private static final String TAG = "QuestionChooseActivity";

    private List<ImageTextVertical> wdjlList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_choose);

        Toolbar toolbar = findViewById(R.id.question_choose_toolbar);
        toolbar.setTitle("问答历史记录");
        setSupportActionBar(toolbar);

        SQLiteDatabase db = LitePal.getDatabase();
        initQuestionItem();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.question_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(wdjlList, 5);
        recyclerView.setAdapter(adapter);
    }

    private void initQuestionItem(){
        Log.d(TAG, "initPlanes: preparing questionitem");
        wdjlList.clear();
        final SharedPreferences pref = getContext().getSharedPreferences("data", MODE_PRIVATE);
        List<WDJL> wdjls = LitePal.findAll(WDJL.class);
        Collections.reverse(wdjls);
        for (WDJL wdjl: wdjls) {
            String jl;
            String xmmc = LitePal.where("wdxmbs like ?", wdjl.getWDXMBS()).find(WDXM.class).get(0).getXMMC();
            String name = LitePal.where("rid like ?", wdjl.getCZZBS()).find(DLQX.class).get(0).getZSXM();
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            Log.d("exam", wdjl.getFJH());
            jl = "项目名称：" + xmmc + "\n操作者:" + name + " 组织者:" + wdjl.getZZZ() + " 飞机号:" + wdjl.getFJH() +
                    "\n客观题评价:" + wdjl.getKGTPJ() + " 主观题评价:" + wdjl.getZGTPJ() + " 综合评价:" + wdjl.getZHPJ() +
            "\n考核时间:" + format.format(wdjl.getWDSJ());
            wdjlList.add(new ImageTextVertical(jl, 0));
        }
    }
}
