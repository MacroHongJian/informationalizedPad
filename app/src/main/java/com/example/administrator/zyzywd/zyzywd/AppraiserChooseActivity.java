package com.example.administrator.zyzywd.zyzywd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.zyzywd.R;

import java.util.ArrayList;
import java.util.List;

public class AppraiserChooseActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private static final String TAG = "PlaneNumberChoose";

    private ImageTextVertical[] appraisers = {new ImageTextVertical("xxx", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("qwe", R.drawable.ic_appraiser_noimg), new ImageTextVertical("gfh", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("qqq", R.drawable.ic_appraiser_noimg), new ImageTextVertical("bnj", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("sdf", R.drawable.ic_appraiser_noimg), new ImageTextVertical("kyt", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("cxd", R.drawable.ic_appraiser_noimg), new ImageTextVertical("bky", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("wer", R.drawable.ic_appraiser_noimg), new ImageTextVertical("shf", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("dfg", R.drawable.ic_appraiser_noimg), new ImageTextVertical("jgb", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("uik", R.drawable.ic_appraiser_noimg), new ImageTextVertical("jev", R.drawable.ic_appraiser_noimg),
            new ImageTextVertical("yuk", R.drawable.ic_appraiser_noimg), new ImageTextVertical("未定义", R.drawable.ic_appraiser_noimg)};

    private List<ImageTextVertical> appraiserList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraiser_choose);

        mTextMessage = (TextView) findViewById(R.id.message);

        initAppraiser();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.appraiser_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(appraiserList, 2);
        recyclerView.setAdapter(adapter);
    }

    private void initAppraiser() {
        Log.d(TAG, "initAppraiser: preparing planesdata");
        appraiserList.clear();
        for (int i = 0; i < 17; i++) {
            appraiserList.add(appraisers[i]);
        }
    }
}
