package com.example.pavel.githubclient.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.pavel.githubclient.app.GithubApp;
import com.example.pavel.githubclient.mvp.GithubService;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.models.UsersResponse;
import com.example.pavel.githubclient.mvp.views.UserDetailsView;
import com.example.pavel.githubclient.mvp.views.UserView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserDetailsPresenter extends BasePresenter<UserDetailsView> {

	@Inject
	GithubService githubService;

	private User user;

	public UserDetailsPresenter(User user) {
		super();
		this.user = user;
		GithubApp.getAppComponent().inject(this);
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().showUserInfo(user);
		loadRepositories();
	}

	public void loadRepositories() {
		getViewState().showLoadingProgress();

		Disposable disposable = githubService.getUserRepos(user.getLogin())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<List<Repository>>() {
					@Override
					public void accept(List<Repository> repositories) throws Exception {
						getViewState().hideLoadingProgress();
						getViewState().showUserRepos(repositories);
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						getViewState().hideLoadingProgress();
						getViewState().showErrorMessage();
					}
				});

		disposeOnDestroy(disposable);
	}
}
