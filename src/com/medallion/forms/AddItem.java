package com.medallion.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.medallion.fragments.Home;
import com.parse.ParseObject;

public class AddItem extends SherlockActivity implements OnClickListener {
	
	
	//Send this data to the database

	Spinner stockType, transactionType;
	String[] stocktypes = { "Item 1", "Item 2", "Item 3", "Item 4" };
	String[] transactiontypes = { "Type 1", "Type 2", "Type 3", "Type 4" };
	Button addItem, save;
	Intent i;
	EditText quantity, subtotalCost;
	DatePicker expirationDate;
	int day, month, year;
	String thestockType, thetransactionType, theexpirationDate, theSupplier,
			theaqDate, placedBy, theTransactionType;
	int thequantity, thesubtotalCost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item);

		setup();
		getbundle();
	}

	private void getbundle() {
		// TODO Auto-generated method stub
		Bundle getdata = getIntent().getExtras();
		theSupplier = getdata.getString("supplier");
		theaqDate = getdata.getString("aqdate");
		placedBy = getdata.getString("placedby");
		theTransactionType = getdata.getString("transactiontype");

	}

	private void setup() {
		// TODO Auto-generated method stub
		stockType = (Spinner) findViewById(R.id.sp_stock_type);
		transactionType = (Spinner) findViewById(R.id.sp_transaction_type);

		ArrayAdapter<String> stype = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, stocktypes);
		stype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stockType.setAdapter(stype);

		ArrayAdapter<String> ttype = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, transactiontypes);
		ttype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		transactionType.setAdapter(ttype);

		addItem = (Button) findViewById(R.id.b_add_item);
		save = (Button) findViewById(R.id.b_save);

		addItem.setOnClickListener(this);
		save.setOnClickListener(this);

		quantity = (EditText) findViewById(R.id.et_item_quantity);
		subtotalCost = (EditText) findViewById(R.id.et_subtotal_cost);

		expirationDate = (DatePicker) findViewById(R.id.dp_expiration_date);
		day = expirationDate.getDayOfMonth();
		month = expirationDate.getMonth();
		year = expirationDate.getYear();

	}

	public void getdata() {
		// TODO Auto-generated method stub
		day = expirationDate.getDayOfMonth();
		month = expirationDate.getMonth();
		year = expirationDate.getYear();
		theexpirationDate = day + "/" + month + "/" + year;
		thestockType = stockType.getSelectedItem().toString();
		thetransactionType = transactionType.getSelectedItem().toString();
		String iq = quantity.getText().toString();
		thequantity = Integer.parseInt(iq);
		String isc = subtotalCost.getText().toString();
		thesubtotalCost = Integer.parseInt(isc);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		getbundle();
		getdata();
		switch (arg0.getId()) {
		case R.id.b_add_item:
			Toast.makeText(
					getApplicationContext(),
					theexpirationDate + " " + thestockType + " "
							+ thetransactionType + " " + thequantity + " "
							+ thesubtotalCost + " " + theSupplier + " "
							+ theaqDate + " " + theTransactionType
							+ " " + placedBy, Toast.LENGTH_SHORT).show();
			i = new Intent(getApplicationContext(), Home.class);
			startActivity(i);
			break;
		case R.id.b_save:
			Toast.makeText(getApplicationContext(), "Item saved",
					Toast.LENGTH_SHORT).show();
			i = new Intent(getApplicationContext(), Home.class);
			startActivity(i);
			break;
		}
	}

//	
//	public void updateStockTypes(){
//		ParseObject po = new ParseObject("StockTypes");
//		po.put("Name", thestockType);
//		po.put("Strength",strength);
//		po.put("Unit", value);
//		po.put("salesPriceKsh", value);
//		po.put("requiredThreshold", value);
//		po.put("Category", value);
//		po.put("Location", value);
//		po.put("Uses", value);
//		po.saveInBackground();
//	
//	}
	
//	public void updateDrugs(){
//		
//		ParseObject po = new ParseObject("StockTypes");
//		po.put("stockType", thestockType);
//		po.put("Unit", value);
//		po.put("salesPricePerUnit", value);
//		po.put("averageCostPerUnit", value);
//		po.put("requiredThreshold", value);
//		po.put("quantityOnHand", value);
//		po.put("expiringSoon", value);
//		po.saveInBackground();
//	
//	}
	
}
