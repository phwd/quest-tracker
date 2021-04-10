package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Verify {
    public static void verify(boolean expression) {
        if (!expression) {
            throw new VerifyException();
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object... errorMessageArgs) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, char p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, int p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, long p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, char p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, int p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, long p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, char p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, int p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, long p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, char p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, int p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, long p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, char p1, @NullableDecl Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, int p1, @NullableDecl Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, long p1, @NullableDecl Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, @NullableDecl Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, @NullableDecl Object p2, @NullableDecl Object p3) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void verify(boolean expression, @NullableDecl String errorMessageTemplate, @NullableDecl Object p1, @NullableDecl Object p2, @NullableDecl Object p3, @NullableDecl Object p4) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@NullableDecl T reference) {
        return (T) verifyNotNull(reference, "expected a non-null reference", new Object[0]);
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@NullableDecl T reference, @NullableDecl String errorMessageTemplate, @NullableDecl Object... errorMessageArgs) {
        verify(reference != null, errorMessageTemplate, errorMessageArgs);
        return reference;
    }

    private Verify() {
    }
}
