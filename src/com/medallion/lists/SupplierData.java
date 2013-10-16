package com.medallion.lists;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.medallion.specifications.StockSpecs;
import com.medallion.specifications.SupplierSpecs;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SupplierData extends SherlockListActivity {

	private static final int ID_INFO = 1;
	private static final int ID_CALL = 2;
	private static final int ID_DELETE = 3;
	private static final int ID_EMAIL = 4;
	
	String s,number,emailAddress;
	int supplierSelected;
	
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
		
		setContentView(R.layout.supplier_data);
		listView1 =getListView();
		retrieveSuppliers();
	
		quickaction();

	}

	private void quickaction() {
		// TODO Auto-generated method stub
		ActionItem info = new ActionItem(ID_INFO, "Info", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem call = new ActionItem(ID_CALL, "call", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem delete = new ActionItem(ID_DELETE, "Delete", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem email = new ActionItem(ID_EMAIL, "email", getResources()
				.getDrawable(R.drawable.ic_launcher));

		info.setSticky(true);
		//edit.setSticky(true);
		delete.setSticky(true);
		quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
		quickAction.addActionItem(info);
		quickAction.addActionItem(call);
		quickAction.addActionItem(delete);
		quickAction.addActionItem(email);
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_INFO:
							i = new Intent(getApplicationContext(),
									SupplierSpecs.class);
							Bundle a= new Bundle();
							a.putInt("drugSelected", supplierSelected);
							i.putExtras(a);
							startActivity(i);
							
							break;
						case ID_EMAIL:
							retrieveContactDetails();
							
							Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
							emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { emailAddress });
							emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
									"Drug supplies");
							emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
									"Write the supplies request here...");
							emailIntent.setType("plain/text");
							startActivity(Intent.createChooser(emailIntent,
									"Choose an Email client :"));
							
							break;
							
						case ID_CALL:
							
							retrieveContactDetails();
							Intent i = new Intent(Intent.ACTION_CALL,
									Uri.parse("tel:"+number));
							startActivity(i);
							
							break;
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									SupplierData.this);
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
											
											deleteSupplier();
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



	public ArrayList<String> retrieveSuppliers(){
		
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Suppliers");
		
		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> stockItems, ParseException e) {

				if (e == null) {

				for(ParseObject p:stockItems){
					
					s=p.getString("Name");
					al.add(s);
				}
//				Toast.makeText(getApplicationContext(), al.toString(), Toast.LENGTH_LONG).show();
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
				supplierSelected=position;

			}

		});
		
	}
		public void deleteSupplier(){

			ParseQuery<ParseObject> p = ParseQuery.getQuery("Suppliers");
			p.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> suppliers, ParseException e) {

				if (e == null) {
					
					ParseObject po = suppliers.get(supplierSelected);
					po.deleteInBackground();
				} else {
				}
			}
		});
			
		}
	public void retrieveContactDetails(){
	ParseQuery<ParseObject> p = ParseQuery.getQuery("Suppliers");
	p.findInBackground(new FindCallback<ParseObject>() {

		@Override
		public void done(List<ParseObject> suppliers, ParseException e) {
			if (e == null) {

			ParseObject p=suppliers.get(supplierSelected);
			number =p.getString("PhoneNumber");
			emailAddress=p.getString("EmailAddress");
			
//			Toast.makeText(getApplicationContext(), al.toString(), Toast.LENGTH_LONG).show();
			
			} else {
				
				Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
			}

		}
	});}
}
