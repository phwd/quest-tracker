package X;

import java.util.Collection;
import javax.annotation.Nullable;

/* renamed from: X.0K2  reason: invalid class name */
public final class AnonymousClass0K2 {
    public static void A01(StringBuilder sb, String str, @Nullable Object... objArr) {
        Object[] objArr2;
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            if (obj instanceof Collection) {
                objArr2 = ((Collection) obj).toArray();
            } else if (obj instanceof Object[]) {
                objArr2 = (Object[]) obj;
            } else {
                sb.append(obj.toString());
            }
            A01(sb, str, objArr2);
        }
    }

    public static String A00(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        A01(sb, str, objArr);
        return sb.toString();
    }
}
