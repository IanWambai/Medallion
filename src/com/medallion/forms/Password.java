package com.medallion.forms;

import com.medallion.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends Activity implements OnClickListener {
	String pass, username, confirmationpass;
	EditText password, confirm;
	Button finish;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Parse.initialize(getApplicationContext(),
				"QOYn8beuGCf7QRsQXQPdhf6zwtNTDPUsSQ7ktWeS",
				"bZR6V8alLCYOjt0o5ZoFDo9yBUJWB9UslvdkjNyP");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		setup();
		getdata();
	}

	private void setup() {
		// TODO Auto-generated method stub
		password = (EditText) findViewById(R.id.etpassword);
		confirm = (EditText) findViewById(R.id.etconfirmpassword);
		finish = (Button) findViewById(R.id.bfinish);

		finish.setOnClickListener(this);
	}

	private void getdata() {
		// TODO Auto-generated method stub
		// Get data from sign up

		pass = password.getText().toString();
		confirmationpass = confirm.getText().toString();
		Bundle b = getIntent().getExtras();
		username = b.getString("idnumber");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bfinish:
			// Sign up process
			getdata();
			signUpToService();
			i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			Toast.makeText(getApplicationContext(),
					"You are now signed up. You may login now",
					Toast.LENGTH_LONG).show();
			break;
		}
	}

	public void signUpToService() {

		if (pass.equals(confirmationpass)) {

			ParseUser user = new ParseUser();
			user.setUsername(username);
			user.setPassword(pass);

			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					if (e == null) {
						// Hooray! Let them use the app now.
						Toast.makeText(getApplicationContext(),
								"sign up successful. u can now log in",
								Toast.LENGTH_LONG).show();
						Intent y = new Intent(Password.this, SignIn.class);
						startActivity(y);

					} else {
						Toast.makeText(getApplicationContext(), e.toString(),
								Toast.LENGTH_LONG).show();
					}
				}
			});

		} else {

			Toast.makeText(getApplicationContext(), "try again",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
