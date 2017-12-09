package com.example.pavel.githubclient.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Repository {
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("full_name")
	private String fullName;
	@SerializedName("description")
	private String description;
	@SerializedName("url")
	private String url;
	@SerializedName("html_url")
	private String htmlUrl;
	@SerializedName("fork")
	private String fork;
	@SerializedName("private")
	private String privateRepo;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("pushed_at")
	private Date pushedAt;
	@SerializedName("git_url")
	private String gitUrl;
	@SerializedName("ssh_url")
	private String sshUrl;
	@SerializedName("clone_url")
	private String cloneUrl;
	@SerializedName("svn_url")
	private String svnUrl;
	@SerializedName("homepage")
	private String homepage;
	@SerializedName("size")
	private long size;
	@SerializedName("stargazers_count")
	private int starsCount;
	@SerializedName("watchers_count")
	private int watchersCount;
	@SerializedName("language")
	private String language;
	@SerializedName("has_issues")
	private boolean hasIssues;
	@SerializedName("has_downloads")
	private boolean hasDownloads;
	@SerializedName("has_wiki")
	private boolean hasWiki;
	@SerializedName("forks_count")
	private int forksCount;
	@SerializedName("open_issues_count")
	private int openIssuesCount;
	@SerializedName("default_branch")
	private String defaultBranch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getFork() {
		return fork;
	}

	public void setFork(String fork) {
		this.fork = fork;
	}

	public String getPrivateRepo() {
		return privateRepo;
	}

	public void setPrivateRepo(String privateRepo) {
		this.privateRepo = privateRepo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public String getSshUrl() {
		return sshUrl;
	}

	public void setSshUrl(String sshUrl) {
		this.sshUrl = sshUrl;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}

	public String getSvnUrl() {
		return svnUrl;
	}

	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getStarsCount() {
		return starsCount;
	}

	public void setStarsCount(int starsCount) {
		this.starsCount = starsCount;
	}

	public int getWatchersCount() {
		return watchersCount;
	}

	public void setWatchersCount(int watchersCount) {
		this.watchersCount = watchersCount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isHasIssues() {
		return hasIssues;
	}

	public void setHasIssues(boolean hasIssues) {
		this.hasIssues = hasIssues;
	}

	public boolean isHasDownloads() {
		return hasDownloads;
	}

	public void setHasDownloads(boolean hasDownloads) {
		this.hasDownloads = hasDownloads;
	}

	public boolean isHasWiki() {
		return hasWiki;
	}

	public void setHasWiki(boolean hasWiki) {
		this.hasWiki = hasWiki;
	}

	public int getForksCount() {
		return forksCount;
	}

	public void setForksCount(int forksCount) {
		this.forksCount = forksCount;
	}

	public int getOpenIssuesCount() {
		return openIssuesCount;
	}

	public void setOpenIssuesCount(int openIssuesCount) {
		this.openIssuesCount = openIssuesCount;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}
}
