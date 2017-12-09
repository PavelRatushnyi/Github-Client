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
import com.example.pavel.githubclient.mvp.models.Repository;
import com.example.pavel.githubclient.mvp.presenters.RepositoryPresenter;
import com.example.pavel.githubclient.mvp.views.RepositoryView;

import java.util.List;

public class RepositoriesAdapter extends MvpRecyclerViewAdapter<RepositoriesAdapter.RepositoryHolder> {

	private List<Repository> repositories;

	public RepositoriesAdapter(MvpDelegate<?> parentDelegate) {
		super(parentDelegate, String.valueOf(1));
	}

	public void setRepositories(List<Repository> repositories) {
		this.repositories = repositories;
		notifyDataSetChanged();
	}

	@Override
	public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_list_item, parent, false);
		return new RepositoryHolder(view);
	}

	@Override
	public void onBindViewHolder(RepositoryHolder holder, int position) {
		Repository repository = getItem(position);
		holder.bind(repository);
	}

	@Override
	public int getItemCount() {
		return repositories == null ? 0 : repositories.size();
	}

	private Repository getItem(int position) {
		return repositories.get(position);
	}

	public class RepositoryHolder extends RecyclerView.ViewHolder implements RepositoryView {

		@InjectPresenter
		RepositoryPresenter repositoryPresenter;

		private Repository repository;
		private MvpDelegate mvpDelegate;

		private TextView name;
		private TextView description;

		public RepositoryHolder(View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			description = itemView.findViewById(R.id.description);
		}

		@ProvidePresenter
		RepositoryPresenter provideRepositoryPresenter() {
			return new RepositoryPresenter(repository);
		}

		private void bind(Repository repository) {
			if (getMvpDelegate() != null) {
				getMvpDelegate().onSaveInstanceState();
				getMvpDelegate().onDetach();
				getMvpDelegate().onDestroyView();
				mvpDelegate = null;
			}

			this.repository = repository;

			getMvpDelegate().onCreate();
			getMvpDelegate().onAttach();
		}

		private MvpDelegate getMvpDelegate() {
			if (repository == null) {
				return null;
			}

			if (mvpDelegate == null) {
				mvpDelegate = new MvpDelegate<>(this);
				mvpDelegate.setParentDelegate(RepositoriesAdapter.this.getMvpDelegate(), String.valueOf(repository.getId()));
			}
			return mvpDelegate;
		}

		@Override
		public void showRepositoryInfo(Repository repository) {
			name.setText(repository.getName());
			description.setText(repository.getDescription());
		}
	}
}
