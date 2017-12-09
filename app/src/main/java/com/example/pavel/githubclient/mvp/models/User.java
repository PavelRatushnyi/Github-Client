package com.example.pavel.githubclient.mvp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
	@SerializedName("login")
	private String login;
	@SerializedName("id")
	private int id;
	@SerializedName("avatar_url")
	private String avatarUrl;
	@SerializedName("gravatar_id")
	private String gravatarId;
	@SerializedName("url")
	private String url;
	@SerializedName("html_url")
	private String htmlUrl;
	@SerializedName("followers_url")
	private String followersUrl;
	@SerializedName("following_url")
	private String followingUrl;
	@SerializedName("gists_url")
	private String gistsUrl;
	@SerializedName("starred_url")
	private String starredUrl;
	@SerializedName("subscriptions_url")
	private String subscriptionsUrl;
	@SerializedName("organizations_url")
	private String organizationsUrl;
	@SerializedName("repos_url")
	private String reposUrl;
	@SerializedName("events_url")
	private String eventsUrl;
	@SerializedName("received_events_url")
	private String receivedEventsUrl;
	@SerializedName("type")
	private String type;
	@SerializedName("site_admin")
	private boolean siteAdmin;
	@SerializedName("score")
	private double score;

	public User() {
	}

	protected User(Parcel in) {
		login = in.readString();
		id = in.readInt();
		avatarUrl = in.readString();
		gravatarId = in.readString();
		url = in.readString();
		htmlUrl = in.readString();
		followersUrl = in.readString();
		followingUrl = in.readString();
		gistsUrl = in.readString();
		starredUrl = in.readString();
		subscriptionsUrl = in.readString();
		organizationsUrl = in.readString();
		reposUrl = in.readString();
		eventsUrl = in.readString();
		receivedEventsUrl = in.readString();
		type = in.readString();
		siteAdmin = in.readByte() != 0;
		score = in.readDouble();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getGravatarId() {
		return gravatarId;
	}

	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getFollowersUrl() {
		return followersUrl;
	}

	public void setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
	}

	public String getFollowingUrl() {
		return followingUrl;
	}

	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	public String getGistsUrl() {
		return gistsUrl;
	}

	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	public String getStarredUrl() {
		return starredUrl;
	}

	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	public String getReposUrl() {
		return reposUrl;
	}

	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	public void setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(login);
		parcel.writeInt(id);
		parcel.writeString(avatarUrl);
		parcel.writeString(gravatarId);
		parcel.writeString(url);
		parcel.writeString(htmlUrl);
		parcel.writeString(followersUrl);
		parcel.writeString(followingUrl);
		parcel.writeString(gistsUrl);
		parcel.writeString(starredUrl);
		parcel.writeString(subscriptionsUrl);
		parcel.writeString(organizationsUrl);
		parcel.writeString(reposUrl);
		parcel.writeString(eventsUrl);
		parcel.writeString(receivedEventsUrl);
		parcel.writeString(type);
		parcel.writeByte((byte) (siteAdmin ? 1 : 0));
		parcel.writeDouble(score);
	}
}
