package X;

import javax.annotation.Nullable;

/* renamed from: X.0Ii  reason: invalid class name and case insensitive filesystem */
public final class C00740Ii {
    public static void A01(@Nullable Boolean bool) {
        if (bool != null && !bool.booleanValue()) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;Ljava/lang/Object;)TT; */
    public static void A02(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null) {
            throw new NullPointerException(String.valueOf(obj2));
        }
    }

    public static void A03(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void A04(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void A05(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static String A00(@Nullable String str, Object... objArr) {
        String valueOf = String.valueOf(str);
        int length = valueOf.length();
        int length2 = objArr.length;
        StringBuilder sb = new StringBuilder(length + (length2 << 4));
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            int indexOf = valueOf.indexOf("%s", i2);
            if (indexOf == -1) {
                break;
            }
            sb.append(valueOf.substring(i2, indexOf));
            sb.append(objArr[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append(valueOf.substring(i2));
        if (i < length2) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i3 = i + 1; i3 < length2; i3++) {
                sb.append(", ");
                sb.append(objArr[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
