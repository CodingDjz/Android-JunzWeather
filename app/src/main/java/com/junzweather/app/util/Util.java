package com.junzweather.app.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.junzweather.app.R;
import com.junzweather.app.model.Weather.Root;

/**
 * @data 2016/6/23
 * @author 丁君之
 * 
 */
public class Util {
	public static Root root;
	public static Handler handler;
	public static final int AUTOUPDATE_SERVER = 0;
	public static final int NO_CITY_INFO = 1;
	public static final int AUTOUPDATE_BGP = 2;

	/**
	 * 使用GSON解析JSON
	 * 
	 * @param response
	 * @return
	 */
	public static Root parseWeatherResponse(String response) {
		Gson gson = new Gson();
		root = gson.fromJson(response, Root.class);
		return root;

	}

	/**
	 * 获得可用城市名
	 * 
	 * @param cityNameStr
	 * @return
	 */
	public static String getShortCityName(Context context, String cityNameStr) {
		if (cityNameStr.length() > 2
				&& cityNameStr.endsWith(context.getString(R.string.city)))
			cityNameStr = cityNameStr.substring(0, cityNameStr.length() - 1);
		return cityNameStr;
	}

	/**
	 * 通过左边吗，你想地理解析，获得城市名称
	 * 
	 * @param context
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static String getCityNameByLatLon(Context context, double lat,
			double lon) {

		Geocoder geocoder = new Geocoder(context);
		try { // 得到逆理编码，传入的参数分别为：纬度，经度，最大结果集 listAddress =
			List<Address> listAddress = geocoder.getFromLocation(lat, lon, 1);
			if (listAddress.size() != 0) {
				return listAddress.get(0).getLocality();
			}

		} catch (IOException e) {
			Log.e("Util", "start", e);
		}
		return null;
	}

}