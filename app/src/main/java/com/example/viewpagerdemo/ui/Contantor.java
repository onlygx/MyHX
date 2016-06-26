package com.example.viewpagerdemo.ui;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Contantor {

//Contantor.Imagepost
    public static final int REQUEST_CODE_HAS_NEW = 1111;
    public static final int REQUEST_CODE_HAS_DEL = 1112;
    public static final int REQUEST_CODE_HAS_ITEM_CHAGE = 1113;
    public static final int REQUEST_IMAGE = 110;
    public static final int RESULT_PHOTO_REQUEST_CODE = 111;
    public static final int RESULT_SELECT_TIME = 112;

    public static final String GETUPLOADINFO =  "oss/sign";


    public static  String Imagepost="http://120.27.120.27:8080";
//    public static  String Imagepost="http://119.188.182.131:8080";

    public static  String post=Imagepost+"/app";
    public static  String logdin=post+"/user/login";
    public static  String code=post+"/user/sendCode";
    public static  String code2=post+"/user/sendCodePwd";
    public static  String UpdataPWD=post+"/user/changePwd";//更改密码
    public static  String register=post+"/user/register";
    public static  String main_eat=post+"/goods/listByRegion";//主页商品列表
    public static  String main_Banner=post+"/goods/indexBanner";//主页Banner
    public static  String main_ShppingSave=post+"/collection/save";//商品收藏
    public static  String shoppingInfo=post+"/goods/findById";
    public static  String shoppingComments=post+"/goodsComment/save";//保持评论
    public static  String shopInfo=post+"/shop/findById";//店铺详情
    public static  String shopInfoPL=post+"/goodsComment/listByGoodsId";//获取评论
    public static  String IMAGEURLHEADER=post+"/user/changeHead";//头像
    public static  String FindDDList=post+"/orderFrom/listByUserId";//我---订单列表
    public static  String FindInfo=post+"/user/updateInfo";//我---资料
    public static  String FindInfoAddr=post+"/receiptAddress/listByUserId";//我---收货地址
    public static  String FindInfoADDAddr=post+"/receiptAddress/save";//我---添加收货地址
    public static  String FindInfoADelAddr=post+"/receiptAddress/delete";//我---删除收货地址
    public static  String listByUserId=post+"/message/listByUserId";//朋友圈---与我相关
    public static  String setAllRead=post+"/message/setAllRead";//朋友圈---与我相关
    public static  String listByUnRead=post+"/message/listByUnRead";//朋友圈---数量
    public static  String FindInfoAUpAddr=post+"/receiptAddress/update";//我---删除收货地址
    public static  String submitAddr=post+"/orderFrom/submit";//下单
    public static  String UsersubmitAddr=post+"/friend/listByUserId";//同学录
    public static  String applyListByUserId=post+"/friend/applyListByUserId";//好友申请列表
    public static  String applyFriend=post+"/friend/applyFriend";//处理好友申请列表
    public static  String UpdaeFriend=post+"/friend/uploadFriend";//上传好友

    public static  String ByThinksId=post+"/friend/applyUserByThinksId";//ThinkID+好友
    public static  String applyUser=post+"/friend/applyUser";//UserID+好友
    public static  String qianbao=post+"/user/qianbao";//钱包信息
    public static  String zhuanzhang=post+"/user/zhuanzhang";//钱包--转
    public static  String ByUserId=post+"/collection/listByUserId";//收藏列表
    public static  String delete=post+"/collection/delete";//收藏列表删除
    public static  String pay=post+"/orderFrom/pay";//支付
    public static  String Ralesepay=post+"/demand/save";//需求发布
    public static  String MyRalesepay=post+"/demand/listBySend";//我发布的需求
    public static  String DelMyRalesepay=post+"/demand/deleteById";//删除需求
    public static  String listByRegion=post+"/demand/listByRegion";//首页需求
    public static  String OrderInfo=post+"/orderFrom/findById";//订单详情
    public static  String OrderInfocancel=post+"/orderFrom/cancel";//订单取消
    public static  String Orderfinish=post+"/orderFrom/finish";//确认收货
    public static  String goodsComment=post+"/goodsComment/save";//评价
    public static  String comment=post+"/orderFrom/comment";//评价完成
    public static  String commentInfo=post+"/demand/findById";//需求详情
    public static  String commentreceive=post+"/demand/receive";//需求承接
    public static  String Cjcommentreceive=post+"/demand/listByReceive";//我的承接
    public static  String CjcomlistByRegion=post+"/talk/listByRegion";//朋友圈
    public static  String aboe=post+"/opinion/submit";//aboe
    public static  String delaboe=post+"/talk/deleteTalk";//a删除说说
    public static  String record=post+"/record/submit";//a赞
    public static  String listTop=post+"/type/listTop";//获取首页分类
}
