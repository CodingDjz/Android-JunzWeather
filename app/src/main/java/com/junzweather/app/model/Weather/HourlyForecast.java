package com.junzweather.app.model.Weather;

import java.util.Date;

/**
 * Auto-generated: 2016-06-28 15:5:2
 * 
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class HourlyForecast {

	private String date;
	private String hum;
	private String pop;
	private String pres;
	private String tmp;
	private Wind wind;

	public static class Wind {

		private String deg;
		private String dir;
		private String sc;
		private String spd;

		public void setDeg(String deg) {
			this.deg = deg;
		}

		public String getDeg() {
			return deg;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}

		public String getDir() {
			return dir;
		}

		public String getSc() {
			return sc;
		}

		public void setSc(String sc) {
			this.sc = sc;
		}

		public void setSpd(String spd) {
			this.spd = spd;
		}

		public String getSpd() {
			return spd;
		}

	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setHum(String hum) {
		this.hum = hum;
	}

	public String getHum() {
		return hum;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

	public String getPop() {
		return pop;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getPres() {
		return pres;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getTmp() {
		return tmp;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Wind getWind() {
		return wind;
	}

}