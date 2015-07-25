package dev.k7c1.ee23fastandroid;

import android.content.Context;
import android.widget.ImageView;

import com.qozix.tileview.TileView;

/**
 * Created by katanagari7c1 on 25/07/15.
 */
public class Marker {

    public double x, y;
    public String name;

    public void insert(TileView tileView) {
        Context context = tileView.getContext();
        placeMarker(tileView, context, R.drawable.mark, this.x, this.y);
    }

    private void placeMarker(TileView tileView, Context context, int resId, double x, double y) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(resId);
        double scale = tileView.getScale();
        tileView.addMarker(imageView, x/scale, y/scale);
    }

}
