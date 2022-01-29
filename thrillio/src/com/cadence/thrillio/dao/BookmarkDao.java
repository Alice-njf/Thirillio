package com.cadence.thrillio.dao;

import com.cadence.thrillio.DataStore;
import com.cadence.thrillio.entities.Bookmark;
import com.cadence.thrillio.entities.UserBookmark;

public class BookmarkDao {
	
	public Bookmark[][] getBookmark(){
		return DataStore.getBookmark();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}

}
