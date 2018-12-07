package com.altimetrik.music.AltimetrikMusic.controller;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.music.AltimetrikMusic.domain.Album;
import com.altimetrik.music.AltimetrikMusic.domain.Artist;
import com.altimetrik.music.AltimetrikMusic.iservice.IAlbumService;
import com.altimetrik.music.AltimetrikMusic.iservice.IArtistService;
import com.altimetrik.music.AltimetrikMusic.serviceImpl.AlbumService;
import com.altimetrik.music.AltimetrikMusic.serviceImpl.ArtistService;

@RestController
@RequestMapping("/altimetrik")
public class MusicController {

	@Autowired
	IArtistService artistService;

	@Autowired
	IAlbumService albumService;

	@GetMapping("/artist-info/{name}")
	public ResponseEntity<?> getArtist(@PathVariable String name) {
		Artist artist = artistService.getArtistInfo(name);
		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@GetMapping("/top-album/{country}")
	public ResponseEntity<?> getTopAlbum(@PathVariable String country) {
		Album album = albumService.getTopAlbum(country);
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}

	@GetMapping("/mm")
	public String getLyr() throws MusixMatchException {
		String apiKey = "e3f6c4a2f80300296b8b1f0e703a648c";
		MusixMatch musixMatch = new MusixMatch(apiKey);
		String trackName = "Don't stop the Party";
		String artistName = "The Black Eyed Peas";

		// Track Search [ Fuzzy ]
		Track track = musixMatch.getMatchingTrack(trackName, artistName);
		TrackData data = track.getTrack();

		System.out.println("AlbumID : " + data.getAlbumId());
		System.out.println("Album Name : " + data.getAlbumName());
		System.out.println("Artist ID : " + data.getArtistId());
		System.out.println("Album Name : " + data.getArtistName());
		System.out.println("Track ID : " + data.getTrackId());
		return "x";
	}

	@GetMapping("/tt")
	public String getTra() throws MusixMatchException {
		String apiKey = "e3f6c4a2f80300296b8b1f0e703a648c";
		MusixMatch musixMatch = new MusixMatch(apiKey);
		String trackName = "Take Me Out";
		String artistName = "Franz Ferdinand";

		// Track Search [ Fuzzy ]
		Track track = musixMatch.getMatchingTrack(trackName, artistName);
		TrackData data = track.getTrack();

		System.out.println("AlbumID : " + data.getAlbumId());
		System.out.println("Album Name : " + data.getAlbumName());
		System.out.println("Artist ID : " + data.getArtistId());
		System.out.println("Album Name : " + data.getArtistName());
		System.out.println("Track ID : " + data.getTrackId());
		return "x";
	}

}
