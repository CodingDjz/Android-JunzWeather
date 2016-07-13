package com.junzweather.app.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.junzweather.app.R;
import com.junzweather.app.db.JunzWeatherDB;
import com.junzweather.app.util.Util;

public class SearchCityActivity extends BaseActivity implements
		OnQueryTextListener, OnItemClickListener {

	private SearchView searchView;
	private ListView listView;
	private List<String> cityNameList;
	private JunzWeatherDB db;
	private searchAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		initdataBase();
		inintView();
	}

	/**
	 * 初始化搜索栏
	 */
	private void inintView() {
		searchView = (SearchView) findViewById(R.id.search_view);
		listView = (ListView) findViewById(R.id.city_list_view);
		searchView.onActionViewExpanded();
		searchView.setQueryHint(Html.fromHtml("<font color = #B5B5B5>"
				+ getResources().getString(R.string.hint_message) + "</font>"));
		searchView.setOnQueryTextListener(this);
		adapter = new searchAdapter(this, android.R.layout.simple_list_item_1,
				cityNameList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	/**
	 * 初始化数据库
	 */
	private void initdataBase() {
		db = JunzWeatherDB.getInstance(this);
		cityNameList = db.searchAllCityName();
	}

	/**
	 * 搜索提交监听方法
	 */
	@Override
	public boolean onQueryTextSubmit(String query) {
		intentToMainActivity(query);
		return false;
	}

	/**
	 * 搜索内容改变监听方法
	 */
	@Override
	public boolean onQueryTextChange(String newText) {
		newText = Util.getShortCityName(this, newText);

		cityNameList.clear();
		if ("".equals(newText)) {
			cityNameList.addAll(db.searchAllCityName());
		} else {
			cityNameList.addAll(db.searchCityName(newText));
		}
		adapter.notifyDataSetChanged();
		return false;
	}

	/**
	 * ListView元素点击监听
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		String selectCityName = cityNameList.get(position);
		intentToMainActivity(selectCityName);
	}

	/**
	 * 跳转方法
	 * 
	 * @param selectCityName
	 */
	private void intentToMainActivity(String selectCityName) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("cityName", selectCityName);
		startActivity(intent);
		finish();
	}

	private class searchAdapter extends ArrayAdapter<String> {

		private int resource;

		public searchAdapter(Context context, int resource, List<String> objects) {
			super(context, resource, objects);
			this.resource = resource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(getContext())
						.inflate(resource, null);
			} else {
				view = convertView;
			}

			return super.getView(position, convertView, parent);
		}

	}

}
