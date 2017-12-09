package com.example.pavel.githubclient.di.modules;

import com.example.pavel.githubclient.app.GithubApi;
import com.example.pavel.githubclient.mvp.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {GithubApiModule.class})
public class GithubModule {

	@Provides
	@Singleton
	public GithubService provideGithubService(GithubApi githubApi) {
		return new GithubService(githubApi);
	}
}
