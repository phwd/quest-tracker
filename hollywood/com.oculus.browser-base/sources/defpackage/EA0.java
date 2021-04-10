package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PayerData;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.PaymentRequestSpec;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;
import org.chromium.components.payments.PaymentRequestUpdateEventListener$$CC;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.Origin;

/* renamed from: EA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EA0 extends AbstractC1582Zy0 implements AbstractC1460Xy0, PaymentRequestUpdateEventListener, AbstractC1216Ty0, AbstractC1277Uy0, AbstractC2996iB0 {

    /* renamed from: a  reason: collision with root package name */
    public static EA0 f7940a;
    public AbstractC1797bA0 A;
    public AbstractC3254jk B;
    public AbstractC3166jB0 C;
    public HashMap D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public PaymentApp f7941J;
    public final Runnable b;
    public final RenderFrameHost c;
    public final DA0 d;
    public final List e = new ArrayList();
    public WebContents f;
    public H40 g;
    public String h;
    public String i;
    public Origin j;
    public String k;
    public byte[][] l;
    public boolean m;
    public C1523Yz0 n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public PaymentRequestSpec t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public String y;
    public boolean z;

    public EA0(RenderFrameHost renderFrameHost, AbstractC1797bA0 ba0, Runnable runnable, DA0 da0) {
        this.c = renderFrameHost;
        this.A = ba0;
        this.b = runnable;
        this.d = da0;
        this.u = false;
    }

    public void A(String str, String str2, PayerData payerData) {
        AbstractC3254jk jkVar;
        TA0 ta0;
        if (this.C != null && (jkVar = this.B) != null) {
            C0289Es es = (C0289Es) jkVar;
            PaymentApp k2 = es.f.k();
            if (k2 != null && k2.q() == 1 && !k2.g.isEmpty()) {
                PersonalDataManager c2 = PersonalDataManager.c();
                String str3 = k2.g;
                Objects.requireNonNull(c2);
                Object obj = ThreadUtils.f10596a;
                N.M4tBhXBK(c2.b, c2, str3);
            }
            if (es.n && (ta0 = es.f.w) != null) {
                ta0.j0 = true;
                ta0.f(true);
                ta0.K.a();
            }
            H40 h40 = this.g;
            N.Mb7SmCEg(h40.f8134a, h40, 4);
            C0350Fs fs = (C0350Fs) this.C;
            fs.g = this;
            C2825hB0 hb0 = fs.e;
            hb0.d = str;
            hb0.e = str2;
            fs.j = payerData;
            fs.i = false;
            if (!fs.h) {
                fs.a();
            }
        }
    }

    public void B(C1033Qy0 qy0) {
        if (this.A != null) {
            if (AbstractC3984nz0.a("WebPaymentsRedactShippingAddress")) {
                qy0.k = "";
                qy0.m = "";
                qy0.l = "";
                qy0.e = new String[0];
            }
            ((C4018oA0) this.A).n0(qy0);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:185:0x0197 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:183:0x019a */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x034f  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03d8  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x03e0  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x037f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x011a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final defpackage.C1462Xz0 C() {
        /*
        // Method dump skipped, instructions count: 1012
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.EA0.C():Xz0");
    }

    public final String D() {
        WindowAndroid windowAndroid;
        TA0 ta0;
        boolean z2;
        PaymentApp k2;
        H40 h40 = this.g;
        N.M7kt1TFI(h40.f8134a, h40, this.t.f().e.d, this.t.f().e.e, false);
        AbstractC3254jk jkVar = this.B;
        boolean z3 = this.z;
        C0289Es es = (C0289Es) jkVar;
        AbstractC0228Ds ds = es.c;
        RenderFrameHost renderFrameHost = es.b;
        Objects.requireNonNull(ds);
        WebContents a2 = FA0.a(renderFrameHost);
        String str = null;
        if (a2 == null) {
            windowAndroid = null;
        } else {
            windowAndroid = a2.I();
        }
        if (windowAndroid == null) {
            return "Unable to find Chrome window.";
        }
        Context a3 = es.c.a(es.b);
        if (a3 == null) {
            return "Unable to find Chrome context.";
        }
        if (es.n) {
            int i2 = 1;
            if ((!z3 || ((ArrayList) es.f.i()).size() != 1 || (k2 = es.f.k()) == null || !k2.y() || TextUtils.isEmpty(k2.h())) ? false : N.M1X7xdZV("WebPaymentsMinimalUI")) {
                AB0 ab0 = es.f;
                C1035Qz0 f2 = es.i.f();
                C5835ys ysVar = new C5835ys(es);
                C6005zs zsVar = new C6005zs(es);
                C0045As as = new C0045As(es);
                Objects.requireNonNull(ab0);
                Context context = (Context) windowAndroid.f11022J.get();
                if (context == null) {
                    z2 = false;
                } else {
                    C5894zB0 zb0 = ab0.t;
                    zb0.f11732a = true;
                    zb0.b();
                    ab0.L = new C0640Kk0();
                    PaymentApp k3 = ab0.k();
                    C0640Kk0 kk0 = ab0.L;
                    C5375w80 w80 = ab0.x.f9030a;
                    Objects.requireNonNull(kk0);
                    Map c2 = UH0.c(AbstractC1310Vk0.p);
                    TH0 th0 = AbstractC1310Vk0.n;
                    String a4 = ((C5723yB) ab0.q.get(f2.e.d)).a(k3.h());
                    LH0 lh0 = new LH0(null);
                    lh0.f8415a = a4;
                    HashMap hashMap = (HashMap) c2;
                    hashMap.put(th0, lh0);
                    OH0 oh0 = AbstractC1310Vk0.b;
                    String str2 = w80.c;
                    LH0 lh02 = new LH0(null);
                    lh02.f8415a = str2;
                    hashMap.put(oh0, lh02);
                    OH0 oh02 = AbstractC1310Vk0.c;
                    String str3 = w80.b;
                    LH0 lh03 = new LH0(null);
                    lh03.f8415a = str3;
                    hashMap.put(oh02, lh03);
                    QH0 qh0 = AbstractC1310Vk0.e;
                    GH0 gh0 = new GH0(null);
                    gh0.f8081a = true;
                    hashMap.put(qh0, gh0);
                    QH0 qh02 = AbstractC1310Vk0.i;
                    GH0 gh02 = new GH0(null);
                    gh02.f8081a = true;
                    hashMap.put(qh02, gh02);
                    QH0 qh03 = AbstractC1310Vk0.f;
                    GH0 gh03 = new GH0(null);
                    gh03.f8081a = false;
                    hashMap.put(qh03, gh03);
                    OH0 oh03 = AbstractC1310Vk0.f9101a;
                    Drawable drawable = k3.h;
                    LH0 lh04 = new LH0(null);
                    lh04.f8415a = drawable;
                    hashMap.put(oh03, lh04);
                    OH0 oh04 = AbstractC1310Vk0.d;
                    String a5 = k3.a();
                    LH0 lh05 = new LH0(null);
                    lh05.f8415a = a5;
                    UH0 o2 = AbstractC2531fV.o(hashMap, oh04, lh05, c2, null);
                    View$OnClickListenerC1249Uk0 uk0 = new View$OnClickListenerC1249Uk0(context, k3, o2, ysVar, zsVar, as, new RunnableC0396Gk0(kk0));
                    kk0.f8383a = uk0;
                    C5638xj xjVar = (C5638xj) ((AbstractC4448qj) AbstractC5978zj.f11762a.e(windowAndroid.U));
                    xjVar.j(uk0);
                    C1371Wk0 wk0 = new C1371Wk0(context);
                    wk0.e.setOnClickListener(kk0.f8383a);
                    wk0.d.setOnClickListener(kk0.f8383a);
                    kk0.b = new RunnableC0518Ik0(kk0, ZH0.a(o2, wk0, new C0457Hk0()), xjVar, wk0);
                    i2 = 1;
                    z2 = xjVar.u(wk0, true);
                }
                if (!z2) {
                    return "Payment minimal UI suppressed.";
                }
                H40 h402 = es.e;
                N.Mb7SmCEg(h402.f8134a, h402, i2);
                return null;
            }
            PaymentApp k4 = es.f.k();
            if ((k4 == null || k4.q() != 3) && (ta0 = es.f.w) != null) {
                ta0.K.a();
            }
            H40 h403 = es.e;
            N.Mb7SmCEg(h403.f8134a, h403, 8);
            str = null;
            es.f(null, null, k4);
        } else {
            AB0 ab02 = es.f;
            if (ab02.q()) {
                ab02.a(a3);
            }
        }
        return str;
    }

    public final void E(int i2, String str, int i3) {
        H40 h40 = this.g;
        if (!h40.b) {
            h40.b = true;
            N.MPhEgSJd(h40.f8134a, h40, i2);
        }
        AbstractC1797bA0 ba0 = this.A;
        if (ba0 != null) {
            ((C4018oA0) ba0).i0(i3, str);
        }
        v();
    }

    public void F() {
        if (this.A != null) {
            boolean z2 = false;
            this.H = false;
            int i2 = (!this.F || !((C5495ws) this.d).d()) ? 0 : 1;
            ((C4018oA0) this.A).g0(i2 ^ 1);
            H40 h40 = this.g;
            if (i2 != 0 || this.m) {
                z2 = true;
            }
            N.MzcQanKX(h40.f8134a, h40, z2);
        }
    }

    public void G() {
        int i2;
        if (this.A != null) {
            boolean z2 = this.E;
            boolean z3 = false;
            this.I = false;
            if (N.MNXjZ6_e(this.f, this.h, this.i, this.D)) {
                i2 = !z2 ? 1 : 0;
            } else {
                i2 = ((this.f.g() || !N.MSVZEfSr(this.f.e())) ? 1 : null) != null ? 2 : z2 ? 3 : 4;
            }
            ((C4018oA0) this.A).j0(i2);
            H40 h40 = this.g;
            if (z2 || this.m) {
                z3 = true;
            }
            N.Ma1hMajT(h40.f8134a, h40, z3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC1460Xy0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(defpackage.AbstractC1521Yy0 r10) {
        /*
        // Method dump skipped, instructions count: 324
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.EA0.a(Yy0):void");
    }

    @Override // defpackage.AbstractC4701sA0
    public C1523Yz0 b() {
        return this.n;
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public boolean c(C1033Qy0 qy0) {
        PaymentApp paymentApp;
        if (qy0 == null || (paymentApp = this.f7941J) == null || paymentApp.C() || !this.o || this.A == null) {
            return false;
        }
        B(qy0);
        return true;
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public boolean changePaymentMethodFromInvokedApp(String str, String str2) {
        PaymentApp paymentApp;
        AbstractC1797bA0 ba0;
        if (TextUtils.isEmpty(str) || str2 == null || (paymentApp = this.f7941J) == null || paymentApp.C() || (ba0 = this.A) == null) {
            return false;
        }
        ((C4018oA0) ba0).l0(str, str2);
        return true;
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public boolean changeShippingAddress(ByteBuffer byteBuffer) {
        return PaymentRequestUpdateEventListener$$CC.changeShippingAddress$$dflt$$(this, byteBuffer);
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public boolean changeShippingOptionFromInvokedApp(String str) {
        PaymentApp paymentApp;
        boolean z2;
        if (TextUtils.isEmpty(str) || (paymentApp = this.f7941J) == null || paymentApp.C() || !this.o || this.t.e() == null || this.A == null) {
            return false;
        }
        Iterator it = this.t.e().iterator();
        while (true) {
            if (it.hasNext()) {
                if (str.equals(((C3337kB0) it.next()).d)) {
                    z2 = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        if (!z2) {
            return false;
        }
        ((C4018oA0) this.A).o0(str);
        return true;
    }

    @Override // defpackage.AbstractC1460Xy0
    public AbstractC1582Zy0 d() {
        return this;
    }

    @Override // defpackage.AbstractC4701sA0
    public boolean e() {
        return this.u;
    }

    @Override // defpackage.AbstractC4701sA0
    public C1035Qz0 f() {
        return this.t.f();
    }

    @Override // defpackage.AbstractC4701sA0
    public Map g() {
        return Collections.unmodifiableMap(this.t.b());
    }

    @Override // defpackage.AbstractC4701sA0
    public Map h() {
        return this.t.a();
    }

    @Override // defpackage.AbstractC1460Xy0
    public void i(boolean z2) {
        this.F = z2;
        if (this.H) {
            F();
        }
    }

    @Override // defpackage.AbstractC1460Xy0
    public void j(PaymentApp paymentApp) {
        AbstractC3254jk jkVar = this.B;
        if (jkVar != null) {
            C0289Es es = (C0289Es) jkVar;
            boolean z2 = es.j;
            Objects.requireNonNull(paymentApp);
            es.j = z2 | false;
            paymentApp.k = es.f.F;
            this.E |= paymentApp.i();
            boolean z3 = paymentApp instanceof C1870be;
            this.G |= !z3;
            if (z3) {
                H40 h40 = this.g;
                N.Mb7SmCEg(h40.f8134a, h40, 134217728);
            } else if (paymentApp.p().contains("https://google.com/pay") || paymentApp.p().contains("https://android.com/pay")) {
                H40 h402 = this.g;
                N.Mb7SmCEg(h402.f8134a, h402, 268435456);
            } else {
                H40 h403 = this.g;
                N.Mb7SmCEg(h403.f8134a, h403, 536870912);
            }
            this.e.add(paymentApp);
        }
    }

    @Override // defpackage.AbstractC1460Xy0
    public void k(String str) {
        if (TextUtils.isEmpty(this.y)) {
            this.y = str;
        }
    }

    @Override // defpackage.AbstractC1582Zy0
    public byte[][] l() {
        return this.l;
    }

    @Override // defpackage.AbstractC1582Zy0
    public boolean m() {
        return !((C0289Es) this.B).f.H || AbstractC3984nz0.a("AlwaysAllowJustInTimePaymentApp");
    }

    @Override // defpackage.AbstractC1582Zy0
    public String n() {
        return this.i;
    }

    @Override // defpackage.AbstractC1582Zy0
    public Origin o() {
        return this.j;
    }

    @Override // defpackage.AbstractC1582Zy0
    public RenderFrameHost p() {
        return this.c;
    }

    @Override // defpackage.AbstractC1582Zy0
    public PaymentRequestSpec q() {
        return this.t;
    }

    @Override // defpackage.AbstractC1582Zy0
    public String r() {
        return this.h;
    }

    @Override // defpackage.AbstractC1582Zy0
    public String s() {
        ((C5495ws) this.d).b();
        return null;
    }

    @Override // defpackage.AbstractC1582Zy0
    public WebContents t() {
        return this.f;
    }

    public void u(String str) {
        H40 h40 = this.g;
        if (h40 != null) {
            h40.a(2);
        }
        AbstractC1797bA0 ba0 = this.A;
        if (ba0 != null) {
            ((C4018oA0) ba0).i0(5, str);
        }
        v();
    }

    public void v() {
        if (!this.u) {
            this.u = true;
            f7940a = null;
            AbstractC3254jk jkVar = this.B;
            if (jkVar != null) {
                jkVar.a();
                this.B = null;
            }
            AbstractC1797bA0 ba0 = this.A;
            if (ba0 != null) {
                ((AbstractC2459f30) ba0).close();
                this.A = null;
            }
            this.b.run();
            H40 h40 = this.g;
            if (h40 != null) {
                long j2 = h40.f8134a;
                if (j2 != 0) {
                    N.MK$_cVJA(j2, h40);
                    h40.f8134a = 0;
                }
            }
            PaymentRequestSpec paymentRequestSpec = this.t;
            if (paymentRequestSpec != null) {
                long j3 = paymentRequestSpec.f10875a;
                if (j3 != 0) {
                    N.MiX2Cegu(j3);
                    paymentRequestSpec.f10875a = 0;
                }
            }
        }
    }

    public void w(String str, int i2) {
        AbstractC1797bA0 ba0 = this.A;
        if (ba0 != null) {
            ((C4018oA0) ba0).i0(i2, str);
        }
        v();
    }

    public final boolean x(C2788gz0 gz0) {
        if (gz0.j == null) {
            Objects.requireNonNull((AbstractC0228Ds) this.d);
            return N.MFiPq6M_(gz0.b()) && this.B.c(gz0);
        }
    }

    public void y(boolean z2) {
        AbstractC1797bA0 ba0 = this.A;
        if (ba0 != null) {
            ((C4018oA0) ba0).f0(z2);
        }
        if (z2) {
            this.g.a(1);
            v();
        }
    }

    public void z(String str) {
        this.f7941J = null;
        AbstractC3254jk jkVar = this.B;
        if (jkVar != null) {
            C0289Es es = (C0289Es) jkVar;
            AB0 ab0 = es.f;
            if (ab0.L != null) {
                es.e.a(0);
                AB0 ab02 = es.f;
                RunnableC0167Cs cs = new RunnableC0167Cs(es);
                ab02.L.f8383a.d(new C3508lB0(cs), null, Integer.valueOf((int) R.string.f58640_resource_name_obfuscated_RES_2131953181));
            } else if (es.n) {
                es.e.a(0);
                es.d(str);
            } else {
                TA0 ta0 = ab0.w;
                if (ta0 != null) {
                    ta0.j0 = false;
                    ta0.f(false);
                    ta0.K.a();
                    ta0.n();
                }
                C3813mz0.a().d();
            }
        }
    }
}
