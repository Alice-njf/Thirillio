package com.cadence.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.cadence.thrillio.dao.BookmarkDao;
import com.cadence.thrillio.entities.Book;
import com.cadence.thrillio.entities.Bookmark;
import com.cadence.thrillio.entities.Movie;
import com.cadence.thrillio.entities.User;
import com.cadence.thrillio.entities.UserBookmark;
import com.cadence.thrillio.entities.WebLink;
import com.cadence.thrillio.util.HttpConnect;
import com.cadence.thrillio.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {

	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);
		return movie;

	}

	public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher,
			String[] authors, String genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);
		return book;

	}

	public WebLink createWebLink(long id, String title, String profileUrl, String Url, String host) {
		WebLink webLink = new WebLink();
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setProfileUrl(profileUrl);
		webLink.setUrl(Url);
		webLink.setHost(host);

		return webLink;
	}

	public Bookmark[][] getBookmark() {
		return dao.getBookmark();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		dao.saveUserBookmark(userBookmark);

	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println("Kid friendly status: " + kidFriendlyStatus + ", Marked by " + user.getEmail() + ", Bookmark: "
				+ bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);

		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book)bookmark).getItemData());
		} else if (bookmark instanceof WebLink) {
			//System.out.println(((WebLink)bookmark).getItemData());
			try {
				String url = ((WebLink)bookmark).getUrl();
				if(!url.endsWith(".PDF")) {
					String webpage = HttpConnect.download(((WebLink)bookmark).getUrl());
					if(webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			}
		 catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
}
