package com.example.pavel.githubclient.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.pavel.githubclient.mvp.models.User;

import java.util.List;

public interface UsersListView extends MvpView {

	void addUsers(List<User> usersList);

	void setLoading(boolean loading);

	void addLoadingFooter();

	void removeLoadingFooter();

	void showErrorMessage();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showUserDetails(User user);
}
