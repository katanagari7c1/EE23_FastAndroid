package dev.k7c1.ee23fastandroid.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dev.k7c1.ee23fastandroid.Marker;
import io.realm.Realm;
import io.realm.RealmResults;

public class MarkerDao {

    Context context;

    public MarkerDao(Context context) {
        this.context = context;
    }

    public void saveMarker(Marker marker) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
            MarkerRealm markerRealm = new MarkerRealm(marker);
            realm.copyToRealm(markerRealm);
        realm.commitTransaction();
    }

    public List<Marker> getAllMarkers() {
        Realm realm = Realm.getInstance(context);
        RealmResults<MarkerRealm> results = realm.allObjects(MarkerRealm.class);

        ArrayList<Marker> output = new ArrayList<>();
        for (MarkerRealm m : results) {
            Marker outMarker = new Marker();
            outMarker.x = m.getX();
            outMarker.y = m.getY();
            outMarker.name = m.getName();

            output.add(outMarker);
        }

        return output;
    }
}
