package com.example.pavel.githubclient.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.pavel.githubclient.mvp.models.User;

public interface UserView extends MvpView {

	void showUserInfo(User user);
}
