package com.junzweather.app.model.Weather;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Root {
	@SerializedName("HeWeather data service 3.0")
	private ArrayList<Weather> weatherList;

	public static class Weather {

		private List<Alarms> alarms;
		private Aqi aqi;
		private Basic basic;
		@SerializedName("daily_forecast")
		private List<DailyForecast> dailyForecast;
		@SerializedName("hourly_forecast")
		private List<HourlyForecast> hourlyForecast;
		private Now now;
		private String status;
		private Suggestion suggestion;

		public void setAlarms(List<Alarms> alarms) {
			this.alarms = alarms;
		}

		public List<Alarms> getAlarms() {
			return alarms;
		}

		public void setAqi(Aqi aqi) {
			this.aqi = aqi;
		}

		public Aqi getAqi() {
			return aqi;
		}

		public void setBasic(Basic basic) {
			this.basic = basic;
		}

		public Basic getBasic() {
			return basic;
		}

		public void setDailyForecast(List<DailyForecast> dailyForecast) {
			this.dailyForecast = dailyForecast;
		}

		public List<DailyForecast> getDailyForecast() {
			return dailyForecast;
		}

		public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
			this.hourlyForecast = hourlyForecast;
		}

		public List<HourlyForecast> getHourlyForecast() {
			return hourlyForecast;
		}

		public void setNow(Now now) {
			this.now = now;
		}

		public Now getNow() {
			return now;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}

		public void setSuggestion(Suggestion suggestion) {
			this.suggestion = suggestion;
		}

		public Suggestion getSuggestion() {
			return suggestion;
		}

	}

	public ArrayList<Weather> getWeatherList() {
		return weatherList;
	}

	public void setWeatherList(ArrayList<Weather> weatherList) {
		this.weatherList = weatherList;
	}

}
