package com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.MyDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.BoilingDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.CollisionLoadingRenderer;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.LoadingDrawable;
import com.xingkesi.foodapp.R;

import butterknife.ButterKnife;

/**
 * Created by huaping on 2016/5/18.
 */
public class JLBaseFragment extends Fragment {
    BoilingDialog.Builder builder;
    BoilingDialog dialog;
    InputMethodManager manager;

    MyDialog md;
    LoadingDrawable mBalloonDrawable;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(setViewLayout(), container, false);
        ButterKnife.bind(this, v);
        builder= new BoilingDialog.Builder(getActivity());
        dialog = builder.build();
        manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        testDialog();
        return v;
    }

    public 	void testDialog() {
        md = new MyDialog(getActivity(), R.style.dialog);
        md.setContentView(R.layout.jlloadinglayout);
        ImageView iv_image = (ImageView) md.getWindow().findViewById(R.id.iv_image);//图片
        mBalloonDrawable=new LoadingDrawable(new CollisionLoadingRenderer(getActivity()));
        iv_image.setImageDrawable(mBalloonDrawable);
        md.setCanceledOnTouchOutside(false);

    }
    protected void showWait(){
        if(md==null){
            md = new MyDialog(getActivity(), R.style.dialog);
        }
        md.show();
        if(mBalloonDrawable!=null)
        mBalloonDrawable.start();
    }

    protected void closeWait(){
        if(md==null){
            md = new MyDialog(getActivity(), R.style.dialog);
        }
        mBalloonDrawable.stop();
        md.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(getActivity());
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    public int setViewLayout() {
        return 0;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitObject();
        SetData();
    }


    public void InitObject(){
    }
    public void SetData(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
