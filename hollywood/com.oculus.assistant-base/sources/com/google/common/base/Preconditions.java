package com.google.common.base;

import X.AnonymousClass08;

public final class Preconditions {
    public static String badPositionIndex(int i, int i2, String str) {
        Object[] objArr;
        String str2;
        if (i < 0) {
            objArr = new Object[]{str, Integer.valueOf(i)};
            str2 = "%s (%s) must not be negative";
        } else if (i2 >= 0) {
            objArr = new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)};
            str2 = "%s (%s) must not be greater than size (%s)";
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A00("negative size: ", i2));
        }
        return Strings.lenientFormat(str2, objArr);
    }

    public static int checkElementIndex(int i, int i2) {
        Object[] objArr;
        String str;
        if (i >= 0 && i < i2) {
            return i;
        }
        if (i < 0) {
            objArr = new Object[]{"index", Integer.valueOf(i)};
            str = "%s (%s) must not be negative";
        } else if (i2 >= 0) {
            objArr = new Object[]{"index", Integer.valueOf(i), Integer.valueOf(i2)};
            str = "%s (%s) must be less than size (%s)";
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A00("negative size: ", i2));
        }
        throw new IndexOutOfBoundsException(Strings.lenientFormat(str, objArr));
    }

    public static Object checkNotNull(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(String.valueOf(obj2));
    }

    public static int checkPositionIndex(int i, int i2) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(badPositionIndex(i, i2, "index"));
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        String str;
        if (i >= 0) {
            if (i2 >= i && i2 <= i3) {
                return;
            }
            if (i <= i3) {
                if (i2 < 0 || i2 > i3) {
                    str = badPositionIndex(i2, i3, "end index");
                } else {
                    str = Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
                }
                throw new IndexOutOfBoundsException(str);
            }
        }
        str = badPositionIndex(i, i3, "start index");
        throw new IndexOutOfBoundsException(str);
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj));
        }
    }
}
