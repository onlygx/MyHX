package com.yiw.circledemo2.adapter;

import android.content.Context;
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
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.bean.ChildBean;
import com.yiw.circledemo2.bean.RecordListBean;
import com.yiw.circledemo2.spannable.CircleMovementMethod;
import com.yiw.circledemo2.spannable.CircleMovementMethodC;
import com.yiw.circledemo2.spannable.SpannableClickable;
import com.yiw.circledemo2.utils.UrlUtils;
import com.yiw.circledemo2.widgets.ChildCommentListView;
import com.yiw.circledemo2.widgets.CommentListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiwei on 16/3/2.
 */
public class ChildAdapter {

    private Context mContext;
    private ChildCommentListView mListview;
    private List<ChildBean> mDatas;
    int position;

    public ChildAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList<>();
    }

    public ChildAdapter(Context context, List<ChildBean> datas) {
        mContext = context;
        setDatas(datas);
    }

    public void bindListView(ChildCommentListView listView) {
        if (listView == null) {
            throw new IllegalArgumentException("CommentListView is null....");
        }
        mListview = listView;
    }

    public void setDatas(List<ChildBean> datas) {
        if (datas == null) {
            datas = new ArrayList<ChildBean>();
        }
        mDatas = datas;
    }

    public List<ChildBean> getDatas() {
        return mDatas;
    }

    public int getCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    public ChildBean getItem(int position) {
        if (mDatas == null) {
            return null;
        }
        if (position < mDatas.size()) {
            return mDatas.get(position);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private View getView(final int position) {
        this.position = position;
        View convertView = View.inflate(mContext,
                R.layout.im_childsocial_item_comment, null);

        TextView commentTv = (TextView) convertView.findViewById(R.id.ChildcommentTv);
        final CircleMovementMethodC circleMovementMethod = new CircleMovementMethodC(R.color.name_selector_color,
                R.color.name_selector_color);

        final ChildBean bean = mDatas.get(position);
        String Myname = bean.getUser().getNickName();//

        String toReplyName = "";
        if (bean.getUser() != null) {
            toReplyName = bean.getReceiveUser().getNickName();
        }

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(Myname, bean.getUser().getId() + ""));


        if (!TextUtils.isEmpty(toReplyName)) {
            //回复评论

            builder.append(setClickableSpan(" 回复 ", bean.getUser().getId() + ""));
            builder.append(setClickableSpan(toReplyName, bean.getUser().getId() + ""));
            builder.append(setClickableSpan(": ", bean.getUser().getId() + ""));

        }
        //转换表情字符
        String contentBodyStr = bean.getContent();
        builder.append(setClickableSpan(UrlUtils.formatUrlString(contentBodyStr).toString(), bean.getUser().getId() + ""));
        commentTv.setText(builder);

        commentTv.setMovementMethod(circleMovementMethod);


        mListview.setOnItemClick(new ChildCommentListView.ChildCommentListViewOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("LD", "CCCCCC11111111111111111setOnClickListener");
                //mListview.getOnItemClickListener().onItemClick(position);
            }
        });

        //回复
        commentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildBean cb=mDatas.get(position);
                String str=cb.getType()+"=="+cb.getContent()+"=="+cb.getUser().getNickName();
                Log.d("LD", "CCCCCC222222222222222setOnClickListener"+str);

               /* if (circleMovementMethod.isPassToTv()) {
                    mListview.getOnItemClickListener().onItemClick(position);
                }*/
            }
        });
        commentTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LD", "set33333333333333333333333333333OnLongClickListener");
                if (circleMovementMethod.isPassToTv()) {
                    mListview.getOnItemLongClickListener().onItemLongClick(position);
                    return true;
                }
                return false;
            }
        });


        return convertView;
    }


    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable() {
                                    @Override
                                    public void onClick(View widget) {
                                        // Toast.makeText(MyApplication.getContext(), textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
                                        //if (circleMovementMethod.isPassToTv()) {
                                        //
                                        // }
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public void notifyDataSetChanged() {
        if (mListview == null) {
            DD.e("istview is null, please bindListView first...");
            throw new NullPointerException("listview is null, please bindListView first...");
        }
        mListview.removeAllViews();
        if (mDatas == null || mDatas.size() == 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mDatas.size(); i++) {
            final int index = i;
            View view = getView(index);
            if (view == null) {
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }

            mListview.addView(view, index, layoutParams);
        }

    }

}
