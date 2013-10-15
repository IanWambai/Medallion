package com.medallion.specifications;

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

public class StockSpecs extends SherlockActivity {
	int drugSelected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stockspecs);
		
		Bundle b = getIntent().getExtras();
		drugSelected= b.getInt("drugSelected");
		 
	retrieveDrugDetails();
	}

	public void retrieveDrugDetails(){
		
		
		ParseQuery<ParseObject> pq = ParseQuery.getQuery("Drugs");
		pq.setLimit(1000);
		pq.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> drugs, ParseException e) {

				if (e == null) {

					ParseObject po = drugs.get(drugSelected);
					String drugName = po.getString("stockType");
					int avgCost = po.getInt("averageCostPerUnit");
					int requiredThreshold = po.getInt("requiredThreshold");
					int salesPrice=po.getInt("salesPricePerUnit");
					String unit=po.getString("Unit");
					int quantityOnHand=po.getInt("quantityOnHand");
					int price=po.getInt("salesPriceKsh");
					

					Toast.makeText(getApplicationContext(), drugName+"\n"+avgCost+"\n"+requiredThreshold+"\n"+salesPrice+"\n"+unit+"\n"+price+"\n"+quantityOnHand+"\n", Toast.LENGTH_LONG).show();
				} else {

				}

			}
		});
		
		
		
		
	}
}
