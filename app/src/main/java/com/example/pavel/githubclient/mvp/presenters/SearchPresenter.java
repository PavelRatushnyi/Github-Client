package com.example.pavel.githubclient.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.pavel.githubclient.mvp.views.SearchView;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {

	public void onSearchSuccessful() {
		getViewState().showUserList();
	}

	public void onSearchFailed() {
		getViewState().showEmptyInputToast();
	}
}
