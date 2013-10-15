package com.medallion.forms;

import com.medallion.R;
import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Confirm extends Activity implements OnClickListener {
	String fname, lname, email, gender, mobile, idnumber;
	Button confirm, report, cancel;
	Intent i;
	TextView first_name, last_name, person_gender, person_mobile,
			person_idnumber, person_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"QOYn8beuGCf7QRsQXQPdhf6zwtNTDPUsSQ7ktWeS",
				"bZR6V8alLCYOjt0o5ZoFDo9yBUJWB9UslvdkjNyP");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		setup();

		data();

	}

	private void setup() {
		// TODO Auto-generated method stub
		confirm = (Button) findViewById(R.id.bconfirm);
		report = (Button) findViewById(R.id.breport);
		cancel = (Button) findViewById(R.id.bcancel);

		first_name = (TextView) findViewById(R.id.tvfname);
		last_name = (TextView) findViewById(R.id.tvlname);
		person_gender = (TextView) findViewById(R.id.tvgender);
		person_email = (TextView) findViewById(R.id.tvemail);
		person_mobile = (TextView) findViewById(R.id.tvmobile);
		person_idnumber = (TextView) findViewById(R.id.tvidnumber);

		confirm.setOnClickListener(this);
		report.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	private void data() {
		// TODO Auto-generated method stub
		// Get data and add it to the Confirm.java activity

		Bundle get = getIntent().getExtras();
		fname = get.getString("fname");
		lname = get.getString("lname");
		email = get.getString("email");
		gender = get.getString(gender);
		mobile = get.getString("mobile");
		idnumber = get.getString("idnumber");

		settext();

	}

	private void settext() {
		// TODO Auto-generated method stub
		first_name.setText("First Name: " + fname);
		last_name.setText("Last Name: " + lname);
		person_email.setText("Email Address: " + email);
		person_mobile.setText("Mobile Number: " + mobile);
		person_idnumber.setText("ID Number: " + idnumber);
		person_gender.setText("Gender: " + gender);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bconfirm:
			i = new Intent(getApplicationContext(), Password.class);
			Bundle b = new Bundle();
			b.putString("idnumber", idnumber);
			i.putExtras(b);
			startActivity(i);
			break;
		case R.id.breport:
			// Create and send a report
			Toast.makeText(getApplicationContext(), "Report sent successfully",
					Toast.LENGTH_LONG).show();
			break;
		case R.id.bcancel:
			i = new Intent(getApplicationContext(), SignUp.class);
			startActivity(i);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			data();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	public void detailsToNext() {
	}
}
