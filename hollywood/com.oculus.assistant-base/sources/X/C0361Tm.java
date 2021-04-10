package X;

/* renamed from: X.Tm  reason: case insensitive filesystem */
public final class C0361Tm {
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
