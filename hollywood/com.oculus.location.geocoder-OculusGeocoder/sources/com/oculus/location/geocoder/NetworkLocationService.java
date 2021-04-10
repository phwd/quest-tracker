package com.oculus.location.geocoder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NetworkLocationService extends Service {
    private static final String TAG = NetworkLocationService.class.getSimpleName();
    private NetworkLocationProvider mProvider;

    public IBinder onBind(Intent intent) {
        return getProvider().getBinder();
    }

    public boolean onUnbind(Intent intent) {
        NetworkLocationProvider provider = getProvider();
        if (provider != null) {
            provider.onDisable();
        }
        this.mProvider = null;
        return super.onUnbind(intent);
    }

    private NetworkLocationProvider getProvider() {
        if (this.mProvider == null) {
            this.mProvider = new NetworkLocationProvider(this);
        }
        return this.mProvider;
    }
}
