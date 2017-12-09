package com.example.pavel.githubclient.ui.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.pavel.githubclient.R;
import com.example.pavel.githubclient.mvp.presenters.SearchPresenter;
import com.example.pavel.githubclient.mvp.views.SearchView;

public class SearchActivity extends MvpAppCompatActivity implements SearchView {

	@InjectPresenter
	SearchPresenter searchPresenter;

	EditText searchEditText;
	Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		searchEditText = (EditText) findViewById(R.id.search_edit_text);
		searchButton = (Button) findViewById(R.id.search_button);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				searchPresenter.search(searchEditText.getText().toString().trim());
			}
		});
	}

	@Override
	public void showUserList() {
		UsersListActivity.showInstance(this, searchEditText.getText().toString().trim());
	}

	@Override
	public void showEmptyInputToast() {
		Toast.makeText(this, getString(R.string.empty_input_toast), Toast.LENGTH_SHORT).show();
	}
}
