package com.example.administrator.zyzywd.zyzywd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zyzywd.MainActivity;
import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.QuestionContent;

import org.litepal.LitePal;

import java.nio.charset.Charset;
import java.util.List;


public class ExamActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    public static Context mContext;

    private String questionItemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        mContext = this;

        final SharedPreferences pref = mContext.getSharedPreferences("data", MODE_PRIVATE);

        Toast.makeText(mContext, pref.getString("plane", ""), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, pref.getString("appraiser", ""), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, pref.getString("question", ""), Toast.LENGTH_SHORT).show();

        TextView questionItem = findViewById(R.id.text_view_question);
        questionItem.setText(pref.getString("question", ""));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Button prevButton = findViewById(R.id.previous_question);
        final Button nextButton = findViewById(R.id.next_question);

        final TextView questionNumber = findViewById(R.id.text_view_question_count);


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                if(mViewPager.getCurrentItem() != 9) {
                    nextButton.setText("下一题");
                }
                questionNumber.setText(Integer.toString(mViewPager.getCurrentItem() + 1));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == 9) {
                    new AlertDialog.Builder(ExamActivity.this)
                            .setTitle("")
                            .setMessage("你确定要结束问答吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent main = new Intent(ExamActivity.this, MainActivity.class);
                                    startActivity(main);
                                }
                            })

                            .setNegativeButton("返回", null)
                            .show();

                }
                mViewPager.

                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                if (mViewPager.getCurrentItem() == 9) {
                    nextButton.setText("结束问答");
                }
                questionNumber.setText(Integer.toString(mViewPager.getCurrentItem() + 1));

            }
        });
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_exam, container, false);
            RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
            RadioButton[] radioButtons = new RadioButton[4];
            radioButtons[0] = rootView.findViewById(R.id.radio_button0);
            radioButtons[1] = rootView.findViewById(R.id.radio_button1);
            radioButtons[2] = rootView.findViewById(R.id.radio_button2);
            radioButtons[3] = rootView.findViewById(R.id.radio_button3);
            TextView textView = rootView.findViewById(R.id.section_label);
            TextView sectionType = rootView.findViewById(R.id.section_type);
            final SharedPreferences pref = mContext.getSharedPreferences("data", MODE_PRIVATE);

            List<QuestionContent> questionContents = LitePal.where("itemname like ?", pref.getString("question", "").toString()).find(QuestionContent.class);

            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER), questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getContentName()));
            sectionType.setText(questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getTypes() + " （10分）");

            radioButtons[0].setText(questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getOptionA());
            radioButtons[1].setText(questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getOptionB());
            radioButtons[2].setText(questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getOptionC());
            radioButtons[3].setText(questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getOptionD());

            addListenerOnButton(inflater, container);
            return rootView;
        }

        public void addListenerOnButton(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_exam, container, false);
            RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = rootView.findViewById(selectedId);

            if (selectedId != -1) {
                 Toast.makeText(mContext, "choose " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title1";
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 10;
        }
    }
}
