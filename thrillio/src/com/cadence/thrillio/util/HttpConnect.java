package com.cadence.thrillio.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {
	//is going to download a webpage
	public static String download(String sourceUrl) throws MalformedURLException, URISyntaxException {
		System.out.println("HttpConnect Downloading: " + sourceUrl);
		URL url = new URI(sourceUrl).toURL();
		
		try{
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			int responseCode = con.getResponseCode();
		
			if (responseCode >= 200 && responseCode< 300) { //ok
				return IOUtil.read(con.getInputStream());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}