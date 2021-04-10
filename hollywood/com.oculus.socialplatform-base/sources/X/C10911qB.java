package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1qB  reason: invalid class name and case insensitive filesystem */
public final class C10911qB {
    public static C10911qB A01;
    public static final PorterDuff.Mode A02 = PorterDuff.Mode.SRC_IN;
    public C10821pj A00;

    public final synchronized ColorStateList A02(@NonNull Context context, @DrawableRes int i) {
        return this.A00.A03(context, i);
    }

    public final synchronized Drawable A03(@NonNull Context context, @DrawableRes int i) {
        return this.A00.A04(context, i);
    }

    public static synchronized C10911qB A00() {
        C10911qB r0;
        synchronized (C10911qB.class) {
            if (A01 == null) {
                A01();
            }
            r0 = A01;
        }
        return r0;
    }

    public static synchronized void A01() {
        synchronized (C10911qB.class) {
            if (A01 == null) {
                C10911qB r1 = new C10911qB();
                A01 = r1;
                r1.A00 = C10821pj.A01();
                C10821pj r12 = A01.A00;
                C10881q8 r0 = new C10881q8();
                synchronized (r12) {
                    r12.A01 = r0;
                }
            }
        }
    }
}
