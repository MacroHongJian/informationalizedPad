package com.example.administrator.zyzywd.zyzywd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zyzywd.MainNavigationActivity;
import com.example.administrator.zyzywd.R;
import com.example.administrator.zyzywd.database.WDJL;
import com.example.administrator.zyzywd.database.WDNR;

import org.litepal.LitePal;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ExamActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    public static Context mContext;
    private TextView mTextMessage;

    public static boolean[][] checkBoxs = new boolean[11][9];
    public static String[] checkBox2daan = new String[11];
    public static int rightAnswers = 0;
    public String pingJia = "不合格";
    public boolean[] wenDa = new boolean[10];
    public String wenDaPingJia = "请答题";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mTextMessage = findViewById(R.id.nav_firstshow);
            RelativeLayout relativeLayout = findViewById(R.id.exam_fragment);
            LinearLayout mLinearLayout = findViewById(R.id.wendalist);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(Html.fromHtml("<h2>技术准备</h2><br><p>Description</p>"));
                    mTextMessage.setVisibility(TextView.VISIBLE);
                    relativeLayout.setVisibility(RelativeLayout.GONE);
                    mLinearLayout.setVisibility(RecyclerView.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setVisibility(TextView.GONE);
                    relativeLayout.setVisibility(RelativeLayout.VISIBLE);
                    mLinearLayout.setVisibility(RecyclerView.GONE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setVisibility(TextView.GONE);
                    relativeLayout.setVisibility(RelativeLayout.GONE);
                    mLinearLayout.setVisibility(RecyclerView.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        mContext = this;

        final SharedPreferences pref = mContext.getSharedPreferences("data", MODE_PRIVATE);
        final List<WDNR> questionContents = LitePal.where("wdxmbs like ?", pref.getString("wdxmbs", "").toString()).find(WDNR.class);

        TextView questionItem = findViewById(R.id.text_view_question);
        questionItem.setText(pref.getString("question", ""));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout relativeLayout = findViewById(R.id.exam_fragment);
        relativeLayout.setVisibility(RelativeLayout.GONE);
        LinearLayout listView = findViewById(R.id.wendalist);
        listView.setVisibility(LinearLayout.GONE);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //问答题
        int wdNum = 0;
        for (final WDNR wdnr : questionContents) {
            if (wdnr.getWDLX().equals("问答题")) {
                wdNum++;
                final int a = wdNum;
                final Button btn = new Button(mContext);
                btn.setText(wdnr.getWDNR());
                btn.setTextSize(20);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(mContext)
                                .setTitle(wdnr.getWDNR())
                                .setMessage(wdnr.getWDDA())
                                .setPositiveButton("合格", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        wenDa[a] = true;
                                    }
                                })
                                .setNegativeButton("不合格", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        wenDa[a] = false;
                                    }
                                })
                                .show();

                    }
                });
                listView.addView(btn);
//                questionList.add(new ImageTextVertical(wdnr.getWDNR(), 0));
            }
        }
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        recyclerView.setLayoutManager(layoutManager);
//        ItemAdapter adapter = new ItemAdapter(questionList, 5);
//        recyclerView.setAdapter(adapter);

        Button endWenDa = findViewById(R.id.end_exam);
        endWenDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wdRightNum = 0;
                for (boolean i : wenDa) {
                    if (i) {
                        wdRightNum++;
                    }
                }
                if (wdRightNum > 1) {
                    wenDaPingJia = "合格";
                } else {
                    wenDaPingJia = "不合格";
                }
                String zonghepingjia;
                if (pingJia.equals("合格") && wenDaPingJia.equals("合格")) {
                    zonghepingjia = "合格";
                } else {
                    zonghepingjia = "不合格";
                }

                // 记录数据库
                WDJL wdjl = new WDJL();
                wdjl.setWDJLBS(java.util.UUID.randomUUID().toString());
                wdjl.setCZZBS(pref.getString("rid", ""));
                wdjl.setZZZ(pref.getString("appraiser", ""));
                wdjl.setFJH(pref.getString("plane", ""));
                wdjl.setWDXMBS(pref.getString("wdxmbs", ""));
                wdjl.setKGTPJ(pingJia);
                wdjl.setZGTPJ(wenDaPingJia);
                wdjl.setZHPJ(zonghepingjia);
                wdjl.setWDSJ(Calendar.getInstance(Locale.CHINESE).getTime());
                wdjl.save();

                new AlertDialog.Builder(ExamActivity.this)
                        .setTitle("考核结果")
                        .setMessage("客观题评价：" + pingJia +
                                "\n主观题评价：" + wenDaPingJia +
                                "\n综合评价为：" + zonghepingjia +
                                "\n时间：" + Calendar.getInstance(Locale.CHINESE).getTime())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ExamActivity.this, MainNavigationActivity.class);
                                mContext.startActivity(intent);
                            }
                        })
                        .show();
            }
        });

        //fragment 里的按钮
        Button prevButton = findViewById(R.id.previous_question);
        final Button nextButton = findViewById(R.id.next_question);
        final TextView questionNumber = findViewById(R.id.text_view_question_count);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
