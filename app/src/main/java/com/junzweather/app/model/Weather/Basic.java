package com.junzweather.app.model.Weather;

/**
 * Auto-generated: 2016-06-28 15:5:2
 * 
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class Basic {

	private String city;
	private String cnty;
	private String id;
	private String lat;
	private String lon;
	private Update update;

	public static class Update {

		private String loc;
		private String utc;

		public void setLoc(String loc) {
			this.loc = loc;
		}

		public String getLoc() {
			return loc;
		}

		public void setUtc(String utc) {
			this.utc = utc;
		}

		public String getUtc() {
			return utc;
		}

	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCnty(String cnty) {
		this.cnty = cnty;
	}

	public String getCnty() {
		return cnty;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLat() {
		return lat;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLon() {
		return lon;
	}

	public void setUpdate(Update update) {
		this.update = update;
	}

	public Update getUpdate() {
		return update;
	}

}