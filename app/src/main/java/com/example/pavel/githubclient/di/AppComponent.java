package com.example.pavel.githubclient.di;

import android.content.Context;

import com.example.pavel.githubclient.di.modules.ContextModule;
import com.example.pavel.githubclient.di.modules.GithubModule;
import com.example.pavel.githubclient.mvp.presenters.UsersListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, GithubModule.class})
public interface AppComponent {

	Context getContext();

	void inject(UsersListPresenter usersListPresenter);
}
