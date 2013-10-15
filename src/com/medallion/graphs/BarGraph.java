package com.medallion.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BarGraph {
	public Intent getIntent(Context context) {

		int[] y = { 30, 45, 78, 23, 78, 90, 55, 135, 155, 166 };
		CategorySeries series = new CategorySeries("2012");
		for (int i = 0; i < y.length; i++) {
			series.add("Bar " + (i + 1), y[i]);
		}

		int[] y2 = { 78, 47, 68, 33, 22, 10, 20, 70, 179, 150 };
		CategorySeries series2 = new CategorySeries("2013");
		for (int i = 0; i < y2.length; i++) {
			series.add("Bar " + (i + 1), y2[i]);
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setDisplayChartValues(true);
		renderer2.setChartValuesSpacing((float) 0.5);
		renderer2.setColor(Color.CYAN);

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float) 0.5);
		renderer.setColor(Color.YELLOW);

		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.setXTitle("Time");
		mrenderer.setYTitle("Stock Levels");

		Intent intent = ChartFactory.getBarChartIntent(context, dataset,
				mrenderer, Type.DEFAULT);
		return intent;

	}

}
