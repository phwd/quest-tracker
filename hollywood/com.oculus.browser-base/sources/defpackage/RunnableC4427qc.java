package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: qc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4427qc implements Runnable {
    public final Profile F;

    public RunnableC4427qc(Profile profile) {
        this.F = profile;
    }

    public void run() {
        Profile profile = this.F;
        Object obj = ThreadUtils.f10596a;
        Bw1 a2 = Bw1.a();
        Objects.requireNonNull(a2);
        if (C2474f80.f9900a.f()) {
            if (a2.f != null) {
                a2.d(3);
                a2.f.Q(a2.h);
                a2.f.destroy();
                a2.f = null;
                a2.h = null;
            }
            N.M8gRDwVJ(profile);
        }
    }
}
