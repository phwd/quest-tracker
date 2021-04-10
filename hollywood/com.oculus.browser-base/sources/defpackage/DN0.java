package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: DN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DN0 extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GN0 f7886a;

    public DN0(GN0 gn0) {
        this.f7886a = gn0;
    }

    @Override // defpackage.I70
    public void a(int i) {
        if (i != 1) {
            GN0.h(this.f7886a);
        }
    }

    @Override // defpackage.I70
    public void c(int i) {
        if (i == 1) {
            GN0.h(this.f7886a);
        }
    }

    @Override // defpackage.I70
    public void d(int i, boolean z, boolean z2) {
        if (i == 1) {
            GN0.h(this.f7886a);
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (!(i == 0 || i == 3 || this.f7886a.m0.get() == null)) {
            ((ContextualSearchManager) this.f7886a.m0.get()).i(0);
        }
        if (i == 1) {
            DQ dq = this.f7886a.L;
            if (dq != null) {
                dq.a();
            }
            GN0.h(this.f7886a);
        }
    }
}
