package com.junzweather.app.util;

/**
 * @data 2016/6/23
 * @author ¶¡¾ýÖ®
 * 
 */
public interface HttpCallbackListener {

	void onFinish(String response);

	void onError(Exception e);
}
