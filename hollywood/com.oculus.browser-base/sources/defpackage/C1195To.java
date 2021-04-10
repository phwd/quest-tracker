package defpackage;

import java.util.Comparator;

/* renamed from: To  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1195To implements Comparator {
    public C1195To(AbstractC1073Ro ro) {
    }

    public static int b(C1134So so, C1134So so2) {
        boolean z = so.d;
        if (z && !so2.d) {
            return -1;
        }
        if (z || !so2.d) {
            return Long.signum(so.c - so2.c);
        }
        return 1;
    }

    /* renamed from: a */
    public int compare(C1134So so, C1134So so2) {
        boolean z = so.b;
        boolean z2 = (z && so.c == 0) || so.e == 2;
        boolean z3 = so2.b;
        boolean z4 = (z3 && so2.c == 0) || so2.e == 2;
        if (z2 && z4) {
            return b(so, so2);
        }
        if (z2 && !z4) {
            return -1;
        }
        if (!z2 && z4) {
            return 1;
        }
        boolean z5 = (z && so.c > 0 && so.d) || so.e == 1;
        boolean z6 = (z3 && so2.c > 0 && so2.d) || so2.e == 1;
        if (z5 && z6) {
            return b(so, so2);
        }
        if (z5 && !z6) {
            return -1;
        }
        if (!z5 && z6) {
            return 1;
        }
        if (z && z3) {
            return b(so, so2);
        }
        if (!z || z3) {
            return (z || !z3) ? 0 : 1;
        }
        return -1;
    }
}
