package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
/* renamed from: X.0r5  reason: invalid class name and case insensitive filesystem */
public final class C07340r5 {
    @CanIgnoreReturnValue
    public static void A00(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06(str, " cannot be negative but was: ", i));
        }
    }

    public static void A01(Object obj, Object obj2) {
        String str;
        if (obj == null) {
            str = "null key in entry: null=" + obj2;
        } else if (obj2 == null) {
            str = "null value in entry: " + obj + "=null";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
