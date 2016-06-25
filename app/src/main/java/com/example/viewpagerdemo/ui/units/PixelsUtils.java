package com.example.viewpagerdemo.ui.units;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

/**
 * 元素转换工具类
 * @Create by dingwei on 2015/12/03
 */
public class PixelsUtils {
	public static int SCREEN_WIDTH_PIXELS;
	public static int SCREEN_HEIGHT_PIXELS;
	public static float SCREEN_DENSITY;
	public static int SCREEN_WIDTH_DP;
	public static int SCREEN_HEIGHT_DP;
	
	/**
	 * Convert Dp to Pixel
	 */
	public static int dpToPx(float dp, Resources resources){
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
		return (int) px;
	}
	public static void init(Context context) {
		if (context == null) {
			return;
		}
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		SCREEN_WIDTH_PIXELS = dm.widthPixels;
		SCREEN_HEIGHT_PIXELS = dm.heightPixels;
		SCREEN_DENSITY = dm.density;
		SCREEN_WIDTH_DP = (int) (SCREEN_WIDTH_PIXELS / dm.density);
		SCREEN_HEIGHT_DP = (int) (SCREEN_HEIGHT_PIXELS / dm.density);
	}

	public static int dp2px(float dp) {
		final float scale = SCREEN_DENSITY;
		return (int) (dp * scale + 0.5f);
	}

	public static int designedDP2px(float designedDp) {
		if (SCREEN_WIDTH_DP != 320) {
			designedDp = designedDp * SCREEN_WIDTH_DP / 320f;
		}
		return dp2px(designedDp);
	}

	public static void setPadding(final View view, float left, float top, float right, float bottom) {
		view.setPadding(designedDP2px(left), dp2px(top), designedDP2px(right), dp2px(bottom));
	}

	public static int getWidth(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;
		return width;
	}
	public static int getHeight(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
		int height = metric.heightPixels;
		return height;
	}
	public static int getScale(Context context,int s) {
		final float scale = ((Activity) context).getResources().getDisplayMetrics().density;
		int sa = (int)(s * scale + 0.5f);
		return sa;
	}

}
