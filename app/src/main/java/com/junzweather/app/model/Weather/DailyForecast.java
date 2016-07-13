package com.junzweather.app.model.Weather;


/**
 * Auto-generated: 2016-06-28 15:5:2
 * 
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class DailyForecast {

	private Astro astro;
	private Cond cond;
	private String date;
	private String hum;
	private String pcpn;
	private String pop;
	private String pres;
	private Tmp tmp;
	private String vis;
	private Wind wind;

	/**
	 * Astro¿‡
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Astro {

		private String sr;
		private String ss;

		public void setSr(String sr) {
			this.sr = sr;
		}

		public String getSr() {
			return sr;
		}

		public void setSs(String ss) {
			this.ss = ss;
		}

		public String getSs() {
			return ss;
		}

	}

	public static class Cond {
		private String code_d;

		private String code_n;

		private String txt_d;

		private String txt_n;

		public void setCode_d(String code_d) {
			this.code_d = code_d;
		}

		public String getCode_d() {
			return this.code_d;
		}

		public void setCode_n(String code_n) {
			this.code_n = code_n;
		}

		public String getCode_n() {
			return this.code_n;
		}

		public void setTxt_d(String txt_d) {
			this.txt_d = txt_d;
		}

		public String getTxt_d() {
			return this.txt_d;
		}

		public void setTxt_n(String txt_n) {
			this.txt_n = txt_n;
		}

		public String getTxt_n() {
			return this.txt_n;
		}
	}

	public static class Tmp {

		private String max;
		private String min;

		public void setMax(String max) {
			this.max = max;
		}

		public String getMax() {
			return max;
		}

		public void setMin(String min) {
			this.min = min;
		}

		public String getMin() {
			return min;
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

	public void setAstro(Astro astro) {
		this.astro = astro;
	}

	public Astro getAstro() {
		return astro;
	}

	public void setCond(Cond cond) {
		this.cond = cond;
	}

	public Cond getCond() {
		return cond;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public void setTmp(Tmp tmp) {
		this.tmp = tmp;
	}

	public Tmp getTmp() {
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