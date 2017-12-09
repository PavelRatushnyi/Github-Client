package com.example.pavel.githubclient.mvp.presenters;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.pavel.githubclient.mvp.views.SearchView;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {

	public void search(String searchQuery) {
		if (TextUtils.isEmpty(searchQuery)) {
			showEmptyInputToast();
		} else {
			showUsersList();
		}
	}

	private void showUsersList() {
		getViewState().showUserList();
	}

	private void showEmptyInputToast() {
		getViewState().showEmptyInputToast();
	}
}
