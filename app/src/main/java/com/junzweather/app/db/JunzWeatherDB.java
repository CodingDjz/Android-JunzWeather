package com.junzweather.app.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.junzweather.app.util.DBUtil;

/**
 * @data 2016/6/23
 * @author 丁君之
 * 
 */
public class JunzWeatherDB {
	/**
	 * 数据库名，在Helper第二个参数传进去
	 */

	private static final int version = 1;
	private static JunzWeatherDB junzWeatherDB;
	private SQLiteDatabase db;

	private JunzWeatherDB(Context context) {
		JunzWeatherOpenHelper dbHelper = new JunzWeatherOpenHelper(context,
				DBUtil.DB_NAME, null, version);
		// getwrite 如果不能写入，会报错。
		// getread不会报错
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 单例模式
	 */
	public synchronized static JunzWeatherDB getInstance(Context context) {
		if (junzWeatherDB == null)
			junzWeatherDB = new JunzWeatherDB(context);
		return junzWeatherDB;
	}

	// /**
	// * 存储City
	// *
	// * @param city
	// */
	// public void saveCity(City city) {
	// if (city != null) {
	// ContentValues values = new ContentValues();
	// values.put("area_id", city.getAreaId());
	// values.put("area_en", city.getAreaNameEn());
	// values.put("aren_zh", city.getAreaNameZn());
	// values.put("city", city.getCity());
	// values.put("province", city.getProvince());
	// db.insert("city", null, null);
	// }
	// }

	/**
	 * 通过城市名字查询城市ID
	 * 
	 * @param cityName
	 * @return
	 */

	public List<String> searchCityName(String SearchText) {
		String sql = "SELECT DISTINCT area_zh FROM city WHERE area_zh LIKE ? OR area_en LIKE ? ORDER BY area_en;";
		Cursor cursor = db.rawQuery(sql, new String[] { "%" + SearchText + "%",
				"%" + SearchText + "%" });
		List<String> cityNameList = new ArrayList<>();
		if (cursor.moveToFirst()) {
			do {
				cityNameList.add(cursor.getString(cursor
						.getColumnIndex("area_zh")));
			} while (cursor.moveToNext());
		}
		return cityNameList;
	}

	/**
	 * 获取所有城市列表
	 * 
	 * @return
	 */
	public List<String> searchAllCityName() {
		Cursor cursor = db.rawQuery(
				"SELECT area_zh FROM city ORDER BY area_en;", null);
		List<String> cityNameList = new ArrayList<>();
		if (cursor.moveToFirst()) {
			do {
				cityNameList.add(cursor.getString(cursor
						.getColumnIndex("area_zh")));

			} while (cursor.moveToNext());
		}
		return cityNameList;
	}
}
