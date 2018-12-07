package com.altimetrik.music.AltimetrikMusic.microservices;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.music.AltimetrikMusic.domain.Artist;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ArtistSearchProxy {

	@Autowired
	private RestOperations restOperationTemplate;

	// Static because if i need to implemnt new method
	public static final String ARTIST_SERVICE_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&api_key=10a105f3d6a6f7acee7485be78a72a88&format=json&artist=";

	// Return to me Artist with information about it
	public Artist getArtistInformation(String q) {

		// q -- query can be first, lat or FullName
		Artist artist = new Artist();
		artist.setQuery(q);

		String apiURL = ARTIST_SERVICE_URL + q;

		ResponseEntity<String> apiRespose = this.restOperationTemplate.getForEntity(apiURL, String.class);
		String aritstInfoJson = apiRespose.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;

		// Handel error if happen in mapping
		try {
			rootNode = objectMapper.readTree(aritstInfoJson);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (rootNode != null) {

			JsonNode artistNode = rootNode.path("artist");

			artist.setName(artistNode.path("name").asText());
			artist.setMbid(artistNode.path("mbid").asText());
			artist.setUrl(artistNode.path("url").asText());
			
			System.out.println(artistNode.path("url").asText());

			// Has list of image
			Iterator<JsonNode> imgItr = artistNode.path("image").elements();
			while (imgItr.hasNext()) {
				// In future we can determin device type then return image size for it
				// Now i'm suppose that is big
				if (imgItr.next().path("size").asText() == "large") {
					String currentImag = imgItr.next().path("#text").asText();
					artist.setImge(currentImag);
					break;
				}

			}
			JsonNode bio = artistNode.path("bio");
			artist.setSummery(bio.path("summary").asText());

		}
		return artist;

	}

}
