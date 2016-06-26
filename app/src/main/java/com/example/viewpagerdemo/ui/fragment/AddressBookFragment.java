package com.example.viewpagerdemo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.ABooksZActivity;
import com.example.viewpagerdemo.ui.activity.ShenQFrendMainActivity;
import com.example.viewpagerdemo.ui.bean.AddBookBean;
import com.example.viewpagerdemo.ui.bean.ShenQBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityData;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityItem;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.sarchcity.ContactListViewImpl;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 通讯录
 */
public class AddressBookFragment extends JLBaseFragment implements TextWatcher {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_left_text_num)
    TextView tv_left_text_num;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.iv_back)
    ImageView iv_back;

    @Bind(R.id.listview)
    ContactListViewImpl listview;
    @Bind(R.id.input_search_query)
    EditText searchBox;
    @Bind(R.id.noLoa)
    LinearLayout noLoa;
    @Bind(R.id._add_log)
    Button _add_log;
    private String searchString;
    private Object searchLock = new Object();
    boolean inSearchMode = false;
    List<AddBookBean> bookList;
    List<ContactItemInterface> contactList;
    List<ContactItemInterface> filterList;
    private SearchListTask curSearchTask = null;

    List<ShenQBean> list;


    private Context mContext;
    CityAdapter adapter;
    YWIMKit mIMKit;

    @Override
    public int setViewLayout() {
        return R.layout.citylist;

    }


    @Override
    public void onResume() {
        super.onResume();
        //登录状态
        if (MyApplication.getInstan().getUser() != null &&
                MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            noLoa.setVisibility(View.GONE);
            getFrd();
            getNewNum();
        } else {
            noLoa.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见时加载数据相当于Fragment的onResume
            if (MyApplication.getInstan().getUser() != null &&
                    MyApplication.getInstan().getUser().getData().getThinksId() != null) {
                getFrd();
                getNewNum();
            }
        }
    }


    void getNewNum() {
        AjaxParams pa = new AjaxParams();
        pa.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        String url = Contantor.applyListByUserId;
        // DD.d("通讯录最新数量:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                //  DD.d("通讯录最新数量s:" + s);
                if (s != null && !s.equals("")) {
                    list = JSONArray.parseArray(s, ShenQBean.class);
                    if (list.size() > 0) {
                        tv_left_text_num.setVisibility(View.VISIBLE);
                        tv_left_text_num.setText(list.size() + "");
                    }
                } else {
                    tv_left_text_num.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    @Override
    public void InitObject() {
        bookList = new ArrayList<>();
        iv_back.setImageResource(R.drawable.addbookleft);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("通讯录");

        //登录状态
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            tv_title.setTextColor(getResources().getColor(R.color.waiter));
            iv_back.setVisibility(View.VISIBLE);
            noLoa.setVisibility(View.GONE);
            noLoa.setVisibility(View.GONE);

        }
        iv_right_image.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(R.drawable.addbookright);
        filterList = new ArrayList<>();
        mContext = getActivity();
        list = new ArrayList<>();
        listview.setFastScrollEnabled(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int pos, long idse) {

                AddBookBean abb = bookList.get(pos);
                List<ContactItemInterface> searchList = inSearchMode ? filterList : contactList;
                long ICtyF = searchList.get(pos).getFrendID();
                int state = abb.getFriendStatus();
                long id = abb.getId();

                DD.e(searchList.get(pos).getID()+"点击了："+searchList.get(pos).getDisplayInfo());

                if (state == 1) {//1是好友  2邀请中  0可邀请
                    //是好友---开始聊天
                    String name = searchList.get(pos).getDisplayInfo();
                    String ids = searchList.get(pos).getID();
                    String type = searchList.get(pos).getTypes();
                    String thinksId = searchList.get(pos).getThinkeSId();
                    Toast.makeText(getActivity(), "选择：" + name + "=" + ids + "=" + type +
                            "==thinksId:" + thinksId, Toast.LENGTH_SHORT).show();
                    //---------------------------------------------------------------------
                    final String target = thinksId; //消息接收者ID
                    //final String appkey = "123456"; //消息接收者appKey
                    YWIMKit mIMKit = YWAPI.getIMKitInstance(MyApplication.getInstan().getUserName(), MyApplication.APP_KEY);
                    Intent intent = mIMKit.getChattingActivityIntent(target, MyApplication.APP_KEY);
                    startActivity(intent);
                } else {
                    //邀请好友
                    userFriendUserId(id + "", ICtyF + "", abb);
                }
            }
        });


        searchBox.addTextChangedListener(this);
        //登录按钮
        _add_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("tag", "finsh");
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //处理好友申请列表
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstan().getUser() != null &&
                        MyApplication.getInstan().getUser().getData().getId() != 0
                        ) {
                    Intent it = new Intent(getActivity(), ShenQFrendMainActivity.class);
                    it.putExtra("list", (Serializable) list);
                    startActivityForResult(it, 101);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 101://好友表数量

                Bundle bd = data.getExtras();
                String num = bd.getString("num");
                if (Integer.parseInt(num) > 0) {
                    tv_left_text_num.setText(num);
                } else {
                    tv_left_text_num.setVisibility(View.GONE);
                }
                break;

        }
    }

    public void getFrd() {

        //  showWait();
        AjaxParams pa = new AjaxParams();
        pa.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        String url = Contantor.UsersubmitAddr;
          DD.d("通讯录:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                   DD.d("通讯录s:" + s);
                bookList.clear();
                bookList = JSONArray.parseArray(s, AddBookBean.class);
                contactList = CityData.getSampleContactList(bookList);
                adapter = new CityAdapter(getActivity(), R.layout.city_item, contactList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                // closeWait();
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

   /* @Override
    public void callbook(int pos, int states) {

        AddBookBean abb = bookList.get(pos);
        List<ContactItemInterface> searchList = inSearchMode ? filterList : contactList;
        long ICtyF = searchList.get(pos).getFrendID();
        int state = abb.getFriendStatus();
        long id = abb.getId();
        // long friendUserId = abb.getFriendId();
        // DD.v("邀：" + ICtyF + "====" + id + "===");

        if (state == 1) {//1是好友  2邀请中  0可邀请
            //是好友---开始聊天
            String name = searchList.get(pos).getDisplayInfo();
            String ids = searchList.get(pos).getID();
            String type = searchList.get(pos).getTypes();
            String thinksId = searchList.get(pos).getThinkeSId();
            Toast.makeText(getActivity(), "选择：" + name + "=" + ids + "=" + type +
                    "==thinksId:" + thinksId, Toast.LENGTH_SHORT).show();
            //---------------------------------------------------------------------
            final String target = thinksId; //消息接收者ID
            final String appkey = "123456"; //消息接收者appKey

            Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
            startActivity(intent);
        } else {
            //邀请好友
            userFriendUserId(id + "", ICtyF + "", abb);
        }
    }*/

    /**
     * 使用riendUserId加好友
     * @param friendUserId
     */
    void userFriendUserId(String id, String friendUserId, final AddBookBean abb) {
        showWait();
        AjaxParams ap = new AjaxParams();
        ap.put("id", id);
        ap.put("friendUserId", friendUserId);
        String url = Contantor.applyUser;
        //  DD.d("UserId+++好友：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                //     DD.d("UserId+++好友s：" + s);
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

                    CityAdapter adapter = new CityAdapter(getActivity(), R.layout.city_item, filterList );
                    adapter.setInSearchMode(true);
                    listview.setInSearchMode(true);
                    if (listview != null && adapter != null) {
                        listview.setAdapter(adapter);
                    }
                } else {
                    CityAdapter adapter = new CityAdapter(getActivity(), R.layout.city_item, contactList );
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
