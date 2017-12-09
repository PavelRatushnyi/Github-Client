package com.example.pavel.githubclient.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
	@SerializedName("total_count")
	private int totalCount;
	@SerializedName("incomplete_results")
	private boolean incompleteResults;
	@SerializedName("items")
	private List<User> users;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isIncompleteResults() {
		return incompleteResults;
	}

	public void setIncompleteResults(boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
