package com.example.pavel.githubclient.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.pavel.githubclient.mvp.models.Repository;

public interface RepositoryView extends MvpView {

	void showRepositoryInfo(Repository repository);
}
