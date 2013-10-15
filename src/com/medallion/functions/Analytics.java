package com.medallion.functions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.medallion.R;
import com.medallion.graphs.BarGraph;
import com.medallion.graphs.LineGraph;

public class Analytics extends SherlockActivity implements OnClickListener {

	Button line, bar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.analytics);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		line = (Button) findViewById(R.id.b_plot_line_graph);
		line.setOnClickListener(this);
		
		bar = (Button) findViewById(R.id.b_plot_bar_graph);
		bar.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.b_plot_line_graph:
			LineGraph line = new LineGraph();
			Intent lineintent = line.getIntent(this);
			startActivity(lineintent);
			break;
		case R.id.b_plot_bar_graph:
			BarGraph bar = new BarGraph();
			Intent barintent = bar.getIntent(this);
			startActivity(barintent);
			break;
		}
	}
}
