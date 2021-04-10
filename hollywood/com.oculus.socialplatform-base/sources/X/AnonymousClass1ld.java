package X;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import javax.annotation.Nullable;

/* renamed from: X.1ld  reason: invalid class name */
public final class AnonymousClass1ld {
    public static final Drawable A00 = new ColorDrawable(0);

    public static Drawable A01(Drawable drawable, AnonymousClass1lm r4, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            C10201la r0 = new C10201la(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            A02(r0, r4);
            return r0;
        } else if (drawable instanceof NinePatchDrawable) {
            C10311lo r02 = new C10311lo((NinePatchDrawable) drawable);
            A02(r02, r4);
            return r02;
        } else if (drawable instanceof ColorDrawable) {
            C10211lc r03 = new C10211lc(((ColorDrawable) drawable).getColor());
            A02(r03, r4);
            return r03;
        } else {
            AnonymousClass0J5.A05("WrappingUtils", "Don't know how to round that drawable: %s", drawable);
            return drawable;
        }
    }

    public static void A02(AnonymousClass1m6 r2, AnonymousClass1lm r3) {
        r2.A9k(r3.A05);
        r2.AA7(r3.A06);
        r2.A9g(r3.A02, r3.A00);
        r2.AA3(r3.A01);
        r2.AAA(false);
        r2.AA4(false);
    }

    public static Drawable A00(@Nullable Drawable drawable, @Nullable AnonymousClass1lm r4, Resources resources) {
        try {
            if (!(drawable == null || r4 == null || r4.A04 != AnonymousClass007.A01)) {
                if (drawable instanceof AnonymousClass1lb) {
                    AnonymousClass0Mg r2 = (AnonymousClass1lb) drawable;
                    while (true) {
                        Drawable A3p = r2.A3p();
                        if (A3p == r2 || !(A3p instanceof AnonymousClass0Mg)) {
                            r2.A9r(A01(r2.A9r(A00), r4, resources));
                        } else {
                            r2 = (AnonymousClass0Mg) A3p;
                        }
                    }
                    r2.A9r(A01(r2.A9r(A00), r4, resources));
                } else {
                    drawable = A01(drawable, r4, resources);
                }
            }
            C01060Pq.A00();
            return drawable;
        } finally {
            C01060Pq.A00();
        }
    }
}
