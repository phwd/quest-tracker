package androidx.core.location;

import android.location.LocationManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.facebook.graphservice.interfaces.Summary;

public final class LocationManagerCompat {
    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.isLocationEnabled();
        }
        return locationManager.isProviderEnabled(Summary.Source.NETWORK) || locationManager.isProviderEnabled("gps");
    }

    private LocationManagerCompat() {
    }
}
