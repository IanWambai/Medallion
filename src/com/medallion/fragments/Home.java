package com.medallion.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.medallion.R;
import com.medallion.functions.MenuListAdapter;
import com.medallion.functions.Preferences;
import com.medallion.utility.About;
import com.medallion.utility.Help;
import com.parse.Parse;
import com.parse.ParseUser;

public class Home extends SherlockFragmentActivity implements
		OnDrawerCloseListener, OnDrawerOpenListener, OnClickListener {

	
	int loginConfirmation=1;
	// Declare Variable
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Intent i;
	Fragment fragment1 = new Notifications();
	Fragment fragment2 = new CurrentStock();
	Fragment fragment3 = new TransactionHistory();
	Fragment fragment4 = new StockProjections();
	Fragment fragment5 = new Suppliers();
	Fragment fragment6 = new ItemTypes();
	Fragment fragment7 = new Prescribe();
	Fragment fragment8 = new Users();
	SlidingDrawer sd;
	Button handle, help, about, review;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);
		
		
		setup();

		// Generate title
		title = new String[] { "Notifications", "Current Stock",
				"Transaction History", "Stock Projections", "Suppliers",
				"Item Types", "Prescribe", "Users" };

		// Generate subtitle
		subtitle = new String[] { "Your database's status", "What you have",
				"What you've done", "The future", "Your suppliers",
				"Your items", "Provide an item", "The personnel" };

		// Generate icon
		icon = new int[] { android.R.drawable.ic_menu_view,
				android.R.drawable.ic_menu_compass,
				android.R.drawable.ic_menu_manage,
				android.R.drawable.ic_menu_compass,
				android.R.drawable.ic_menu_view,
				android.R.drawable.ic_menu_compass,
				android.R.drawable.ic_menu_manage,
				android.R.drawable.ic_menu_view };

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass results to MenuListAdapter Class
		mMenuAdapter = new MenuListAdapter(this, title, subtitle, icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture button clicks on side menu
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	private void setup() {
		// TODO Auto-generated method stub
		sd = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		sd.setOnDrawerCloseListener(this);
		sd.setOnDrawerOpenListener(this);
		handle = (Button) findViewById(R.id.handle);
		help = (Button) findViewById(R.id.bhelp);
		about = (Button) findViewById(R.id.babout);
		review = (Button) findViewById(R.id.breview);

		help.setOnClickListener(this);
		about.setOnClickListener(this);
		review.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;
		case R.id.msettings:
			Toast.makeText(getApplicationContext(), "Settings",
					Toast.LENGTH_SHORT).show();
			i = new Intent(getApplicationContext(), Preferences.class);
			startActivity(i);
			break;
		case R.id.mlogout:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
			alertDialog.setTitle("Sign Out?");
			alertDialog.setMessage("Are you sure you want to logout?");
			alertDialog.setIcon(android.R.drawable.ic_menu_revert);
			alertDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Logout process
							Toast.makeText(getApplicationContext(),
									"Signed Out", Toast.LENGTH_SHORT).show();
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
		case R.id.mexit:
			AlertDialog.Builder ealertDialog = new AlertDialog.Builder(
					Home.this);
			ealertDialog.setTitle("Exit?");
			ealertDialog.setMessage("Are you sure you want to exit?");
			ealertDialog.setIcon(android.R.drawable.ic_lock_power_off);
			ealertDialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							android.os.Process.killProcess(android.os.Process
									.myPid());
						}
					});
			ealertDialog.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			ealertDialog.show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			//checkUserStatus();
			
			ft.replace(R.id.content_frame, fragment1);
			
			break;
		case 1:
			ft.replace(R.id.content_frame, fragment2);
			break;
		case 2:
			ft.replace(R.id.content_frame, fragment3);
			break;
		case 3:
			ft.replace(R.id.content_frame, fragment4);
			break;
		case 4:
			ft.replace(R.id.content_frame, fragment5);
			break;
		case 5:
			ft.replace(R.id.content_frame, fragment6);
			break;
		case 6:
			ft.replace(R.id.content_frame, fragment7);
			break;
		case 7:
			ft.replace(R.id.content_frame, fragment8);
			break;
		}

		ft.commit();
		mDrawerList.setItemChecked(position, true);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
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
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		handle.setBackgroundResource(android.R.drawable.ic_menu_info_details);
	}

	@Override
	public void onDrawerClosed() {
		// TODO Auto-generated method stub
		handle.setBackgroundResource(android.R.drawable.ic_dialog_info);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bhelp:
			i = new Intent(getApplicationContext(), Help.class);
			startActivity(i);
			break;
		case R.id.babout:
			i = new Intent(getApplicationContext(), About.class);
			startActivity(i);
			break;
		case R.id.breview:
			Toast.makeText(getApplicationContext(), "Send to Play Store",
					Toast.LENGTH_LONG).show();
			break;
		}
	}

	public void checkUserStatus(){
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			loginConfirmation=0;
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
			alertDialog.setTitle("PLEASE LOGIN OR SIGNUP");
			alertDialog.setMessage("login / signup");
			alertDialog.setIcon(android.R.drawable.ic_lock_power_off);
			alertDialog.setPositiveButton("SignUp",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							android.os.Process.killProcess(android.os.Process
									.myPid());
						}
					});
			alertDialog.setNegativeButton("Login",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			alertDialog.show();
			checkUserStatus();

		// do stuff with the user
		} else {
		// show the signup or login screen
		}
		
	
		
		
	}
}
