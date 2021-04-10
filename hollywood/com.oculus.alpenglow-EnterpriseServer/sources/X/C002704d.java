package X;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.squareup.okhttp.internal.framed.Hpack;

@SuppressLint({"RestrictedAPI"})
@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.04d  reason: invalid class name and case insensitive filesystem */
public final class C002704d {
    public static final Rect A00 = new Rect();
    public static final int[] A01 = {16842912};
    public static final int[] A02 = new int[0];

    static {
        try {
            Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static PorterDuff.Mode A00(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static boolean A02(@NonNull Drawable drawable) {
        Drawable drawable2;
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            Drawable[] children = ((DrawableContainer.DrawableContainerState) constantState).getChildren();
            for (Drawable drawable3 : children) {
                if (!A02(drawable3)) {
                    return false;
                }
            }
            return true;
        }
        if (drawable instanceof AnonymousClass08g) {
            drawable2 = ((AnonymousClass08g) drawable).getWrappedDrawable();
        } else if (drawable instanceof AnonymousClass18J) {
            drawable2 = ((AnonymousClass18J) drawable).A00();
        } else if (!(drawable instanceof ScaleDrawable)) {
            return true;
        } else {
            drawable2 = ((ScaleDrawable) drawable).getDrawable();
        }
        return A02(drawable2);
    }

    public static void A01(@NonNull Drawable drawable) {
        int[] iArr;
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            int[] state = drawable.getState();
            if (state == null || state.length == 0) {
                iArr = A01;
            } else {
                iArr = A02;
            }
            drawable.setState(iArr);
            drawable.setState(state);
        }
    }
}
