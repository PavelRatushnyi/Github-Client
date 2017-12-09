package com.example.pavel.githubclient.app;

import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.UsersResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {
	int PER_PAGE = 30;

	@GET("/search/users")
	Observable<UsersResponse> getUsers(@Query("q") String query, @Query("page") int page, @Query("per_page") int perPage);

	@GET("/users/{login}/repos")
	Observable<List<Repository>> getUserRepos(@Path("login") String login);
}
