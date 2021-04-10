package com.facebook.common.stringformat;

import java.util.Formattable;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;

public class StringFormatUtil {
    private static final Object[] a = {null};

    private static int a(String str) {
        return a((StringBuilder) null, str, 0, (Object) null, (Object) null, (Object) null, (Object) null);
    }

    private static int a(String str, int i) {
        int i2 = i + 1;
        if (str.length() <= i2) {
            return -101;
        }
        char charAt = str.charAt(i2);
        return (charAt == 's' || charAt == 'd' || charAt == '%') ? -100 : -101;
    }

    private static int a(String str, Object obj) {
        return a((StringBuilder) null, str, 1, obj, (Object) null, (Object) null, (Object) null);
    }

    private static int a(String str, Object obj, Object obj2) {
        return a((StringBuilder) null, str, 2, obj, obj2, (Object) null, (Object) null);
    }

    private static int a(String str, Object obj, Object obj2, Object obj3) {
        return a((StringBuilder) null, str, 3, obj, obj2, obj3, (Object) null);
    }

    private static int a(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return a((StringBuilder) null, str, 4, obj, obj2, obj3, obj4);
    }

    private static int a(StringBuilder sb, Object obj) {
        if (!(obj instanceof Formattable)) {
            String str = null;
            if (obj instanceof String) {
                str = (String) obj;
            } else if (obj != null) {
                str = obj.toString();
            }
            if (str == null) {
                str = "null";
            }
            if (sb == null) {
                return str.length();
            }
            sb.append(str);
            return -3;
        } else if (sb == null) {
            return -1;
        } else {
            throw new AssertionError();
        }
    }

    private static int a(StringBuilder sb, String str, int i, int i2, boolean z) {
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
        if (z) {
            return i2 + i3;
        }
        return -3;
    }

