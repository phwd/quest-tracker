package org.chromium.chrome.browser.browserservices.permissiondelegation;

import android.net.Uri;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstalledWebappGeolocationBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10623a;
    public final C4649rt0 b;
    public final C2414eo1 c;
    public final Tn1 d = new C20(this);

    public InstalledWebappGeolocationBridge(long j, C4649rt0 rt0, C2414eo1 eo1) {
        this.f10623a = j;
        this.b = rt0;
        this.c = eo1;
    }

    public static InstalledWebappGeolocationBridge create(long j, String str) {
        C4649rt0 a2 = C4649rt0.a(Uri.parse(str));
        if (a2 == null) {
            return null;
        }
        return new InstalledWebappGeolocationBridge(j, a2, AbstractApplicationC3785mq.g().e());
    }

    public void start(boolean z) {
        C2414eo1 eo1 = this.c;
        C4649rt0 rt0 = this.b;
        Tn1 tn1 = this.d;
        Objects.requireNonNull(eo1);
        eo1.b(rt0.f11230a, new C1731ao1(eo1, z, tn1));
    }

    public void stopAndDestroy() {
        this.f10623a = 0;
        C2414eo1 eo1 = this.c;
        C4649rt0 rt0 = this.b;
        Objects.requireNonNull(eo1);
        eo1.b(rt0.f11230a, new C1902bo1(eo1));
    }
}
