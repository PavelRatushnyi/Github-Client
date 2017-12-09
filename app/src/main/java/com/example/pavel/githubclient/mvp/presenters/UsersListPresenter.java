package com.example.pavel.githubclient.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.pavel.githubclient.app.GithubApp;
import com.example.pavel.githubclient.mvp.GithubService;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.models.UsersResponse;
import com.example.pavel.githubclient.mvp.views.UsersListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.pavel.githubclient.app.GithubApi.PER_PAGE;

@InjectViewState
public class UsersListPresenter extends BasePresenter<UsersListView> {

	@Inject
	GithubService githubService;

	private String searchQuery;
	private int page = 1;
	private List<User> users = new ArrayList<>();
	private boolean loading = false;
	private boolean lastPage = false;

	public UsersListPresenter(String searchQuery) {
		super();
		this.searchQuery = searchQuery;
		GithubApp.getAppComponent().inject(this);
	}

	private boolean isLoading() {
		return loading;
	}

	private void setLoading(boolean loading) {
		this.loading = loading;
		getViewState().setLoading(loading);
		if (loading) {
			getViewState().addLoadingFooter();
		} else {
			getViewState().removeLoadingFooter();
		}
	}

	private boolean isLastPage() {
		return lastPage;
	}

	private void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().setUsersList(users);
		loadUsers();
	}

	public void onScrolledToBottom() {
		if (!isLoading() && !isLastPage()) {
			loadMoreUsers();
		}
	}

	private void loadMoreUsers() {
		page++;
		loadUsers();
	}

	private void loadUsers() {
		setLoading(true);

		Disposable disposable = githubService.getUsers(searchQuery, page, PER_PAGE)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<UsersResponse>() {
					@Override
					public void accept(UsersResponse response) throws Exception {
						setLoading(false);
						getViewState().addUsers(response.getUsers());
						if (getUsersCount() == response.getTotalCount()) {
							setLastPage(true);
						}
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						setLoading(false);
						getViewState().showErrorMessage();
					}
				});

		disposeOnDestroy(disposable);
	}

	private int getUsersCount() {
		return users.size();
	}
}
