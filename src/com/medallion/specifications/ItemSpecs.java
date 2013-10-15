package com.medallion.specifications;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ItemSpecs extends SherlockActivity {
	
	int drugSelected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Parse.initialize(getApplicationContext(),
				"9srShDsfwTBTz2YvZxUgn54ajlF8tJiakIMnjNQM",
				"uPo0AHDOeExNlS0a5FrWGzEsatB93TqrgpAfRYTE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemspecs);
		
		Bundle b = getIntent().getExtras();
		drugSelected= b.getInt("drugSelected");
		 
	retrieveDrugDetails();
	}

	public void retrieveDrugDetails(){
		
		
		ParseQuery<ParseObject> pq = ParseQuery.getQuery("StockTypes");
		pq.setLimit(1000);
		pq.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> drugs, ParseException e) {

				if (e == null) {

					ParseObject po = drugs.get(drugSelected);
					String drugName = po.getString("Name");
					String strength = po.getString("Strength");
					String location = po.getString("Location");
					String category = po.getString("Category");
					String unit=po.getString("Unit");
					int price=po.getInt("salesPriceKsh");
					String uses= po.getString("Uses");

					Toast.makeText(getApplicationContext(), drugName+"\n"+strength+"\n"+location+"\n"+category+"\n"+unit+"\n"+price+"\n"+uses+"\n", Toast.LENGTH_LONG).show();
				} else {

				}

			}
		});
		
	}
}
