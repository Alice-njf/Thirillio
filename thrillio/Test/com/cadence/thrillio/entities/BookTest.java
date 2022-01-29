package com.cadence.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cadence.thrillio.constants.BookGenre;
import com.cadence.thrillio.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidfriendlyEligible() {
		// Test 1: title self_help --> false
		Book book = BookmarkManager.getInstance().createBook(4000, "self_help", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.FICTION, 4.3);
		boolean isKidFriendlyEligible = book.isKidfriendlyEligible();
		assertFalse("title self_help", isKidFriendlyEligible);
		
		//Test 2: title Horror
		book = BookmarkManager.getInstance().createBook(4000, "Horror", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.ART, 4.3);
		isKidFriendlyEligible = book.isKidfriendlyEligible();
		assertFalse("title Horror", isKidFriendlyEligible);
		
		//Test 3: Book Genre PHILPSOPHY
		book = BookmarkManager.getInstance().createBook(4000, "Book", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		isKidFriendlyEligible = book.isKidfriendlyEligible();
		assertFalse("Book Genre PHILPSOPHY", isKidFriendlyEligible);
		
		//Test 3: Book Genre ART
		book = BookmarkManager.getInstance().createBook(4000, "Book", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.ART, 4.3);
		isKidFriendlyEligible = book.isKidfriendlyEligible();
		assertTrue("Book Genre ART", isKidFriendlyEligible);
	}

}
