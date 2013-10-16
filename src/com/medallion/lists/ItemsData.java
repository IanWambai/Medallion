package com.medallion.lists;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockListActivity;
import com.medallion.R;


import com.medallion.functions.ActionItem;
import com.medallion.functions.QuickAction;
import com.medallion.specifications.ItemSpecs;
import com.parse.*;

public class ItemsData extends SherlockListActivity {

	private static final int ID_INFO = 1;
	private static final int ID_EDIT = 2;
	private static final int ID_DELETE = 3;
	String s;
	int drugSelected;
	Intent i;
	ArrayList<String> al = new ArrayList<String>();
	

	QuickAction quickAction;
	ListView listView1;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Change these IDs
		Parse.initialize(getApplicationContext(),
				"Parse Key",
				"Parse Key");
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.items_data);
		listView1 =getListView();
		retrieveStockTypes();
	
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
		//edit.setSticky(true);
		delete.setSticky(true);
		quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
		quickAction.addActionItem(info);
		//quickAction.addActionItem(edit);
		quickAction.addActionItem(delete);
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_INFO:
							i = new Intent(getApplicationContext(),
									ItemSpecs.class);
							Bundle a= new Bundle();
							a.putInt("drugSelected", drugSelected);
							i.putExtras(a);
							startActivity(i);
							
							break;
						case ID_EDIT:
							i = new Intent(getApplicationContext(),
									ItemSpecs.class);
							Bundle b= new Bundle();
							b.putInt("drugSelected", drugSelected);
							i.putExtras(b);
							startActivity(i);
							break;
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									ItemsData.this);
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
											
											deleteDrug();
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



	public ArrayList<String> retrieveStockTypes(){
		
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("StockTypes");
	
		ph.setLimit(1000);
		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> stockItems, ParseException e) {

				if (e == null) {

				for(ParseObject p:stockItems){
					
					s=p.getString("Name");
					al.add(s);
				}
				
				populateListView();
				
				} else {
					
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}

			}
		});
	return al;
	}
	
	public void populateListView(){
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.listitem, R.id.tvcustom, al);

		// Assign adapter to ListView
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				
				quickAction.show(view);
				drugSelected=position;

			}

		});
		
	}
		public void deleteDrug(){

			ParseQuery<ParseObject> ph = ParseQuery.getQuery("StockTypes");
			ph.setLimit(1000);
			ph.findInBackground(new FindCallback<ParseObject>() {
				
			@Override
			public void done(List<ParseObject> drugs, ParseException e) {

				if (e == null) {
					
					ParseObject po = drugs.get(drugSelected);
					po.deleteInBackground();
				} else {
				}
			}
		});
			
		}
		
	
			
}
			

