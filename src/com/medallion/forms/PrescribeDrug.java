package com.medallion.forms;

import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.medallion.fragments.Home;
import com.medallion.lists.NotificationsData;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PrescribeDrug extends SherlockActivity implements OnClickListener {

	// The ball is in your court. Godspeed.
	ProgressDialog pd,pf;
	String unit;
	DateTime dt;
	DatePicker dp_prescription_date;
	int price, quantity, day, month, year, result;
	AutoCompleteTextView drug;
	ArrayList<String> al = new ArrayList<String>();
	String s;
	String[] drugs = { "Drug 1", "Drug 2", "Drug 3" };
	Button baddanother, bfinish;
	EditText etpatientId, etdrugQuantity;
	TextView tvcost;
	Intent i;
	String patientId, drugName, drugQuantity, drugCost = "0",
			dateOfPrescription, objectId;

	// Send this data to the database
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",

				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prescribe_drug);
		setup();
		
		pd = ProgressDialog.show(PrescribeDrug.this, "Please wait",
				"Retrieving data...", true);
		retrieveDrugNames();
		pd.dismiss();
		
		
	}

	private void setup() {
		// TODO Auto-generated method stub
		setUpAutocomplete();
		// spdrugName = (Spinner) findViewById(R.id.sp_drug_name);
		ArrayAdapter<String> aname = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, drugs);
		aname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spdrugName.setAdapter(aname);

		etpatientId = (EditText) findViewById(R.id.et_patient_id);
		etdrugQuantity = (EditText) findViewById(R.id.et_prescribe_quantity);
		tvcost = (TextView) findViewById(R.id.tv_prescribe_cost);
		dp_prescription_date = (DatePicker) findViewById(R.id.dp_prescription_date);
		baddanother = (Button) findViewById(R.id.b_add_new_prescription);
		bfinish = (Button) findViewById(R.id.b_finish_add_prescription);
		baddanother.setOnClickListener(this);
		bfinish.setOnClickListener(this);
	}

	private void getUserData() {
		// TODO Auto-generated method stub
		patientId = etpatientId.getText().toString().trim();
		drugName = drug.getText().toString().trim();
		drugQuantity = etdrugQuantity.getText().toString().trim();
		quantity = Integer.valueOf(drugQuantity);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// setdata();
		switch (arg0.getId()) {
		case R.id.b_add_new_prescription:

			emptyFields();
			break;
		case R.id.b_finish_add_prescription:

			pd = ProgressDialog.show(PrescribeDrug.this, "Please wait",
					"Updating data...", true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// do the thing that takes a long time
					try {
						Thread.sleep(2000);
						getUserData();
						findDate();
						findDrugPriceAndUnit();
						Toast.makeText(getApplicationContext(), "Data saved",
								Toast.LENGTH_LONG).show();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						pd.dismiss();
					}
				}
			}).start();

			// // i = new Intent(getApplicationContext(), Home.class);
			// // startActivity(i);
			// break;
		}
	}

	public void setUpAutocomplete() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, al);
		drug = (AutoCompleteTextView) findViewById(R.id.et);
		drug.setThreshold(3);
		drug.setAdapter(adapter);
	}

	public void retrieveDrugNames() {
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Drugs");
		ph.setLimit(1000);
		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> stockItems, ParseException e) {

				if (e == null) {

					for (ParseObject p : stockItems) {
						s = p.getString("stockType");
						al.add(s);
					}
					Toast.makeText(getApplicationContext(), al.toString(),
							Toast.LENGTH_LONG).show();
					
				} else {

					Toast.makeText(getApplicationContext(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	public int findDrugPriceAndUnit() {
		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Drugs");
		ph.whereContains("stockType", drugName);

		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> stockItems, ParseException e) {

				if (e == null) {

					ParseObject p = stockItems.get(0);
					price = p.getInt("salesPricePerUnit");
					unit = p.getString("Unit");
					objectId = p.getObjectId();

					Toast.makeText(getApplicationContext(),
							String.valueOf(price), Toast.LENGTH_LONG).show();
					computeCost();
					try {
						updateDrugsTable();

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(),
								e1.toString() + "\n" + "please try again",
								Toast.LENGTH_LONG).show();
					} finally {
						updateTransactionsTable();
					}
				} else {

					Toast.makeText(getApplicationContext(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

			}
		});
		return price;
	}

	public void computeCost() {
		result = price * quantity;

		tvcost.setText(String.valueOf(result));
	}

	public void findDate() {

		day = dp_prescription_date.getDayOfMonth() + 0;
		month = dp_prescription_date.getMonth() + 1;
		year = dp_prescription_date.getYear();
		dateOfPrescription = day + "/" + month + "/" + year;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		dt = formatter.parseDateTime(dateOfPrescription);
		
	}

	public void updateDrugsTable() throws ParseException {

		ParseQuery query = new ParseQuery("Drugs");
		ParseObject o = query.get(objectId);
		o.increment("quantityOnHand", -quantity);
		o.save();

	}

	public void updateTransactionsTable() {

		// ParseUser pu = ParseUser.getCurrentUser();
		// String user = pu.getUsername();

		ParseObject updateTransactions = new ParseObject("Transactions");
		updateTransactions.put("stockType", drugName);
		updateTransactions.put("patientId", patientId);
		// updateTransactions.put("Creator", user);
		updateTransactions.put("Quantity", quantity);
		updateTransactions.put("Unit", unit);
		updateTransactions.put("totalCollectedOrPaid", result);
		updateTransactions.put("transactionDate", dt.toDate());
		updateTransactions.put("transactionType", "sale");
		updateTransactions.saveInBackground();
	}

	public void emptyFields() {
		etpatientId.setText("");
		drug.setText("");
		etdrugQuantity.setText("");
	}
}
