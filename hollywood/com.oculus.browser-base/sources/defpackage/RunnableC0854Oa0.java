package defpackage;

import org.chromium.device.geolocation.LocationProviderAdapter;

/* renamed from: Oa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0854Oa0 implements Runnable {
    public final /* synthetic */ LocationProviderAdapter F;

    public RunnableC0854Oa0(LocationProviderAdapter locationProviderAdapter) {
        this.F = locationProviderAdapter;
    }

    public void run() {
        this.F.f10955a.stop();
    }
}
