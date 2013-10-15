package com.medallion.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.medallion.R;
import com.medallion.functions.Analytics;

public class StockProjections extends SherlockFragment {

	Context context;
	View rootView;
	Intent i;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.stock_projections, container,
				false);
		setHasOptionsMenu(true);
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.stock_projections, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		context = getActivity();
		switch (item.getItemId()) {
		case R.id.m_get_data:
			Toast.makeText(context, "Analytics", Toast.LENGTH_LONG).show();
			i = new Intent(context, Analytics.class);
			startActivity(i);
		case R.id.m_search:
			Toast.makeText(context, "Search", Toast.LENGTH_LONG).show();
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
