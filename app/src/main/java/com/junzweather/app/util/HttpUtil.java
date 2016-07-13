package com.junzweather.app.util;

/**
 * @data 2016/6/23
 * @author 丁君之
 * 
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.junzweather.app.activity.MainActivity;

public class HttpUtil {

	public static final int WEATHER_REQUEST = 0;
	public static final int LOCATION_REQUEST = 1;

	/**
	 * 发送请求并接收结果
	 * 
	 * @param HttpUrl
	 * @param cityCode
	 * @param listener
	 */
	public static void sendHttpCallBackRequest(final String addressUrl,
			final String cityNameArg, int RequestType,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String response;
				/*
				 * 解析网址，并返回数据
				 */
				try {
					response = sendHttpRequest(addressUrl, cityNameArg,
							WEATHER_REQUEST);
					/**
					 * 将结果回调
					 */
					if (listener != null)
						listener.onFinish(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 发送HTTPrequest
	 * 
	 * @param addressUrl
	 * @param urlArg
	 * @param RequestType
	 * @return
	 */
	private static String sendHttpRequest(final String addressUrl,
			final String urlArg, int RequestType) {
		StringBuilder response = new StringBuilder();
		BufferedReader reader;
		HttpURLConnection connection = null;
		String HttpUrl = addressUrl + "?" + urlArg;
		try {
			URL url = new URL(HttpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			if (RequestType == WEATHER_REQUEST)
				connection.setRequestProperty("apikey",
						"0070c9f49b552312b2ba99a43db17f37");

			InputStream in = connection.getInputStream();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(in));
			response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return response.toString();

	}

}
