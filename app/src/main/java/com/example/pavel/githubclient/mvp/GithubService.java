package com.example.pavel.githubclient.mvp;

import com.example.pavel.githubclient.app.GithubApi;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.UsersResponse;

import java.util.List;

import io.reactivex.Observable;

public class GithubService {
	private GithubApi githubApi;

	public GithubService(GithubApi githubApi) {
		this.githubApi = githubApi;
	}

	public Observable<UsersResponse> getUsers(String query, int page, int perPage) {
		return githubApi.getUsers(query, page, perPage);
	}

	public Observable<List<Repository>> getUserRepos(String login) {
		return githubApi.getUserRepos(login);
	}
}
