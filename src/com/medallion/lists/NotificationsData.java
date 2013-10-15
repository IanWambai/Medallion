package com.medallion.lists;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.medallion.R;
import com.medallion.functions.ActionItem;
import com.medallion.functions.QuickAction;
import com.medallion.specifications.NotificationSpecs;

public class NotificationsData extends SherlockListActivity {

	private static final int ID_INFO = 1;
	private static final int ID_EDIT = 2;
	private static final int ID_DELETE = 3;

	Intent i;

	// Do the logic shit to make a notification string
	// Get the notification name and put it in an array list. Then on click (in
	// the
	// Quick Action)get
	// all the data about the notification in all the other fieds of the drug
	// and send
	// them to NotificationSpecs. Create TextViews and display the data in those
	// textviews with appropriate labels
	// Create the listview in notifications_data.xml

	QuickAction quickAction;
	// Warnings here

	String[] notifications = {
			"Hydrogen Peroxide - 200ml: Quantity on hand in the clinic is 3 vials less than the required threshold amount of 5 vials",
			"Duo-cotecxin - 400mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Calvlon Antiseptic - 5 litres: Quantity on hand in the clinic is 17 litres less than the required threshold amount of 30 litres",
			"Cotrimaxazole- 480 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Furosemide Injection - 2 ml/20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Triamcinolone Acetonide Ointment USP - 80 gm: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Paracetamol Suspension - 100 ml: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Povidone Iodine - 10% / Liter: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Paracetamol Suppossitory - 250mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Loperamide - 2 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Piroxicam - 20 mg: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"Metoclopramide Injection - 2 ml: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units",
			"KY Jelly - 42 gm: Quantity on hand in the clinic is 17 units less than the required threshold amount of 30 units" };
	ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_item, R.id.tvcustom, notifications));

		// Add header to Parse listView
		// listView1 = (ListView) findViewById(R.id.listView1);
		//
		// View header = (View) getLayoutInflater().inflate(
		// R.layout.listview_header_row, null);
		// listView1.addHeaderView(header);

		quickaction();

	}

	private void quickaction() {
		// TODO Auto-generated method stub
		ActionItem info = new ActionItem(ID_INFO, "Info", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem edit = new ActionItem(ID_EDIT, "Edit", getResources()
				.getDrawable(R.drawable.ic_launcher));
		ActionItem delete = new ActionItem(ID_DELETE, "Delete", getResources()
				.getDrawable(R.drawable.ic_launcher));

		info.setSticky(true);
		edit.setSticky(true);
		delete.setSticky(true);
		quickAction = new QuickAction(this, QuickAction.HORIZONTAL);

		// add action items into QuickAction
		quickAction.addActionItem(info);
		quickAction.addActionItem(edit);
		quickAction.addActionItem(delete);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						switch (actionId) {
						case ID_INFO:
							i = new Intent(getApplicationContext(),
									NotificationSpecs.class);
							startActivity(i);
							break;
						case ID_EDIT:
							i = new Intent(getApplicationContext(),
									NotificationSpecs.class);
							startActivity(i);
							break;
						case ID_DELETE:
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									NotificationsData.this);
							alertDialog.setTitle("Delete?");
							alertDialog
									.setMessage("Are you sure you want to delete?");
							alertDialog
									.setIcon(android.R.drawable.ic_lock_power_off);
							alertDialog.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											Toast.makeText(
													getApplicationContext(),
													"Deleted",
													Toast.LENGTH_SHORT).show();
										}
									});
							alertDialog.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							alertDialog.show();
							break;

						}
					}
				});

		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		quickAction.show(v);
	}

}
