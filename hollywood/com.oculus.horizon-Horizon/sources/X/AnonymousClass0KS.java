package X;

import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: X.0KS  reason: invalid class name */
public final class AnonymousClass0KS {
    public AnonymousClass0KR A00;
    public final AnonymousClass0KR A01;
    public final String A02;

    public static void A00(AnonymousClass0KS r2, @Nullable String str, Object obj) {
        AnonymousClass0KR r1 = new AnonymousClass0KR();
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
        for (AnonymousClass0KR r4 = this.A01.A02; r4 != null; r4 = r4.A02) {
            Object obj = r4.A00;
            sb.append(str);
            if (r4.A01 != null) {
                sb.append(r4.A01);
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

    public AnonymousClass0KS(String str) {
        AnonymousClass0KR r0 = new AnonymousClass0KR();
        this.A01 = r0;
        this.A00 = r0;
        if (str != null) {
            this.A02 = str;
            return;
        }
        throw null;
    }
}
