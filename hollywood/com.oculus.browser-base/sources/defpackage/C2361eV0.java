package defpackage;

import J.N;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: eV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2361eV0 extends AbstractC2145dC0 {
    public static final long N;
    public static final long O;
    public static final long P;
    public static final P20 Q;
    public static final P20 R;
    public static final P20 S;
    public static C6012zu0 T = new C6012zu0();
    public long U = -1;
    public long V = -1;
    public long W = -1;
    public String X;
    public C1078Rq0 Y = new C1078Rq0();
    public WK Z;
    public int a0 = 2;

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        long millis = timeUnit.toMillis(7);
        N = millis;
        long millis2 = TimeUnit.HOURS.toMillis(1);
        O = millis2;
        long seconds = timeUnit.toSeconds(90);
        P = seconds;
        Q = new P20("TabGridLayoutAndroid", "price_tracking_stale_tab_threshold_seconds", (int) seconds);
        R = new P20("TabGridLayoutAndroid", "price_tracking_time_to_live_ms", (int) millis2);
        S = new P20("TabGridLayoutAndroid", "price_tracking_display_time_ms", (int) millis);
    }

    public C2361eV0(Tab tab) {
        super(tab, EnumC3169jC0.a(C2361eV0.class, tab.a()).b(), EnumC3169jC0.a(C2361eV0.class, tab.a()).R);
        t(tab);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        this.G.I(this.Z);
        C1078Rq0 rq0 = this.K;
        if (rq0 != null) {
            rq0.I.c(this.L);
            this.L = null;
        }
    }

    @Override // defpackage.AbstractC2145dC0
    public boolean e(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        try {
            C2191dV0 dv0 = (C2191dV0) AbstractC2360eV.k(C2191dV0.e, bArr);
            this.V = dv0.h;
            this.W = dv0.i;
            this.f9756J = dv0.k;
            this.U = dv0.l;
            return true;
        } catch (L30 e) {
            AbstractC1220Ua0.a("SPTD", String.format(Locale.US, "There was a problem deserializing ShoppingPersistedTabData. Details: %s", e.getMessage()), new Object[0]);
            return false;
        }
    }

    @Override // defpackage.AbstractC2145dC0
    public long j() {
        return (long) R.c();
    }

    @Override // defpackage.AbstractC2145dC0
    public String k() {
        return "SPTD";
    }

    @Override // defpackage.AbstractC2145dC0
    public byte[] o() {
        C2191dV0 dv0 = C2191dV0.e;
        C2191dV0 dv02 = C2191dV0.e;
        C2191dV0 dv03 = new C2191dV0();
        long j = this.V;
        dv03.g |= 1;
        dv03.h = j;
        long j2 = this.W;
        dv03.g |= 2;
        dv03.i = j2;
        long j3 = this.f9756J;
        dv03.g |= 8;
        dv03.k = j3;
        long j4 = this.U;
        dv03.g |= 16;
        dv03.l = j4;
        Objects.requireNonNull(dv03);
        C2163dI0.f9768a.b(dv03).c(dv03);
        if (dv03.i()) {
            return dv03.c();
        }
        throw new C5488wp1();
    }

    public void q() {
        this.Y.m(Boolean.FALSE);
    }

    public final String r(long j) {
        String str;
        C5723yB yBVar = new C5723yB(this.X, Locale.getDefault());
        if (j < 10000000) {
            N.M6ORVMPQ(yBVar.f11669a, 2);
            str = String.format(Locale.getDefault(), "%.2f", Double.valueOf(((double) ((j * 100) / 1000000)) / 100.0d));
        } else {
            N.M6ORVMPQ(yBVar.f11669a, 0);
            str = String.format(Locale.getDefault(), "%d", Long.valueOf((long) Math.floor(((double) (j + 500000)) / 1000000.0d)));
        }
        return yBVar.a(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.C2020cV0 s() {
        /*
            r9 = this;
            long r0 = r9.V
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0016
            long r7 = r9.W
            int r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0016
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0016
            r0 = r5
            goto L_0x0017
        L_0x0016:
            r0 = r6
        L_0x0017:
            r1 = 0
            if (r0 == 0) goto L_0x004f
            long r7 = r9.U
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0033
            long r2 = java.lang.System.currentTimeMillis()
            long r7 = r9.U
            long r2 = r2 - r7
            P20 r0 = defpackage.C2361eV0.S
            int r0 = r0.c()
            long r7 = (long) r0
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r5 = r6
        L_0x0034:
            if (r5 == 0) goto L_0x0037
            goto L_0x004f
        L_0x0037:
            long r2 = r9.V
            long r4 = r9.W
            java.lang.String r0 = r9.r(r2)
            java.lang.String r2 = r9.r(r4)
            boolean r3 = r0.equals(r2)
            if (r3 == 0) goto L_0x004a
            goto L_0x004f
        L_0x004a:
            cV0 r1 = new cV0
            r1.<init>(r0, r2)
        L_0x004f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2361eV0.s():cV0");
    }

    public final void t(Tab tab) {
        q();
        C1078Rq0 rq0 = this.Y;
        this.K = rq0;
        ZB0 zb0 = new ZB0(this);
        this.L = zb0;
        rq0.l(zb0);
        C1669aV0 av0 = new C1669aV0(this);
        this.Z = av0;
        tab.A(av0);
    }

    public C2361eV0(Tab tab, byte[] bArr, AbstractC3511lC0 lc0, String str) {
        super(tab, lc0, str);
        h(bArr);
        t(tab);
    }
}
