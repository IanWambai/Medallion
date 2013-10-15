package com.medallion.forms;

import java.util.List;

import com.medallion.R;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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

public class SignIn extends Activity implements OnClickListener {
	EditText idnumber, password;
	Button signin;
	Intent i;
	String id, pass, personCapacity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"QOYn8beuGCf7QRsQXQPdhf6zwtNTDPUsSQ7ktWeS",
				"bZR6V8alLCYOjt0o5ZoFDo9yBUJWB9UslvdkjNyP");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		signin = (Button) findViewById(R.id.binsignin);
		idnumber = (EditText) findViewById(R.id.etinid);
		password = (EditText) findViewById(R.id.etinpassword);
		signin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.binsignin:
			getData();
			AsyncTask<String, Void, Boolean> task = new ProgressTask(this);
			task.execute();
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	public class ProgressTask extends AsyncTask<String, Void, Boolean> {

		private Activity activity;
		private ProgressDialog dialog;

		public ProgressTask(Activity activity) {
			this.activity = activity;
			dialog = new ProgressDialog(SignIn.this);
		}

		protected void onPreExecute() {
			this.dialog.setTitle("Sign In");
			this.dialog.setMessage("Signing you in...");
			this.dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... arg0) {
			// TODO Auto-generated method stub

			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
				// Determine role
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Staff");
				query.whereEqualTo("ID_Number", id);
				query.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> rol, ParseException e) {
						if (e == null) {

							ParseObject post = rol.get(0);

							personCapacity = post.getString("Capacity");

							Toast.makeText(getApplicationContext(),
									personCapacity, Toast.LENGTH_LONG).show();

							if (personCapacity.equalsIgnoreCase("Doctor")) {

//								i = new Intent(SignIn.this, Provider.class);
//								dialog.dismiss();
//								startActivity(i);
							} else if (personCapacity
									.equalsIgnoreCase("Administrator")) {

//								i = new Intent(SignIn.this, Administrator.class);
//								dialog.dismiss();
//								startActivity(i);

							} else {
//								i = new Intent(SignIn.this, Desk.class);
//								dialog.dismiss();
//								startActivity(i);

							}

						} else {
							dialog.dismiss();
							Toast.makeText(getApplicationContext(),
									e.toString(), Toast.LENGTH_LONG).show();
						}
					}
				});

			} else {
				// Login user
				ParseUser.logInInBackground(id, pass, new LogInCallback() {
					public void done(ParseUser user, ParseException e) {
						if (user != null && e == null) {
							ParseQuery<ParseObject> query = ParseQuery
									.getQuery("Staff");
							query.whereEqualTo("ID_Number", id);
							query.findInBackground(new FindCallback<ParseObject>() {
								@Override
								public void done(List<ParseObject> rol,
										ParseException e) {
									if (e == null) {

										ParseObject post = rol.get(0);

										personCapacity = post
												.getString("Capacity");

										Toast.makeText(getApplicationContext(),
												personCapacity,
												Toast.LENGTH_LONG).show();

										if (personCapacity
												.equalsIgnoreCase("Doctor")) {

//											i = new Intent(SignIn.this,
//													Provider.class);
//											dialog.dismiss();
//											startActivity(i);
										} else if (personCapacity
												.equalsIgnoreCase("Administrator")) {

//											i = new Intent(SignIn.this,
//													Administrator.class);
//											dialog.dismiss();
//											startActivity(i);

										} else {
//											i = new Intent(SignIn.this,
//													Desk.class);
//											dialog.dismiss();
//											startActivity(i);

										}

									} else {
										dialog.dismiss();
										Toast.makeText(getApplicationContext(),
												e.toString(), Toast.LENGTH_LONG)
												.show();
									}
								}
							});
						} else if (user == null && e != null) {
							dialog.dismiss();
							idnumber.setError("Please enter the ID Number");
							password.setError("Please enter the password");
						} else if (user == null && pass != null) {
							dialog.dismiss();
							idnumber.setError("Please enter the ID Number");

						} else if (user != null && pass == null) {
							dialog.dismiss();
							password.setError("Please enter the password");
						} else {
							dialog.dismiss();
							Toast.makeText(getApplicationContext(),
									e.toString(), Toast.LENGTH_LONG).show();
						}
					}
				});
			}

			return null;
		}

	}

	private void getData() {
		id = idnumber.getText().toString();
		if (id == null) {
			idnumber.setError("Please enter the ID Nmuber");
		}
		pass = password.getText().toString();
		if (pass == null) {
			password.setError("Please enter the password");
		}
	}

}
