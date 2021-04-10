package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue F;
    public TypedValue G;
    public TypedValue H;
    public TypedValue I;

    /* renamed from: J  reason: collision with root package name */
    public TypedValue f9461J;
    public TypedValue K;
    public final Rect L = new Rect();
    public U7 M;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        U7 u7 = this.M;
        if (u7 != null) {
            Objects.requireNonNull(u7);
        }
    }

    public void onDetachedFromWindow() {
        C4676s2 s2Var;
        super.onDetachedFromWindow();
        U7 u7 = this.M;
        if (u7 != null) {
            LayoutInflater$Factory2C3156j8 j8Var = u7.f9007a;
            CD cd = j8Var.U;
            if (cd != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) cd;
                actionBarOverlayLayout.m();
                ActionMenuView actionMenuView = actionBarOverlayLayout.K.f8179a.F;
                if (!(actionMenuView == null || (s2Var = actionMenuView.b0) == null)) {
                    s2Var.b();
                }
            }
            if (j8Var.Z != null) {
                j8Var.O.getDecorView().removeCallbacks(j8Var.a0);
                if (j8Var.Z.isShowing()) {
                    try {
                        j8Var.Z.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                j8Var.Z = null;
            }
            j8Var.y();
            C4616ri0 ri0 = j8Var.D(0).h;
            if (ri0 != null) {
                ri0.c(true);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 226
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }
}
