package com.example.viewpagerdemo.ui.sarchcity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.AddBookBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends Activity implements TextWatcher {
    private ContactListViewImpl listview;
    TextView tv_title;

    private EditText searchBox;
    private String searchString;

    private Object searchLock = new Object();
    boolean inSearchMode = false;

    private final static String TAG = "MainActivity2";
    List<com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface> contactList;
    List<com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface> filterList;
    private SearchListTask curSearchTask = null;
    //
    List<AddBookBean> bookList;
    com.example.viewpagerdemo.ui.jlfragmenwork.city.CityAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchcitylist);

        String city = getIntent().getStringExtra("city");
        Toast.makeText(CityListActivity.this, city, Toast.LENGTH_SHORT).show();

        filterList = new ArrayList<>();
        bookList = new ArrayList<>();
        listview = (ContactListViewImpl) this.findViewById(R.id.listview);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("当前城市-" + city);
        listview.setFastScrollEnabled(true);
        searchBox = (EditText) findViewById(R.id.input_search_query);
        searchBox.addTextChangedListener(this);
        getFrd();
    }

    @Override
    public void afterTextChanged(Editable s) {
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

    public void getFrd() {

        AjaxParams pa = new AjaxParams();
        pa.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        String url = Contantor.UsersubmitAddr;
        DD.d("TxL:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("TxLs:" + s);
                bookList = JSONArray.parseArray(s, AddBookBean.class);
                contactList = com.example.viewpagerdemo.ui.jlfragmenwork.city.CityData.getSampleContactList(bookList);
                DD.d("contactList===" + contactList.size());
                adapter = new com.example.viewpagerdemo.ui.jlfragmenwork.city.CityAdapter(CityListActivity.this,
                        R.layout.city_item, contactList);
                listview.setAdapter(adapter);
                // adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // do nothing
    }



    private class SearchListTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            filterList.clear();

            String keyword = params[0];

            inSearchMode = (keyword.length() > 0);

            if (inSearchMode) {
                // get all the items matching this
                for (com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface item : contactList) {
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

                    com.example.viewpagerdemo.ui.jlfragmenwork.city.CityAdapter adapter = new com.example.viewpagerdemo.ui.
                            jlfragmenwork.city.CityAdapter(CityListActivity.this,
                            R.layout.city_item, contactList);
                    adapter.setInSearchMode(true);
                    listview.setInSearchMode(true);
                    listview.setAdapter(adapter);
                } else {
                    com.example.viewpagerdemo.ui.jlfragmenwork.city.CityAdapter adapter = new com.example.viewpagerdemo
                            .ui.jlfragmenwork.city.CityAdapter(CityListActivity.this,
                            R.layout.city_item, contactList);
                    adapter.setInSearchMode(false);
                    listview.setInSearchMode(false);
                    listview.setAdapter(adapter);
                }
            }

        }
    }

}
