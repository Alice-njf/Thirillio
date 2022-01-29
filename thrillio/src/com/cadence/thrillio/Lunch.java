package com.cadence.thrillio;

import com.cadence.thrillio.entities.Bookmark;
import com.cadence.thrillio.entities.User;
import com.cadence.thrillio.managers.BookmarkManager;
import com.cadence.thrillio.managers.UserManager;

public class Lunch {  
	
	private static User[] users;
	private static Bookmark[][] bookmarks;
	
	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmark();
		System.out.println("\n Printing Data ...");
		printUserData();
		printBookmarkData();
		
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
		
	}

	private static void printUserData() {
		for (User user: users) {
			System.out.println(user);
		}
		
	}
	private static void startBookmarking() {
		System.out.println("\n 2. Bookmarking ...");
		for (User user: users) {
			//View.bookmark(user, bookmarks);
			View.browse(user, bookmarks);
		}
		
	}

	public static void main(String[] args) {
		
		loadData();
		startBookmarking();


	}





}
