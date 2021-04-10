package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
public final class QN {
    @CanIgnoreReturnValue
    public static void A00(int i, String str) {
        if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" cannot be negative but was: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void A01(Object obj, Object obj2) {
        String str;
        if (obj == null) {
            StringBuilder sb = new StringBuilder("null key in entry: null=");
            sb.append(obj2);
            str = sb.toString();
        } else if (obj2 == null) {
            StringBuilder sb2 = new StringBuilder("null value in entry: ");
            sb2.append(obj);
            sb2.append("=null");
            str = sb2.toString();
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
