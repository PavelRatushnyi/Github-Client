package com.example.pavel.githubclient.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.pavel.githubclient.R;
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.presenters.UserDetailsPresenter;
import com.example.pavel.githubclient.mvp.views.UserDetailsView;
import com.example.pavel.githubclient.ui.adapters.RepositoriesAdapter;
import com.example.pavel.githubclient.widget.EmptyStateRecyclerView;

import java.util.List;

public class UserDetailsActivity extends MvpAppCompatActivity implements UserDetailsView {

	private static final String USER = "user";

	@InjectPresenter
	UserDetailsPresenter userDetailsPresenter;

	private EmptyStateRecyclerView repositoriesRecyclerView;
	private ProgressBar loadingProgressBar;
	private View errorView;
	private TextView userLogin;
	private TextView userScore;
	private RepositoriesAdapter repositoriesAdapter;

	public static void showInstance(Context context, User user) {
		Intent intent = new Intent(context, UserDetailsActivity.class);
		intent.putExtra(USER, user);
		context.startActivity(intent);
	}

	@ProvidePresenter
	UserDetailsPresenter provideUserDetailsPresenter() {
		User user = getIntent().getParcelableExtra(USER);
		return new UserDetailsPresenter(user);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);

		repositoriesRecyclerView = (EmptyStateRecyclerView) findViewById(R.id.user_repositories_recycler_view);
		View emptyView = findViewById(R.id.empty_view);
		repositoriesRecyclerView.setEmptyView(emptyView);
		loadingProgressBar = (ProgressBar) findViewById(R.id.loading_progress_bar);
		errorView = (TextView) findViewById(R.id.error_view);
		userLogin = (TextView) findViewById(R.id.login);
		userScore = (TextView) findViewById(R.id.score);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		repositoriesRecyclerView.setLayoutManager(linearLayoutManager);
		repositoriesAdapter = new RepositoriesAdapter(getMvpDelegate());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(repositoriesRecyclerView.getContext(), linearLayoutManager.getOrientation());
		repositoriesRecyclerView.addItemDecoration(dividerItemDecoration);
		repositoriesRecyclerView.setAdapter(repositoriesAdapter);
	}

	@Override
	public void showLoadingProgress() {
		repositoriesRecyclerView.setVisibility(View.GONE);
		loadingProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingProgress() {
		loadingProgressBar.setVisibility(View.GONE);
		repositoriesRecyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showErrorMessage() {
		repositoriesRecyclerView.setVisibility(View.GONE);
		errorView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showUserInfo(User user) {
		userLogin.setText(user.getLogin());
		userScore.setText(String.valueOf(user.getScore()));
	}

	@Override
	public void showUserRepos(List<Repository> repositories) {
		repositoriesAdapter.setRepositories(repositories);
	}
}
