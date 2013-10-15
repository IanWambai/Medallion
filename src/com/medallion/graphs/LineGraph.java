package com.medallion.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class LineGraph {
	public Intent getIntent(Context context) {

		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] y = { 30, 45, 78, 23, 78, 90, 55, 135, 155, 166 };

		TimeSeries series = new TimeSeries("Drug Outflow");
		for (int i = 0; i < x.length; i++) {
			series.add(x[i], y[i]);

		}
		
		int[] x2 = { 3, 8, 1, 4, 5, 6, 7, 0, 9, 13 };
		int[] y2 = { 1, 5, 33, 45, 55, 52, 188, 100, 50, 200 };

		TimeSeries series2 = new TimeSeries("Patient Inflow");
		for (int i = 0; i < x2.length; i++) {
			series.add(x2[i], y2[i]);

		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.GREEN);
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);
		
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.RED);
		renderer2.setPointStyle(PointStyle.SQUARE);
		renderer2.setFillPoints(true);
		
		
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.setChartTitle("Drug Outflow Chart");

		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				mrenderer, "Drug Outflow Line Graph");

		return intent;
	}
}
