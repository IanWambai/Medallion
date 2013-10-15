package com.medallion.functions;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import com.medallion.R;
import com.medallion.fragments.Home;

public class WidgetConfig extends Activity implements OnClickListener {

	EditText info;
	AppWidgetManager awm;
	Context context;
	int awid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetconfig);
		Button but = (Button) findViewById(R.id.bwidgetconfig);
		but.setOnClickListener(this);

		context = WidgetConfig.this;

		//Getting info about widget that launched this activity
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if (extras != null) {
			awid = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}else{
			finish();
		}
		awm=AppWidgetManager.getInstance(context);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String e="The Data";
		
		RemoteViews v=new RemoteViews(context.getPackageName(),R.layout.widgetlayout);
		v.setTextViewText(R.id.tvconfiginput, e);
		
		Intent in=new Intent(getApplicationContext(), Home.class);
		PendingIntent pi=PendingIntent.getActivity(context, 0, in, 0);	
		v.setOnClickPendingIntent(R.id.bwidgetopen, pi);
		
		awm.updateAppWidget(awid, v);
		
		Intent result=new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awid);
		setResult(RESULT_OK, result);
		
		finish();
	}

}
