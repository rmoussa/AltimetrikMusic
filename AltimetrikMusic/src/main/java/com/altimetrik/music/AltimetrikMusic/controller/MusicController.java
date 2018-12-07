package com.altimetrik.music.AltimetrikMusic.controller;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/altimetrik")
@Api(value = "The Controller")
public class MusicController {

	@Autowired
	IArtistService artistService;

	@Autowired
	IAlbumService albumService;

	@ApiOperation(value = "Get Artist Information", response = Artist.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieve Artist Information!"),
			@ApiResponse(code = 401, message = "Not Authorized :("),
			@ApiResponse(code = 403, message = "Sorry forbidden!!"),
			@ApiResponse(code = 404, message = "URL not found, Make sure of url") })
	@GetMapping("/artist-info/{name}")
	public ResponseEntity<?> getArtist(@PathVariable String name) {
		Artist artist = artistService.getArtistInfo(name);
		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@ApiOperation(value = "Get Top album with Lyrics", response = Album.class)
	@GetMapping("/top-album/{country}")
	public ResponseEntity<?> getTopAlbum(@PathVariable String country) {
		Album album = albumService.getTopAlbum(country);
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}

}
