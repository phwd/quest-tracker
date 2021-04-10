package defpackage;

import android.app.Activity;
import android.content.Intent;
import org.chromium.base.Callback;

/* renamed from: lq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3616lq1 implements AbstractC3103iq1, AbstractC4371qE {
    public final Callback F;
    public Activity G;
    public M2 H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public C5321vq1 f10377J;

    public C3616lq1(Activity activity, M2 m2) {
        C3274jq1 jq1 = new C3274jq1(this);
        this.F = jq1;
        this.G = activity;
        this.H = m2;
        AbstractC4981tq1.f11374a.a(jq1);
        this.H.a(this);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        AbstractC4981tq1.f11374a.e(this.F);
        this.H.b(this);
        this.H = null;
        this.G = null;
    }

    @Override // defpackage.AbstractC3103iq1
    public void f(Intent intent) {
        this.I = intent.getBooleanExtra("org.chromium.chrome.browser.omaha.inline_update_notification_received_extra", false);
        g();
    }

    public final void g() {
        C5321vq1 vq1 = this.f10377J;
        if (vq1 != null) {
            int i = vq1.f11503a;
            if (i == 1) {
                h();
            } else if (i == 3) {
                if (this.I) {
                    AbstractC4981tq1.f11374a.g(2, this.G);
                    this.I = false;
                    return;
                }
                h();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void h() {
        /*
        // Method dump skipped, instructions count: 199
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3616lq1.h():void");
    }
}
