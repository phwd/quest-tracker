package X;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1Am  reason: invalid class name */
public final class AnonymousClass1Am {
    public final AnonymousClass1B6 A00 = new AnonymousClass1B6();
    public final AnonymousClass1B5 A01;
    public final List<View> A02 = new ArrayList();

    public static int A00(AnonymousClass1Am r5, int i) {
        if (i >= 0) {
            int A3V = r5.A01.A3V();
            int i2 = i;
            while (i2 < A3V) {
                AnonymousClass1B6 r1 = r5.A00;
                int A012 = i - (i2 - r1.A01(i2));
                if (A012 != 0) {
                    i2 += A012;
                } else {
                    while (r1.A06(i2)) {
                        i2++;
                    }
                    return i2;
                }
            }
        }
        return -1;
    }

    public static void A01(AnonymousClass1Am r1, View view) {
        if (r1.A02.remove(view)) {
            r1.A01.A7E(view);
        }
    }

    public final int A02() {
        return this.A01.A3V() - this.A02.size();
    }

    public final int A03(View view) {
        int A5a = this.A01.A5a(view);
        if (A5a != -1) {
            AnonymousClass1B6 r1 = this.A00;
            if (!r1.A06(A5a)) {
                return A5a - r1.A01(A5a);
            }
        }
        return -1;
    }

    public final void A05(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int A002;
        if (i < 0) {
            A002 = this.A01.A3V();
        } else {
            A002 = A00(this, i);
        }
        this.A00.A05(A002, z);
        if (z) {
            this.A02.add(view);
            this.A01.A73(view);
        }
        this.A01.A1X(view, A002, layoutParams);
    }

    public final String toString() {
        return AnonymousClass006.A08(this.A00.toString(), ", hidden list:", this.A02.size());
    }

    public AnonymousClass1Am(AnonymousClass1B5 r2) {
        this.A01 = r2;
    }

    public final View A04(int i) {
        return this.A01.A3U(A00(this, i));
    }
}
