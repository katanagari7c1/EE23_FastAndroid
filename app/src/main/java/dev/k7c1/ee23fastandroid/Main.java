package dev.k7c1.ee23fastandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerEventListener;
import com.software.shell.fab.ActionButton;


public class Main extends ActionBarActivity {

    private TileView tileView;
    private ActionButton fab;
    private boolean savePointMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileView = (TileView)findViewById(R.id.tile_view);
        tileView.setCacheEnabled(false);
        tileView.setSize(2400, 1800);
        tileView.addDetailLevel(1.0f, "tiles/partyplace/1000/%row%_%col%.png", "samples/partyplace.png", 600, 600);

        tileView.addTileViewEventListener(new TileView.TileViewEventListener() {
            @Override
            public void onFingerDown(int i, int i1) {

            }

            @Override
            public void onFingerUp(int i, int i1) {

            }

            @Override
            public void onDrag(int i, int i1) {

            }

            @Override
            public void onDoubleTap(int i, int i1) {

            }

            @Override
            public void onTap(int i, int i1) {
                if (savePointMode) {
                    showFab();
                    placeMarker(R.drawable.mark, i, i1);
                }

            }

            @Override
            public void onPinch(int i, int i1) {

            }

            @Override
            public void onPinchStart(int i, int i1) {

            }

            @Override
            public void onPinchComplete(int i, int i1) {

            }

            @Override
            public void onFling(int i, int i1, int i2, int i3) {

            }

            @Override
            public void onFlingComplete(int i, int i1) {

            }

            @Override
            public void onScaleChanged(double v) {

            }

            @Override
            public void onScrollChanged(int i, int i1) {

            }

            @Override
            public void onZoomStart(double v) {

            }

            @Override
            public void onZoomComplete(double v) {

            }

            @Override
            public void onDetailLevelChanged() {

            }

            @Override
            public void onRenderStart() {

            }

            @Override
            public void onRenderComplete() {

            }
        });

        tileView.setScaleLimits(0, 2);
        tileView.setScale(0.5);

        setupFAB();

    }

    private void showFab() {
        fab.show();
        fab.playShowAnimation();
        savePointMode = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        tileView.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tileView.destroy();
    }

    private void setupFAB() {
        fab = (ActionButton) findViewById(R.id.action_button);
        fab.setType(ActionButton.Type.DEFAULT);
        fab.setButtonColor(getResources().getColor(R.color.orange));
        fab.setButtonColorPressed(getResources().getColor(R.color.orange_dark));
        fab.setImageResource(R.drawable.ic_pin_drop_white_24dp);

        fab.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fab.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActionButton) v).hide();
                ((ActionButton) v).playHideAnimation();
                setSavePointMode();
            }
        });
    }

    private void setSavePointMode() {
        savePointMode = true;
        Toast.makeText(this, "Toca en donde quieras dejar la marca", Toast.LENGTH_SHORT).show();
    }

    private void placeMarker( int resId, double x, double y ) {
        ImageView imageView = new ImageView( this );
        imageView.setImageResource(resId);
        double scale = tileView.getScale();
        tileView.addMarker(imageView, x/scale, y/scale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (savePointMode) {
            showFab();
        }
        else {
            super.onBackPressed();
        }
    }
}
