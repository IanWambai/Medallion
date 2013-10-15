package com.medallion.specifications;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import org.joda.time.DateTime;

import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TransactionSpecs extends SherlockActivity {
	TextView transactionSpecs;
	Date transactionDate;
	int totalCollectedOrPaid, quantity;
	Date d;
	int timeRange;
	String u, v, y, z, result = "";
	int x, w;

	String stockType, patientId, unit;
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al1 = new ArrayList<String>();
	ArrayList<Date> al2 = new ArrayList<Date>();
	ArrayList<Integer> al3 = new ArrayList<Integer>();
	ArrayList<Integer> al4 = new ArrayList<Integer>();
	ArrayList<String> al5 = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",

				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transactionspecs);
		transactionSpecs = (TextView) findViewById(R.id.transaction_specs);
		Bundle b = getIntent().getExtras();
		timeRange = b.getInt("timeRange");
		checkTimeRange();
		retrieveTransactionData();

	}

	public void checkTimeRange() {

		switch (timeRange) {

		case 0:
			computeDate();
			break;
		case 1:
			computeWeek();
			break;

		case 2:
			computeMonth();
			break;

		case 3:
			computeYears();
			break;

		case 4:
			computeAllTime();
			break;

		}

	}

	public ArrayList<String> retrieveTransactionData() {

		ParseQuery<ParseObject> ph = ParseQuery.getQuery("Transactions");
		ph.whereGreaterThan("transactionDate", d);
		ph.setLimit(1000);
		ph.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> prescriptionItems,
					ParseException e) {

				if (e == null) {

					for (ParseObject p : prescriptionItems) {

						stockType = p.getString("stockType");
						patientId = p.getString("patientId");
						transactionDate = p.getDate("transactionDate");
						totalCollectedOrPaid = p.getInt("totalCollectedOrPaid");
						quantity = p.getInt("Quantity");
						unit = p.getString("Unit");

						al5.add(unit);
						al.add(stockType);
						al1.add(patientId);
						al2.add(transactionDate);
						al3.add(totalCollectedOrPaid);
						al4.add(quantity);

					}

					for (int i = 0; i < al1.size(); i++) {

						u = al.get(i);
						v = al1.get(i);
						w = al4.get(i);
						x = al3.get(i);
						y = al5.get(i);
						z = al2.get(i).toString();

						result += ("STOCK_TYPE-" + "\t" + u + "\n"
								+ "PATIENT_ID-" + "\t" + v + "\n" + "QUANTITY-"
								+ "\t" + w + "\n" + "TOTAL_COLLECTED/PAID-"
								+ "\t" + x + "\n" + "UNIT-" + "\t" + y + "\n"
								+ "TRANSACTION_DATE-" + "\t" + z + "\n" + "\n");

					}

					transactionSpecs.setText(result);

					/*
					 * find a way to like tabulate this data mazel tov
					 */
				} else {

					Toast.makeText(getApplicationContext(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

			}
		});
		return al;
	}

	public void computeDate() {
		// get current date time with Date()
		Date date = new Date();
		DateTime n = new DateTime(date);
		DateTime f = n.minusDays(1);
		d = f.toDate();
		Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG)
				.show();
	}

	public void computeWeek() {

		Date date = new Date();
		DateTime n = new DateTime(date);
		DateTime f = n.minusWeeks(1);
		d = f.toDate();
		Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG)
				.show();
	}

	public void computeMonth() {

		Date date = new Date();
		DateTime n = new DateTime(date);
		DateTime f = n.minusMonths(1);
		d = f.toDate();
		Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG)
				.show();
	}

	public void computeYears() {

		Date date = new Date();
		DateTime n = new DateTime(date);
		DateTime f = n.minusYears(1);
		d = f.toDate();
		Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG)
				.show();
	}

	public void computeAllTime() {

		Date date = new Date();
		DateTime n = new DateTime(date);
		DateTime f = n.minusYears(43);
		d = f.toDate();
		Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG)
				.show();

	}
}
