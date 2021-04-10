package X;

import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* renamed from: X.1lk  reason: invalid class name and case insensitive filesystem */
public final class C10281lk {
    public static int A00(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public static void A01(@Nullable Drawable drawable, @Nullable Drawable.Callback callback, @Nullable AbstractC00940Mi r3) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof AnonymousClass1mn) {
                ((AnonymousClass1mn) drawable).AAF(r3);
            }
        }
    }

    public static void A02(@Nullable Drawable drawable, @Nullable Drawable drawable2) {
        if (drawable != null && drawable != drawable2) {
            drawable.setBounds(drawable2.getBounds());
            drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
            drawable.setLevel(drawable2.getLevel());
            drawable.setVisible(drawable2.isVisible(), false);
            drawable.setState(drawable2.getState());
        }
    }

    public static void A03(@Nullable Drawable drawable, @Nullable C00930Mh r5) {
        if (drawable != null && r5 != null) {
            int i = r5.A00;
            if (i != -1) {
                drawable.setAlpha(i);
            }
            if (r5.A04) {
                drawable.setColorFilter(r5.A03);
            }
            int i2 = r5.A01;
            boolean z = true;
            if (i2 != -1) {
                boolean z2 = false;
                if (i2 != 0) {
                    z2 = true;
                }
                drawable.setDither(z2);
            }
            int i3 = r5.A02;
            if (i3 != -1) {
                if (i3 == 0) {
                    z = false;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }
}
