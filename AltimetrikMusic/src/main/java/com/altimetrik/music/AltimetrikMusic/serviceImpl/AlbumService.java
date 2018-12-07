package com.altimetrik.music.AltimetrikMusic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.music.AltimetrikMusic.domain.Album;
import com.altimetrik.music.AltimetrikMusic.iservice.IAlbumService;
import com.altimetrik.music.AltimetrikMusic.microservices.AlbumProxy;

@Service
public class AlbumService implements IAlbumService {
	@Autowired
	AlbumProxy albumProxy;
	
	@Override
	public Album getTopAlbum(String q) {
		Album album= albumProxy.getTopAlbum(q);
		return album;
	}

}
