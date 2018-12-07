package com.altimetrik.music.AltimetrikMusic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.music.AltimetrikMusic.domain.Artist;
import com.altimetrik.music.AltimetrikMusic.iservice.IArtistService;
import com.altimetrik.music.AltimetrikMusic.microservices.ArtistSearchProxy;


@Service
public class ArtistService implements IArtistService {

	// Inject the Artist microservice
	@Autowired
	ArtistSearchProxy artistSearchProxy;
	
	@Override
	public Artist getArtistInfo(String query) {
		Artist artist = artistSearchProxy.getArtistInformation(query);
		return artist;
	}

}
