package com.altimetrik.music.AltimetrikMusic.microservices;

import java.io.IOException;
import java.util.Iterator;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.music.AltimetrikMusic.domain.Album;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AlbumProxy {

	@Autowired
	private RestOperations restOperationTemplate;
	// Static because if i need to implemnt new method
	public static final String ARTIST_SERVICE_URL = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&api_key=10a105f3d6a6f7acee7485be78a72a88&limit=1&format=json&country=";
	public static final String apiKey = "e3f6c4a2f80300296b8b1f0e703a648c";
	MusixMatch musixMatch = new MusixMatch(apiKey);

	public Album getTopAlbum(String query) {
		Album album = new Album();

		album.setQuery(query);

		String apiURL = ARTIST_SERVICE_URL + query;

		ResponseEntity<String> apiRespose = this.restOperationTemplate.getForEntity(apiURL, String.class);
		String albumInfoJson = apiRespose.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;

		// Handel error if happen in mapping
		try {
			rootNode = objectMapper.readTree(albumInfoJson);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (rootNode != null) {

			JsonNode albumNode = rootNode.path("tracks");
			JsonNode trackNode = albumNode.path("track").get(0);
			JsonNode artistNode = trackNode.path("artist");

			String trackName = trackNode.path("name").asText();
			String artistName = artistNode.path("name").asText();

			// Track Search to get id and then pass it to another API to get lyrics
			Track track = new Track();
			try {
				track = musixMatch.getMatchingTrack(trackName, artistName);
			} catch (MusixMatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TrackData data = track.getTrack();

			album.setName(trackNode.path("name").asText());
			album.setTrakId(data.getTrackId());
			album.setArtist(data.getArtistName());

			Lyrics lyrics = new Lyrics();
			try {
				lyrics = musixMatch.getLyrics(data.getTrackId());
				album.setLyrics(lyrics.getLyricsBody());
//				album.setLyric(lyrics);

			} catch (MusixMatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Has list of image
			Iterator<JsonNode> imgItr = trackNode.path("image").elements();
			while (imgItr.hasNext()) {
				// In future we can determin device type then return image size for it
				// Now i'm suppose that is big
				if (imgItr.next().path("size").asText() == "large") {
					String currentImag = imgItr.next().path("#text").asText();
					album.setImge(currentImag);
					break;
				}

			}

		}
		return album;

	}

}
