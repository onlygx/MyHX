package com.yiw.circledemo2.widgets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.ReleaseFrendActivity;
import com.yiw.circledemo2.adapter.CircleAdapter;
import com.yiw.circledemo2.adapter.MCircleAdapter;
import com.yiw.circledemo2.bean.ListBean;
import com.yiw.circledemo2.bean.MCommentConfig;
import com.yiw.circledemo2.bean.RecordListBean;
import com.yiw.circledemo2.bean.ZanListBean;
import com.yiw.circledemo2.mvp.presenter.MCirclePresenter;
import com.yiw.circledemo2.mvp.view.MICircleView;
import com.yiw.circledemo2.utils.CommonUtils;

import java.util.List;

/**
 * @author yiw
 * @ClassName: CircleFriendsActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 我的圈
 * @date 2015-12-28 下午4:21:18
 */
public class MyCircleFriendsActivity extends JLBaseActivity implements MICircleView {
    /*compile 'com.commit451:PhotoView:1.2.4'*/
    private MCircleAdapter mAdapter;
    private LinearLayout mEditTextBody;
    private EditText mEditText;
    private ImageView sendIv;

    private int mScreenHeight;
    private int mEditTextBodyHeight;
    private int mCurrentKeyboardH;
    private int mSelectListBeanH;
    private int mSelectCommentItemOffset;

    private MCirclePresenter mPresenter;
    private MCommentConfig mCommentConfig;
    private SuperRecyclerView recyclerView;
    private RelativeLayout bodyLayout;
    private LinearLayoutManager layoutManager;
    private MTitleBar titleBar;

    private final static int TYPE_PULLREFRESH = 1;
    private final static int TYPE_UPLOADREFRESH = 2;



