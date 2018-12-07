package com.altimetrik.music.AltimetrikMusic.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.music.AltimetrikMusic.domain.Artist;
import com.altimetrik.music.AltimetrikMusic.iservice.IArtistService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralTest {
	@Autowired
	IArtistService artistService;
	
	@Test
	public void testGetArtistInformation() {
		String query = "adele";
		String result = "Adele";

		Artist testCase = artistService.getArtistInfo(query);
		String artistName = "";

		if (testCase != null) {
			artistName = testCase.getName();
		}

		assertEquals(artistName, result);
	}

}
