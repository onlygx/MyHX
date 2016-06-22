package com.example.viewpagerdemo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.ABooksZActivity;
import com.example.viewpagerdemo.ui.activity.ShenQFrendMainActivity;
import com.example.viewpagerdemo.ui.adapter.CityAdapter;
import com.example.viewpagerdemo.ui.bean.AddBookBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityData;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityItem;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactListViewImpl;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 通讯录
 */
public class AddressBookFragment extends JLBaseFragment implements TextWatcher, CityAdapter.BookCall {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.iv_back)
    ImageView iv_back;


    @Bind(R.id._add_log)
    Button _add_log;
    @Bind(R.id.noLoa)
    LinearLayout noLoa;

    private ContactListViewImpl listview;

    private EditText searchBox;
    private String searchString;

    private Object searchLock = new Object();
    boolean inSearchMode = false;

    private final static String TAG = "MainActivity2";
    List<AddBookBean> bookList;
    List<ContactItemInterface> contactList;
    List<ContactItemInterface> filterList;
    private SearchListTask curSearchTask = null;


    private Context mContext;
    CityAdapter adapter;

    @Override
    public int setViewLayout() {
        return R.layout.citylist;
    }


    @Override
    public void onResume() {
        super.onResume();
        DD.d("好友 onResume");
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getId() != 0) {
            //数据
            getFrd();
           // noLoa.setVisibility(View.GONE);
        } else {
          //  noLoa.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见时加载数据相当于Fragment的onResume
            if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
                getFrd();
            }
        } else {
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        DD.d("好友 onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DD.d("好友 onDestroy");
    }

    @Override
    public void InitObject() {
        bookList = new ArrayList<>();
        iv_back.setImageResource(R.drawable.tianjia);
        tv_title.setText("通讯录");
        iv_right_image.setVisibility(View.VISIBLE);
        filterList = new ArrayList<>();
        mContext = getActivity();
        listview = (ContactListViewImpl) getActivity().findViewById(R.id.listview);
        searchBox = (EditText) getActivity().findViewById(R.id.input_search_query);
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getId() != 0) {
            //数据
            getFrd();
           // noLoa.setVisibility(View.GONE);
        } else {
           // noLoa.setVisibility(View.INVISIBLE);
        }
        listview.setFastScrollEnabled(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int pos, long idse) {

                AddBookBean abb = bookList.get(pos);
                List<ContactItemInterface> searchList = inSearchMode ? filterList : contactList;
                long ICtyF = searchList.get(pos).getFrendID();
                int state = abb.getFriendStatus();
                long id = abb.getId();
                // long friendUserId = abb.getFriendId();
                DD.v("邀请好友：" + ICtyF + "====" + id + "===");

                if (state == 1) {//1是好友  2邀请中  0可邀请
                    //是好友---开始聊天

                    String name = searchList.get(pos).getDisplayInfo();
                    String ids = searchList.get(pos).getID();
                    String type = searchList.get(pos).getTypes();
                    String thinksId = searchList.get(pos).getThinkeSId();
                    Toast.makeText(getActivity(), "选择：" + name + "=" + ids + "=" + type + "==thinksId:" + thinksId, Toast.LENGTH_SHORT).show();
                    //---------------------------------------------------------------------
                   /* final String target = thinksId; //消息接收者ID
                    final String appkey = "23386286"; //消息接收者appKey
                    Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                    startActivity(intent);*/
                } else {
                    //邀请好友
                    userFriendUserId(id + "", ICtyF + "", abb);
                }
            }
        });

        _add_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tag", "finsh");
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        searchBox.addTextChangedListener(this);


        //处理好友申请列表
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstan().getUser() != null &&
                        MyApplication.getInstan().getUser().getData().getId() != 0
                        ) {
                    startActivity(new Intent(getActivity(), ShenQFrendMainActivity.class));
                }
            }
        });
        //添加好友
        iv_right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstan().getUser() != null &&
                        MyApplication.getInstan().getUser().getData().getId() != 0) {
                    startActivity(new Intent(getActivity(), ABooksZActivity.class));
                }
            }
        });
    }

    public void getFrd() {

        showWait();
        AjaxParams pa = new AjaxParams();
        pa.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        String url = Contantor.UsersubmitAddr;
        DD.d("通讯录请求:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("通讯录s:" + s);
                closeWait();
                bookList = JSONArray.parseArray(s, AddBookBean.class);
                contactList = CityData.getSampleContactList(bookList);
                DD.d("contactList==="+contactList.size());
                adapter = new CityAdapter(getActivity(), R.layout.city_item, contactList, AddressBookFragment.this);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                closeWait();
            }
        });


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        searchString = searchBox.getText().toString().trim().toUpperCase();

        if (curSearchTask != null
                && curSearchTask.getStatus() != AsyncTask.Status.FINISHED) {
            try {
                curSearchTask.cancel(true);
            } catch (Exception e) {
                Log.i(TAG, "Fail to cancel running search task");
            }

        }
        curSearchTask = new SearchListTask();
        curSearchTask.execute(searchString);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void callbook(int pos, int state) {
    }

    /**
     * 使用riendUserId加好友
     *
     * @param friendUserId
     */
    void userFriendUserId(String id, String friendUserId, final AddBookBean abb) {
        showWait();
        AjaxParams ap = new AjaxParams();
        ap.put("id", id);
        ap.put("friendUserId", friendUserId);
        String url = Contantor.applyUser;
        DD.d("UserId+++好友：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("UserId+++好友s：" + s);
                try {
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                        abb.setFriendStatus(2);
                        adapter.notifyDataSetChanged();
                        TS.shortTime("申请成功等待对方确认");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                closeWait();

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                closeWait();
            }
        });
    }


    private class SearchListTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            filterList.clear();

            String keyword = params[0];

            inSearchMode = (keyword.length() > 0);

            if (inSearchMode) {
                // get all the items matching this
                for (ContactItemInterface item : contactList) {
                    CityItem contact = (CityItem) item;

                    boolean isPinyin = contact.getFullName().toUpperCase().indexOf(keyword) > -1;
                    boolean isChinese = contact.getNickName().indexOf(keyword) > -1;

                    if (isPinyin || isChinese) {
                        filterList.add(item);
                    }

                }

            }
            return null;
        }

        protected void onPostExecute(String result) {

            synchronized (searchLock) {

                if (inSearchMode) {

                    CityAdapter adapter = new CityAdapter(getActivity(), R.layout.city_item, filterList, AddressBookFragment.this);
                    adapter.setInSearchMode(true);
                    listview.setInSearchMode(true);
                    if (listview != null && adapter != null) {
                        listview.setAdapter(adapter);
                    }
                } else {
                    CityAdapter adapter = new CityAdapter(getActivity(), R.layout.city_item, contactList, AddressBookFragment.this);
                    adapter.setInSearchMode(false);
                    listview.setInSearchMode(false);
                    if (listview != null && adapter != null) {
                        listview.setAdapter(adapter);
                    }
                }
            }

        }
    }
}