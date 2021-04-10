package com.facebook.common.stringformat;

import X.AnonymousClass06;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;
import javax.annotation.Nullable;

@NullsafeStrict
@DoNotStrip
public class StringFormatUtil {
    public static final Object[] A00 = {null};

    public static int A01(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (i == 0) {
            return A03(null, str, 0, null, null, null, null);
        }
        if (i == 1) {
            return A03(null, str, 1, obj, null, null, null);
        }
        if (i == 2) {
            return A03(null, str, 2, obj, obj2, null, null);
        }
        if (i == 3) {
            return A03(null, str, 3, obj, obj2, obj3, null);
        }
        if (i != 4) {
            return A05(null, str, objArr);
        }
        return A03(null, str, 4, obj, obj2, obj3, obj4);
    }

    public static int A03(@Nullable StringBuilder sb, String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        int A04;
        boolean z = false;
        if (sb == null) {
            z = true;
        }
        int i2 = 0;
        if (i == 0) {
            i2 = -1;
        }
        int i3 = i2;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i3 >= i) {
                break;
            }
            if (i3 == -1) {
                A04 = A04(sb, str, i4, null, false);
            } else if (i3 == 0) {
                A04 = A04(sb, str, i4, obj, true);
            } else if (i3 == 1) {
                A04 = A04(sb, str, i4, obj2, true);
            } else if (i3 == 2) {
                A04 = A04(sb, str, i4, obj3, true);
            } else if (i3 == 3) {
                A04 = A04(sb, str, i4, obj4, true);
            } else {
                throw new AssertionError();
            }
            if (A04 == -1) {
                return -1;
            }
            if (z) {
                i5 += A04;
            }
            i4 = A00(str, i4);
            if (i3 == i2 && i4 == -200) {
                if (!z) {
                    return -3;
                }
                return -2;
            } else if (i4 >= 0) {
                i3++;
            } else if (i4 == -200 || i4 == -201) {
                if (z) {
                    return i5;
                }
            }
        }
        return A02(sb, str, i4, i5, z);
    }

    public static int A05(@Nullable StringBuilder sb, String str, @Nullable Object... objArr) {
        boolean z;
        boolean z2 = false;
        if (sb == null) {
            z2 = true;
        }
        if (objArr == null || objArr.length == 0) {
            objArr = A00;
            z = true;
        } else {
            z = false;
        }
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        boolean z3 = false;
        for (int i3 = 0; i3 < length; i3++) {
            int A04 = A04(sb, str, i, objArr[i3], !z);
            if (A04 == -1) {
                return -1;
            }
            if (z2) {
                i2 += A04;
            }
            i = A00(str, i);
            if (i == -200) {
                break;
            }
            z3 = true;
            if (i == -201) {
                break;
            }
        }
        if (z2 && !z3) {
            return -2;
        }
        if (!(i == -200 || i == -201)) {
            return A02(sb, str, i, i2, z2);
        }
        if (z2) {
            return i2;
        }
        return -3;
    }

    @DoNotStrip
    public static void appendFormatStrLocaleSafe(StringBuilder sb, String str, Object... objArr) {
        int A01 = A01(str, -1, null, null, null, null, objArr);
        if (A01 == -1) {
            new Formatter(sb).format(null, str, objArr);
        } else if (A01 == -2) {
            sb.append(str);
        } else {
            sb.ensureCapacity(A01);
            A05(sb, str, objArr);
        }
    }

    public static int A00(String str, int i) {
        int i2;
        char charAt;
        int length = str.length();
        boolean z = false;
        while (i < length) {
            if (str.charAt(i) == '%' && length > (i2 = i + 1) && ((charAt = str.charAt(i2)) == 's' || charAt == 'd' || charAt == '%')) {
                int i3 = i + 1;
                if (str.charAt(i3) != '%') {
                    return i + 2;
                }
                i = i3;
                z = true;
            }
            i++;
        }
        if (z) {
            return -201;
        }
        return -200;
    }

    public static int A02(@Nullable StringBuilder sb, String str, int i, int i2, boolean z) {
        int length = str.length();
        int i3 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '%' || (length > (i = i + 1) && str.charAt(i) == '%')) {
                if (sb == null) {
                    i3++;
                } else {
                    sb.append(charAt);
                }
                i++;
            } else if (z) {
                return -1;
            } else {
                throw new AssertionError();
            }
        }
        int i4 = i2 + i3;
        if (!z) {
            return -3;
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        if (r0 == null) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00ba A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0076 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00bf A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A04(@javax.annotation.Nullable java.lang.StringBuilder r8, java.lang.String r9, int r10, @javax.annotation.Nullable java.lang.Object r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.stringformat.StringFormatUtil.A04(java.lang.StringBuilder, java.lang.String, int, java.lang.Object, boolean):int");
    }

    public static String A06(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        Object[] objArr2;
        int A01 = A01(str, i, obj, obj2, obj3, obj4, objArr);
        if (A01 == -1) {
            if (i == 0) {
                objArr2 = new Object[0];
            } else if (i == 1) {
                objArr2 = new Object[]{obj};
            } else if (i == 2) {
                objArr2 = new Object[]{obj, obj2};
            } else if (i == 3) {
                objArr2 = new Object[]{obj, obj2, obj3};
            } else if (i != 4) {
                return String.format(null, str, objArr);
            } else {
                objArr2 = new Object[]{obj, obj2, obj3, obj4};
            }
            try {
                return String.format(null, str, objArr2);
            } catch (MissingFormatArgumentException | UnknownFormatConversionException e) {
                throw new RuntimeException(AnonymousClass06.A04(e.getMessage(), ": ", str));
            }
        } else if (A01 == -2) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(A01);
            if (i == -1) {
                A05(sb, str, objArr);
            } else {
                A03(sb, str, i, obj, obj2, obj3, obj4);
            }
            return sb.toString();
        }
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str) {
        return A06(str, 0, null, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj) {
        return A06(str, 1, obj, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2) {
        return A06(str, 2, obj, obj2, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        return A06(str, 3, obj, obj2, obj3, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        return A06(str, 4, obj, obj2, obj3, obj4, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return A06(str, -1, null, null, null, null, objArr);
    }
}
