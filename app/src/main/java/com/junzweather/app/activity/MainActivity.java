package com.junzweather.app.activity;

/**
 * @data 2016/6/29
 * @author 丁君之
 * 
 */
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.junzweather.app.R;
import com.junzweather.app.model.Weather.Alarms;
import com.junzweather.app.model.Weather.Root;
import com.junzweather.app.model.Weather.Root.Weather;
import com.junzweather.app.service.AutoChangeBackground;
import com.junzweather.app.service.AutoUpdateSercice;
import com.junzweather.app.util.HttpCallbackListener;
import com.junzweather.app.util.HttpUtil;
import com.junzweather.app.util.Util;

public class MainActivity extends BaseActivity implements OnClickListener,
		BDLocationListener {
	private RelativeLayout relativeLayout;
	private TextView cityName;
	private TextView weatherCondition;
	private TextView nowTemp;
	private TextView aqiValue;
	private TextView aqiquality;

	private TextView TodayText;
	private TextView tomorrowText;
	private TextView ttomorrowText;
	private TextView todayTempScope;
	private TextView tomorrowTempScope;
	private TextView ttomorrowTempScope;
	private TextView alarmText;
	private ImageView searchButton;
	private ImageView refreshButton;

	Intent autoupdatebgpIntent;

	public static int TODAY_INDEX = 0;
	public static int TOMORROW_INDEX = 1;
	public static int TTOMORROW_INDEX = 2;

	String cityNameStr = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		setUI();
		initViewListener();
		inithandler();
	}

	@Override
	protected void onStop() {
		stopService(autoupdatebgpIntent);
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		stopService(autoupdatebgpIntent);
		super.onDestroy();
	}

	/**
	 * 获得控件实例
	 */
	private void initView() {
		relativeLayout = (RelativeLayout) findViewById(R.id.weather_info_layout);
		cityName = (TextView) findViewById(R.id.city_name);
		weatherCondition = (TextView) findViewById(R.id.weather_desp);
		nowTemp = (TextView) findViewById(R.id.now_temp);
		aqiValue = (TextView) findViewById(R.id.aqi_value);
		aqiquality = (TextView) findViewById(R.id.aqi_quality);
		TodayText = (TextView) findViewById(R.id.list_today_condition);
		tomorrowText = (TextView) findViewById(R.id.list_tomorrow_condition);
		ttomorrowText = (TextView) findViewById(R.id.list_ttomorrow_condition);
		todayTempScope = (TextView) findViewById(R.id.list_today_tempscope);
		tomorrowTempScope = (TextView) findViewById(R.id.list_tomorrow_tempscope);
		ttomorrowTempScope = (TextView) findViewById(R.id.list_ttomorrow_tempscope);
		alarmText = (TextView) findViewById(R.id.alarm);
		searchButton = (ImageView) findViewById(R.id.serch_button);
		refreshButton = (ImageView) findViewById(R.id.refresh_button);

	}

	/**
	 * 初始化各种监听
	 */
	private void initViewListener() {
		searchButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
	}

	/**
	 * 初始化handler，接受Message
	 */
	public void inithandler() {
		Util.handler = new Handler() {
			public void handleMessage(Message message) {
				switch (message.what) {
				case Util.AUTOUPDATE_SERVER:
					initLocation(getApplicationContext());
					Log.v("MainActivity", "hander AUTOUPDATE");
					Intent autoupdateIntent = new Intent(MainActivity.this,
							AutoUpdateSercice.class);
					startService(autoupdateIntent);
					break;
				case Util.NO_CITY_INFO:
					showErrorWeatherInfo();
					break;
				case Util.AUTOUPDATE_BGP:
					if (message.arg1 == AutoChangeBackground.DAY_BGP) {
						relativeLayout.setBackgroundResource(R.drawable.day);
					} else if (message.arg1 == AutoChangeBackground.NIGHT_BGP) {
						relativeLayout.setBackgroundResource(R.drawable.night);
					}
				default:
					break;
				}
			}
		};
	}

	/**
	 * 初始化定位信息
	 * 
	 * @param context
	 */
	private void initLocation(Context context) {

		LocationClient locationClient = new LocationClient(context);
		locationClient.registerLocationListener(this);
		locationConfig(locationClient);
		locationClient.start();
	}

	/**
	 * 初始化定位配置方法
	 * 
	 * @param locationClient
	 */
	private void locationConfig(LocationClient locationClient) {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		locationClient.setLocOption(option);
	}

	/**
	 * 设置显示信息
	 */
	private void setUI() {
		// 设置背景
		autoupdatebgpIntent = new Intent(this, AutoChangeBackground.class);
		startService(autoupdatebgpIntent);
		// 地区信息
		Intent intent = getIntent();
		cityNameStr = intent.getStringExtra("cityName");
		if (cityNameStr == null) {
			initLocation(getApplicationContext());
		} else {
			queryWeatherInfoBycityName(cityNameStr);
		}
	}

	/**
	 * 通过城市查询信息并更新UI
	 * 
	 * @param cityName
	 */

	public void queryWeatherInfoBycityName(String cityName) {
		String AddressUrl = "http://apis.baidu.com/heweather/pro/weather";
		String cityNameArg = "city=" + cityName.toLowerCase();
		// 在这里发送request，请求天气信息,结果返回到onFinish。
		HttpUtil.sendHttpCallBackRequest(AddressUrl, cityNameArg,
				HttpUtil.WEATHER_REQUEST, new HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						final Root root = Util.parseWeatherResponse(response);
						if ("ok".equals(root.getWeatherList().get(0)
								.getStatus())) {
							// 启动UI线程
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// 更新天气信息
									showWeatherInfo(root);
								}
							});
						} else {
							Message message = new Message();
							message.what = Util.NO_CITY_INFO;
							Util.handler.sendMessage(message);
							cityNameStr = getString(R.string.mars);
						}
					}

					@Override
					public void onError(Exception e) {
						setContentView(R.layout.activity_main_error);
						Toast.makeText(MainActivity.this,
								getString(R.string.something_happened),
								Toast.LENGTH_SHORT);
						return;
					}
				});
	}

	/**
	 * 显示天气信息到主界面
	 * 
	 * @param weather
	 */
	private void showWeatherInfo(Root root) {
		Root.Weather weather = root.getWeatherList().get(0);
		cityName.setText(weather.getBasic().getCity());
		weatherCondition.setText(weather.getDailyForecast().get(0).getCond()
				.getTxt_d());
		nowTemp.setText(weather.getNow().getTmp() + "°");
		if (weather.getAqi() != null) {
			aqiValue.setText(weather.getAqi().getCity().getAqi());
			aqiquality.setText(weather.getAqi().getCity().getQlty());
		}
		TodayText.setText(getWeatherDesp(weather, TODAY_INDEX));
		tomorrowText.setText(getWeatherDesp(weather, TOMORROW_INDEX));
		ttomorrowText.setText(getWeatherDesp(weather, TTOMORROW_INDEX));
		todayTempScope.setText(tempScope(weather, TODAY_INDEX));
		tomorrowTempScope.setText(tempScope(weather, TOMORROW_INDEX));
		ttomorrowTempScope.setText(tempScope(weather, TTOMORROW_INDEX));
		alarmText.setText(getAlarmsText(weather));
		// if (weather.getAlarms()!=null) {
		// LocationManager locationManager = getSystemService(name)
		// }
	}

	private void showErrorWeatherInfo() {
		cityName.setText(getString(R.string.default_cityname));
		weatherCondition.setText(getString(R.string.default_weatherdesp));
		nowTemp.setText(getString(R.string.default_nowtemp));
		aqiValue.setText(getString(R.string.default_aqivalue));
		aqiquality.setText(getString(R.string.default_aqiquality));
		TodayText.setText(getString(R.string.default_list_today_condition));
		tomorrowText
				.setText(getString(R.string.default_list_tomorrow_condition));
		ttomorrowText
				.setText(getString(R.string.default_list_ttomorrow_condition));
		todayTempScope
				.setText(getString(R.string.default_list_today_tempscope));
		tomorrowTempScope
				.setText(getString(R.string.default_list_tomorrow_tempscope));
		ttomorrowTempScope
				.setText(getString(R.string.default_list_ttomorrow_tempscope));
		alarmText.setText(getString(R.string.default_alarm_text));
	}

	/**
	 * 获得告警
	 * 
	 * @param weather
	 * @return
	 */
	private String getAlarmsText(Weather weather) {
		List<Alarms> alarmList = weather.getAlarms();
		StringBuilder text = new StringBuilder();
		if (alarmList.get(0).getTitle() != null) {
			for (Alarms alarm : alarmList) {
				String title = alarm.getTitle();
				title = title.substring(title.indexOf("发布") + 2);
				if (!text.toString().contains(title)) {
					text.append("! ");
					text.append(title);
					text.append("\n");
				}
			}
		} else {
			text.delete(0, text.length());
		}
		return text.toString();
	}

	/**
	 * 获得未来天气状况字符串
	 * 
	 * @param weather
	 * @param whatDay
	 * @return
	 */
	private String getWeatherDesp(Root.Weather weather, int whatDay) {
		return weather.getDailyForecast().get(whatDay).getCond().getTxt_d();
	}

	/**
	 * 获得气温高低字符串
	 * 
	 * @param weather
	 * @param whatDay
	 * @return
	 */
	private String tempScope(Root.Weather weather, int whatDay) {
		return weather.getDailyForecast().get(whatDay).getTmp().getMax() + "°~"
				+ weather.getDailyForecast().get(whatDay).getTmp().getMin()
				+ "°";
	}

	/**
	 * 按钮监听行为
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.serch_button:
			Intent searchIntent = new Intent(this, SearchCityActivity.class);
			startActivity(searchIntent);
			break;
		case R.id.refresh_button:
			if (cityName != null) {
				queryWeatherInfoBycityName(cityNameStr);
			} else {
				setContentView(R.layout.activity_main_error);
			}
			Toast.makeText(this,
					getString(R.string.refresh_toast) + cityNameStr,
					Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

	@Override
	public void onReceiveLocation(BDLocation location) {

		cityNameStr = Util.getCityNameByLatLon(this, location.getLatitude(),
				location.getLongitude());
		cityNameStr = Util.getShortCityName(this, cityNameStr);
		if (cityNameStr != null) {
			queryWeatherInfoBycityName(cityNameStr);
		} else {
			setContentView(R.layout.activity_main_error);
		}
	}
}
