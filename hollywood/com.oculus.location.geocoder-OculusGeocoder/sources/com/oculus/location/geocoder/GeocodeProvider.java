package com.oculus.location.geocoder;

import android.content.Context;
import android.location.Address;
import android.location.GeocoderParams;
import android.location.IGeocodeProvider;
import android.os.IBinder;
import java.util.List;

public final class GeocodeProvider extends IGeocodeProvider.Stub {
    public GeocodeProvider(Context context) {
    }

    public String getFromLocation(double latitude, double longitude, int maxResults, GeocoderParams params, List<Address> list) {
        return null;
    }

    public String getFromLocationName(String locationName, double lowerLeftLatitude, double lowerLeftLongitude, double upperRightLatitude, double upperRightLongitude, int maxResults, GeocoderParams params, List<Address> list) {
        return null;
    }

    public void startup() {
    }

    public void shutdown() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.oculus.location.geocoder.GeocodeProvider */
    /* JADX WARN: Multi-variable type inference failed */
    public IBinder getBinder() {
        return this;
    }
}
