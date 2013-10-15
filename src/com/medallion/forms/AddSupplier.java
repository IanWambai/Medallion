package com.medallion.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.medallion.fragments.Home;
import com.parse.Parse;
import com.parse.ParseObject;

public class AddSupplier extends SherlockActivity implements OnClickListener {
	
	//Send this data to the database

	EditText etname, etnotes, etphonenumber, etemail, etlocation;
	CheckBox cbdelivers;
	String name, notes, tel, email, location, delivers;
	Button baddsupplier, bfinish;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_supplier);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		etname = (EditText) findViewById(R.id.et_supplier_name);
		etnotes = (EditText) findViewById(R.id.et_supplier_notes);
		etphonenumber = (EditText) findViewById(R.id.et_supplier_tel);
		etemail = (EditText) findViewById(R.id.et_supplier_email);
		etlocation = (EditText) findViewById(R.id.et_supplier_location);
		cbdelivers = (CheckBox) findViewById(R.id.cb_delivers);
		baddsupplier = (Button) findViewById(R.id.b_add_new_supplier);
		bfinish = (Button) findViewById(R.id.b_finish_add_suppplier);

		baddsupplier.setOnClickListener(this);
		bfinish.setOnClickListener(this);

		if (cbdelivers.isChecked()) {
			delivers = "Y";
		} else {
			delivers = "N";
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		setup();
		switch (arg0.getId()) {
		case R.id.b_add_new_supplier:
			Toast.makeText(
					getApplicationContext(),
					name + " " + notes + " " + tel + " " + email + " "
							+ location + " " + delivers, Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.b_finish_add_suppplier:
			getUserData();
			updateSuppliersTable();
			break;
		}
	}
	public void updateSuppliersTable(){

		ParseObject updateSuppliers = new ParseObject("Suppliers");
		updateSuppliers.put("Name", name);
		updateSuppliers.put("Location", location);
		updateSuppliers.put("EmailAddress", email);
		updateSuppliers.put("PhoneNumber", tel);
		updateSuppliers.put("Delivery", delivers);
		updateSuppliers.saveInBackground();
	}
	public void getUserData(){
		
		name = etname.getText().toString().trim();
		notes = etnotes.getText().toString().trim();
		tel = etphonenumber.getText().toString().trim();
		email = etemail.getText().toString().trim();
		location = etlocation.getText().toString().trim();
	}
}
