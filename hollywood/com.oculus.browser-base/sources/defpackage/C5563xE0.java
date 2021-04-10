package defpackage;

import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* renamed from: xE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5563xE0 implements AbstractC1509Ys0 {
    public final C1128Sl F;
    public final C1570Zs0 G = new C1570Zs0();
    public final AbstractC1509Ys0 H;
    public AbstractC5733yE0 I;

    /* renamed from: J  reason: collision with root package name */
    public Boolean f11599J;

    public C5563xE0(RQ rq, AbstractC1509Ys0 ys0) {
        C1128Sl sl = new C1128Sl();
        this.F = sl;
        this.H = ys0;
        Callback b = sl.b(new C5053uE0(this));
        Objects.requireNonNull(rq);
        Object obj = ThreadUtils.f10596a;
        if (rq.b) {
            ((C1067Rl) b).onResult(Boolean.valueOf(rq.c));
        } else {
            rq.e.add(b);
        }
        ys0.g(sl.b(new C5223vE0(this)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (J.N.MCCtS0px(r3.f10882a, r3) != false) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r6 = this;
            Zs0 r0 = r6.G
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.Boolean r0 = r6.f11599J
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0017
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0017
            r0 = r1
            goto L_0x0018
        L_0x0017:
            r0 = r2
        L_0x0018:
            Ys0 r3 = r6.H
            java.lang.Object r3 = r3.get()
            if (r3 == 0) goto L_0x0031
            Ys0 r3 = r6.H
            java.lang.Object r3 = r3.get()
            org.chromium.components.policy.PolicyService r3 = (org.chromium.components.policy.PolicyService) r3
            long r4 = r3.f10882a
            boolean r3 = J.N.MCCtS0px(r4, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r1 = r2
        L_0x0032:
            if (r0 == 0) goto L_0x003c
            Zs0 r0 = r6.G
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            r0.a(r1)
            goto L_0x0045
        L_0x003c:
            if (r1 == 0) goto L_0x0045
            Zs0 r0 = r6.G
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r0.a(r1)
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5563xE0.a():void");
    }

    @Override // defpackage.AbstractC1509Ys0
    public Object g(Callback callback) {
        C1570Zs0 zs0 = this.G;
        Objects.requireNonNull(zs0.G);
        zs0.F.g(callback);
        return (Boolean) zs0.get();
    }

    @Override // defpackage.Q31
    public Object get() {
        return (Boolean) this.G.get();
    }
}
