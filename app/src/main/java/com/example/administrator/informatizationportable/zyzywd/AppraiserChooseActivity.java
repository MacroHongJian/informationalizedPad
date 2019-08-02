package com.example.administrator.zyzywd.zyzywd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.DLQX;

import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class AppraiserChooseActivity extends AppCompatActivity {

    private static final String TAG = "PlaneNumberChoose";

    private List<ImageTextVertical> appraiserList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraiser_choose);
        final SharedPreferences pref = getContext().getSharedPreferences("data", MODE_PRIVATE);
        String rid = pref.getString("rid", "null");
        DLQX persion = LitePal.where("rid like ?", rid).find(DLQX.class).get(0);
        final List<DLQX> dlqxes = LitePal.where("yhzy like ?", persion.getYHZY()).find(DLQX.class);
        initAppraiser(dlqxes, "all");

        SearchView searchAppraiser = findViewById(R.id.appraiser_search);
        searchAppraiser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                initAppraiser(dlqxes, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                initAppraiser(dlqxes, newText);
                return false;
            }
        });
    }

    private void initAppraiser(List<DLQX> dlqxes, String query) {
        Log.d(TAG, "initAppraiser: preparing planesdata");
        appraiserList.clear();
        if (query == "all") {
            for (int i = 0; i < dlqxes.size(); i++) {
                File imgFile = new File("/sdcard/data/persion/" + dlqxes.get(i).getPIC());
                if (!(dlqxes.get(i).getPIC() == null) && imgFile.exists()) {
                    appraiserList.add(new ImageTextVertical(dlqxes.get(i).getZSXM(), imgFile));
                } else {
                    appraiserList.add(new ImageTextVertical(dlqxes.get(i).getZSXM(), R.drawable.ic_appraiser_noimg));
                }
            }
        } else {
            for (int i = 0; i < dlqxes.size(); i++) {
                if (dlqxes.get(i).getZSXM().contains(query)) {
                    File imgFile = new File("/sdcard/data/persion/" + dlqxes.get(i).getPIC());
                    if (!(dlqxes.get(i).getPIC() == null) && imgFile.exists()) {
                        appraiserList.add(new ImageTextVertical(dlqxes.get(i).getZSXM(), imgFile));
                    } else {
                        appraiserList.add(new ImageTextVertical(dlqxes.get(i).getZSXM(), R.drawable.ic_appraiser_noimg));
                    }
                }
            }
        }
        RecyclerView recyclerView = findViewById(R.id.appraiser_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(appraiserList, 2);
        recyclerView.setAdapter(adapter);
    }
}
