package org.chromium.device.geolocation;

import J.N;
import android.location.Location;
import java.util.concurrent.FutureTask;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocationProviderAdapter {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC0732Ma0 f10955a;

    public LocationProviderAdapter() {
        AbstractC0732Ma0 ma0 = LocationProviderFactory.f10956a;
        if (ma0 == null) {
            if (!LocationProviderFactory.b || !AbstractC4310pu.a(ContextUtils.getApplicationContext())) {
                LocationProviderFactory.f10956a = new C0915Pa0();
            } else {
                LocationProviderFactory.f10956a = new C0976Qa0(ContextUtils.getApplicationContext());
            }
            ma0 = LocationProviderFactory.f10956a;
        }
        this.f10955a = ma0;
    }

    public static void a(String str) {
        AbstractC1220Ua0.a("LocationProvider", "newErrorAvailable %s", str);
        N.M8Iz7Ptw(str);
    }

    public static void b(Location location) {
        N.MvJnRjJi(location.getLatitude(), location.getLongitude(), ((double) location.getTime()) / 1000.0d, location.hasAltitude(), location.getAltitude(), location.hasAccuracy(), (double) location.getAccuracy(), location.hasBearing(), (double) location.getBearing(), location.hasSpeed(), (double) location.getSpeed());
    }

    public static LocationProviderAdapter create() {
        return new LocationProviderAdapter();
    }

    public void start(boolean z) {
        ThreadUtils.f(new FutureTask(new RunnableC0793Na0(this, z), null));
    }

    public void stop() {
        ThreadUtils.f(new FutureTask(new RunnableC0854Oa0(this), null));
    }
}
