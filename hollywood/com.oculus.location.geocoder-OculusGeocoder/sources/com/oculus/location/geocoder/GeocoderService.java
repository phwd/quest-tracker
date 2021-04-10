package com.oculus.location.geocoder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GeocoderService extends Service {
    private GeocodeProvider mProvider;

    public IBinder onBind(Intent intent) {
        if (this.mProvider == null) {
            this.mProvider = new GeocodeProvider(getApplicationContext());
            this.mProvider.startup();
        }
        return this.mProvider.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        GeocodeProvider geocodeProvider = this.mProvider;
        if (geocodeProvider == null) {
            return false;
        }
        geocodeProvider.shutdown();
        return false;
    }

    public void onDestroy() {
        this.mProvider = null;
    }
}
