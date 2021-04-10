package defpackage;

import java.util.Comparator;
import java.util.Objects;
import org.chromium.components.payments.PaymentApp;

/* renamed from: Vy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1338Vy0 implements Comparator {
    public final AbstractC4701sA0 F;

    public C1338Vy0(AbstractC4701sA0 sa0) {
        this.F = sa0;
    }

    public static double a(int i, long j) {
        return (-Math.log((double) (((System.currentTimeMillis() - j) / 86400000) + 2))) / Math.log((double) (i + 2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v11, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r2v12, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int i;
        PaymentApp paymentApp = (PaymentApp) obj;
        PaymentApp paymentApp2 = (PaymentApp) obj2;
        Objects.requireNonNull(paymentApp);
        Objects.requireNonNull(paymentApp2);
        int i2 = (paymentApp instanceof C1870be ? 1 : 0) - (paymentApp2 instanceof C1870be ? 1 : 0);
        if (i2 != 0) {
            return i2;
        }
        int compare = Integer.compare(paymentApp2.b, paymentApp.b);
        if (compare != 0) {
            return compare;
        }
        C1523Yz0 b = this.F.b();
        if (b != null) {
            if (b.g) {
                int i3 = (paymentApp2.v() ? 1 : 0) - (paymentApp.v() ? 1 : 0);
                if (i3 != 0) {
                    return i3;
                }
            }
            int i4 = 0;
            if (b.d) {
                boolean t = paymentApp.t();
                if (paymentApp2.t()) {
                    i4 = t;
                    i = 1;
                } else {
                    i4 = t;
                    i = 0;
                }
            } else {
                i = 0;
            }
            if (b.e) {
                if (paymentApp.s()) {
                    i4++;
                }
                if (paymentApp2.s()) {
                    i++;
                }
            }
            if (b.f) {
                if (paymentApp.u()) {
                    i4++;
                }
                if (paymentApp2.u()) {
                    i++;
                }
            }
            if (i != i4) {
                if (i - i4 > 0) {
                    return 1;
                }
                return -1;
            }
        }
        int i5 = (paymentApp2.j() ? 1 : 0) - (paymentApp.j() ? 1 : 0);
        if (i5 != 0) {
            return i5;
        }
        String str = paymentApp2.g;
        PU0 pu0 = NU0.f8549a;
        S40 s40 = AbstractC0533Is.f;
        int e = pu0.e(s40.b(str));
        int e2 = pu0.e(s40.b(paymentApp.g));
        String str2 = paymentApp2.g;
        S40 s402 = AbstractC0533Is.g;
        return Double.compare(a(e, pu0.g(s402.b(str2))), a(e2, pu0.g(s402.b(paymentApp2.g))));
    }
}
