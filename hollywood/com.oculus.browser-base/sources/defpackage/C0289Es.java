package defpackage;

import J.N;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.PaymentHandlerHost;
import org.chromium.components.payments.PaymentRequestSpec;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Es  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0289Es extends AbstractC3254jk implements AbstractC5724yB0 {

    /* renamed from: a  reason: collision with root package name */
    public EA0 f7982a;
    public final RenderFrameHost b;
    public final AbstractC0228Ds c;
    public final WebContents d;
    public final H40 e;
    public final AB0 f;
    public boolean g;
    public boolean h;
    public PaymentRequestSpec i;
    public boolean j;
    public PaymentHandlerHost k;
    public TX0 l;
    public boolean m;
    public boolean n;

    public C0289Es(EA0 ea0, AbstractC0228Ds ds) {
        this.f7982a = ea0;
        this.b = ea0.c;
        this.c = ds;
        WebContents webContents = ea0.f;
        this.d = webContents;
        H40 h40 = ea0.g;
        this.e = h40;
        this.f = new AB0(this, ea0, webContents, ea0.m, h40, ea0.h);
        this.f7982a = ea0;
    }

    @Override // defpackage.AbstractC3254jk
    public void a() {
        if (!this.h) {
            this.h = true;
            EA0 ea0 = this.f7982a;
            if (ea0 != null) {
                ea0.v();
                this.f7982a = null;
            }
            AB0 ab0 = this.f;
            ab0.A = true;
            C5177uz0 uz0 = ab0.j;
            if (uz0 != null) {
                Runnable runnable = uz0.f11451a;
                if (runnable != null) {
                    runnable.run();
                    uz0.f11451a = null;
                }
                ab0.j = null;
            }
            C0640Kk0 kk0 = ab0.L;
            if (kk0 != null) {
                kk0.b.run();
                ab0.L = null;
            }
            TA0 ta0 = ab0.w;
            if (ta0 != null) {
                ta0.k0 = true;
                ta0.g(false);
                C0289Es es = (C0289Es) ab0.m;
                AbstractC0228Ds ds = es.c;
                WebContents webContents = es.d;
                Objects.requireNonNull(ds);
                ChromeActivity J0 = ChromeActivity.J0(webContents);
                M2 m2 = J0 == null ? null : J0.Y;
                if (m2 != null) {
                    m2.b(ab0.w);
                }
                ab0.w = null;
                ab0.t.b = false;
            }
            if (ab0.B != null) {
                Iterator it = ((ArrayList) ab0.i()).iterator();
                while (it.hasNext()) {
                    ((PaymentApp) it.next()).l();
                }
                ab0.B = null;
            }
            Objects.requireNonNull(C2187dT0.a());
            C2187dT0.f9784a.remove(ab0);
            AbstractC0124Ca1 ca1 = ab0.I;
            if (ca1 != null) {
                ((AbstractC0246Ea1) ca1).f.c(ab0.g);
                ab0.I = null;
            }
            TabModel tabModel = ab0.f7660J;
            if (tabModel != null) {
                tabModel.w(ab0.h);
                ab0.f7660J = null;
            }
            AbstractC2260du0 du0 = ab0.K;
            if (du0 != null) {
                ((AbstractC3838n70) du0).y0.c(ab0.f);
                ab0.K = null;
            }
            for (C5723yB yBVar : ab0.q.values()) {
                long j2 = yBVar.f11669a;
                if (j2 != 0) {
                    N.MkBG391d(j2, yBVar);
                    yBVar.f11669a = 0;
                }
            }
            ab0.q.clear();
            PaymentHandlerHost paymentHandlerHost = this.k;
            if (paymentHandlerHost != null) {
                N.MDWZVETg(paymentHandlerHost.f10873a);
                paymentHandlerHost.f10873a = 0;
                this.k = null;
            }
            C3813mz0.a().d();
        }
    }

    @Override // defpackage.AbstractC3254jk
    public List b() {
        return this.f.i();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC3254jk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(defpackage.C2788gz0 r5) {
        /*
            r4 = this;
            TX0 r0 = r4.l
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0024
            boolean r3 = r0.d
            if (r3 != 0) goto L_0x000c
        L_0x000a:
            r5 = r2
            goto L_0x0022
        L_0x000c:
            kB0[] r5 = r5.f
            if (r5 == 0) goto L_0x0021
            int r3 = r5.length
            if (r3 != r2) goto L_0x0021
            r3 = r5[r1]
            boolean r3 = r3.g
            if (r3 != 0) goto L_0x001a
            goto L_0x0021
        L_0x001a:
            r5 = r5[r1]
            java.lang.String r5 = r5.d
            r0.i = r5
            goto L_0x000a
        L_0x0021:
            r5 = r1
        L_0x0022:
            if (r5 == 0) goto L_0x0025
        L_0x0024:
            r1 = r2
        L_0x0025:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0289Es.c(gz0):boolean");
    }

    public final void d(String str) {
        EA0 ea0 = this.f7982a;
        if (ea0 != null) {
            AbstractC1797bA0 ba0 = ea0.A;
            if (ba0 != null) {
                ((C4018oA0) ba0).i0(1, str);
            }
            ea0.v();
        }
        a();
    }

    public Context e() {
        return this.c.a(this.b);
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean f(defpackage.C1997cK r20, defpackage.C1997cK r21, org.chromium.components.payments.PaymentApp r22) {
        /*
        // Method dump skipped, instructions count: 507
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0289Es.f(cK, cK, org.chromium.components.payments.PaymentApp):boolean");
    }

    public void g(int i2, String str) {
        H40 h40 = this.e;
        if (!h40.b) {
            h40.b = true;
            N.MMB_UdCu(h40.f8134a, h40, i2);
        }
        d(str);
    }
}
