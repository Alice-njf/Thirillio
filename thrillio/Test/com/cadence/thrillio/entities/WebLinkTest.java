package com.cadence.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cadence.thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidfriendlyEligible() {
		//Test 1: porn in URL --> false
		WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.porn123.com", "");
		boolean isKidFriendlyEligible = webLink.isKidfriendlyEligible();
		assertFalse("porn in URL", isKidFriendlyEligible);
		
		//Test 2: porn in title --> false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger porn",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.123.com", "");
		isKidFriendlyEligible = webLink.isKidfriendlyEligible();
		assertFalse( "porn in title",isKidFriendlyEligible);
		
		//Test 3: adult in host --> false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.123.com", "adult");
		isKidFriendlyEligible = webLink.isKidfriendlyEligible();
		assertFalse("adult in host", isKidFriendlyEligible);
		
		//Test 4: adult in URL, but not in host --> true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger",
				"http://www.adult.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.123.com", "");
		isKidFriendlyEligible = webLink.isKidfriendlyEligible();
		assertTrue("adult in URL, but not in host", isKidFriendlyEligible);
		
		//Test 5: adult in title only --> true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger adult",
				"http://www.Taming.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.123.com", "");
		isKidFriendlyEligible = webLink.isKidfriendlyEligible();
		assertTrue("adult in title only", isKidFriendlyEligible);
		
	}

}
