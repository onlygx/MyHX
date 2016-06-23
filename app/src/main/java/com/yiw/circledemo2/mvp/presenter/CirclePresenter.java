package com.yiw.circledemo2.mvp.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.yiw.circledemo2.bean.CommentConfig;
import com.yiw.circledemo2.bean.FrendBean;
import com.yiw.circledemo2.bean.ListBean;
import com.yiw.circledemo2.bean.RecordListBean;
import com.yiw.circledemo2.bean.ZanListBean;
import com.yiw.circledemo2.mvp.modle.CircleModel;
import com.yiw.circledemo2.mvp.modle.IDataRequestListener;
import com.yiw.circledemo2.mvp.view.ICircleView;
import com.yiw.circledemo2.utils.DatasUtil;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author yiw
 * @ClassName: CirclePresenter
 * @Description: 通知model请求服务器和通知view更新
 * @date 2015-12-28 下午4:06:03
 */
public class CirclePresenter extends BasePresenter<ICircleView> {
    private CircleModel mCircleModel;
    List<ListBean> datas;
    int loadType;

    public CirclePresenter() {
        mCircleModel = new CircleModel();
    }

    public void loadData(final int loadType) {
        this.loadType = loadType;
        getPY();
    }

    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("LD", "datas:" + datas.size());
            getView().update2loadData(loadType, datas);
        }
    };

    public void getPY() {
        if(datas!=null && datas.size()>0){
            datas.clear();
        }
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("page", "1");
        ap.put("size", "10");
        Log.v("LD", "PYQ:" + DatasUtil.userPY + "?" + ap.toString());
        new FinalHttp().post(DatasUtil.userPY, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                FrendBean test = JSON.parseObject(s, FrendBean.class);
                DD.v("LD PYQs:" + s);
                if (test.getList().size() > 0) {
                    datas = test.getList();
                    for (int i = 0; i < datas.size(); i++) {
                        if (datas.get(i).getBannerList().size() > 0) {
                            datas.get(i).setType("2");
                        }
                    }
                    han.sendEmptyMessage(0);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }


    /**
     * @param circleId
     * @return void    返回类型
     * @throws
     * @Title: deleteCircle
     * @Description: 删除动态
     */
    public void deleteCircle(final String circleId) {
        mCircleModel.deleteCircle(new IDataRequestListener() {

            @Override
            public void loadSuccess(Object object) {
                getView().update2DeleteCircle(circleId);
                DelePL(circleId);
            }
        });
    }

    /**
     * @param circlePosition
     * @return void    返回类型
     * @throws
     * @Title: addFavort
     * @Description: 点赞
     */
    public void addFavort(final int circlePosition) {
        mCircleModel.addFavort(new IDataRequestListener() {

            @Override
            public void loadSuccess(Object object) {

                Log.d("LD", "消：" + circlePosition);
                String id = datas.get(circlePosition).getId();
                questZ(id, circlePosition);
            }
        });
    }

    //点
    void questZ(String id, final int circlePosition) {
        String url = Contantor.record;
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("infoId", id);
        ap.put("type", "0");
        DD.d("赞说说：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("赞说说s：" + s);
                if (Tools.getSourcess(s)) {
                    try {
                        JSONObject js = new JSONObject(s);
                        String id = js.getJSONObject("data").getString("id");
                        ZanListBean item = DatasUtil.createCurUserFavortItem(id);
                        getView().update2AddFavorite(circlePosition, item);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    //取消赞
    void cancelZ(ZanListBean bena) {
        String url = Contantor.Canrecord;
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId()+"");
//        ap.put("infoId", id);
        ap.put("type", "0");
        DD.d("Z：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("Zs：" + s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    void DelePL(String commentId) {
        //删除
        String url = Contantor.delaboe;
        AjaxParams ap = new AjaxParams();
        ap.put("id", commentId);
        DD.d("删除说说：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("删除说说s：" + s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    /**
     * @param @param circlePosition
     * @param @param favortId
     * @return void    返回类型
     * @throws
     * @Title: deleteFavort
     * @Description: 取消点赞
     */
    public void deleteFavort(final int circlePosition, final String favortId) {
        mCircleModel.deleteFavort(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                Log.d("LD", "取消：" + circlePosition + "===" + favortId);
                getView().update2DeleteFavort(circlePosition, favortId);
            }
        });
    }

    /**
     * @param content
     * @param config  CommentConfig
     * @return void    返回类型
     * @throws
     * @Title: addComment
     * @Description: 增加评论
     */
    public void addComment(final String content, final CommentConfig config) {
        if (config == null) {
            return;
        }
        mCircleModel.addComment(new IDataRequestListener() {

            @Override
            public void loadSuccess(Object object) {
                RecordListBean newItem = null;
                if (config.commentType == CommentConfig.Type.PUBLIC) {//创建

                    addPP(config,content);
                } else if (config.commentType == CommentConfig.Type.REPLY) {//回复
                    newItem = DatasUtil.createReplyComment(config.replyUser, content);
                }


                //getView().update2AddComment(config.circlePosition, newItem);   &type=1
            }

        });
    }

    public void addPP(final CommentConfig config, final String content){
        String url = Contantor.record;
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("infoId", MyApplication.getUserPYId());
        ap.put("type", "1");
        ap.put("content", content);
        DD.d("fbpl：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("fbpls：" + s);
                if (Tools.getSourcess(s)) {
                    try {
                        JSONObject js = new JSONObject(s);
                       // String id = js.getJSONObject("data").getString("id");
                        //RecordListBean  newItem = DatasUtil.createPublicComment(id,content);
                       // getView().update2AddComment(config.circlePosition, newItem);


                        getPY();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }





    /**
     * @param @param circlePosition
     * @param @param commentId
     * @return void    返回类型
     * @throws
     * @Title: deleteComment
     * @Description: 删除评论
     */
    public void deleteComment(final int circlePosition, final String commentId) {
        mCircleModel.deleteComment(new IDataRequestListener() {

            @Override
            public void loadSuccess(Object object) {
                DelePL(commentId);
                getView().update2DeleteComment(circlePosition, commentId);
            }

        });
    }


    /**
     * @param commentConfig
     */
    public void showEditTextBody(CommentConfig commentConfig) {
        Log.d("LD","-:"+commentConfig.toString());
        getView().updateEditTextBodyVisible(View.VISIBLE, commentConfig);
    }

}
