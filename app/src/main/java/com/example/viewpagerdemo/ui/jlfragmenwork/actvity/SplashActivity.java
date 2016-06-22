package com.example.viewpagerdemo.ui.jlfragmenwork.actvity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.activity.MainActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;

import com.xingkesi.foodapp.R;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * 开屏页
 *
 */
public class SplashActivity extends JLBaseActivity {
	private SharedPreferences preferences;
	private int start;

	private ImageView imageView;
	



	@Override
	public int setViewLayout() {
		return R.layout.work_activity_splash;
	}

	@Override
	public void initID() {
		imageView= (ImageView) findViewById(R.id.img);
	}


	@Override
	public void initObject() {
		super.initObject();
		preferences = this.getSharedPreferences("start",0);
		start = preferences.getInt("start", -1);
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(1500);
		imageView.startAnimation(animation);

		MyCount count =new MyCount(2000,1000);
		count.start();

		/*if(start<=0){//引导页
			startActivity(new Intent(SplashActivity.this, NavigationActivity.class));
			finish();
			//MyCount count =new MyCount(2000,1000);
			//count.start();
		}else if(start==1){//首页
			MyCount count =new MyCount(2000,1000);
			count.start();
		}*/
	}

	class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}

		@Override
		public void onFinish() {
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
			finish();
		}
	}


}
