package defpackage;

import J.N;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* renamed from: Y41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y41 implements AbstractC3526lH0 {
    public static Y41 F;
    public static boolean G;
    public final ProfileSyncService H;
    public final C2125d51 I;

    public Y41() {
        C3451ks1 ks1;
        C4858t6 c = C4858t6.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        c.i = this;
        ProfileSyncService b = ProfileSyncService.b();
        this.H = b;
        b.a(this);
        synchronized (AbstractC5828yp1.f11702a) {
            Map map = AbstractC5828yp1.b;
            if (map.containsKey("SYNC")) {
                ks1 = (C3451ks1) map.get("SYNC");
            } else {
                throw new IllegalArgumentException("Unknown generator type SYNC");
            }
        }
        Objects.requireNonNull(ks1);
        PU0 pu0 = NU0.f8549a;
        String i = pu0.i("chromium.sync.sessions.id", null);
        if (i == null) {
            i = UUID.randomUUID().toString();
            pu0.p("chromium.sync.sessions.id", i);
        }
        if (i.isEmpty()) {
            AbstractC1220Ua0.a("SyncController", "Unable to get unique tag for sync. This may lead to unexpected tab sync behavior.", new Object[0]);
        } else {
            Objects.requireNonNull(b);
            N.MgBi3zGI(b.e, b, "session_sync" + i);
        }
        C2125d51 d51 = new C2125d51();
        this.I = d51;
        b.a(d51);
        b();
    }

    public final boolean a() {
        if (C4858t6.c().a()) {
            C4858t6 c = C4858t6.c();
            Objects.requireNonNull(c);
            Object obj = ThreadUtils.f10596a;
            if (c.f) {
                return true;
            }
        }
        return false;
    }

    public final void b() {
        ProfileSyncService profileSyncService = this.H;
        N.Md$qCoTO(profileSyncService.e, profileSyncService, C4858t6.c().a());
        if (a() != this.H.m()) {
            if (a()) {
                ProfileSyncService profileSyncService2 = this.H;
                N.MmphYbNU(profileSyncService2.e, profileSyncService2, true);
            } else if (Profile.b().f()) {
                C4858t6.c().b();
            } else {
                ProfileSyncService profileSyncService3 = this.H;
                if (N.MVjof0rh(profileSyncService3.e, profileSyncService3)) {
                    AbstractC3364kK0.g("Sync.StopSource", !C4858t6.c().a() ? 5 : 4, 6);
                }
                ProfileSyncService profileSyncService4 = this.H;
                N.MmphYbNU(profileSyncService4.e, profileSyncService4, false);
            }
        }
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        Object obj = ThreadUtils.f10596a;
        if (this.H.m()) {
            if (!a()) {
                C4858t6.c().b();
            }
        } else if (a()) {
            C4858t6 c = C4858t6.c();
            Objects.requireNonNull(c);
            c.e(false);
        }
    }
}
