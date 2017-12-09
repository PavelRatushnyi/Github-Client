package com.example.pavel.githubclient.mvp.presenters;

import com.example.pavel.githubclient.di.AppComponent;
import com.example.pavel.githubclient.mvp.GithubService;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.views.UserDetailsView$$State;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GithubClientTestRunner.class)
public class UserDetailsPresenterTest {

	@Mock
	GithubService githubService;

	@Rule
	public TestComponentRule testComponentRule = new TestComponentRule(testAppComponent());

	@Mock
	UserDetailsView$$State userDetailsView$$State;

	private UserDetailsPresenter presenter;
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setLogin(url());

		presenter = new UserDetailsPresenter(user);
		presenter.setViewState(userDetailsView$$State);

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
	public void user_shouldShowUserInfo() {
		List<Repository> repositories = repos();
		when(githubService.getUserRepos(url())).thenReturn(Observable.just(repositories));

		presenter.onFirstViewAttach();

		verify(userDetailsView$$State).showUserInfo(user);
		verify(userDetailsView$$State).showLoadingProgress();
		verify(userDetailsView$$State).hideLoadingProgress();
		verify(userDetailsView$$State).showUserRepos(repositories);
	}

	@Test
	public void user_shouldShowErrorIfSomeExceptionHappended() {
		when(githubService.getUserRepos(url())).thenReturn(Observable.<List<Repository>>error(new Throwable()));

		presenter.loadRepositories();

		verify(userDetailsView$$State).showLoadingProgress();
		verify(userDetailsView$$State).hideLoadingProgress();
		verify(userDetailsView$$State).showErrorMessage();
	}

	private AppComponent testAppComponent() {
		return new TestComponent() {
			@Override
			public void inject(UserDetailsPresenter presenter) {
				presenter.githubService = githubService;
			}
		};
	}

	private List<Repository> repos() {
		List<Repository> repositories = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Repository repository = new Repository();
			repositories.add(repository);
		}
		return repositories;
	}

	private String url() {
		return "https://api.github.com/users/pavelratushnyi/repos";
	}
}
