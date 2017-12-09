package com.example.pavel.githubclient.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.pavel.githubclient.mvp.models.User;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface UsersListView extends MvpView {

	void setUsersList(List<User> usersList);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void addUsers(List<User> usersList);

	void setLoading(boolean loading);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void addLoadingFooter();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void removeLoadingFooter();

	void showErrorMessage();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showUserDetails(User user);
}
