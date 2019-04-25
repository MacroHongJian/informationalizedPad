package com.example.administrator.zyzywd.zyzywd;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.QuestionItem;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

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

    private void initQuestionItem(){
        Log.d(TAG, "initPlanes: preparing questionitem");
        questionList.clear();
        List<QuestionItem> questionItems = LitePal.where("professions like ?", "机械").find(QuestionItem.class);
        for (QuestionItem questionItem: questionItems) {
            questionList.add(new ImageTextVertical(questionItem.getItemName(), 0));
        }
//        questionList.add(new ImageTextVertical("hello", 0));
    }
}
