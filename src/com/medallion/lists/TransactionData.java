package com.medallion.lists;

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
import com.medallion.specifications.TransactionSpecs;

public class TransactionData extends SherlockListActivity {

	private static final int ID_VIEW = 1;
	
	private static final int ID_DELETE = 3;
	
	int timeRange;
	Intent i;
	
	//Find a way to display the data in today, this week, this year and all time
	// Get the transaction name and put it in an array list. Then on click (in the
	// Quick Action)get
	// all the data about the transaction in all the other fieds of the drug and send
	// them to TransactionSpecs. Create TextViews and display the data in those
	// textviews with appropriate labels
	// Create the listview in transaction_data.xml

	QuickAction quickAction;

	String[] transactions = { "past 24hrs", "past Week",
			"past Month", "past Year", "All Time"};
	ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.tvcustom, transactions));

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
		ActionItem view = new ActionItem(ID_VIEW, "view", getResources()
				.getDrawable(R.drawable.plus));
		
		ActionItem delete = new ActionItem(ID_DELETE, "Delete", getResources()
				.getDrawable(R.drawable.ic_launcher));

		view.setSticky(true);
		
		delete.setSticky(true);
		quickAction = new QuickAction(this, QuickAction.HORIZONTAL);

		// add action items into QuickAction
		quickAction.addActionItem(view);
		quickAction.addActionItem(delete);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_VIEW:
							i = new Intent(getApplicationContext(),
									TransactionSpecs.class);
							Bundle b = new Bundle ();
							b.putInt("timeRange",timeRange);
							i.putExtras(b);
							startActivity(i);
							break;
						
						
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									TransactionData.this);
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
		timeRange=position;
	}

}
