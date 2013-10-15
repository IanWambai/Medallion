package com.medallion.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.medallion.fragments.Home;

public class EnterItemType extends SherlockActivity implements OnClickListener {

	String[] units = { "Ampule", "Batch", "Bottle", "Box", "Capsule", "Dose",
			"Injection", "IV", "Jerrican", "Jug", "Kit", "Packet", "Pair",
			"Piece", "Roll", "Sachet", "Set", "Strips", "Tablet", "Tins",
			"Tube", "Unit", "Vial" };
	String[] categories = { "Name 1", "Name 2", "Name 3" };
	String[] locations = { "Purchase", "Bonus", "Donation", "Extra" };
	Spinner unit, category, location;
	EditText etname, etstrength, etprice, etthreshold, etuses;
	Button baddanother, bfinish;
	String name, strength, unittopatient, threshold, scategory, slocation,
			uses;
	int price;
	Intent i;
	
	//Send this data to the database

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_item_type);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		unit = (Spinner) findViewById(R.id.sp_unit_to_patient);
		category = (Spinner) findViewById(R.id.sp_new_item_category);
		location = (Spinner) findViewById(R.id.sp_new_item_location);

		ArrayAdapter<String> aunit = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, units);
		aunit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		unit.setAdapter(aunit);

		ArrayAdapter<String> acategory = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);
		acategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(acategory);

		ArrayAdapter<String> alocation = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locations);
		alocation
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		location.setAdapter(alocation);

		etname = (EditText) findViewById(R.id.et_new_item_name);
		etstrength = (EditText) findViewById(R.id.et_new_item_strength);
		etprice = (EditText) findViewById(R.id.et_new_item_sales_price);
		etthreshold = (EditText) findViewById(R.id.et_new_item_required_threshold);
		etuses = (EditText) findViewById(R.id.et_new_item_uses);

		name = etname.getText().toString();
		strength = etstrength.getText().toString();
		unittopatient = unit.getSelectedItem().toString();
		String ip = etprice.getText().toString();
		price = Integer.parseInt(ip);		
		threshold = etthreshold.getText().toString();
		scategory = category.getSelectedItem().toString();
		slocation = location.getSelectedItem().toString();
		uses = etuses.getText().toString();


		baddanother = (Button) findViewById(R.id.b_new_item_add_another);
		bfinish = (Button) findViewById(R.id.b_new_item_finish);

		baddanother.setOnClickListener(this);
		bfinish.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		setup();
		switch (arg0.getId()) {
		case R.id.b_new_item_add_another:
			Toast.makeText(
					getApplicationContext(),
					name + " " + strength + " " + unittopatient + " " + price
							+ " " + threshold + " " + scategory + " "
							+ slocation + " " + uses, Toast.LENGTH_LONG).show();
			break;
		case R.id.b_new_item_finish:
			Toast.makeText(getApplicationContext(), "Item has been added",
					Toast.LENGTH_LONG).show();
			i = new Intent(getApplicationContext(), Home.class);
			startActivity(i);
			break;
		}
	}
}
