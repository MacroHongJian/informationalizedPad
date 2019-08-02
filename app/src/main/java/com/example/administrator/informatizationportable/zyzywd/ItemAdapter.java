package com.example.administrator.zyzywd.zyzywd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zyzywd.R;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context mContext;
    private List<ImageTextVertical> mItemList;
    private int mtype;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView itemImage;
        TextView itemName;
        TextView planeNumber;
        TextView questionChoose;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            itemImage = view.findViewById(R.id.item_image);
            itemName = view.findViewById(R.id.item_name);
            planeNumber = view.findViewById(R.id.plane_number);
            questionChoose = view.findViewById(R.id.question_choose);
        }
    }

    //type for intent to go
    // 1 for persion, 2 for plane, 3 for question, 4 for exam
    public ItemAdapter(List<ImageTextVertical> itemList, int type) {
        mItemList = itemList;
        mtype = type;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.imagetext, parent, false);
        View planeNumber = LayoutInflater.from(mContext).inflate(R.layout.plane_number, parent, false);
        View questionChoose = LayoutInflater.from(mContext).inflate(R.layout.question_choose, parent, false);
        switch (mtype) {
            case 3:
                return new ViewHolder(planeNumber);
            case 4:
                return new ViewHolder(questionChoose);
            default:
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final SharedPreferences.Editor editor = mContext.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        final SharedPreferences pref = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);


        final String name = pref.getString("name", "");
        final String plane = pref.getString("plane", "");
        final String appraiser = pref.getString("appraiser", "");
        final String mode = pref.getString("mode", "");
        final ImageTextVertical item = mItemList.get(position);
        switch (mtype) {
            case 3:
                holder.planeNumber.setText(item.getName());
                break;
            case 4:
                holder.questionChoose.setText(item.getName());
                break;
            default:
                holder.itemName.setText(item.getName());
                if (item.getImageId() != 0) {
                    Glide.with(mContext).load(item.getImageId()).into(holder.itemImage);
                } else {
                    Glide.with(mContext).load(item.getFile()).into(holder.itemImage);
                }
                break;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click on: " + item.getImageId());

                switch (mtype) {
                    case 2:
                        editor.putString("appraiser", item.getName());
                        editor.apply();
                        Intent planeIntent = new Intent(mContext, PlaneNumberChoose.class);
                        mContext.startActivity(planeIntent);
                        break;
                    case 3:
                        editor.putString("plane", item.getName());
                        editor.apply();
                        Intent questionIntent = new Intent(mContext, QuestionChooseActivity.class);
                        mContext.startActivity(questionIntent);
                        break;
                    case 4:
                        editor.putString("wdxmbs", item.getBs());
                        editor.putString("question", item.getName());
                        editor.apply();
                        if (mode.equals("考核模式")) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle("你确定要开始问答吗？")
                                    .setMessage("操作者：" + name + "\n实施者：" + appraiser + "\n飞机号：" + plane + "\n问答项目：" + item.getName())
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent examIntent = new Intent(mContext, ExamActivity.class);
                                            examIntent.putExtra("questionItem", item.getName());
                                            mContext.startActivity(examIntent);
                                        }
                                    })
                                    .setNegativeButton("返回", null)
                                    .show();
                        } else {
                            Intent examIntent = new Intent(mContext, ExamActivity.class);
                            examIntent.putExtra("questionIte m", item.getName());
                            mContext.startActivity(examIntent);
                        }
                        break;
                    case 5:
                        // 历史记录查询不响应
                        break;
                }
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
