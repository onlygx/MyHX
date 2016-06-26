package com.example.viewpagerdemo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.viewpagerdemo.ui.bean.Wallet_IncomeBean;
import com.example.viewpagerdemo.ui.adapter.WalletIncomeReclerViewAdpter;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;

/**
 * 钱包---收入
 * Created by Administrator on 2016/5/21.
 */
public class Wallet_IncomeFragment extends JLBaseFragment {

    @Bind(R.id.wallet_incomerv)
    RecyclerView wallet_incomerv;

    ArrayList<Wallet_IncomeBean> list;
    WalletIncomeReclerViewAdpter adpter;


    @Override
    public void InitObject() {
        list =new ArrayList<>();
    }

    @Override
    public int setViewLayout() {
        return R.layout.wallet_incomefragmentlayout;
    }

    @Override
    public void SetData() {

       /* list =new ArrayList<>();
        wallet_incomerv.setHasFixedSize(true);
        LinearLayoutManager manager =new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wallet_incomerv.setLayoutManager(manager);
        adpter =new WalletIncomeReclerViewAdpter(getActivity(),list);
        wallet_incomerv.setAdapter(adpter);
*/




    }


}
