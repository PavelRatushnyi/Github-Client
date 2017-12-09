package com.example.pavel.githubclient.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pavel.githubclient.R;

public class UsersListActivity extends AppCompatActivity {

	private static final String SEARCH_USER_NAME = "searchUserName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users_list);
	}

	public static void showIntance(Context context, String searchUserName) {
		Intent intent = new Intent(context, UsersListActivity.class);
		intent.putExtra(SEARCH_USER_NAME, searchUserName);
		context.startActivity(intent);
	}
}
