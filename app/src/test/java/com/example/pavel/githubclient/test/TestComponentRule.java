package com.example.pavel.githubclient.test;

import android.support.annotation.NonNull;

import com.example.pavel.githubclient.app.GithubApp;
import com.example.pavel.githubclient.di.AppComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestComponentRule implements TestRule {

	private AppComponent appComponent;

	public TestComponentRule() {
		appComponent = new TestComponent();
	}

	public TestComponentRule(@NonNull AppComponent component) {
		this.appComponent = component;
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				GithubApp.setAppComponent(appComponent);
				base.evaluate();
			}
		};
	}
}
