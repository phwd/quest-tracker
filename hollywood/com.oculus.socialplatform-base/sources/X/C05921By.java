package X;

import android.view.View;

/* renamed from: X.1By  reason: invalid class name and case insensitive filesystem */
public final class C05921By {
    public static int A00(AnonymousClass1As r1, AbstractC05861Bm r2, View view, View view2, AnonymousClass1Ag r5, boolean z) {
        if (r5.getChildCount() == 0 || r1.A00() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(r5.getPosition(view) - r5.getPosition(view2)) + 1;
        }
        return Math.min(r2.A06(), r2.A08(view2) - r2.A0B(view));
    }

    public static int A01(AnonymousClass1As r3, AbstractC05861Bm r4, View view, View view2, AnonymousClass1Ag r7, boolean z) {
        int A00;
        if (r7.getChildCount() == 0 || (A00 = r3.A00()) == 0 || view == null || view2 == null) {
            return 0;
        }
        if (z) {
            return (int) ((((float) (r4.A08(view2) - r4.A0B(view))) / ((float) (Math.abs(r7.getPosition(view) - r7.getPosition(view2)) + 1))) * ((float) r3.A00()));
        }
        return A00;
    }

    public static int A02(AnonymousClass1As r4, AbstractC05861Bm r5, View view, View view2, AnonymousClass1Ag r8, boolean z, boolean z2) {
        int max;
        if (r8.getChildCount() == 0 || r4.A00() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(r8.getPosition(view), r8.getPosition(view2));
        int max2 = Math.max(r8.getPosition(view), r8.getPosition(view2));
        if (z2) {
            max = Math.max(0, (r4.A00() - max2) - 1);
        } else {
            max = Math.max(0, min);
        }
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(r5.A08(view2) - r5.A0B(view))) / ((float) (Math.abs(r8.getPosition(view) - r8.getPosition(view2)) + 1)))) + ((float) (r5.A05() - r5.A0B(view))));
    }
}
