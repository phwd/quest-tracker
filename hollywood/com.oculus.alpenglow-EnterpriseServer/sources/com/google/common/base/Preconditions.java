package com.google.common.base;

import X.AnonymousClass006;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Preconditions {
    public static String badPositionIndex(int i, int i2, @NullableDecl String str) {
        Object[] objArr;
        String str2;
        if (i < 0) {
            objArr = new Object[]{str, Integer.valueOf(i)};
            str2 = "%s (%s) must not be negative";
        } else if (i2 >= 0) {
            objArr = new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)};
            str2 = "%s (%s) must not be greater than size (%s)";
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A01("negative size: ", i2));
        }
        return Strings.lenientFormat(str2, objArr);
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2) {
        Object[] objArr;
        String str;
        if (i < 0) {
            objArr = new Object[]{"index", Integer.valueOf(i)};
            str = "%s (%s) must not be negative";
        } else if (i < i2) {
            return i;
        } else {
            if (i2 >= 0) {
                objArr = new Object[]{"index", Integer.valueOf(i), Integer.valueOf(i2)};
                str = "%s (%s) must be less than size (%s)";
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A01("negative size: ", i2));
            }
        }
        throw new IndexOutOfBoundsException(Strings.lenientFormat(str, objArr));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
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

    public static void checkArgument(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkState(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }
}
