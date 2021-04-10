package X;

import android.view.View;

/* renamed from: X.2fg  reason: invalid class name */
public final class AnonymousClass2fg {
    public AnonymousClass2fh A00 = new AnonymousClass2fh();
    public final AnonymousClass2ff A01;

    public final View A00(int i, int i2, int i3, int i4) {
        AnonymousClass2ff r6 = this.A01;
        int A4d = r6.A4d();
        int A4c = r6.A4c();
        int i5 = -1;
        if (i2 > i) {
            i5 = 1;
        }
        View view = null;
        while (i != i2) {
            View A3U = r6.A3U(i);
            int A3Y = r6.A3Y(A3U);
            int A3W = r6.A3W(A3U);
            AnonymousClass2fh r2 = this.A00;
            r2.A04 = A4d;
            r2.A03 = A4c;
            r2.A02 = A3Y;
            r2.A01 = A3W;
            r2.A00 = 0;
            r2.A00 = i3 | 0;
            if (r2.A00()) {
                return A3U;
            }
            if (i4 != 0) {
                r2.A00 = 0;
                r2.A00 = i4 | 0;
                if (r2.A00()) {
                    view = A3U;
                }
            }
            i += i5;
        }
        return view;
    }

    public final boolean A01(View view) {
        AnonymousClass2fh r4 = this.A00;
        AnonymousClass2ff r0 = this.A01;
        int A4d = r0.A4d();
        int A4c = r0.A4c();
        int A3Y = r0.A3Y(view);
        int A3W = r0.A3W(view);
        r4.A04 = A4d;
        r4.A03 = A4c;
        r4.A02 = A3Y;
        r4.A01 = A3W;
        AnonymousClass2fh r1 = this.A00;
        r1.A00 = 0;
        r1.A00 = 24579;
        return r1.A00();
    }

    public AnonymousClass2fg(AnonymousClass2ff r2) {
        this.A01 = r2;
    }
}
