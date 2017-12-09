package com.example.pavel.githubclient.mvp.presenters;

import com.example.pavel.githubclient.di.AppComponent;
import com.example.pavel.githubclient.mvp.GithubService;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.models.UsersResponse;
import com.example.pavel.githubclient.mvp.views.UsersListView$$State;
import com.example.pavel.githubclient.test.GithubClientTestRunner;
import com.example.pavel.githubclient.test.TestComponent;
import com.example.pavel.githubclient.test.TestComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GithubClientTestRunner.class)
public class UsersListPresenterTest {

	@Mock
	GithubService githubService;

	@Rule
	public TestComponentRule testComponentRule = new TestComponentRule(testAppComponent());

	@Mock
	UsersListView$$State usersListViewState;

	private String searchQuery;
	private UsersListPresenter presenter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		searchQuery = anyString();
		presenter = new UsersListPresenter(searchQuery);
		presenter.setViewState(usersListViewState);

		RxJavaPlugins.reset();
		RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
			@Override
			public Scheduler apply(Scheduler scheduler) throws Exception {
				return Schedulers.trampoline();
			}
		});

		RxAndroidPlugins.reset();
		RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
			@Override
			public Scheduler apply(Scheduler scheduler) throws Exception {
				return Schedulers.trampoline();
			}
		});
	}

	@Test
	public void users_shouldOnAttachLoadAndShowUsers() {
		UsersResponse usersResponse = users();
		when(githubService.getUsers(searchQuery, anyInt(), anyInt())).thenReturn(Observable.just(usersResponse));

		presenter.onFirstViewAttach();
		verify(usersListViewState).setLoading(true);
		verify(usersListViewState).addLoadingFooter();
		verify(usersListViewState).setLoading(false);
		verify(usersListViewState).removeLoadingFooter();
		verify(usersListViewState).addUsers(usersResponse.getUsers());
	}

	@Test
	public void users_shouldCorrectLoadNextUsers() {
		UsersResponse usersResponse = users();
		when(githubService.getUsers(searchQuery, anyInt(), anyInt())).thenReturn(Observable.just(usersResponse));

		presenter.loadMore(30);
		verify(usersListViewState).setLoading(true);
		verify(usersListViewState).addLoadingFooter();
		verify(usersListViewState).setLoading(false);
		verify(usersListViewState).removeLoadingFooter();
		verify(usersListViewState).addUsers(usersResponse.getUsers());
	}

	@Test
	public void repositories_shouldShowErrorIfSomeExceptionHappended() {
		when(githubService.getUsers(searchQuery, anyInt(), anyInt())).thenReturn(Observable.<UsersResponse>error(new Throwable()));

		presenter.loadMore(30);
		verify(usersListViewState).setLoading(true);
		verify(usersListViewState).addLoadingFooter();
		verify(usersListViewState).setLoading(false);
		verify(usersListViewState).removeLoadingFooter();
		verify(usersListViewState).showErrorMessage();
	}

	@Test
	public void details_shouldShowDetailsContainer() {
		User user = new User();
		presenter.showUserDetails(user);
		verify(usersListViewState).showUserDetails(user);
	}

	private UsersResponse users() {
		UsersResponse usersResponse = new UsersResponse();
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			users.add(user);
		}

		usersResponse.setUsers(users);
		return usersResponse;
	}


	private AppComponent testAppComponent() {
		return new TestComponent() {
			@Override
			public void inject(UsersListPresenter presenter) {
				presenter.githubService = githubService;
			}
		};
	}
}
