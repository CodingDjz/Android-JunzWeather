package com.junzweather.app.model.Weather;

import java.util.Date;

/**
 * Auto-generated: 2016-06-28 15:5:2
 * 
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class Now {

	private Cond cond;
	private String fl;
	private String hum;
	private String pcpn;
	private String pres;
	private String tmp;
	private String vis;
	private Wind wind;

	public static class Cond {

		private String code;
		private String txt;

		public void setCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setTxt(String txt) {
			this.txt = txt;
		}

		public String getTxt() {
			return txt;
		}

	}

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

	public void setCond(Cond cond) {
		this.cond = cond;
	}

	public Cond getCond() {
		return cond;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public String getFl() {
		return fl;
	}

	public void setHum(String hum) {
		this.hum = hum;
	}

	public String getHum() {
		return hum;
	}

	public void setPcpn(String pcpn) {
		this.pcpn = pcpn;
	}

	public String getPcpn() {
		return pcpn;
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

	public void setVis(String vis) {
		this.vis = vis;
	}

	public String getVis() {
		return vis;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Wind getWind() {
		return wind;
	}

}