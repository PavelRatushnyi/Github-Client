package com.example.pavel.githubclient.app;

import android.app.Application;

import com.example.pavel.githubclient.di.AppComponent;
import com.example.pavel.githubclient.di.DaggerAppComponent;
import com.example.pavel.githubclient.di.modules.ContextModule;

public class GithubApp extends Application {
	private static AppComponent appComponent;

	public static AppComponent getAppComponent() {
		return appComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		appComponent = DaggerAppComponent.builder()
				.contextModule(new ContextModule(this))
				.build();
	}
}
