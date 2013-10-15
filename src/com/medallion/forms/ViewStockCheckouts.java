package com.medallion.forms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.medallion.R;
import com.medallion.functions.ActionItem;
import com.medallion.functions.QuickAction;
import com.medallion.specifications.CheckoutSpecs;

public class ViewStockCheckouts extends SherlockListActivity {

	private static final int ID_INFO = 1;
	private static final int ID_EDIT = 2;
	private static final int ID_DELETE = 3;

	Intent i;

	QuickAction quickAction;

	String[] checkouts = { "Checkout 1", "Checkout 2", "Checkout 3", "Checkout 4",
			"Checkout 5", "Checkout 6" };
	ListView listView1;
	
	//Check the website and figuure out what checckuts means

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.tvcustom, checkouts));

		// Add header to Parse listView
		// listView1 = (ListView) findViewById(R.id.listView1);
		//
		// View header = (View) getLayoutInflater().inflate(
		// R.layout.listview_header_row, null);
		// listView1.addHeaderView(header);

		quickaction();

	}

	private void quickaction() {
		// TODO Auto-generated method stub
		ActionItem info = new ActionItem(ID_INFO, "Info", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem edit = new ActionItem(ID_EDIT, "Edit", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem delete = new ActionItem(ID_DELETE, "Delete", getResources()
				.getDrawable(R.drawable.ic_launcher));

		info.setSticky(true);
		edit.setSticky(true);
		delete.setSticky(true);
		quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
		quickAction.addActionItem(info);
		quickAction.addActionItem(edit);
		quickAction.addActionItem(delete);
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_INFO:
							i = new Intent(getApplicationContext(),
									CheckoutSpecs.class);
							startActivity(i);
							break;
						case ID_EDIT:
							i = new Intent(getApplicationContext(),
									CheckoutSpecs.class);
							startActivity(i);
							break;
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									ViewStockCheckouts.this);
							alertDialog.setTitle("Delete?");
							alertDialog
									.setMessage("Are you sure you want to delete?");
							alertDialog
									.setIcon(android.R.drawable.ic_lock_power_off);
							alertDialog.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											Toast.makeText(
													getApplicationContext(),
													"Deleted",
													Toast.LENGTH_SHORT).show();
										}
									});
							alertDialog.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							alertDialog.show();
							break;

						}
					}
				});

		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		quickAction.show(v);
	}

}
