package com.yiw.circledemo2.utils;

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
	
	public static ZanListBean createCurUserFavortItem() {
		ZanListBean item = new ZanListBean();
		item.setId("1");
		item.setUser(curUser);
		return item;
	}

	/*public static List<RecordListBean> createCommentItemList() {
		List<RecordListBean> items = new ArrayList<RecordListBean>();
		int size = getRandomNum(10);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				items.add(createComment());
			}
		}
		return items;
	}*/

	/*public static RecordListBean createComment() {
		RecordListBean item = new RecordListBean();
		item.setId(String.valueOf(commentId++));
		item.setContent("哈哈");
		UserBean user = getUser();
		item.setUser(user);
		if (getRandomNum(10) % 2 == 0) {
			while (true) {
				UserBean replyUser = getUser();
				if (!user.getId().equals(replyUser.getId()+"")) {
					item.setUser(replyUser);
					break;
				}
			}
		}
		return item;
	}*/
	
	/**
	 * 创建发布评论
	 * @return
	 */
	public static RecordListBean createPublicComment(String content){
		RecordListBean item = new RecordListBean();
		item.setId(String.valueOf(commentId++));
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