package defpackage;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

/* renamed from: jV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3214jV implements LocationListener {

    /* renamed from: a  reason: collision with root package name */
    public final LocationManager f10208a;
    public final Handler b;
    public final Runnable c;
    public boolean d;

    public C3214jV(LocationManager locationManager, AbstractC2873hV hVVar) {
        this.f10208a = locationManager;
        Handler handler = new Handler();
        this.b = handler;
        RunnableC3044iV iVVar = new RunnableC3044iV(this);
        this.c = iVVar;
        handler.postDelayed(iVVar, 60000);
    }

    public void onLocationChanged(Location location) {
        this.b.removeCallbacks(this.c);
        AbstractC3385kV.f10283a = null;
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