    private static int a(StringBuilder sb, String str, int i, Object obj, Object obj2, Object obj3, Object obj4) {
        int i2;
        boolean z = sb == null;
        int i3 = i == 0 ? -1 : 0;
        int i4 = i3;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i4 >= i) {
                i2 = -200;
                break;
            }
            int a2 = a(sb, str, i5, obj, obj2, obj3, obj4, i4);
            if (a2 == -1) {
                return -1;
            }
            if (z) {
                i6 += a2;
            }
            i5 = b(str, i5);
            i2 = -200;
            if (i4 != i3 || i5 != -200 || !z) {
                if (i5 < 0) {
                    break;
                }
                i4++;
            } else {
                return -2;
            }
        }
        if (i5 != i2 && i5 != -201) {
            return a(sb, str, i5, i6, z);
        }
        if (z) {
            return i6;
        }
        return -3;
    }

    private static int a(StringBuilder sb, String str, int i, Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        if (i2 == -1) {
            return a(sb, str, i, (Object) null, false);
        }
        if (i2 == 0) {
            return a(sb, str, i, obj, true);
        }
        if (i2 == 1) {
            return a(sb, str, i, obj2, true);
        }
        if (i2 == 2) {
            return a(sb, str, i, obj3, true);
        }
        if (i2 == 3) {
            return a(sb, str, i, obj4, true);
        }
        throw new AssertionError();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0049 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.StringBuilder r9, java.lang.String r10, int r11, java.lang.Object r12, boolean r13) {
        /*
            int r0 = r10.length()
            r1 = 0
            r2 = 0
        L_0x0006:
            if (r11 >= r0) goto L_0x005c
            char r3 = r10.charAt(r11)
            r4 = 37
            r5 = 1
            if (r3 != r4) goto L_0x0052
            int r3 = a(r10, r11)
            r6 = -100
            r7 = -1
            if (r3 != r6) goto L_0x0051
            int r3 = r11 + 1
            char r6 = r10.charAt(r3)
            if (r13 != 0) goto L_0x0025
            if (r6 == r4) goto L_0x0025
            return r7
        L_0x0025:
            r8 = 115(0x73, float:1.61E-43)
            if (r6 != r8) goto L_0x0030
            int r3 = a(r9, r12)
        L_0x002d:
            r4 = r11
            r11 = 1
            goto L_0x0047
        L_0x0030:
            r8 = 100
            if (r6 != r8) goto L_0x0039
            int r3 = b(r9, r12)
            goto L_0x002d
        L_0x0039:
            if (r6 != r4) goto L_0x0044
            if (r9 == 0) goto L_0x0040
            r9.append(r4)
        L_0x0040:
            r4 = r3
            r11 = 0
            r3 = 1
            goto L_0x0047
        L_0x0044:
            r4 = r11
            r11 = 1
            r3 = -1
        L_0x0047:
            if (r3 != r7) goto L_0x004a
            return r7
        L_0x004a:
            if (r9 != 0) goto L_0x004d
            int r2 = r2 + r3
        L_0x004d:
            if (r11 != 0) goto L_0x005c
            r11 = r4
            goto L_0x005a
        L_0x0051:
            return r7
        L_0x0052:
            if (r9 != 0) goto L_0x0057
            int r2 = r2 + 1
            goto L_0x005a
        L_0x0057:
            r9.append(r3)
        L_0x005a:
            int r11 = r11 + r5
            goto L_0x0006
        L_0x005c:
            if (r9 != 0) goto L_0x005f
            return r2
        L_0x005f:
            r9 = -3
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.stringformat.StringFormatUtil.a(java.lang.StringBuilder, java.lang.String, int, java.lang.Object, boolean):int");
    }

    private static int a(StringBuilder sb, String str, Object... objArr) {
        boolean z;
        int i = 0;
        boolean z2 = sb == null;
        if (objArr == null || objArr.length == 0) {
            objArr = a;
            z = true;
        } else {
            z = false;
        }
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i >= length) {
                break;
            }
            int a2 = a(sb, str, i2, objArr[i], !z);
            if (a2 == -1) {
                return -1;
            }
            if (z2) {
                i3 += a2;
            }
            i2 = b(str, i2);
            if (i2 == -200) {
                break;
            } else if (i2 == -201) {
                z3 = true;
                break;
            } else {
                i++;
                z3 = true;
            }
        }
        if (z2 && !z3) {
            return -2;
        }
        if (i2 != -200 && i2 != -201) {
            return a(sb, str, i2, i3, z2);
        }
        if (z2) {
            return i3;
        }
        return -3;
    }

    private static String a(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        int b = b(str, i, obj, obj2, obj3, obj4, objArr);
        if (b == -1) {
            return c(str, i, obj, obj2, obj3, obj4, objArr);
        }
        if (b == -2) {
            return str;
        }
        StringBuilder sb = new StringBuilder(b);
        if (i == -1) {
            a(sb, str, objArr);
        } else {
            a(sb, str, i, obj, obj2, obj3, obj4);
        }
        return sb.toString();
    }

    private static String a(String str, Object... objArr) {
        try {
            return String.format(null, str, objArr);
        } catch (MissingFormatArgumentException | UnknownFormatConversionException e) {
            throw new RuntimeException(e.getMessage() + ": " + str);
        }
    }

    public static void appendFormatStrLocaleSafe(StringBuilder sb, String str, Object... objArr) {
        int b = b(str, -1, null, null, null, null, objArr);
        if (b == -1) {
            new Formatter(sb).format(null, str, objArr);
        } else if (b == -2) {
            sb.append(str);
        } else {
            sb.ensureCapacity(b);
            a(sb, str, objArr);
        }
    }

    private static int b(String str, int i) {
        int length = str.length();
        boolean z = false;
        while (i < length) {
            if (str.charAt(i) == '%' && a(str, i) == -100) {
                int i2 = i + 1;
                if (str.charAt(i2) != '%') {
                    return i + 2;
                }
                i = i2;
                z = true;
            }
            i++;
        }
        return z ? -201 : -200;
    }

    private static int b(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? a((StringBuilder) null, str, objArr) : a(str, obj, obj2, obj3, obj4) : a(str, obj, obj2, obj3) : a(str, obj, obj2) : a(str, obj) : a(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r2 == null) goto L_0x0041;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0044 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int b(java.lang.StringBuilder r2, java.lang.Object r3) {
        /*
            r0 = 4
            if (r3 != 0) goto L_0x000c
            if (r2 != 0) goto L_0x0006
            goto L_0x0041
        L_0x0006:
            java.lang.String r3 = "null"
            r2.append(r3)
            goto L_0x0040
        L_0x000c:
            boolean r1 = r3 instanceof java.lang.Integer
            if (r1 == 0) goto L_0x001f
            if (r2 != 0) goto L_0x0015
            r0 = 11
            goto L_0x0041
        L_0x0015:
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r2.append(r3)
            goto L_0x0040
        L_0x001f:
            boolean r1 = r3 instanceof java.lang.Short
            if (r1 == 0) goto L_0x0027
            if (r2 != 0) goto L_0x0015
            r0 = 6
            goto L_0x0041
        L_0x0027:
            boolean r1 = r3 instanceof java.lang.Byte
            if (r1 == 0) goto L_0x002e
            if (r2 != 0) goto L_0x0015
            goto L_0x0041
        L_0x002e:
            boolean r0 = r3 instanceof java.lang.Long
            if (r0 == 0) goto L_0x0046
            if (r2 != 0) goto L_0x0037
            r0 = 20
            goto L_0x0041
        L_0x0037:
            java.lang.Long r3 = (java.lang.Long) r3
            long r0 = r3.longValue()
            r2.append(r0)
        L_0x0040:
            r0 = 0
        L_0x0041:
            if (r2 != 0) goto L_0x0044
            return r0
        L_0x0044:
            r2 = -3
            return r2
        L_0x0046:
            if (r2 != 0) goto L_0x004a
            r2 = -1
            return r2
        L_0x004a:
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>()
            goto L_0x0051
        L_0x0050:
            throw r2
        L_0x0051:
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.stringformat.StringFormatUtil.b(java.lang.StringBuilder, java.lang.Object):int");
    }

    private static String c(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        if (i == 0) {
            return a(str, new Object[0]);
        }
        if (i == 1) {
            return a(str, obj);
        } else if (i == 2) {
            return a(str, obj, obj2);
        } else if (i == 3) {
            return a(str, obj, obj2, obj3);
        } else if (i != 4) {
            return a(str, objArr);
        } else {
            return a(str, obj, obj2, obj3, obj4);
        }
    }

    public static String formatStrLocaleSafe(String str) {
        return a(str, 0, (Object) null, (Object) null, (Object) null, (Object) null, (Object[]) null);
    }

    public static String formatStrLocaleSafe(String str, Object obj) {
        return a(str, 1, obj, (Object) null, (Object) null, (Object) null, (Object[]) null);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2) {
        return a(str, 2, obj, obj2, (Object) null, (Object) null, (Object[]) null);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3) {
        return a(str, 3, obj, obj2, obj3, (Object) null, (Object[]) null);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return a(str, 4, obj, obj2, obj3, obj4, (Object[]) null);
    }

    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return a(str, -1, (Object) null, (Object) null, (Object) null, (Object) null, objArr);
    }
}
