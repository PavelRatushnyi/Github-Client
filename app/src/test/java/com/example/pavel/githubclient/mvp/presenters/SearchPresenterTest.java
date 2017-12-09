package com.example.pavel.githubclient.mvp.presenters;

import com.example.pavel.githubclient.mvp.views.SearchView$$State;
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
public class SearchPresenterTest {

	@Rule
	public TestComponentRule testComponentRule = new TestComponentRule();

	@Mock
	SearchView$$State searchViewState;

	private SearchPresenter presenter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		presenter = new SearchPresenter();
		presenter.setViewState(searchViewState);
	}

	@Test
	public void search_shouldShowUsersList() {
		presenter.search(validSearchQuery());

		verify(searchViewState).showUserList();
	}

	@Test
	public void search_shouldShowEmptyInputToast() {
		presenter.search(emptySearchQuery());

		verify(searchViewState).showEmptyInputToast();
	}

	private String validSearchQuery() {
		return "test";
	}

	private String emptySearchQuery() {
		return "";
	}
}
