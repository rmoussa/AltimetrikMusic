package com.altimetrik.music.AltimetrikMusic.domain;


public class Artist {
	
	private String name;
	
	private String mbid;
	
	private String url;
	
	private String imge;
	
	private String summery;
	
	private String query;
	
	
	public Artist(String name, String mbid, String url, String imge, String summery, String query) {
		super();
		this.name = name;
		this.mbid = mbid;
		this.url = url;
		this.imge = imge;
		this.summery = summery;
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Artist() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImge() {
		return imge;
	}

	public void setImge(String imge) {
		this.imge = imge;
	}

	public String getSummery() {
		return summery;
	}

	public void setSummery(String summery) {
		this.summery = summery;
	}
	
	
	

}
