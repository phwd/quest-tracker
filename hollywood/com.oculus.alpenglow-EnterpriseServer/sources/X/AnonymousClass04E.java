package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.04E  reason: invalid class name */
public final class AnonymousClass04E {
    public static AnonymousClass04E A01;
    public static final PorterDuff.Mode A02 = PorterDuff.Mode.SRC_IN;
    public AnonymousClass17F A00;

    public final synchronized ColorStateList A03(@NonNull Context context, @DrawableRes int i) {
        return this.A00.A03(context, i);
    }

    public final synchronized Drawable A04(@NonNull Context context, @DrawableRes int i) {
        return this.A00.A05(context, i, true);
    }

    public final synchronized Drawable A05(@NonNull Context context, @DrawableRes int i) {
        return this.A00.A04(context, i);
    }

    public final synchronized void A06(@NonNull Context context) {
        this.A00.A06(context);
    }

    public static synchronized PorterDuffColorFilter A00(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter A002;
        synchronized (AnonymousClass04E.class) {
            A002 = AnonymousClass17F.A00(i, mode);
        }
        return A002;
    }

    public static synchronized AnonymousClass04E A01() {
        AnonymousClass04E r0;
        synchronized (AnonymousClass04E.class) {
            if (A01 == null) {
                A02();
            }
            r0 = A01;
        }
        return r0;
    }

    public static synchronized void A02() {
        synchronized (AnonymousClass04E.class) {
            if (A01 == null) {
                AnonymousClass04E r1 = new AnonymousClass04E();
                A01 = r1;
                r1.A00 = AnonymousClass17F.A01();
                A01.A00.A07(new AnonymousClass04D());
            }
        }
    }
}
