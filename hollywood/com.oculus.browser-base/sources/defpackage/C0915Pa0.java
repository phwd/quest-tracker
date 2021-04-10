package defpackage;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* renamed from: Pa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0915Pa0 implements LocationListener, AbstractC0732Ma0 {

    /* renamed from: a  reason: collision with root package name */
    public LocationManager f8699a;
    public boolean b;

    @Override // defpackage.AbstractC0732Ma0
    public void a(boolean z) {
        boolean z2;
        Object obj = ThreadUtils.f10596a;
        b();
        if (this.f8699a == null) {
            LocationManager locationManager = (LocationManager) ContextUtils.getApplicationContext().getSystemService("location");
            this.f8699a = locationManager;
            if (locationManager == null) {
                AbstractC1220Ua0.a("LocationProvider", "Could not get location manager.", new Object[0]);
            }
        }
        List<String> providers = this.f8699a.getProviders(true);
        if (!(providers != null && providers.size() == 1 && providers.get(0).equals("passive"))) {
            z2 = false;
        } else {
            Location lastKnownLocation = this.f8699a.getLastKnownLocation("passive");
            if (lastKnownLocation != null) {
                LocationProviderAdapter.b(lastKnownLocation);
            }
            z2 = true;
        }
        if (!z2) {
            this.b = true;
            try {
                Criteria criteria = new Criteria();
                if (z) {
                    criteria.setAccuracy(1);
                }
                this.f8699a.requestLocationUpdates(0, 0.0f, criteria, this, ThreadUtils.c());
            } catch (SecurityException unused) {
                AbstractC1220Ua0.a("LocationProvider", "Caught security exception while registering for location updates from the system. The application does not have sufficient geolocation permissions.", new Object[0]);
                b();
                LocationProviderAdapter.a("application does not have sufficient geolocation permissions.");
            } catch (IllegalArgumentException unused2) {
                AbstractC1220Ua0.a("LocationProvider", "Caught IllegalArgumentException registering for location updates.", new Object[0]);
                b();
            }
        }
    }

    public final void b() {
        if (this.b) {
            this.b = false;
            this.f8699a.removeUpdates(this);
        }
    }

    public void onLocationChanged(Location location) {
        if (this.b) {
            LocationProviderAdapter.b(location);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override // defpackage.AbstractC0732Ma0
    public void stop() {
        Object obj = ThreadUtils.f10596a;
        b();
    }
}
