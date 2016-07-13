package com.junzweather.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.junzweather.app.R;
import com.junzweather.app.service.AutoChangeBackground;
import com.junzweather.app.service.AutoUpdateSercice;

/**
 * @data 2016/7/5
 * @author ����֮
 * 
 */
public class SplashActivity extends BaseActivity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		// �ȴ�����ͼ�����Ż�Ч��������
		Intent autoupdateIntent = new Intent(this, AutoUpdateSercice.class);
		// ������ʱˢ�·���
		startService(autoupdateIntent);
		final Intent mainIntent = new Intent(SplashActivity.this,
				MainActivity.class);

		// n�����������������
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				startActivity(mainIntent);
				finish();
			}

		}, SPLASH_DISPLAY_LENGHT);

	}

	// @Override
	// public void onBackPressed() {
	// // super.onBackPressed();
	// Intent intent = new Intent(Intent.ACTION_MAIN);
	// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// intent.addCategory(Intent.CATEGORY_HOME);
	// startActivity(intent);
	// }
}
