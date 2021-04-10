package com.facebook.common.stringformat;

import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Formattable;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;
import javax.annotation.Nullable;

@NullsafeStrict
@DoNotStrip
public class StringFormatUtil {
    private static final Object[] SINGLE_ITEM_NULL_OBJECT_ARRAY = {null};

    @DoNotStrip
    public static String formatStrLocaleSafe(String str) {
        return formatStrLocaleSafeInner(str, 0, null, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj) {
        return formatStrLocaleSafeInner(str, 1, obj, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2) {
        return formatStrLocaleSafeInner(str, 2, obj, obj2, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        return formatStrLocaleSafeInner(str, 3, obj, obj2, obj3, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        return formatStrLocaleSafeInner(str, 4, obj, obj2, obj3, obj4, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return formatStrLocaleSafeInner(str, -1, null, null, null, null, objArr);
    }

    private static String formatStrLocaleSafeInner(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        int doDryRunInternal = doDryRunInternal(str, i, obj, obj2, obj3, obj4, objArr);
        if (doDryRunInternal == -1) {
            return fallbackToSystem(str, i, obj, obj2, obj3, obj4, objArr);
        }
        if (doDryRunInternal == -2) {
            return str;
        }
        StringBuilder sb = new StringBuilder(doDryRunInternal);
        if (i == -1) {
            doFormatArray(sb, str, objArr);
        } else {
            doFormatArgs(sb, str, i, obj, obj2, obj3, obj4);
        }
        return sb.toString();
    }

    @DoNotStrip
    public static void appendFormatStrLocaleSafe(StringBuilder sb, String str, Object... objArr) {
        int doDryRunInternal = doDryRunInternal(str, -1, null, null, null, null, objArr);
        if (doDryRunInternal == -1) {
            new Formatter(sb).format(null, str, objArr);
        } else if (doDryRunInternal == -2) {
            sb.append(str);
        } else {
            sb.ensureCapacity(doDryRunInternal);
            doFormatArray(sb, str, objArr);
        }
    }

    static int doDryRun(String str) {
        return doFormatArgs(null, str, 0, null, null, null, null);
    }

    static int doDryRun(String str, @Nullable Object obj) {
        return doFormatArgs(null, str, 1, obj, null, null, null);
    }

    static int doDryRun(String str, @Nullable Object obj, @Nullable Object obj2) {
        return doFormatArgs(null, str, 2, obj, obj2, null, null);
    }

    static int doDryRun(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        return doFormatArgs(null, str, 3, obj, obj2, obj3, null);
    }

    static int doDryRun(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        return doFormatArgs(null, str, 4, obj, obj2, obj3, obj4);
    }

    static int doDryRun(String str, @Nullable Object[] objArr) {
        return doFormatArray(null, str, objArr);
    }

    private static int doDryRunInternal(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (i == 0) {
            return doDryRun(str);
        }
        if (i == 1) {
            return doDryRun(str, obj);
        }
        if (i == 2) {
            return doDryRun(str, obj, obj2);
        }
        if (i == 3) {
            return doDryRun(str, obj, obj2, obj3);
        }
        if (i != 4) {
            return doDryRun(str, objArr);
        }
        return doDryRun(str, obj, obj2, obj3, obj4);
    }

    private static int doFormatArgs(@Nullable StringBuilder sb, String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
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
            int appendSegmentFormatArgs = appendSegmentFormatArgs(sb, str, i5, obj, obj2, obj3, obj4, i4);
            if (appendSegmentFormatArgs == -1) {
                return -1;
            }
            if (z) {
                i6 += appendSegmentFormatArgs;
            }
            i5 = getNextFormatSegmentIndex(str, i5);
            i2 = -200;
            if (i4 == i3 && i5 == -200 && z) {
                return -2;
            }
            if (i5 < 0) {
                break;
            }
            i4++;
        }
        if (i5 != i2 && i5 != -201) {
            return processRemainingString(sb, str, i5, i6, z);
        }
        if (z) {
            return i6;
        }
        return -3;
    }

    private static int appendSegmentFormatArgs(@Nullable StringBuilder sb, String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, int i2) {
        if (i2 == -1) {
            return appendSegmentFormat(sb, str, i, null, false);
        }
        if (i2 == 0) {
            return appendSegmentFormat(sb, str, i, obj, true);
        }
        if (i2 == 1) {
            return appendSegmentFormat(sb, str, i, obj2, true);
        }
        if (i2 == 2) {
            return appendSegmentFormat(sb, str, i, obj3, true);
        }
        if (i2 == 3) {
            return appendSegmentFormat(sb, str, i, obj4, true);
        }
        throw new AssertionError();
    }

    private static int doFormatArray(@Nullable StringBuilder sb, String str, @Nullable Object... objArr) {
        boolean z;
        int i = 0;
        boolean z2 = sb == null;
        if (objArr == null || objArr.length == 0) {
            objArr = SINGLE_ITEM_NULL_OBJECT_ARRAY;
            z = true;
        } else {
            z = false;
        }
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i < length) {
                int appendSegmentFormat = appendSegmentFormat(sb, str, i2, objArr[i], !z);
                if (appendSegmentFormat != -1) {
                    if (z2) {
                        i3 += appendSegmentFormat;
                    }
                    i2 = getNextFormatSegmentIndex(str, i2);
                    if (i2 == -200) {
                        break;
                    } else if (i2 == -201) {
                        z3 = true;
                        break;
                    } else {
                        i++;
                        z3 = true;
                    }
                } else {
                    return -1;
                }
            } else {
                break;
            }
        }
        if (z2 && !z3) {
            return -2;
        }
        if (i2 != -200 && i2 != -201) {
            return processRemainingString(sb, str, i2, i3, z2);
        }
        if (z2) {
            return i3;
        }
        return -3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0049 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int appendSegmentFormat(@javax.annotation.Nullable java.lang.StringBuilder r9, java.lang.String r10, int r11, @javax.annotation.Nullable java.lang.Object r12, boolean r13) {
        /*
            int r0 = r10.length()
            r1 = 0
            r2 = 0
        L_0x0006:
            if (r11 >= r0) goto L_0x005d
            char r3 = r10.charAt(r11)
            r4 = 37
            r5 = 1
            if (r3 != r4) goto L_0x0053
            int r3 = validateFormatPercent(r10, r11)
            r6 = -100
            r7 = -1
            if (r3 != r6) goto L_0x0052
            int r3 = r11 + 1
            char r6 = r10.charAt(r3)
            if (r13 != 0) goto L_0x0025
            if (r6 == r4) goto L_0x0025
            return r7
        L_0x0025:
            r8 = 115(0x73, float:1.61E-43)
            if (r6 != r8) goto L_0x0030
            int r3 = appendStringTypeArg(r9, r12)
        L_0x002d:
            r4 = r11
            r11 = 1
            goto L_0x0047
        L_0x0030:
            r8 = 100
            if (r6 != r8) goto L_0x0039
            int r3 = appendIntTypeArg(r9, r12)
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
            if (r11 == 0) goto L_0x0050
            goto L_0x005d
        L_0x0050:
            r11 = r4
            goto L_0x005b
        L_0x0052:
            return r7
        L_0x0053:
            if (r9 != 0) goto L_0x0058
            int r2 = r2 + 1
            goto L_0x005b
        L_0x0058:
            r9.append(r3)
        L_0x005b:
            int r11 = r11 + r5
            goto L_0x0006
        L_0x005d:
            if (r9 != 0) goto L_0x0060
            goto L_0x0061
        L_0x0060:
            r2 = -3
        L_0x0061:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.stringformat.StringFormatUtil.appendSegmentFormat(java.lang.StringBuilder, java.lang.String, int, java.lang.Object, boolean):int");
    }

    private static int appendStringTypeArg(@Nullable StringBuilder sb, @Nullable Object obj) {
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

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int appendIntTypeArg(@javax.annotation.Nullable java.lang.StringBuilder r2, @javax.annotation.Nullable java.lang.Object r3) {
        /*
            r0 = 4
            if (r3 != 0) goto L_0x000c
            if (r2 != 0) goto L_0x0006
            goto L_0x0055
        L_0x0006:
            java.lang.String r3 = "null"
            r2.append(r3)
            goto L_0x0054
        L_0x000c:
            boolean r1 = r3 instanceof java.lang.Integer
            if (r1 == 0) goto L_0x001f
            if (r2 != 0) goto L_0x0015
            r0 = 11
            goto L_0x0055
        L_0x0015:
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r2.append(r3)
            goto L_0x0054
        L_0x001f:
            boolean r1 = r3 instanceof java.lang.Short
            if (r1 == 0) goto L_0x0031
            if (r2 != 0) goto L_0x0027
            r0 = 6
            goto L_0x0055
        L_0x0027:
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r2.append(r3)
            goto L_0x0054
        L_0x0031:
            boolean r1 = r3 instanceof java.lang.Byte
            if (r1 == 0) goto L_0x0042
            if (r2 != 0) goto L_0x0038
            goto L_0x0055
        L_0x0038:
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r2.append(r3)
            goto L_0x0054
        L_0x0042:
            boolean r0 = r3 instanceof java.lang.Long
            if (r0 == 0) goto L_0x005a
            if (r2 != 0) goto L_0x004b
            r0 = 20
            goto L_0x0055
        L_0x004b:
            java.lang.Long r3 = (java.lang.Long) r3
            long r0 = r3.longValue()
            r2.append(r0)
        L_0x0054:
            r0 = 0
        L_0x0055:
            if (r2 != 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r0 = -3
        L_0x0059:
            return r0
        L_0x005a:
            if (r2 != 0) goto L_0x005e
            r2 = -1
            return r2
        L_0x005e:
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.stringformat.StringFormatUtil.appendIntTypeArg(java.lang.StringBuilder, java.lang.Object):int");
    }

    private static int processRemainingString(@Nullable StringBuilder sb, String str, int i, int i2, boolean z) {
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

    private static int validateFormatPercent(String str, int i) {
        int i2 = i + 1;
        if (str.length() <= i2) {
            return -101;
        }
        char charAt = str.charAt(i2);
        return (charAt == 's' || charAt == 'd' || charAt == '%') ? -100 : -101;
    }

    private static int getNextFormatSegmentIndex(String str, int i) {
        int length = str.length();
        boolean z = false;
        while (i < length) {
            if (str.charAt(i) == '%' && validateFormatPercent(str, i) == -100) {
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

    private static String fallbackToSystem(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (i == 0) {
            return doFallbackToSystem(str, new Object[0]);
        }
        if (i == 1) {
            return doFallbackToSystem(str, obj);
        } else if (i == 2) {
            return doFallbackToSystem(str, obj, obj2);
        } else if (i == 3) {
            return doFallbackToSystem(str, obj, obj2, obj3);
        } else if (i != 4) {
            return doFallbackToSystem(str, objArr);
        } else {
            return doFallbackToSystem(str, obj, obj2, obj3, obj4);
        }
    }

    private static String doFallbackToSystem(String str, @Nullable Object... objArr) {
        try {
            return String.format(null, str, (Object[]) Assertions.assumeNotNull(objArr, "Should not be null under normal circumstances"));
        } catch (MissingFormatArgumentException | UnknownFormatConversionException e) {
            throw new RuntimeException(e.getMessage() + ": " + str);
        }
    }
}
