package com.cadence.thrillio.entities;

import com.cadence.thrillio.partner.Shareable;

public class WebLink extends Bookmark implements Shareable {
	private String Url;
	private String host;

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "WebLink [id=" + getId() + ", title=" + getTitle() + ", profileUrl=" + getProfileUrl() + ", Url=" + Url + ", host=" + host + "]";
	}
	
	@Override
	public boolean isKidfriendlyEligible() {
		if(Url.contains("porn") || getTitle().contains("porn") || host.contains("adult") ) {
			return false;	
		}
		return true;
	}

	@Override
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
			builder.append("<type>WebLink</type>");
			builder.append("<title>").append(getTitle()).append("</title>");
			builder.append("<Url>").append(Url).append("</Url>");
			builder.append("<host>").append(host).append("</host>");
		builder.append("</item>");
		return builder.toString();
	}
}
