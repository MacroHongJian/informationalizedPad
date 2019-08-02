package com.example.administrator.zyzywd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zyzywd.database.DLQX;
import com.example.administrator.zyzywd.opratingcard.OperatingCard;
import com.example.administrator.zyzywd.zyzywd.AppraiserChooseActivity;
import com.example.administrator.zyzywd.zyzywd.ExamHistory;
import com.example.administrator.zyzywd.zyzywd.QuestionChooseActivity;

import org.litepal.LitePal;

import java.io.File;

import static org.litepal.LitePalApplication.getContext;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("信息化便携终端");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "离线/在线模式切换", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final SharedPreferences pref = getContext().getSharedPreferences("data", MODE_PRIVATE);
        String rid = pref.getString("rid", "null");
        DLQX persion = LitePal.where("rid like ?", rid).find(DLQX.class).get(0);
        View navView  = navigationView.getHeaderView(0);
        ImageView pic = navView.findViewById(R.id.main_nav_img);
        TextView text1 = navView.findViewById(R.id.main_nav_text1);
        TextView text2 = navView.findViewById(R.id.main_nav_text2);
        text1.setText(persion.getZSXM() + " " + persion.getYHZY());
        text2.setText(persion.getZD());
        File imgFile = new File("/sdcard/data/persion/" + persion.getPIC());
        if (!(persion.getPIC() == null) && imgFile.exists()) {
            Glide.with(this).load(imgFile).into(pic);
        } else {
            Glide.with(this).load(R.drawable.ic_appraiser_noimg).into(pic);
        }
        mainGrid = findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            final SharedPreferences.Editor editor = getSharedPreferences("data",
                    MODE_PRIVATE).edit();
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            editor.putString("mode", "训练模式");
                            editor.apply();
                            Intent questionChoose = new Intent(MainNavigationActivity.this, QuestionChooseActivity.class);
                            startActivity(questionChoose);
                            break;
                        case 1:
                            editor.putString("mode", "考核模式");
                            editor.apply();
                            Intent appraiserChoose = new Intent(MainNavigationActivity.this, AppraiserChooseActivity.class);
                            startActivity(appraiserChoose);
                            break;
                        case 2:
                            Intent historyQuire = new Intent(MainNavigationActivity.this, ExamHistory.class);
                            startActivity(historyQuire);
                            break;
                        case 3:
                            Intent cardQuire = new Intent(MainNavigationActivity.this, AppraiserChooseActivity.class);
                            startActivity(cardQuire);
                            break;
                        case 4:
                            Intent opratingCard = new Intent(MainNavigationActivity.this, OperatingCard.class);
                            startActivity(opratingCard);
                            break;

                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainNavigationActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
