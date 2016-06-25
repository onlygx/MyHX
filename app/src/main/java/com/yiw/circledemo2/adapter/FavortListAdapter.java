package com.yiw.circledemo2.adapter;

import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.spannable.CircleMovementMethod;
import com.yiw.circledemo2.spannable.SpannableClickable;
import com.yiw.circledemo2.widgets.FavortListView;
import com.yiw.circledemo2.bean.ZanListBean;

import java.util.List;

/**
 * @author yiw
 * @Description:
 * @date 16/1/2 18:51
 */
public class FavortListAdapter {

    private FavortListView mListView;
    private List<ZanListBean> datas;

    public List<ZanListBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ZanListBean> datas) {
        this.datas = datas;
    }

    @NonNull
    public void bindListView(FavortListView listview){
        if(listview == null){
            throw new IllegalArgumentException("FavortListView is null ....");
        }
        mListView = listview;
    }


    public int getCount() {
        if(datas != null && datas.size() > 0){
            return datas.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        if(datas != null && datas.size() > position){
            return datas.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public void notifyDataSetChanged(){
        if(mListView == null){
            throw new NullPointerException("listview is null, please bindListView first...");
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if(datas != null && datas.size() > 0){
            //添加点赞图标
            builder.append(setImageSpan());
            //builder.append("  ");
            ZanListBean item = null;
            for (int i=0; i<datas.size(); i++){
                item = datas.get(i);
                if(item != null){
                    String name =item.getUser().getNickName();
                    DD.d("赞遍历："+name);
                    SpannableString ss=setClickableSpan(name, i);
                    DD.d("赞后遍历："+ss.toString());
                    builder.append(ss);
                    if(i != datas.size()-1){
                        builder.append(", ");
                    }
                }
            }
        }
        mListView.setText(builder);
        mListView.setMovementMethod(new CircleMovementMethod(R.color.name_selector_color));
    }

    @NonNull
    private SpannableString setClickableSpan(String textStr, final int position) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(){
                                    @Override
                                    public void onClick(View widget) {
                                        if(mListView.getSpanClickListener()!=null){
                                            mListView.getSpanClickListener().onClick(position);
                                        }
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    private SpannableString setImageSpan(){
        String text = "  ";
        SpannableString imgSpanText = new SpannableString(text);
        imgSpanText.setSpan(new ImageSpan(MyApplication.getContext(), R.drawable.im_ic_dig_tips, DynamicDrawableSpan.ALIGN_BASELINE),
                0 , 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return imgSpanText;
    }
}
