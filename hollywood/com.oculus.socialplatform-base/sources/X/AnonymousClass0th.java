package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
/* renamed from: X.0th  reason: invalid class name */
public final class AnonymousClass0th {
    @CanIgnoreReturnValue
    public static void A00(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A08(str, " cannot be negative but was: ", i));
        }
    }

    public static void A01(Object obj, Object obj2) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder("null key in entry: null=");
            sb.append(obj2);
            throw new NullPointerException(sb.toString());
        } else if (obj2 == null) {
            StringBuilder sb2 = new StringBuilder("null value in entry: ");
            sb2.append(obj);
            sb2.append("=null");
            throw new NullPointerException(sb2.toString());
        }
    }
}
