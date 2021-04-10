package X;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.07J  reason: invalid class name */
public final class AnonymousClass07J {
    public ViewParent A00;
    public ViewParent A01;
    public boolean A02;
    public int[] A03;
    public final View A04;

    public final void A01(int i) {
        ViewParent viewParent;
        if (i == 0) {
            viewParent = this.A01;
        } else if (i == 1) {
            viewParent = this.A00;
        } else {
            return;
        }
        if (viewParent != null) {
            View view = this.A04;
            if (viewParent instanceof AbstractC05480vn) {
                ((AbstractC05480vn) viewParent).A88(view, i);
            } else if (i == 0) {
                try {
                    viewParent.onStopNestedScroll(view);
                } catch (AbstractMethodError e) {
                    StringBuilder sb = new StringBuilder("ViewParent ");
                    sb.append(viewParent);
                    sb.append(" does not implement interface method onStopNestedScroll");
                    Log.e("ViewParentCompat", sb.toString(), e);
                }
            }
            if (i == 0) {
                this.A01 = null;
            } else if (i == 1) {
                this.A00 = null;
            }
        }
    }

    public final boolean A02(float f, float f2) {
        ViewParent viewParent;
        if (!this.A02 || (viewParent = this.A01) == null) {
            return false;
        }
        try {
            return viewParent.onNestedPreFling(this.A04, f, f2);
        } catch (AbstractMethodError e) {
            StringBuilder sb = new StringBuilder("ViewParent ");
            sb.append(viewParent);
            sb.append(" does not implement interface method onNestedPreFling");
            Log.e("ViewParentCompat", sb.toString(), e);
            return false;
        }
    }

    public final boolean A03(float f, float f2, boolean z) {
        ViewParent viewParent;
        if (!this.A02 || (viewParent = this.A01) == null) {
            return false;
        }
        try {
            return viewParent.onNestedFling(this.A04, f, f2, z);
        } catch (AbstractMethodError e) {
            StringBuilder sb = new StringBuilder("ViewParent ");
            sb.append(viewParent);
            sb.append(" does not implement interface method onNestedFling");
            Log.e("ViewParentCompat", sb.toString(), e);
            return false;
        }
    }

    public final boolean A04(int i) {
        ViewParent viewParent;
        if (i != 0) {
            if (i == 1) {
                viewParent = this.A00;
            }
            return false;
        }
        viewParent = this.A01;
        if (viewParent == null) {
            return false;
        }
        return true;
    }

    public final boolean A06(int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        ViewParent viewParent;
        int i4;
        int i5;
        int[] iArr3 = iArr;
        if (this.A02) {
            if (i3 == 0) {
                viewParent = this.A01;
            } else if (i3 == 1) {
                viewParent = this.A00;
            }
            if (viewParent != null) {
                if (i != 0 || i2 != 0) {
                    if (iArr2 != null) {
                        this.A04.getLocationInWindow(iArr2);
                        i4 = iArr2[0];
                        i5 = iArr2[1];
                    } else {
                        i4 = 0;
                        i5 = 0;
                    }
                    if (iArr == null && (iArr3 = this.A03) == null) {
                        iArr3 = new int[2];
                        this.A03 = iArr3;
                    }
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    View view = this.A04;
                    if (viewParent instanceof AbstractC05480vn) {
                        ((AbstractC05480vn) viewParent).A7M(view, i, i2, iArr3, i3);
                    } else if (i3 == 0) {
                        try {
                            viewParent.onNestedPreScroll(view, i, i2, iArr3);
                        } catch (AbstractMethodError e) {
                            StringBuilder sb = new StringBuilder("ViewParent ");
                            sb.append(viewParent);
                            sb.append(" does not implement interface method onNestedPreScroll");
                            Log.e("ViewParentCompat", sb.toString(), e);
                        }
                    }
                    if (iArr2 != null) {
                        view.getLocationInWindow(iArr2);
                        iArr2[0] = iArr2[0] - i4;
                        iArr2[1] = iArr2[1] - i5;
                    }
                    if (iArr3[0] == 0 && iArr3[1] == 0) {
                        return false;
                    }
                    return true;
                } else if (iArr2 != null) {
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                }
            }
        }
        return false;
    }

    public AnonymousClass07J(@NonNull View view) {
        this.A04 = view;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A05(int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass07J.A05(int, int):boolean");
    }

    public static boolean A00(AnonymousClass07J r16, int i, int i2, int i3, @Nullable int i4, int[] iArr, @Nullable int i5, int[] iArr2) {
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
                    if (viewParent instanceof AnonymousClass0WA) {
                        ((AnonymousClass0WA) viewParent).A7O(view, i, i2, i3, i4, i5, iArr3);
                    } else {
                        iArr3[0] = iArr3[0] + i3;
                        iArr3[1] = iArr3[1] + i4;
                        if (viewParent instanceof AbstractC05480vn) {
                            ((AbstractC05480vn) viewParent).A7N(view, i, i2, i3, i4, i5);
                        } else if (i5 == 0) {
                            try {
                                viewParent.onNestedScroll(view, i, i2, i3, i4);
                            } catch (AbstractMethodError e) {
                                StringBuilder sb = new StringBuilder("ViewParent ");
                                sb.append(viewParent);
                                sb.append(" does not implement interface method onNestedScroll");
                                Log.e("ViewParentCompat", sb.toString(), e);
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
