package dev.k7c1.ee23fastandroid;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerEventListener;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.Collection;


public class Main extends ActionBarActivity {

    private ToolTipRelativeLayout tooltipLayout;
    private TileView tileView;
    private ActionButton fab;
    private boolean savePointMode = false;

    private Collection<Marker> markers;
    private Marker tmpMarker = null;
    private ToolTipView shownTooltip = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.markers = new ArrayList<>();

        setContentView(R.layout.activity_main);

        tileView = (TileView)findViewById(R.id.tile_view);
        tooltipLayout = (ToolTipRelativeLayout)findViewById(R.id.tooltip_layout);

        initializeTileView();
        setupFAB();

    }

    private void initializeTileView() {
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
                    tmpMarker = new Marker();
                    tmpMarker.x = i;
                    tmpMarker.y = i1;
                    showInsertNameDialog();
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
                if (shownTooltip != null) {
                    shownTooltip.remove();
                }
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

        tileView.addMarkerEventListener(new MarkerEventListener() {
            @Override
            public void onMarkerTap(View view, int i, int i1) {
                if (shownTooltip != null) {
                    shownTooltip.remove();
                }

                Marker marker = findMarkerFromView(view);

                if (marker != null) {
                    ToolTip tooltip = new ToolTip()
                            .withText(marker.name)
                            .withColor(getResources().getColor(R.color.white))
                            .withAnimationType(ToolTip.AnimationType.FROM_TOP)
                            .withShadow();

                    shownTooltip = tooltipLayout.showToolTipForView(tooltip, view);
                }
            }
        });

        tileView.setScaleLimits(0, 2);
        tileView.setScale(0.5);
    }

    private Marker findMarkerFromView(View view) {
        for (Marker marker : this.markers) {
            if (marker.view.equals(view)) {
                return marker;
            }
        }

        return null;
    }

    private void showInsertNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿cómo lo vas a llamar?");

        final EditText input = new EditText(this);

        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showFab();
                tmpMarker.name = input.getText().toString();
                tmpMarker.insert(tileView);

                markers.add(tmpMarker);
                tmpMarker = null;
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showFab();
                tmpMarker = null;
            }
        });

        builder.show();
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
