package com.example.pavel.githubclient.mvp.presenters;

import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.views.RepositoryView$$State;
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
public class RepositoryPresenterTest {

	@Rule
	public TestComponentRule testComponentRule = new TestComponentRule();

	@Mock
	RepositoryView$$State repositoryViewState;

	private RepositoryPresenter presenter;
	private Repository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		repository = new Repository();

		presenter = new RepositoryPresenter(repository);
		presenter.setViewState(repositoryViewState);
	}

	@Test
	public void repository_shouldShowRepositoryInfo() {
		presenter.onFirstViewAttach();

		verify(repositoryViewState).showRepositoryInfo(repository);
	}

}
