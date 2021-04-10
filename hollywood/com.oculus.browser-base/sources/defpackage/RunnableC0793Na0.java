package defpackage;

import org.chromium.device.geolocation.LocationProviderAdapter;

/* renamed from: Na0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0793Na0 implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ LocationProviderAdapter G;

    public RunnableC0793Na0(LocationProviderAdapter locationProviderAdapter, boolean z) {
        this.G = locationProviderAdapter;
        this.F = z;
    }

    public void run() {
        this.G.f10955a.a(this.F);
    }
}
