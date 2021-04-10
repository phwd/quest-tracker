package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: rv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4656rv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11234a = new int[2];

    public static int a(Context context, float f) {
        return b(context.getResources().getDisplayMetrics(), f);
    }

    public static int b(DisplayMetrics displayMetrics, float f) {
        return Math.round(TypedValue.applyDimension(1, f, displayMetrics));
    }

    public static void c(View view, View view2, int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        if (view != null && view2 != view) {
            while (view2 != null) {
                iArr[0] = (int) (view2.getX() + ((float) iArr[0]));
                iArr[1] = (int) (view2.getY() + ((float) iArr[1]));
                if (view2.getParent() != view) {
                    view2 = (View) view2.getParent();
                } else {
                    return;
                }
            }
        }
    }

    public static void d(View view, View view2, int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        if (view != null && view2 != view) {
            while (view2 != null) {
                iArr[0] = view2.getLeft() + iArr[0];
                iArr[1] = view2.getTop() + iArr[1];
                if (view2.getParent() != view) {
                    view2 = (View) view2.getParent();
                } else {
                    return;
                }
            }
        }
    }

    public static boolean e(View view) {
        if (!(view.isInTouchMode() ? view.isFocusableInTouchMode() : view.isFocusable())) {
            return true;
        }
        return view.hasFocus();
    }

    public static void f(ViewGroup viewGroup, boolean z) {
        while (viewGroup != null) {
            viewGroup.setClipChildren(z);
            if ((viewGroup.getParent() instanceof ViewGroup) && viewGroup.getId() != 16908290) {
                viewGroup = (ViewGroup) viewGroup.getParent();
            } else {
                return;
            }
        }
    }

    public static void g(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                g(viewGroup.getChildAt(i), z);
            }
        }
    }

    public static void h(View view, View view2, Canvas canvas) {
        while (view2 != view) {
            canvas.translate((float) view2.getLeft(), (float) view2.getTop());
            if (view2.getParent() instanceof View) {
                view2 = (View) view2.getParent();
            } else {
                throw new IllegalArgumentException("View 'to' was not a desendent of 'from'.");
            }
        }
    }
}
