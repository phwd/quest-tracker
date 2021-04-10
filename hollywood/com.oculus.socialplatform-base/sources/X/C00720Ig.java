package X;

import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: X.0Ig  reason: invalid class name and case insensitive filesystem */
public final class C00720Ig {
    public C00710If A00;
    public final C00710If A01;
    public final String A02;

    public static void A00(C00720Ig r2, @Nullable String str, Object obj) {
        C00710If r1 = new C00710If();
        r2.A00.A02 = r1;
        r2.A00 = r1;
        r1.A00 = obj;
        if (str != null) {
            r1.A01 = str;
            return;
        }
        throw null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.A02);
        sb.append('{');
        String str = "";
        for (C00710If r3 = this.A01.A02; r3 != null; r3 = r3.A02) {
            Object obj = r3.A00;
            sb.append(str);
            if (r3.A01 != null) {
                sb.append(r3.A01);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public C00720Ig(String str) {
        C00710If r0 = new C00710If();
        this.A01 = r0;
        this.A00 = r0;
        if (str != null) {
            this.A02 = str;
            return;
        }
        throw null;
    }
}
