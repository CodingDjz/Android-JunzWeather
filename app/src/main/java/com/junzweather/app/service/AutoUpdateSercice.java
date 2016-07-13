package com.junzweather.app.service;

/**
 * @data 2016/7/6
 * @author 丁君之
 * 后台自动更新天气信息服务！
 * 和MainActivity的方法互相调用达到一致运行效果！
 */
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.junzweather.app.util.Util;

public class AutoUpdateSercice extends Service {
	public static final int UPDATE_INTERVAL = 1000 * 60 * 20;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Message message = new Message();
				message.what = Util.AUTOUPDATE_SERVER;
				Util.handler.sendMessage(message);
			}

		}, UPDATE_INTERVAL);
		// 有四个返回值！
		return START_REDELIVER_INTENT;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
