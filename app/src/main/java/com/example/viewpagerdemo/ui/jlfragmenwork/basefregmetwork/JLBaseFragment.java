package com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoadingDialog;
import com.xingkesi.foodapp.R;

import butterknife.ButterKnife;

/**
 * Created by huaping on 2016/5/18.
 */
public class JLBaseFragment extends Fragment {
    InputMethodManager manager;
    LoadingDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(setViewLayout(), container, false);
        ButterKnife.bind(this, v);
        manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        testDialog();
        return v;
    }

    public 	void testDialog() {

    }
    protected void showWait(){
        if(dialog==null){
            dialog  = new LoadingDialog(getActivity(), R.style.LoadingDialog_style);
        }
        dialog.show();
    }

    protected void closeWait(){
        if(dialog!=null)
            dialog.dismiss();
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
