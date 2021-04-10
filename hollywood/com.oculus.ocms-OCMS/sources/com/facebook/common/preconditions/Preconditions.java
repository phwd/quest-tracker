package com.facebook.common.preconditions;

import com.facebook.common.stringformat.StringFormatUtil;
import javax.annotation.Nullable;

public class Preconditions {
    private Preconditions() {
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void checkArgument(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static String checkNotEmpty(@Nullable String str) {
        if (str == null) {
            throw new RuntimeException("Expected a non-empty string, but got null");
        } else if (!str.isEmpty()) {
            return str;
        } else {
            throw new RuntimeException("Expected a non-empty string");
        }
    }

    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull(@Nullable T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(StringFormatUtil.formatStrLocaleSafe(str, objArr));
    }

    public static int checkArgumentPositive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static <T extends Comparable<T>> T checkArgumentInRange(T t, T t2, T t3) {
        if (t.compareTo(t2) < 0) {
            throw new IllegalStateException("Expected a value larger than min (" + t2 + ") but got " + t);
        } else if (t.compareTo(t3) <= 0) {
            return t;
        } else {
            throw new IllegalStateException("Expected a value smaller than max (" + t3 + ") but got " + t);
        }
    }
}
