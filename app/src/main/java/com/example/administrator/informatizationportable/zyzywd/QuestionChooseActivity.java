package com.example.administrator.zyzywd.zyzywd;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.DLQX;
import com.example.administrator.zyzywd.database.WDXM;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class QuestionChooseActivity extends AppCompatActivity {

    private static final String TAG = "QuestionChooseActivity";

    private List<ImageTextVertical> questionList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_choose);
        SQLiteDatabase db = LitePal.getDatabase();
        initQuestionItem();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.question_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(questionList, 4);
        recyclerView.setAdapter(adapter);
    }

    private void initQuestionItem() {
        Log.d(TAG, "initPlanes: preparing questionitem");
        questionList.clear();
        final SharedPreferences pref = getContext().getSharedPreferences("data", MODE_PRIVATE);
        String rid = pref.getString("rid", "null");
        DLQX persion = LitePal.where("rid like ?", rid).find(DLQX.class).get(0);
        List<WDXM> wdxms = LitePal.where("xmzy like ?", persion.getYHZY()).find(WDXM.class);
        for (WDXM wdxm : wdxms) {
            questionList.add(new ImageTextVertical(wdxm.getXMMC(), 0, wdxm.getWDXMBS()));
        }
//        questionList.add(new ImageTextVertical("hello", 0));
    }
}
