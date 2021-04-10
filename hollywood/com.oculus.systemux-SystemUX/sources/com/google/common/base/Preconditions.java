package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Preconditions {
    private Preconditions() {
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

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (!z) {
            throw new IllegalStateException(format(str, objArr));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Integer.valueOf(i)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), obj));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Integer.valueOf(i)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3));
        }
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3, obj4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, objArr));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), obj));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), obj));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), obj));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3));
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3, obj4));
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2) {
        return checkElementIndex(i, i2, "index");
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2, @NullableDecl String str) {
        if (i >= 0 && i < i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(badElementIndex(i, i2, str));
    }

    private static String badElementIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return format("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException("negative size: " + i2);
        }
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2) {
        return checkPositionIndex(i, i2, "index");
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2, @NullableDecl String str) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(badPositionIndex(i, i2, str));
    }

    private static String badPositionIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return format("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException("negative size: " + i2);
        }
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i, i2, i3));
        }
    }

    private static String badPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return badPositionIndex(i, i3, "start index");
        }
        if (i2 < 0 || i2 > i3) {
            return badPositionIndex(i2, i3, "end index");
        }
        return format("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    static String format(@NullableDecl String str, @NullableDecl Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i2 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append((CharSequence) valueOf, i2, indexOf);
            sb.append(objArr[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) valueOf, i2, valueOf.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i3 = i + 1; i3 < objArr.length; i3++) {
                sb.append(", ");
                sb.append(objArr[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
