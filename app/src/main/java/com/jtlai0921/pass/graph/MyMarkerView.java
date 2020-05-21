
package com.jtlai0921.pass.graph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.jtlai0921.pass.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {

    private final TextView tvContent;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    /**
     * This method enables a specified custom MarkerView to update it's content
     * everytime the MarkerView is redrawn.
     *
     * @param e            The Entry the MarkerView belongs to. This can also be any
     *                     subclass of Entry, like BarEntry or CandleEntry, simply cast
     *                     it at runtime.
     * @param dataSetIndex the index of the DataSet the selected value is in
     */


    /**
     * Use this to return the desired offset you wish the MarkerView to have on
     * the x-axis. By returning -(getWidth() / 2) you will center the MarkerView
     * horizontally.
     *
     * @return
     */


    /**
     * Use this to return the desired position offset you wish the MarkerView to
     * have on the y-axis. By returning -getHeight() you will cause the
     * MarkerView to be above the selected value.
     *
     * @return
     */


    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 1, true));
        } else {

            tvContent.setText(Utils.formatNumber(e.getY(), 1, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
