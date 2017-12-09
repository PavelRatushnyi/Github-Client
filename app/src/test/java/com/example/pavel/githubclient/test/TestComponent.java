package com.example.pavel.githubclient.test;

import android.content.Context;

import com.example.pavel.githubclient.di.AppComponent;
import com.example.pavel.githubclient.mvp.presenters.UserDetailsPresenter;
import com.example.pavel.githubclient.mvp.presenters.UsersListPresenter;

import org.robolectric.RuntimeEnvironment;

public class TestComponent implements AppComponent {
	@Override
	public Context getContext() {
		return RuntimeEnvironment.application;
	}

	@Override
	public void inject(UsersListPresenter usersListPresenter) {

	}

	@Override
	public void inject(UserDetailsPresenter userDetailsPresenter) {

	}
}
