package com.example.administrator.zyzywd.zyzywd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.zyzywd.R;

import java.util.ArrayList;
import java.util.List;

public class PlaneNumberChoose extends AppCompatActivity {

    private static final String TAG = "PlaneNumberChoose";

    private ImageTextVertical[] planes = {new ImageTextVertical("01", 0),
            new ImageTextVertical("02", 0), new ImageTextVertical("03", 0),
            new ImageTextVertical("06", 0), new ImageTextVertical("08", 0),
            new ImageTextVertical("10", 0), new ImageTextVertical("11", 0),
            new ImageTextVertical("12", 0), new ImageTextVertical("13", 0),
            new ImageTextVertical("15", 0), new ImageTextVertical("18", 0),
            new ImageTextVertical("19", 0), new ImageTextVertical("20", 0),
            new ImageTextVertical("21", 0), new ImageTextVertical("22", 0),
            new ImageTextVertical("23", 0), new ImageTextVertical("25", 0),
            new ImageTextVertical("26", 0), new ImageTextVertical("27", 0),
            new ImageTextVertical("28", 0), new ImageTextVertical("30", 0),
            new ImageTextVertical("31", 0), new ImageTextVertical("32", 0),
            new ImageTextVertical("33", 0), new ImageTextVertical("36", 0),
            new ImageTextVertical("37", 0), new ImageTextVertical("未定义", 0)};

    private List<ImageTextVertical> planeList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_number_choose);

        initPlanes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plane_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(planeList, 3);
        recyclerView.setAdapter(adapter);
    }

    private void initPlanes(){
        Log.d(TAG, "initPlanes: preparing planesdata");
        planeList.clear();
        for (int i = 0; i < 27; i++) {
            planeList.add(planes[i]);
        }
    }
}
