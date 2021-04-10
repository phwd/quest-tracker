package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.components.payments.PaymentApp;

/* renamed from: yl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5815yl0 implements AbstractC1617aA0 {
    public final C5155us F;
    public EA0 G;

    public C5815yl0(C5155us usVar) {
        this.F = usVar;
    }

    @Override // defpackage.AbstractC1617aA0
    public void G(int i) {
        EA0 ea0 = this.G;
        if (ea0 != null && ea0.B != null) {
            if (i != 0) {
                H40 h40 = ea0.g;
                if (!h40.b) {
                    h40.b = true;
                    N.My$biNCo(h40.f8134a, h40);
                }
                H40 h402 = ea0.g;
                N.M7kt1TFI(h402.f8134a, h402, ea0.t.f().e.d, ea0.t.f().e.e, true);
            }
            AbstractC3254jk jkVar = ea0.B;
            CA0 ca0 = new CA0(ea0);
            C0289Es es = (C0289Es) jkVar;
            Objects.requireNonNull(es);
            if (i != 0) {
                PU0 pu0 = NU0.f8549a;
                if (!pu0.d("payment_complete_once", false)) {
                    pu0.m("payment_complete_once", true);
                }
            }
            AB0 ab0 = es.f;
            C0106Bs bs = new C0106Bs(es);
            String str = ab0.k().g;
            PU0 pu02 = NU0.f8549a;
            pu02.c(AbstractC0533Is.f.b(str));
            pu02.o(AbstractC0533Is.g.b(str), System.currentTimeMillis());
            C0640Kk0 kk0 = ab0.L;
            if (kk0 != null) {
                C3679mB0 mb0 = new C3679mB0(ca0);
                if (i == 0) {
                    kk0.f8383a.d(bs, null, Integer.valueOf((int) R.string.f58640_resource_name_obfuscated_RES_2131953181));
                } else {
                    kk0.f8383a.b(mb0);
                }
            } else {
                ca0.run();
            }
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void U() {
        EA0 ea0 = this.G;
        if (ea0 != null) {
            if (ea0.v) {
                ea0.G();
            } else {
                ea0.I = true;
            }
        }
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        EA0 ea0 = this.G;
        if (ea0 != null) {
            ea0.g.a(3);
            ea0.v();
            this.G = null;
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void a0(BB0 bb0) {
        boolean z;
        EA0 ea0 = this.G;
        if (ea0 != null && ea0.B != null) {
            boolean z2 = false;
            if (bb0 == null) {
                z = false;
            } else {
                z = N.MQ3mQqrV(bb0.b());
            }
            if (!z) {
                ea0.g.a(2);
                AbstractC1797bA0 ba0 = ea0.A;
                if (ba0 != null) {
                    ((C4018oA0) ba0).i0(1, "Invalid payment validation errors.");
                }
                ea0.v();
                return;
            }
            N.MiyjJ0og(ea0.t.f10875a, bb0.b());
            C0289Es es = (C0289Es) ea0.B;
            es.g = true;
            Context a2 = es.c.a(es.b);
            if (a2 == null) {
                es.d("Unable to find Chrome context.");
                return;
            }
            AB0 ab0 = es.f;
            C5084uR0 ur0 = new C5084uR0(4, 0, new ArrayList(Arrays.asList(ab0.k())));
            ab0.B = ur0;
            ab0.w.o(4, ur0);
            TA0 ta0 = ab0.w;
            C5721yA0 ya0 = ta0.f0;
            ya0.a0 = false;
            ya0.k(ta0.p0);
            TA0 ta02 = ab0.w;
            ta02.j0 = false;
            ta02.f(false);
            ta02.K.a();
            ta02.n();
            C3813mz0.a().d();
            if (!TextUtils.isEmpty(bb0.d)) {
                ab0.w.l(bb0.d);
            } else {
                ab0.w.l(a2.getResources().getString(R.string.f58640_resource_name_obfuscated_RES_2131953181));
            }
            if (ab0.q()) {
                T3 t3 = bb0.f;
                if (!TextUtils.isEmpty(t3.d) || !TextUtils.isEmpty(t3.e) || !TextUtils.isEmpty(t3.f) || !TextUtils.isEmpty(t3.g) || !TextUtils.isEmpty(t3.h) || !TextUtils.isEmpty(t3.i) || !TextUtils.isEmpty(t3.j) || !TextUtils.isEmpty(t3.k) || !TextUtils.isEmpty(t3.l) || !TextUtils.isEmpty(t3.m)) {
                    ab0.e.add(new RunnableC3850nB0(ab0, bb0));
                }
            }
            if (ab0.p()) {
                C0972Py0 py0 = bb0.e;
                if (!TextUtils.isEmpty(py0.e) || !TextUtils.isEmpty(py0.f) || !TextUtils.isEmpty(py0.d)) {
                    z2 = true;
                }
                if (z2) {
                    ab0.e.add(new RunnableC4021oB0(ab0, bb0));
                }
            }
            if (!ab0.e.isEmpty()) {
                ab0.d.post((Runnable) ab0.e.remove());
            }
        }
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        EA0 ea0 = this.G;
        if (ea0 != null) {
            ea0.g.a(4);
            ea0.v();
            this.G = null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:224:0x020d */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47, types: [int] */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01df, code lost:
        if (defpackage.AbstractC3984nz0.a("PaymentRequestSkipToGPayIfNoCard") != false) goto L_0x01e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x05c3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x05c4  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0210  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0223  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // defpackage.AbstractC1617aA0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void n(defpackage.AbstractC1797bA0 r26, defpackage.C1401Wz0[] r27, defpackage.C2788gz0 r28, defpackage.C1523Yz0 r29, boolean r30) {
        /*
        // Method dump skipped, instructions count: 1504
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5815yl0.n(bA0, Wz0[], gz0, Yz0, boolean):void");
    }

    @Override // defpackage.AbstractC1617aA0
    public void o() {
        EA0 ea0 = this.G;
        if (ea0 != null) {
            if (ea0.v) {
                ea0.F();
            } else {
                ea0.H = true;
            }
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void p() {
        EA0 ea0 = this.G;
        if (ea0 != null && ea0.B != null) {
            if (!ea0.w) {
                ea0.g.a(2);
                AbstractC1797bA0 ba0 = ea0.A;
                if (ba0 != null) {
                    ((C4018oA0) ba0).i0(1, "Attempted updateWith without show.");
                }
                ea0.v();
                return;
            }
            N.M_B2Caox(ea0.t.f10875a);
            PaymentApp paymentApp = ea0.f7941J;
            if (paymentApp == null || !paymentApp.C()) {
                AbstractC3254jk jkVar = ea0.B;
                C0289Es es = (C0289Es) jkVar;
                es.f.r(N.MCGOhrza(ea0.t.f10875a));
                es.f.e();
                return;
            }
            ea0.f7941J.D();
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void q(C2788gz0 gz0) {
        EA0 ea0 = this.G;
        if (ea0 != null && ea0.B != null) {
            String str = "Invalid state.";
            if (ea0.x) {
                if (gz0 == null || !ea0.x(gz0)) {
                    ea0.g.a(2);
                    str = "Invalid payment details.";
                } else if (TextUtils.isEmpty(gz0.h)) {
                    N.MnD0tUEj(ea0.t.f10875a, gz0.b());
                    ea0.x = false;
                    AbstractC3254jk jkVar = ea0.B;
                    C2788gz0 c = ea0.t.c();
                    boolean z = ea0.v;
                    C0289Es es = (C0289Es) jkVar;
                    if (es.c.a(es.b) == null) {
                        str = "Unable to find Chrome context.";
                    } else {
                        es.f.t(c);
                        if (z && !es.n) {
                            es.f.e();
                        }
                        str = null;
                    }
                    if (str == null) {
                        str = !ea0.v ? null : ea0.D();
                    }
                }
                if (str != null) {
                    ea0.E(3, str, 1);
                }
            } else if (!ea0.w) {
                ea0.g.a(2);
                AbstractC1797bA0 ba0 = ea0.A;
                if (ba0 != null) {
                    ((C4018oA0) ba0).i0(1, "Attempted updateWith without show.");
                }
                ea0.v();
            } else {
                PaymentApp paymentApp = ea0.f7941J;
                boolean z2 = paymentApp != null && paymentApp.C();
                if (!AbstractC1584Zz0.a(ea0.n) && !z2) {
                    ea0.g.a(2);
                    AbstractC1797bA0 ba02 = ea0.A;
                    if (ba02 != null) {
                        ((C4018oA0) ba02).i0(1, str);
                    }
                    ea0.v();
                } else if (gz0 == null || !ea0.x(gz0)) {
                    ea0.g.a(2);
                    AbstractC1797bA0 ba03 = ea0.A;
                    if (ba03 != null) {
                        ((C4018oA0) ba03).i0(5, "Invalid payment details.");
                    }
                    ea0.v();
                } else {
                    N.MnD0tUEj(ea0.t.f10875a, gz0.b());
                    if (z2) {
                        PaymentApp paymentApp2 = ea0.f7941J;
                        boolean v = paymentApp2.v();
                        C4530rA0 ra0 = new C4530rA0();
                        ra0.g = gz0.h;
                        ra0.h = gz0.k;
                        if (v) {
                            ra0.i = gz0.i;
                        }
                        C1035Qz0 qz0 = gz0.d;
                        if (qz0 != null) {
                            ra0.d = qz0.e;
                        }
                        if (gz0.g != null) {
                            ArrayList arrayList = new ArrayList();
                            int i = 0;
                            while (true) {
                                C2959hz0[] hz0Arr = gz0.g;
                                if (i >= hz0Arr.length) {
                                    break;
                                }
                                if (paymentApp2.B(hz0Arr[i].f.d, null)) {
                                    C0121Bz0 bz0 = new C0121Bz0();
                                    C0060Az0 az0 = new C0060Az0();
                                    bz0.e = az0;
                                    C2959hz0[] hz0Arr2 = gz0.g;
                                    az0.d = hz0Arr2[i].f.d;
                                    az0.e = hz0Arr2[i].f.e;
                                    if (hz0Arr2[i].d != null) {
                                        bz0.d = hz0Arr2[i].d.e;
                                    }
                                    arrayList.add(bz0);
                                }
                                i++;
                            }
                            ra0.f = (C0121Bz0[]) arrayList.toArray(new C0121Bz0[arrayList.size()]);
                        }
                        if (v && gz0.f != null) {
                            ArrayList arrayList2 = new ArrayList();
                            for (int i2 = 0; i2 < gz0.f.length; i2++) {
                                C3337kB0 kb0 = new C3337kB0();
                                C3337kB0[] kb0Arr = gz0.f;
                                kb0.f = kb0Arr[i2].f;
                                kb0.d = kb0Arr[i2].d;
                                kb0.e = kb0Arr[i2].e;
                                kb0.g = kb0Arr[i2].g;
                                arrayList2.add(kb0);
                            }
                            ra0.e = (C3337kB0[]) arrayList2.toArray(new C3337kB0[arrayList2.size()]);
                        }
                        paymentApp2.F(ra0);
                    }
                    AbstractC3254jk jkVar2 = ea0.B;
                    C2788gz0 c2 = ea0.t.c();
                    C0289Es es2 = (C0289Es) jkVar2;
                    es2.f.t(c2);
                    if (!z2) {
                        es2.f.r(c2.h);
                        es2.f.e();
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void y() {
        EA0 ea0 = this.G;
        if (ea0 != null) {
            PaymentApp paymentApp = ea0.f7941J;
            if (paymentApp != null) {
                paymentApp.g(ea0);
            } else {
                ea0.y(true);
            }
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void z(boolean z, boolean z2) {
        C1462Xz0 C;
        EA0 ea0 = this.G;
        if (ea0 != null && ea0.B != null) {
            if (ea0.w) {
                ea0.g.a(2);
                AbstractC1797bA0 ba0 = ea0.A;
                if (ba0 != null) {
                    ((C4018oA0) ba0).i0(1, "Attempted show twice.");
                }
                ea0.v();
            } else if (EA0.f7940a != null) {
                ea0.E(2, "Another PaymentRequest UI is already showing in a different tab or window.", 4);
            } else {
                EA0.f7940a = ea0;
                H40 h40 = ea0.g;
                N.MpLIjj0f(h40.f8134a, h40, 1);
                ea0.w = true;
                ea0.z = z;
                ea0.x = z2;
                H40 h402 = ea0.g;
                N.MN_WbtBu(h402.f8134a, h402);
                if (ea0.v && (C = ea0.C()) != null) {
                    ea0.E(C.f9247a, C.b, C.c);
                }
            }
        }
    }
}
