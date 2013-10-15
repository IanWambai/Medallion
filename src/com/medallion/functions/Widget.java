package com.medallion.functions;

import com.medallion.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Widget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appwidgetid = appWidgetIds[i];
			RemoteViews rview = new RemoteViews(context.getPackageName(),
					R.layout.widgetlayout);
			//RemotViews is how to set the TextViews
			rview.setTextViewText(R.id.tvwidgetupdate, "Theory of Everything");
			appWidgetManager.updateAppWidget(appwidgetid, rview);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Medallion widget deleted", Toast.LENGTH_LONG).show();
	}
}
