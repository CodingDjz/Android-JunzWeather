package com.junzweather.app.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.junzweather.app.util.DBUtil;

/**
 * @data 2016/6/23
 * @author 丁君之
 * 
 */
public class JunzWeatherOpenHelper extends SQLiteOpenHelper {

	private Context mContext;

	public JunzWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建表,有实体类对应
		executeAssetsSQL(db, DBUtil.CITY_SQL);
	}

	/**
	 * 读取外部sql文件并执行
	 * 
	 * @param db
	 * @param file
	 */
	private void executeAssetsSQL(SQLiteDatabase db, String file) {
		BufferedReader in = null;
		try {
			String sqlPath = DBUtil.ASSETS_SCHEME_PATH + File.separator + file;
			in = new BufferedReader(new InputStreamReader(mContext.getAssets()
					.open(sqlPath)));
			String line;
			StringBuilder buffer = new StringBuilder();
			while ((line = in.readLine()) != null) {
				buffer.append(line);
				if (line.trim().endsWith(";")) {
					db.execSQL(buffer.toString().replace(";", ""));
					buffer.delete(0, buffer.length());
				}
			}
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
