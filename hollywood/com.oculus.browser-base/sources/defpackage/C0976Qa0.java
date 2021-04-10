package defpackage;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* renamed from: Qa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0976Qa0 implements WV, XV, AbstractC0489Ia0, AbstractC0732Ma0 {

    /* renamed from: a  reason: collision with root package name */
    public final YV f8770a;
    public C3872nI1 b = AbstractC1037Ra0.d;
    public boolean c;
    public LocationRequest d;

    public C0976Qa0(Context context) {
        AbstractC1220Ua0.d("LocationProvider", "Google Play Services", new Object[0]);
        VV vv = new VV(context);
        C2470f7 f7Var = AbstractC1037Ra0.c;
        SE0.i(f7Var, "Api must not be null");
        vv.g.put(f7Var, null);
        Objects.requireNonNull(f7Var.f9899a);
        List emptyList = Collections.emptyList();
        vv.b.addAll(emptyList);
        vv.f9088a.addAll(emptyList);
        SE0.i(this, "Listener must not be null");
        vv.l.add(this);
        SE0.i(this, "Listener must not be null");
        vv.m.add(this);
        this.f8770a = vv.a();
    }

    @Override // defpackage.AbstractC0732Ma0
    public void a(boolean z) {
        Object obj = ThreadUtils.f10596a;
        if (this.f8770a.i()) {
            this.f8770a.e();
        }
        this.c = z;
        this.f8770a.d();
    }

    @Override // defpackage.AbstractC0482Hx
    public void c(int i) {
    }

    @Override // defpackage.AbstractC0777Ms0
    public void e0(ConnectionResult connectionResult) {
        StringBuilder i = AbstractC2531fV.i("Failed to connect to Google Play Services: ");
        i.append(connectionResult.toString());
        LocationProviderAdapter.a(i.toString());
    }

    @Override // defpackage.AbstractC0482Hx
    public void f(Bundle bundle) {
        Location location;
        LocationManager locationManager;
        Object obj = ThreadUtils.f10596a;
        LocationRequest locationRequest = new LocationRequest();
        this.d = locationRequest;
        boolean z = true;
        if (this.c) {
            locationRequest.A(100);
            locationRequest.x(500);
        } else {
            Objects.requireNonNull(C1159Ta0.a());
            Context applicationContext = ContextUtils.getApplicationContext();
            if (Build.VERSION.SDK_INT < 28 ? Settings.Secure.getInt(applicationContext.getContentResolver(), "location_mode", 0) == 1 : !((locationManager = (LocationManager) applicationContext.getSystemService("location")) == null || !C4179p7.c(locationManager) || !locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network"))) {
                this.d.A(100);
            } else {
                this.d.A(102);
            }
            this.d.x(1000);
        }
        C3872nI1 ni1 = this.b;
        YV yv = this.f8770a;
        Objects.requireNonNull(ni1);
        SE0.b(yv != null, "GoogleApiClient parameter is required.");
        C3176jE1 je1 = (C3176jE1) yv.g(AbstractC1037Ra0.f8840a);
        if (je1 == null) {
            z = false;
        }
        SE0.k(z, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        try {
            location = je1.z();
        } catch (Exception unused) {
            location = null;
        }
        if (location != null) {
            LocationProviderAdapter.b(location);
        }
        try {
            C3872nI1 ni12 = this.b;
            YV yv2 = this.f8770a;
            LocationRequest locationRequest2 = this.d;
            Looper c2 = ThreadUtils.c();
            Objects.requireNonNull(ni12);
            yv2.f(new C1645aJ1(yv2, locationRequest2, this, c2));
        } catch (IllegalStateException | SecurityException e) {
            AbstractC1220Ua0.a("LocationProvider", " mLocationProviderApi.requestLocationUpdates() " + e, new Object[0]);
            LocationProviderAdapter.a("Failed to request location updates: " + e.toString());
        }
    }

    @Override // defpackage.AbstractC0732Ma0
    public void stop() {
        Object obj = ThreadUtils.f10596a;
        if (this.f8770a.i()) {
            C3872nI1 ni1 = this.b;
            YV yv = this.f8770a;
            Objects.requireNonNull(ni1);
            yv.f(new C4046oJ1(yv, this));
            this.f8770a.e();
        }
    }
}
