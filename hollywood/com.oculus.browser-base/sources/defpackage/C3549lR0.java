package defpackage;

import java.util.regex.Pattern;

/* renamed from: lR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3549lR0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10344a = AbstractC5686xz.c(19);
    public final float b;
    public final boolean c;

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r7 == false) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C3549lR0(defpackage.C4700sA r7, defpackage.C5210vA r8, int r9, int r10) {
        /*
            r6 = this;
            r6.<init>()
            r0 = 19
            boolean r0 = defpackage.AbstractC5686xz.c(r0)
            r6.f10344a = r0
            float r7 = r7.c
            r6.b = r7
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L_0x0048
            boolean r2 = r8.d
            if (r2 == 0) goto L_0x0048
            long r2 = java.lang.System.nanoTime()
            long r4 = r8.c
            long r2 = r2 - r4
            r4 = 200000000(0xbebc200, double:9.8813129E-316)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0044
            r4 = 3000000000(0xb2d05e00, double:1.4821969375E-314)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x002f
            goto L_0x0044
        L_0x002f:
            float r2 = r8.f11463a
            float r9 = (float) r9
            float r2 = r2 - r9
            float r2 = r2 * r7
            float r8 = r8.b
            float r9 = (float) r10
            float r8 = r8 - r9
            float r8 = r8 * r7
            float r2 = r2 * r2
            float r8 = r8 * r8
            float r8 = r8 + r2
            r7 = 1147207680(0x44610000, float:900.0)
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 > 0) goto L_0x0044
            r7 = r0
            goto L_0x0045
        L_0x0044:
            r7 = r1
        L_0x0045:
            if (r7 == 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r0 = r1
        L_0x0049:
            r6.c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3549lR0.<init>(sA, vA, int, int):void");
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return false;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(19, Boolean.valueOf(i()));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2 && i()) {
            Pattern pattern = AA.f7657a;
            AbstractC3100ip1.f10165a.a("Search.ContextualSearchSecondTapMlOverrideSeen", z);
        }
    }

    @Override // defpackage.AbstractC5856yz
    public boolean i() {
        return this.f10344a && this.c;
    }
}
