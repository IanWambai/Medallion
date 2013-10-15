package com.medallion.specifications;

import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PrescriptionSpecs extends SherlockActivity {
	
	int prescriptionSelected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prescriptionspecs);
		Bundle b = getIntent().getExtras();
		prescriptionSelected= b.getInt("prescriptionSelected");
	}

	public void retrievePrescriptionData(){
		
		
		ParseQuery<ParseObject> pq = ParseQuery.getQuery("Transactions");
		pq.setLimit(1000);
		pq.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> prescriptions, ParseException e) {

				if (e == null) {

					ParseObject po = prescriptions.get(prescriptionSelected);
					String drugName = po.getString("stockType");
					String patientId=po.getString("patientId");
					int quantity=po.getInt("Quantity");
					String unit=po.getString("Unit");
					int totalCollectedOrPaid=po.getInt("totalCollectedOrPaid;");
					Date transactionDate=po.getDate("transactionDate");
					

					Toast.makeText(getApplicationContext(), drugName+"\n"+patientId+"\n"+quantity+"\n"+unit+"\n"+totalCollectedOrPaid+"\n"+transactionDate.toString()+"\n", Toast.LENGTH_LONG).show();
				} else {

				}

			}
		});
		
		
		
		
		
	}
}
