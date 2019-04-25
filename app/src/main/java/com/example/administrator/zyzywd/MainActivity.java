package com.example.administrator.zyzywd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.example.administrator.zyzywd.opratingcard.OperatingCard;
import com.example.administrator.zyzywd.zyzywd.AppraiserChooseActivity;
import com.example.administrator.zyzywd.zyzywd.QuestionChooseActivity;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
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
                            Intent questionChoose = new Intent(MainActivity.this, QuestionChooseActivity.class);
                            startActivity(questionChoose);
                            break;
                        case 1:
                            editor.putString("mode", "考核模式");
                            editor.apply();
                            Intent appraiserChoose = new Intent(MainActivity.this, AppraiserChooseActivity.class);
                            startActivity(appraiserChoose);
                            break;
//                        case 2:
//                            Intent historyQuire = new Intent(MainActivity.this, AppraiserChooseActivity.class);
//                            startActivity(historyQuire);
//                            break;
//                        case 3:
//                            Intent cardQuire = new Intent(MainActivity.this, AppraiserChooseActivity.class);
//                            startActivity(cardQuire);
//                            break;
                        case 4:
                            Intent opratingCard = new Intent(MainActivity.this, OperatingCard.class);
                            startActivity(opratingCard);
                            break;

                    }
                }
            });
        }
    }
}

