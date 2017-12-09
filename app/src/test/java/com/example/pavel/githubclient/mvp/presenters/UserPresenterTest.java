package com.example.pavel.githubclient.mvp.presenters;

import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.views.UserView$$State;
import com.example.pavel.githubclient.test.GithubClientTestRunner;
import com.example.pavel.githubclient.test.TestComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

@RunWith(GithubClientTestRunner.class)
public class UserPresenterTest {

	@Rule
	public TestComponentRule testComponentRule = new TestComponentRule();

	@Mock
	UserView$$State userViewState;

	private UserPresenter presenter;
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User();

		presenter = new UserPresenter(user);
		presenter.setViewState(userViewState);
	}

	@Test
	public void user_shouldShowUserInfo() {
		presenter.onFirstViewAttach();

		verify(userViewState).showUserInfo(user);

	}
}
