package com.cadence.thrillio;

import com.cadence.thrillio.constants.KidFriendlyStatus;
import com.cadence.thrillio.constants.UserType;
import com.cadence.thrillio.controllers.BookmarkController;
import com.cadence.thrillio.entities.Bookmark;
import com.cadence.thrillio.entities.User;
import com.cadence.thrillio.partner.Shareable;

public class View {
	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n " + user.getEmail() + "is bookmarking"); for(int i = 0
	 * ; i< DataStore.USER_BOOKMARK_LIMIT; i ++) { int typeOffset =
	 * (int)(Math.random()* DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset =
	 * (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE); Bookmark bookmark =
	 * bookmarks[typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * System.out.println(bookmark); }
	 * 
	 * }
	 */

	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n ***user type : " + user.getUserType() + ", " + user.getEmail() + "is browsing items ... ");
		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmark = getBookmarkDecision(bookmark);
					if (isBookmark) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("new item bookmarked .. " + bookmark);
					}
				}
				
				
				if (user.getUserType().equals(UserType.CHIEFEDITOR) || user.getUserType().equals(UserType.EDITOR)) {
					
					//Mark as kid friendly
					if (bookmark.isKidfriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyDesicion(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);

						}
					}
					//sharing
					if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if(isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}
	}
	
	//TODO: Below methods simulate user input After IO, we take inpute via console.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidFriendlyDesicion(Bookmark bookmark) {
		double randomVal = Math.random();
		return randomVal < 0.4 ? KidFriendlyStatus.APPROVED
				: (randomVal >= 0.4 && randomVal < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;

	}

}