//                if (mViewPager.getCurrentItem() != 9) {
//                    nextButton.setText("下一题");
//                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == 9) {
                    rightAnswers = 0;
                    for (int i = 0; i < 10; i++) {
                        WDNR wdnr = questionContents.get(i);
                        Log.d("exam", wdnr.getWDDA() + checkBox2daan[i + 1]);
                        if (wdnr.getWDDA().equals(checkBox2daan[i + 1])) {
                            rightAnswers++;
                        }
                        if (rightAnswers > 7) {
                            pingJia = "合格";
                        } else {
                            pingJia = "不合格";
                        }
                    }
                    new AlertDialog.Builder(ExamActivity.this)
                            .setTitle("客观题答题结果")
                            .setMessage("你答对了" + rightAnswers + "道问题\n成绩为：" + pingJia)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();

                }

                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
//                if (mViewPager.getCurrentItem() == 9) {
//                    nextButton.setText("提交答案");
//                }
                // questionNumber.setText(Integer.toString(mViewPager.getCurrentItem() + 1));

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                questionNumber.setText(Integer.toString(i + 1));
                if (i == 9) {
                    nextButton.setText("提交答案");
                } else {
                    nextButton.setText("下一题");
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.nav_firstshow);
        mTextMessage.setText(Html.fromHtml("<h4>\n" +
                "\t评分规则：\n" +
                "</h4>\n" +
                "<p>\n" +
                "\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题目分为客观题和主观题，客观题10道答对8道以上即为合格，主观题答对两道即为合格。\n" +
                "</p>"));
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
            final View rootView = inflater.inflate(R.layout.fragment_exam, container, false);
            final SharedPreferences pref = mContext.getSharedPreferences("data", MODE_PRIVATE);
            final List<WDNR> questionContents = LitePal.where("wdxmbs like ?", pref.getString("wdxmbs", "").toString()).find(WDNR.class);
            WDNR wdnr = questionContents.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
            String wdlx = wdnr.getWDLX();
            TextView textView = rootView.findViewById(R.id.section_label);
            TextView sectionType = rootView.findViewById(R.id.section_type);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER), wdnr.getWDNR()));
            sectionType.setText(wdnr.getWDLX() + " （10分）");
            addChooseButtons(wdnr, rootView);
            return rootView;
        }


        public void addChooseButtons(final WDNR wdnr, View v) {
            final Context context = getContext();
            RadioGroup radioGroup = new RadioGroup(context);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            LinearLayout ll = v.findViewById(R.id.checkBoxs);
            ll.setOrientation(LinearLayout.VERTICAL);

            if (wdnr.getWDLX().equals("判断题")) {

                for (int i = 1; i <= 2; i++) {
                    final RadioButton rdbtn = new RadioButton(context);
                    rdbtn.setId(i);
                    rdbtn.setTextSize(20);
                    rdbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int page = getArguments().getInt(ARG_SECTION_NUMBER);
                            if (rdbtn.getId() == Integer.valueOf(1)) {
                                checkBox2daan[page] = "正确";
                            } else {
                                checkBox2daan[page] = "错误";
                            }
//                            Toast.makeText(context, checkBox2daan[page], Toast.LENGTH_SHORT).show();
                        }
                    });
                    if (i == 1) {
                        rdbtn.setText("正确");
                    } else {
                        rdbtn.setText("错误");
                    }
                    radioGroup.addView(rdbtn);
                }
                ((ViewGroup) v.findViewById(R.id.radioGroup)).addView(radioGroup);
            } else if (wdnr.getWDLX().equals("单选题")) {
                for (int i = 1; i <= 4; i++) {
                    final RadioButton rdbtn = new RadioButton(context);
                    rdbtn.setId(i);
                    rdbtn.setTextSize(20);
                    rdbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int page = getArguments().getInt(ARG_SECTION_NUMBER);
                            if (rdbtn.getId() == Integer.valueOf(1)) {
                                checkBox2daan[page] = "A";
                            } else if (rdbtn.getId() == Integer.valueOf(2)) {
                                checkBox2daan[page] = "B";
                            } else if (rdbtn.getId() == Integer.valueOf(3)) {
                                checkBox2daan[page] = "C";
                            } else {
                                checkBox2daan[page] = "D";
                            }
//                            Toast.makeText(context, checkBox2daan[page], Toast.LENGTH_SHORT).show();
                        }
                    });
                    switch (i) {
                        case 1:
                            rdbtn.setText(wdnr.getXXA());
                            break;
                        case 2:
                            rdbtn.setText(wdnr.getXXB());
                            break;
                        case 3:
                            rdbtn.setText(wdnr.getXXC());
                            break;
                        case 4:
                            rdbtn.setText(wdnr.getXXD());
                            break;
                    }
                    radioGroup.addView(rdbtn);
                }
                ((ViewGroup) v.findViewById(R.id.radioGroup)).addView(radioGroup);
            } else if (wdnr.getWDLX().equals("多选题")) {
                for (int i = 1; i < 9; i++) {
                    final CheckBox checkBox = new CheckBox(context);
                    checkBox.setTextSize(20);
                    checkBox.setId(i);
                    final int page = getArguments().getInt(ARG_SECTION_NUMBER);
                    for (int j = 1; j < 9; j++) {
                        checkBoxs[page][j] = false;
                    }
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (checkBox.isChecked()) {
                                checkBoxs[page][checkBox.getId()] = true;
                            } else {
                                checkBoxs[page][checkBox.getId()] = false;
                            }
                            checkBox2daan[page] = "";
                            for (int k = 0; k < 8; k++) {
                                char a = 'A';
                                if (checkBoxs[page][k + 1]) {
                                    a += k;
                                    checkBox2daan[page] += Character.toString(a);
                                }
                            }
//                            Toast.makeText(context, checkBox2daan[page], Toast.LENGTH_SHORT).show();
                        }
                    });
                    switch (i) {
                        case 1:
                            checkBox.setText(wdnr.getXXA());
                            ll.addView(checkBox);
                            break;
                        case 2:
                            checkBox.setText(wdnr.getXXB());
                            ll.addView(checkBox);
                            break;
                        case 3:
                            checkBox.setText(wdnr.getXXC());
                            ll.addView(checkBox);
                            break;
                        case 4:
                            checkBox.setText(wdnr.getXXD());
                            ll.addView(checkBox);
                            break;
                        case 5:
                            if (!wdnr.getXXE().isEmpty()) {
                                checkBox.setText(wdnr.getXXE());
                                ll.addView(checkBox);
                            }
                            break;
                        case 6:
                            if (!wdnr.getXXF().isEmpty()) {
                                checkBox.setText(wdnr.getXXF());
                                ll.addView(checkBox);
                            }
                            break;
                        case 7:
                            if (!wdnr.getXXG().isEmpty()) {
                                checkBox.setText(wdnr.getXXG());
                                ll.addView(checkBox);
                            }
                            break;

                        case 8:
                            if (!wdnr.getXXH().isEmpty()) {
                                checkBox.setText(wdnr.getXXH());
                                ll.addView(checkBox);
                            }
                            break;
                    }
                }
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