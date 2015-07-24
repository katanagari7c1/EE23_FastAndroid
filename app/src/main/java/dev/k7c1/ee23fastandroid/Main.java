package dev.k7c1.ee23fastandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.qozix.tileview.TileView;
import com.software.shell.fab.ActionButton;


public class Main extends ActionBarActivity {

    private TileView tileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileView = (TileView)findViewById(R.id.tile_view);
        tileView.setSize(2048,1280);
        tileView.addDetailLevel(1.0f, "tiles/partyplace/1000/%row%_%col%.png");
        //tileView.setScale(0.5);

        setupFAB();

    }

    private void setupFAB() {
        ActionButton fab = (ActionButton) findViewById(R.id.action_button);
        fab.setType(ActionButton.Type.DEFAULT);
        fab.setButtonColor(getResources().getColor(R.color.orange));
        fab.setButtonColorPressed(getResources().getColor(R.color.orange_dark));
        fab.setImageResource(R.drawable.ic_pin_drop_white_24dp);
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
}
