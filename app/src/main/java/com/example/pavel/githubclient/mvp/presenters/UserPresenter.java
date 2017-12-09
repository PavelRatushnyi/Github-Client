package com.example.pavel.githubclient.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.views.UserView;

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView> {

	private User user;

	public UserPresenter(User user) {
		super();
		this.user = user;
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().showUserInfo(user);
	}
}
