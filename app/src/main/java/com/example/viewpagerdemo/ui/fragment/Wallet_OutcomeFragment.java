package com.example.viewpagerdemo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.viewpagerdemo.ui.bean.Wallet_IncomeBean;
import com.example.viewpagerdemo.ui.adapter.WalletOutcomeReclerViewAdpter;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/21.
 * 钱包支出
 */
public class Wallet_OutcomeFragment extends JLBaseFragment {

    @Bind(R.id.wallet_incomerv)
    RecyclerView wallet_incomerv;

    ArrayList<Wallet_IncomeBean> list;
    WalletOutcomeReclerViewAdpter adpter;





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
        list =TestData();
        wallet_incomerv.setHasFixedSize(true);
        LinearLayoutManager manager =new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wallet_incomerv.setLayoutManager(manager);
        adpter =new WalletOutcomeReclerViewAdpter(getActivity(),list);
        wallet_incomerv.setAdapter(adpter);
    }

    //----------测试数据-----------
    ArrayList<Wallet_IncomeBean>  TestData(){
        ArrayList<Wallet_IncomeBean> list =new ArrayList<>();
      /*  for(int i=0;i<10;i++){
            Wallet_IncomeBean wb =new Wallet_IncomeBean();
            wb.setComeId(i+"");
            wb.setComeName("新疆苹果");
            Random tan =new Random();
            int ran =tan.nextInt(15);
            int money =ran+50+i;
            wb.setComeMoney(money+"");
            list.add(wb);
        }*/

        return list;


    }
}
