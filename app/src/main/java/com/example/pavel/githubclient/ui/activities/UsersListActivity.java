package com.example.pavel.githubclient.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.pavel.githubclient.R;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.presenters.UsersListPresenter;
import com.example.pavel.githubclient.mvp.views.UsersListView;
import com.example.pavel.githubclient.ui.adapters.UsersAdapter;
import com.example.pavel.githubclient.widget.EmptyStateRecyclerView;

import java.util.List;

public class UsersListActivity extends MvpAppCompatActivity implements UsersListView {

	private static final String SEARCH_USER_NAME = "searchUserName";

	@InjectPresenter
	UsersListPresenter usersListPresenter;

	private EmptyStateRecyclerView usersRecyclerView;
	private UsersAdapter usersAdapter;
	private View errorView;

	public static void showInstance(Context context, String searchUserName) {
		Intent intent = new Intent(context, UsersListActivity.class);
		intent.putExtra(SEARCH_USER_NAME, searchUserName);
		context.startActivity(intent);
	}

	@ProvidePresenter
	UsersListPresenter provideUsersListPresenter() {
		String searchQuery = getIntent().getStringExtra(SEARCH_USER_NAME);
		return new UsersListPresenter(searchQuery);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users_list);

		usersRecyclerView = (EmptyStateRecyclerView) findViewById(R.id.users_recycler_view);
		View emptyView = findViewById(R.id.empty_view);
		usersRecyclerView.setEmptyView(emptyView);
		errorView = (TextView) findViewById(R.id.error_view);

		final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		usersRecyclerView.setLayoutManager(linearLayoutManager);
		usersAdapter = new UsersAdapter(getMvpDelegate());
		usersAdapter.setOnUserClickListener(new UsersAdapter.OnUserClickListener() {
			@Override
			public void onUserClick(View view) {
				int position = usersRecyclerView.getChildAdapterPosition(view);
				if (position != RecyclerView.NO_POSITION) {
					usersListPresenter.showUserDetails(usersAdapter.getItem(position));
				}
			}
		});
		usersRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				int visibleItemCount = linearLayoutManager.getChildCount();
				int totalItemCount = linearLayoutManager.getItemCount();
				int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
				if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
					usersListPresenter.onScrolledToBottom();
				}
			}
		});
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(usersRecyclerView.getContext(), linearLayoutManager.getOrientation());
		usersRecyclerView.addItemDecoration(dividerItemDecoration);
		usersRecyclerView.setAdapter(usersAdapter);
	}

	@Override
	public void setUsersList(List<User> users) {
		usersAdapter.setUsers(users);
	}

	@Override
	public void addUsers(List<User> users) {
		usersAdapter.addUsers(users);
	}

	@Override
	public void setLoading(boolean loading) {
		usersAdapter.setLoading(loading);
	}

	@Override
	public void addLoadingFooter() {
		usersAdapter.addLoadingFooter();
	}

	@Override
	public void removeLoadingFooter() {
		usersAdapter.removeLoadingFooter();
	}

	@Override
	public void showErrorMessage() {
		usersRecyclerView.setVisibility(View.GONE);
		errorView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showUserDetails(User user) {
		UserDetailsActivity.showInstance(this, user);
	}
}
