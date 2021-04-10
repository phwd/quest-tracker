package defpackage;

import android.os.Handler;

/* renamed from: ao  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1729ao extends AbstractC2412eo {
    public final Cdo k;
    public final Cdo l;

    public C1729ao(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, int i, C1559Zn zn) {
        super(handler, runnable, str, str2, null, z, z2, z3, null);
        this.k = new Cdo(handler, runnable, str, AbstractC2531fV.f(str2, "0"), null, z, z2, z3, i, null);
        this.l = new Cdo(handler, runnable, str, AbstractC2531fV.f(str2, "1"), null, z, z2, z3, i, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    @Override // defpackage.AbstractC2412eo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.C5653xo c(android.content.Context r6, android.os.Bundle r7, defpackage.AbstractC5483wo r8) {
        /*
            r5 = this;
            do r0 = r5.k
            xo r1 = r0.f(r6, r7)
            r2 = 0
            if (r1 != 0) goto L_0x000a
            goto L_0x0024
        L_0x000a:
            boolean r3 = r0.i
            java.lang.String r4 = "ChildProcessConnection.tryStart"
            org.chromium.base.TraceEvent.Y(r4, r2)     // Catch:{ all -> 0x0043 }
            boolean r3 = r1.b(r3)     // Catch:{ all -> 0x0043 }
            if (r3 != 0) goto L_0x001c
            r3 = 0
            org.chromium.base.TraceEvent.f0(r4)
            goto L_0x0022
        L_0x001c:
            r1.k = r8
            org.chromium.base.TraceEvent.f0(r4)
            r3 = 1
        L_0x0022:
            if (r3 != 0) goto L_0x0026
        L_0x0024:
            r1 = r2
            goto L_0x002b
        L_0x0026:
            va r0 = r0.l
            r0.add(r1)
        L_0x002b:
            if (r1 == 0) goto L_0x002e
            return r1
        L_0x002e:
            do r0 = r5.l
            xo r6 = r0.f(r6, r7)
            if (r6 != 0) goto L_0x0037
            goto L_0x0042
        L_0x0037:
            va r7 = r0.l
            r7.add(r6)
            boolean r7 = r0.i
            r6.m(r7, r8)
            r2 = r6
        L_0x0042:
            return r2
        L_0x0043:
            r6 = move-exception
            org.chromium.base.TraceEvent.f0(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1729ao.c(android.content.Context, android.os.Bundle, wo):xo");
    }

    @Override // defpackage.AbstractC2412eo
    public void d(C5653xo xoVar) {
        if (this.k.l.contains(xoVar)) {
            this.k.l.remove(xoVar);
        } else if (this.l.l.contains(xoVar)) {
            this.l.l.remove(xoVar);
        }
    }

    @Override // defpackage.AbstractC2412eo
    public int e() {
        return -1;
    }
}
