package com.medallion.lists;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.medallion.forms.PrescribeDrug;
import com.medallion.functions.ActionItem;
import com.medallion.functions.QuickAction;
import com.medallion.specifications.PrescriptionSpecs;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PrescriptionData extends SherlockListActivity {

	private static final int ID_INFO = 1;
	private static final int ID_EDIT = 2;
	private static final int ID_DELETE = 3;
	ProgressDialog pd;
	Intent i;
	String s;
	int prescriptionSelected;
	ArrayList<String> al = new ArrayList<String>();
	//Find a way of get a list of the prescriptions made
	// Get the prescription name and put it in an array list. Then on click (in the
	// Quick Action)get
	// all the data about the prescription in all the other fieds of the drug and send
	// them to PrescriptionSpecs. Create TextViews and display the data in those
	// textviews with appropriate labels
	// Create the listview in prescription_data.xml

	QuickAction quickAction;
	ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prescription_data);
		listView1=getListView();
		
		pd = ProgressDialog.show(PrescriptionData.this, "Please wait",
				"Updating data...", true);
		retrievePrescriptionData();
		pd.dismiss();


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

		// add action items into QuickAction
		quickAction.addActionItem(info);
		//quickAction.addActionItem(edit);
		quickAction.addActionItem(delete);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_INFO:
							i = new Intent(getApplicationContext(),
									PrescriptionSpecs.class);
							Bundle b= new Bundle();
							b.putInt("prescriptionSelected", prescriptionSelected);
							i.putExtras(b);
							startActivity(i);
							break;
						case ID_EDIT:
							i = new Intent(getApplicationContext(),
									PrescriptionSpecs.class);
							startActivity(i);
							break;
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									PrescriptionData.this);
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
											deleteprescription();
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
	
	public ArrayList<String> retrievePrescriptionData(){
		
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Transactions");
		ph.whereEqualTo("transactionType", "sale");
		ph.setLimit(1000);
		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> prescriptionItems, ParseException e) {

				if (e == null) {

				for(ParseObject p:prescriptionItems){
					
					s=p.getString("stockType");
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
				prescriptionSelected=position;

			}

		});
		
	}
	
	public void deleteprescription(){
		
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Transactions");
		ph.setLimit(1000);
		ph.findInBackground(new FindCallback<ParseObject>() {
			
		@Override
		public void done(List<ParseObject> prescriptions, ParseException e) {

			if (e == null) {
				
				ParseObject po = prescriptions.get(prescriptionSelected);
				po.deleteInBackground();
			} else {
			}
		}
	});
		
		
	}
}

