package com.altimetrik.music.AltimetrikMusic.domain;

import org.jmusixmatch.entity.lyrics.Lyrics;

public class Album {
	public String name;

//	public Artist artist;
	public String artist;

	public Integer trakId;

	public String lyrics;
	
	public String query;
	
	private String imge;
	
//	public Lyrics lyric;

	

//	public Lyrics getLyric() {
//		return lyric;
//	}
//
//	public void setLyric(Lyrics lyric) {
//		this.lyric = lyric;
//	}

	
	public Album() {
		super();
	}

	public Album(String name, String artist, Integer trakId, String lyrics, String query, String imge) {
	super();
	this.name = name;
	this.artist = artist;
	this.trakId = trakId;
	this.lyrics = lyrics;
	this.query = query;
	this.imge = imge;
}

	public String getImge() {
		return imge;
	}

	public void setImge(String imge) {
		this.imge = imge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getTrakId() {
		return trakId;
	}

	public void setTrakId(Integer trakId) {
		this.trakId = trakId;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	

}
