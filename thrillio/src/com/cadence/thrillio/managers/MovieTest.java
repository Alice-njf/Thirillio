package com.cadence.thrillio.managers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cadence.thrillio.constants.MovieGenre;
import com.cadence.thrillio.entities.Movie;

class MovieTest {

	@Test
	void testIsKidfriendlyEligible() {
		//Test 1: Sex in title
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Sex", "", 1941,
				new String[] { "Orson Welles,Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CLASSICS,
				8.5);
		boolean isKidFriendlyEligible = movie.isKidfriendlyEligible();
		assertFalse("Sex in title", isKidFriendlyEligible);
		
		//Test 2: Genre: Horror 
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen of Germany", "", 1941,
				new String[] { "Orson Welles,Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		isKidFriendlyEligible = movie.isKidfriendlyEligible();
		assertFalse("Genre: Horror",isKidFriendlyEligible);
		
		//Test 3:
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen of Germany", "", 1941,
				new String[] { "Orson Welles,Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CHILDREN_AND_FAMILY,
				8.5);
		isKidFriendlyEligible = movie.isKidfriendlyEligible();
		assertTrue("All good!", isKidFriendlyEligible);
		
	}

}
