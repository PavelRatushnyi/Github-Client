package com.example.pavel.githubclient.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.pavel.githubclient.R;
import com.example.pavel.githubclient.mvp.models.User;
import com.example.pavel.githubclient.mvp.presenters.UserPresenter;
import com.example.pavel.githubclient.mvp.views.UserView;

import java.util.List;

public class UsersAdapter extends MvpRecyclerViewAdapter<RecyclerView.ViewHolder> {

	private static final int USER_VIEW_TYPE = 34;
	private static final int LOADING_VIEW_TYPE = 35;

	private List<User> users;
	private boolean loading;

	public UsersAdapter(MvpDelegate<?> parentDelegate) {
		super(parentDelegate, String.valueOf(0));
	}

	public boolean isLoading() {
		return loading;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == USER_VIEW_TYPE) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
			return new UserHolder(view);
		} else {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_list_item, parent, false);
			return new LoadingHolder(view);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof UserHolder) {
			User user = getItem(position);
			((UserHolder) holder).bind(user);
		}
	}

	@Override
	public int getItemViewType(int position) {
		return position == getItemCount() - 1 && isLoading() ? LOADING_VIEW_TYPE : USER_VIEW_TYPE;
	}

	private User getItem(int position) {
		return users.get(position);
	}

	@Override
	public int getItemCount() {
		return users == null ? 0 : users.size();
	}

	public void setUsers(List<User> users) {
		this.users = users;
		notifyDataSetChanged();
	}

	public void addUsers(List<User> users) {
		int position = getItemCount();
		this.users.addAll(users);
		notifyItemRangeInserted(position, users.size());
	}

	public void addLoadingFooter() {
		users.add(new User());
		notifyItemInserted(getItemCount());
	}

	public void removeLoadingFooter() {
		users.remove(getItemCount() - 1);
		notifyItemRemoved(getItemCount());
	}

	public class LoadingHolder extends RecyclerView.ViewHolder {

		private LoadingHolder(View itemView) {
			super(itemView);
		}
	}

	public class UserHolder extends RecyclerView.ViewHolder implements UserView {

		@InjectPresenter
		UserPresenter userPresenter;

		private User user;
		private MvpDelegate mvpDelegate;

		private TextView login;
		private TextView avatarUrl;

		private UserHolder(View itemView) {
			super(itemView);
			login = itemView.findViewById(R.id.login);
			avatarUrl = itemView.findViewById(R.id.avatar_url);
		}

		@ProvidePresenter
		UserPresenter providerUserPresenter() {
			return new UserPresenter(user);
		}

		private void bind(User user) {
			if (getMvpDelegate() != null) {
				getMvpDelegate().onSaveInstanceState();
				getMvpDelegate().onDetach();
				getMvpDelegate().onDestroyView();
				mvpDelegate = null;
			}

			this.user = user;

			getMvpDelegate().onCreate();
			getMvpDelegate().onAttach();
		}

		@Override
		public void showUserInfo(User user) {
			login.setText(user.getLogin());
			avatarUrl.setText(user.getAvatarUrl());
		}

		private MvpDelegate getMvpDelegate() {
			if (user == null) {
				return null;
			}

			if (mvpDelegate == null) {
				mvpDelegate = new MvpDelegate<>(this);
				mvpDelegate.setParentDelegate(UsersAdapter.this.getMvpDelegate(), String.valueOf(user.getId()));
			}
			return mvpDelegate;
		}
	}
}
