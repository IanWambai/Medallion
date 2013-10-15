package com.medallion.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;


import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.parse.Parse;
import com.parse.ParseObject;

public class AddStockItems extends SherlockActivity implements OnClickListener {

	String[] suppliers = { "Supplier 1", "Supplier 2", "Supplier 3" };
	String[] names = { "Name 1", "Name 2", "Name 3" };
	String[] types = { "Purchase", "Bonus", "Donation", "Extra" };
	Spinner supplier, placedBy, transactionType;
	Button next;
	Intent i;
	DatePicker acquisition_date;
	int day, month, year;
	String theSupplier, stransactionType, splacedBy, acquisitionDate;

	//Send this data to the database
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_stock_items);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		supplier = (Spinner) findViewById(R.id.sp_supplier);
		placedBy = (Spinner) findViewById(R.id.sp_placed_by);
		transactionType = (Spinner) findViewById(R.id.sp_transaction_type);
		next = (Button) findViewById(R.id.b_next);
		next.setOnClickListener(this);

		ArrayAdapter<String> supp = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, suppliers);
		supp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		supplier.setAdapter(supp);

		ArrayAdapter<String> placedby = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, names);
		placedby.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		placedBy.setAdapter(placedby);

		ArrayAdapter<String> type = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, types);
		type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		transactionType.setAdapter(type);

		acquisition_date = (DatePicker) findViewById(R.id.dp_acquisition_date);
		day = acquisition_date.getDayOfMonth();
		month = acquisition_date.getMonth();
		year = acquisition_date.getYear();

		getdata();

	}

	public void getdata() {
		// TODO Auto-generated method stub
		day = acquisition_date.getDayOfMonth();
		month = acquisition_date.getMonth();
		year = acquisition_date.getYear();
		acquisitionDate = day + "/" + month + "/" + year;
		
		theSupplier = supplier.getSelectedItem().toString();
		stransactionType = transactionType.getSelectedItem().toString();
		splacedBy = placedBy.getSelectedItem().toString();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		getdata();
		switch (arg0.getId()) {
		case R.id.b_next:
			Toast.makeText(
					getApplicationContext(),
					acquisitionDate + " " + theSupplier + " "
							+ stransactionType + " " + splacedBy,
					Toast.LENGTH_LONG).show();
			i = new Intent(getApplicationContext(), AddItem.class);
			Bundle data=new Bundle();
			data.putString("supplier", theSupplier);
			data.putString("aqdate", acquisitionDate);
			data.putString("placedby", splacedBy);
			data.putString("transactiontype", stransactionType);
			i.putExtras(data);
			startActivity(i);
		
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
