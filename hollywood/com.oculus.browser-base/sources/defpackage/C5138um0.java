package defpackage;

import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: um0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5138um0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11434a;
    public RunnableC0549Ja b;
    public int c;
    public final /* synthetic */ C5478wm0 d;

    public C5138um0(C5478wm0 wm0) {
        this.d = wm0;
    }

    public final C2840hG0 a() {
        try {
            C2474f80.f9900a.b();
            AbstractC2987i80.a();
            return null;
        } catch (C2840hG0 e) {
            return e;
        }
    }

    public final void b(Exception exc) {
        Object obj = ThreadUtils.f10596a;
        int i = this.c;
        if (i >= 0) {
            if (exc == null) {
                int i2 = i - 1;
                this.c = i2;
                if (i2 == 0) {
                    if (CachedFeatureFlags.b("network_service_warm_up_enabled", false)) {
                        AbstractC0281Eo.a(ContextUtils.getApplicationContext(), false);
                    }
                    if (this.f11434a) {
                        AbstractC0281Eo.a(ContextUtils.getApplicationContext(), true);
                    }
                    C5478wm0 wm0 = this.d;
                    wm0.f = Boolean.TRUE;
                    wm0.a();
                    this.c = -1;
                    return;
                }
                return;
            }
            Objects.requireNonNull((AbstractActivityC0731Ma) this.d.f11568a);
            throw new C2840hG0(4, exc);
        }
    }
}
