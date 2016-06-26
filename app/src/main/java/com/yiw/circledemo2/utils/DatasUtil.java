package com.yiw.circledemo2.utils;

import com.example.viewpagerdemo.ui.bean.ReplyContentsBean;
import com.example.viewpagerdemo.ui.bean.UserBeanL;
import com.yiw.circledemo2.bean.ListBean;
import com.yiw.circledemo2.bean.RecordListBean;
import com.yiw.circledemo2.bean.ToolsHost;
import com.yiw.circledemo2.bean.UserBean;
import com.yiw.circledemo2.bean.ZanListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
* @ClassName: DatasUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:16:21 
*
 */
public class DatasUtil {
	static List<ListBean> circleDatas;
	public static String userPY= ToolsHost.HEDEUT+"/app/talk/listByRegion";
	public static String userMyPY= ToolsHost.HEDEUT+"/app/talk/listByRegion";
	static String userIdURL=ToolsHost.HEDEUT+"/app/talk/listByUserId";
	public static String userL=ToolsHost.HEDEUT+"/app/user/login";



	public static List<UserBean> users = new ArrayList<>();
	private static int commentId = 0;
	public static  UserBean curUser ;

	public static UserBean getUser1() {
		return users.get(getRandomNum(users.size()));
	}

	public static int getRandomNum(int max) {
		Random random = new Random();
		int result = random.nextInt(max);
		return result;
	}

	/*public static List<ZanListBean> createFavortItemList() {
		int size = getRandomNum(users.size());
		List<ZanListBean> items = new ArrayList<ZanListBean>();
		List<String> history = new ArrayList<String>();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				ZanListBean newItem = createFavortItem();
				String userid = newItem.getUser().getId();
				if (!history.contains(userid)) {
					items.add(newItem);
					history.add(userid);
				} else {
					i--;
				}
			}
		}
		return items;
	}*/

	/*public static ZanListBean createFavortItem() {
		ZanListBean item = new ZanListBean();
		//item.setId(String.valueOf(favortId++));
		item.setUser(getUser());
		return item;
	}*/
	
	public static ZanListBean createCurUserFavortItem(String id) {
		ZanListBean item = new ZanListBean();
		item.setId(id);
		//item.setUser(curUser);
		return item;
	}


	
	/**
	 * 创建发布评论
	 * @return
	 */
	public static RecordListBean createPublicComment(String id,String content){
		RecordListBean item = new RecordListBean();
		item.setId(id);
		item.setContent(content);
		item.setUser(curUser);
		return item;
	}

	/**
	 * 创建回复评论
	 * @return
	 */
	public static RecordListBean createReplyComment(UserBean replyUser, String content){
		RecordListBean item = new RecordListBean();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		item.setUser(replyUser);
		return item;
	}



}
