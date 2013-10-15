package com.medallion.forms;

import com.medallion.R;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button signup, signin;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		signup = (Button) findViewById(R.id.bsignup);
		signin = (Button) findViewById(R.id.bsignin);

		signup.setOnClickListener(this);
		signin.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bsignup:
			Toast.makeText(getApplicationContext(), "Sign Up",
					Toast.LENGTH_SHORT).show();
			i = new Intent(getApplicationContext(), SignUp.class);
			startActivity(i);
			break;
		case R.id.bsignin:
			Toast.makeText(getApplicationContext(), "Sign In",
					Toast.LENGTH_SHORT).show();
			i = new Intent(getApplicationContext(), SignIn.class);
			startActivity(i);
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.mlogout:
			ParseUser.logOut();
			break;
		case R.id.mexit:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					MainActivity.this);
			alertDialog.setTitle("Exit?");
			alertDialog.setMessage("Are you sure you want to exit?");
			alertDialog.setIcon(android.R.drawable.ic_lock_power_off);
			alertDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							android.os.Process.killProcess(android.os.Process
									.myPid());
						}
					});
			alertDialog.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			alertDialog.show();
			break;
		}
		return false;
	}

}
