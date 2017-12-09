package com.example.pavel.githubclient.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.User;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface UserDetailsView extends MvpView {

	void showLoadingProgress();

	void hideLoadingProgress();

	void showErrorMessage();

	void showUserInfo(User user);

	void showUserRepos(List<Repository> repositories);
}
