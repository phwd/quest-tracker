package defpackage;

import android.content.ComponentName;
import java.util.Objects;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;

/* renamed from: Ja0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0550Ja0 implements Runnable {
    public final C0672La0 F;
    public final C4649rt0 G;
    public final ComponentName H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final long f8298J;

    public RunnableC0550Ja0(C0672La0 la0, C4649rt0 rt0, ComponentName componentName, boolean z, long j) {
        this.F = la0;
        this.G = rt0;
        this.H = componentName;
        this.I = z;
        this.f8298J = j;
    }

    public void run() {
        C0672La0 la0 = this.F;
        C4649rt0 rt0 = this.G;
        ComponentName componentName = this.H;
        boolean z = this.I;
        long j = this.f8298J;
        la0.f8425a.d(rt0, componentName.getPackageName(), 5, z);
        Objects.requireNonNull(la0.c);
        AbstractC3100ip1.f10165a.a("TrustedWebActivity.LocationPermissionRequestIsGranted", z);
        InstalledWebappBridge.a(j, z);
    }
}