    @Override
    public void initID() {
        super.initID();
        mPresenter = new MCirclePresenter();
        mPresenter.attachView(this);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyApplication.getInstan().getUser()!=null && MyApplication.getInstan().getUser().getData().getId()!=-1){
            mPresenter.MyloadData(TYPE_PULLREFRESH);
        }
    }

    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_mainmf;
    }

    @SuppressLint({"ClickableViewAccessibility", "InlinedApi"})
    private void initView() {
        initTitle();
        recyclerView = (SuperRecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(2, true));
        recyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        recyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mEditTextBody.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.MyloadData(TYPE_PULLREFRESH);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Glide.with(MyCircleFriendsActivity.this).resumeRequests();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MyCircleFriendsActivity.this).pauseRequests();
                }

            }
        });



        mAdapter = new MCircleAdapter(this);
        mAdapter.setCirclePresenter(mPresenter);
        recyclerView.setAdapter(mAdapter);

        mEditTextBody = (LinearLayout) findViewById(R.id.editTextBodyLl);
        mEditText = (EditText) findViewById(R.id.circleEt);
        sendIv = (ImageView) findViewById(R.id.sendIv);
        sendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter != null) {
                    //发
                    String content = mEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(MyCircleFriendsActivity.this, "内容不能为空...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mPresenter.addComment(content, mCommentConfig);
                    mEditText.setText("");
                }
                updateEditTextBodyVisible(View.GONE, null);
            }
        });

        setViewTreeObserver();
    }

    private void initTitle() {



        titleBar = (MTitleBar) findViewById(R.id.main_title_bar);
        titleBar.setTitle("我的朋友圈");
        titleBar.setTitleColor(getResources().getColor(R.color.white));
        titleBar.setBackgroundColor(getResources().getColor(R.color.title_bg));

        TextView textView = (TextView) titleBar.addAction(new MTitleBar.TextAction("发布说说") {
            @Override
            public void performAction(View view) {
                //Toast.makeText(CircleFriendsActivity.this, "敬请期待...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyCircleFriendsActivity.this,ReleaseFrendActivity.class));
            }
        });

        titleBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView.setTextColor(getResources().getColor(R.color.white));
    }


    private void setViewTreeObserver() {
        bodyLayout = (RelativeLayout) findViewById(R.id.bodyLayout);
        final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                bodyLayout.getWindowVisibleDisplayFrame(r);
                int statusBarH = getStatusBarHeight();//状态栏高度
                int screenH = bodyLayout.getRootView().getHeight();
                if (r.top != statusBarH) {
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                //	Log.d(TAG, "screenH＝ "+ screenH +" &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if (keyboardH == mCurrentKeyboardH) {//有变化时才处理，否则会陷入死循环
                    return;
                }

                mCurrentKeyboardH = keyboardH;
                mScreenHeight = screenH;//应用屏幕的高度
                mEditTextBodyHeight = mEditTextBody.getHeight();

                //偏移listview
                if (layoutManager != null && mCommentConfig != null) {
                    layoutManager.scrollToPositionWithOffset(mCommentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE,
                            getListviewOffset(mCommentConfig));
                }
            }
        });
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mEditTextBody != null && mEditTextBody.getVisibility() == View.VISIBLE) {
                mEditTextBody.setVisibility(View.GONE);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void update2DeleteCircle(String circleId) {
        List<ListBean> ListBeans = mAdapter.getDatas();
        for (int i = 0; i < ListBeans.size(); i++) {
            if (circleId.equals(ListBeans.get(i).getId())) {
                ListBeans.remove(i);
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public void update2AddFavorite(int circlePosition, ZanListBean addItem) {
        if (addItem != null) {
            ListBean item = (ListBean) mAdapter.getDatas().get(circlePosition);
            item.getZanList().add(addItem);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void update2DeleteFavort(int circlePosition, String favortId) {
        ListBean item = (ListBean) mAdapter.getDatas().get(circlePosition);
        List<ZanListBean> items = item.getZanList();
        for (int i = 0; i < items.size(); i++) {
            if (favortId.equals(items.get(i).getId())) {
                items.remove(i);
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public void update2AddComment(int circlePosition, RecordListBean addItem) {
        if (addItem != null) {
            ListBean item = (ListBean) mAdapter.getDatas().get(circlePosition);
            item.getRecordList().add(addItem);
            mAdapter.notifyDataSetChanged();
        }
        //清空评论文本
        mEditText.setText("");
    }

    @Override
    public void update2DeleteComment(int circlePosition, String commentId) {
        ListBean item = (ListBean) mAdapter.getDatas().get(circlePosition);
        List<RecordListBean> items = item.getRecordList();
        for (int i = 0; i < items.size(); i++) {
            if (commentId.equals(items.get(i).getId())) {
                items.remove(i);
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * 弹出键盘
     *
     * @param visibility
     * @param commentConfig
     */
    @Override
    public void updateEditTextBodyVisible(int visibility, MCommentConfig commentConfig) {
        mCommentConfig = commentConfig;
        mEditTextBody.setVisibility(visibility);

        measureListBeanHighAndCommentItemOffset(commentConfig);

        if (View.VISIBLE == visibility) {
            mEditText.requestFocus();
            //弹出键盘
            CommonUtils.showSoftInput(mEditText.getContext(), mEditText);

        } else if (View.GONE == visibility) {
            //隐藏键盘
            CommonUtils.hideSoftInput(mEditText.getContext(), mEditText);
        }
    }

    @Override
    public void update2loadData(int loadType, List<ListBean> datas) {
        Log.d("LD", "加载数据：" + datas.size() + "loadType====:" + loadType);

        if (loadType == TYPE_PULLREFRESH) {
            mAdapter.setDatas(datas);
        } else if (loadType == TYPE_UPLOADREFRESH) {
            mAdapter.getDatas().addAll(datas);
        }
        mAdapter.notifyDataSetChanged();

    }


    /**
     * 测量偏移量
     *
     * @param commentConfig
     * @return
     */
    private int getListviewOffset(MCommentConfig commentConfig) {
        if (commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        //int listviewOffset = mScreenHeight - mSelectListBeanH - mCurrentKeyboardH - mEditTextBodyHeight;
        int listviewOffset = mScreenHeight - mSelectListBeanH - mCurrentKeyboardH - mEditTextBodyHeight - titleBar.getHeight();
        if (commentConfig.commentType == MCommentConfig.Type.REPLY) {
            //回复评论的情况
            listviewOffset = listviewOffset + mSelectCommentItemOffset;
        }
        // Log.i(TAG, "listviewOffset : " + listviewOffset);
        return listviewOffset;
    }

    private void measureListBeanHighAndCommentItemOffset(MCommentConfig commentConfig) {
        if (commentConfig == null)
            return;

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        //只能返回当前可见区域（列表可滚动）的子项
        View selectListBean = layoutManager.getChildAt(commentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE - firstPosition);

        if (selectListBean != null) {
            mSelectListBeanH = selectListBean.getHeight();
        }

        if (commentConfig.commentType == MCommentConfig.Type.REPLY) {
            //回复评论的情况
            CommentListView commentLv = (CommentListView) selectListBean.findViewById(R.id.commentList);
            if (commentLv != null) {
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if (selectCommentItem != null) {
                    //选择的commentItem距选择的ListBean底部的距离
                    mSelectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if (parentView != null) {
                            mSelectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                        }
                    } while (parentView != null && parentView != selectListBean);
                }
            }
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
