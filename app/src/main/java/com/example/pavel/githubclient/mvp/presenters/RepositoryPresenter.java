package com.example.pavel.githubclient.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.views.RepositoryView;

@InjectViewState
public class RepositoryPresenter extends MvpPresenter<RepositoryView> {

	private Repository repository;

	public RepositoryPresenter(Repository repository) {
		super();
		this.repository = repository;
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().showRepositoryInfo(repository);
	}
}
