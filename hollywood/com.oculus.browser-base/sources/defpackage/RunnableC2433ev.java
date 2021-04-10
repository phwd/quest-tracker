package defpackage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

/* renamed from: ev  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2433ev implements Runnable {
    public final C2604fv F;

    public RunnableC2433ev(C2604fv fvVar) {
        this.F = fvVar;
    }

    public void run() {
        C2604fv fvVar = this.F;
        Objects.requireNonNull(fvVar);
        P21 f0 = P21.f0();
        try {
            new HashSet(fvVar.f9964a.getStringSet("trusted_web_activity_uids", Collections.emptySet()));
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
