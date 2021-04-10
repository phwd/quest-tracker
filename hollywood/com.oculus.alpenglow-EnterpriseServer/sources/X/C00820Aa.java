package X;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.0Aa  reason: invalid class name and case insensitive filesystem */
public final class C00820Aa {
    public ViewParent A00;
    public ViewParent A01;
    public boolean A02;
    public int[] A03;
    public final View A04;

    public C00820Aa(@NonNull View view) {
        this.A04 = view;
    }

    public static boolean A00(C00820Aa r16, int i, int i2, int i3, @Nullable int i4, int[] iArr, @Nullable int i5, int[] iArr2) {
        ViewParent viewParent;
        int i6;
        int i7;
        int[] iArr3 = iArr2;
        if (r16.A02) {
            if (i5 == 0) {
                viewParent = r16.A01;
            } else if (i5 == 1) {
                viewParent = r16.A00;
            }
            if (viewParent != null) {
                if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
                    if (iArr != null) {
                        r16.A04.getLocationInWindow(iArr);
                        i6 = iArr[0];
                        i7 = iArr[1];
                    } else {
                        i6 = 0;
                        i7 = 0;
                    }
                    if (iArr2 == null) {
                        iArr3 = r16.A03;
                        if (iArr3 == null) {
                            iArr3 = new int[2];
                            r16.A03 = iArr3;
                        }
                        iArr3[0] = 0;
                        iArr3[1] = 0;
                    }
                    View view = r16.A04;
                    if (viewParent instanceof AnonymousClass0MR) {
                        ((AnonymousClass0MR) viewParent).A6J(view, i, i2, i3, i4, i5, iArr3);
                    } else {
                        iArr3[0] = iArr3[0] + i3;
                        iArr3[1] = iArr3[1] + i4;
                        if (viewParent instanceof AbstractC03790dG) {
                            ((AbstractC03790dG) viewParent).A6I(view, i, i2, i3, i4, i5);
                        } else if (i5 == 0) {
                            try {
                                viewParent.onNestedScroll(view, i, i2, i3, i4);
                            } catch (AbstractMethodError e) {
                                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e);
                            }
                        }
                    }
                    if (iArr != null) {
                        view.getLocationInWindow(iArr);
                        iArr[0] = iArr[0] - i6;
                        iArr[1] = iArr[1] - i7;
                    }
                    return true;
                } else if (iArr != null) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                }
            }
        }
        return false;
    }
}
