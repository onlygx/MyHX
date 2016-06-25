package com.yiw.circledemo2.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.yiw.circledemo2.adapter.ChildAdapter;
import com.yiw.circledemo2.adapter.CommentAdapter;

/**
 * Created by yiwei on 16/3/2.
 */
public class ChildCommentListView extends LinearLayout{

    private ChildCommentListViewOnItemClickListener mOnItemClickListener;
    private ChildCommentListViewOnItemLongClickListener mOnItemLongClickListener;

    public ChildCommentListView(Context context) {
        super(context);
    }

    public ChildCommentListView(Context context, AttributeSet attrs){
        super(context, attrs);

    }

    public ChildCommentListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }


    public void setAdapter(ChildAdapter adapter){
        adapter.bindListView(this);
    }

    public void setOnItemClick(ChildCommentListViewOnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClick(ChildCommentListViewOnItemLongClickListener listener){
        mOnItemLongClickListener = listener;
    }

    public ChildCommentListViewOnItemClickListener getOnItemClickListener(){
        return mOnItemClickListener;
    }

    public ChildCommentListViewOnItemLongClickListener getOnItemLongClickListener(){
        return mOnItemLongClickListener;
    }


    public static interface ChildCommentListViewOnItemClickListener{
        public void onItemClick(int position);
    }

    public static interface ChildCommentListViewOnItemLongClickListener{
        public void onItemLongClick(int position);
    }
}
