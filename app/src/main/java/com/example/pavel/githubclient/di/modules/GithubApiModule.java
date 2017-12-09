package com.example.pavel.githubclient.di.modules;

import com.example.pavel.githubclient.app.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class GithubApiModule {

	@Provides
	@Singleton
	public GithubApi provideGithubApi(Retrofit retrofit) {
		return retrofit.create(GithubApi.class);
	}
}
