package com.yiw.circledemo2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.ui.MyApplication;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.spannable.CircleMovementMethod;
import com.yiw.circledemo2.spannable.SpannableClickable;
import com.yiw.circledemo2.utils.UrlUtils;
import com.yiw.circledemo2.widgets.CommentListView;
import com.yiw.circledemo2.bean.RecordListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiwei on 16/3/2.
 */
public class CommentAdapter {

    private Context mContext;
    private CommentListView mListview;
    private List<RecordListBean> mDatas;

    public CommentAdapter(Context context){
        mContext = context;
        mDatas = new ArrayList<RecordListBean>();
    }

    public CommentAdapter(Context context, List<RecordListBean> datas){
        mContext = context;
        setDatas(datas);
    }

    public void bindListView(CommentListView listView){
        if(listView == null){
            throw new IllegalArgumentException("CommentListView is null....");
        }
        mListview = listView;
    }

    public void setDatas(List<RecordListBean> datas){
        if(datas == null ){
            datas = new ArrayList<RecordListBean>();
        }
        mDatas = datas;
    }

    public List<RecordListBean> getDatas(){
        return mDatas;
    }

    public int getCount(){
        if(mDatas == null){
            return 0;
        }
        return mDatas.size();
    }

    public RecordListBean getItem(int position){
        if(mDatas == null){
            return null;
        }
        if(position < mDatas.size()){
            return mDatas.get(position);
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private View getView(final int position){

        View convertView = View.inflate(mContext,
                R.layout.im_social_item_comment, null);
        TextView commentTv = (TextView) convertView.findViewById(R.id.commentTv);
        final CircleMovementMethod circleMovementMethod = new CircleMovementMethod(R.color.name_selector_color,
                R.color.name_selector_color);

        Log.d("LD","----1:"+mDatas.toString());
        final RecordListBean bean = mDatas.get(position);
     //   Log.d("LD","----1:"+bean.toString()+"---"+mDatas.toString());
//        Log.d("LD","----2:"+bean.getUser().toString() );
        String name = bean.getUser().getNickName();
       // String id = bean.getId()+"";
        String toReplyName = "";
        if (bean.getUser() != null) {
            toReplyName = bean.getUser().getNickName();
        }

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(name, bean.getUser().getId()+""));

        if (!TextUtils.isEmpty(toReplyName)) {
            builder.append(" 回复 ");
            builder.append(setClickableSpan(toReplyName, bean.getUser().getId()+""));
        }
        builder.append(": ");
        //转换表情字符
        String contentBodyStr = bean.getContent();
        builder.append(UrlUtils.formatUrlString(contentBodyStr));
        commentTv.setText(builder);

        commentTv.setMovementMethod(circleMovementMethod);
        commentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LD","setOnClickListener");

                if (circleMovementMethod.isPassToTv()) {
                    mListview.getOnItemClickListener().onItemClick(position);
                }
            }
        });
        commentTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LD","setOnLongClickListener");
                if (circleMovementMethod.isPassToTv()) {
                    mListview.getOnItemLongClickListener().onItemLongClick(position);
                    return true;
                }
                return false;
            }
        });


        return convertView;
    }

    @NonNull
    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(){
                                    @Override
                                    public void onClick(View widget) {
                                        Toast.makeText(MyApplication.getContext(), textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public void notifyDataSetChanged(){
        if(mListview == null){
            throw new NullPointerException("listview is null, please bindListView first...");
        }
        mListview.removeAllViews();
        if(mDatas == null || mDatas.size() == 0){
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i=0; i<mDatas.size(); i++){
            final int index = i;
            View view = getView(index);
            if(view == null){
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }

            mListview.addView(view, index, layoutParams);
        }

    }

}
