package com.junzweather.app.service;

/**
 * @data 2016/7/11
 * @author 丁君之
 * 
 */
import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.junzweather.app.util.Util;

public class AutoChangeBackground extends Service {
	// 每分钟
	public static final int interval = 1000 * 60;
	public static final int now = 0;

	// 背景图片标志
	public static final int DAY_BGP = 0;
	public static final int NIGHT_BGP = 1;
	int state;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				if (hour >= 19 && state != NIGHT_BGP) {
					state = NIGHT_BGP;
					Message message = new Message();
					message.what = Util.AUTOUPDATE_BGP;
					message.arg1 = state;
					Util.handler.sendMessage(message);
				} else if (hour <= 19 && state != DAY_BGP) {
					state = DAY_BGP;
					Message message = new Message();
					message.what = Util.AUTOUPDATE_BGP;
					message.arg1 = state;
					Util.handler.sendMessage(message);
				}
				Util.handler.postDelayed(this, interval);
			}
		};
		Util.handler.postDelayed(runnable, now);

		return super.onStartCommand(intent, flags, startId);
	}

}
