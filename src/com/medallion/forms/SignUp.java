package com.medallion.forms;

import java.util.List;

import com.medallion.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity implements OnClickListener {
	String fname, lname, email, gender, mobile, idnumber;
	EditText id;
	Button search;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"QOYn8beuGCf7QRsQXQPdhf6zwtNTDPUsSQ7ktWeS",
				"bZR6V8alLCYOjt0o5ZoFDo9yBUJWB9UslvdkjNyP");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		id = (EditText) findViewById(R.id.etid);
		search = (Button) findViewById(R.id.bsearch);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bsearch:
			String check = id.getText().toString();
			if (check == "") {
				id.setError("Please enter the ID Number");
			} else {
				AsyncTask<String, Void, Boolean> task = new ProgressTask(this);
				task.execute();
			}
			break;
		}
	}

	public class ProgressTask extends AsyncTask<String, Void, Boolean> {

		private Activity activity;
		private ProgressDialog dialog;

		public ProgressTask(Activity activity) {
			this.activity = activity;
			dialog = new ProgressDialog(SignUp.this);
		}

		protected void onPreExecute() {
			this.dialog.setTitle("Search");
			this.dialog.setMessage("Searching for your details...");
			this.dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			// Search

			ParseQuery<ParseObject> users = ParseQuery.getQuery("Staff");
			users.whereContains("ID_Number", id.getText().toString());
			users.findInBackground(new FindCallback<ParseObject>() {

				@Override
				public void done(List<ParseObject> us, ParseException e) {

					if (us == null) {
						dialog.dismiss();
						id.setError("Please enter the ID Number");

					} else if (e == null) {
						ParseObject po = us.get(0);
						fname = po.getString("First_Name");
						lname = po.getString("Last_Name");
						idnumber = po.getString("ID_Number");
						gender = po.getString("Gender");
						mobile = po.getString("Mobile_Number");
						email = po.getString("Email_Address");
						bundle();
						dialog.dismiss();

					} else {
						dialog.dismiss();
						Toast.makeText(getApplicationContext(), e.toString(),
								Toast.LENGTH_LONG).show();
					}

				}
			});

			return null;
		}

	}

	private void bundle() {
		// TODO Auto-generated method stub
		// Send the user details to Confirm.java and Password\
		i = new Intent(SignUp.this, Confirm.class);
		Bundle b = new Bundle();
		b.putString("fname", fname);
		b.putString("lname", lname);
		b.putString("idnumber", idnumber);
		b.putString("gender", gender);
		b.putString("mobile", mobile);
		b.putString("email", email);
		i.putExtras(b);
		startActivity(i);

	}

}
