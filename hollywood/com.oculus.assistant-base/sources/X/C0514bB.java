package X;

import java.util.Arrays;

/* renamed from: X.bB  reason: case insensitive filesystem */
public final class C0514bB {
    public static void A00(Object obj) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException();
            A04(nullPointerException);
            throw nullPointerException;
        }
    }

    public static void A01(Object obj, String str) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException(AnonymousClass08.A04(str, " must not be null"));
            A04(nullPointerException);
            throw nullPointerException;
        }
    }

    public static void A02(Object obj, String str) {
        if (obj == null) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            NullPointerException nullPointerException = new NullPointerException(AnonymousClass08.A08("Parameter specified as non-null is null: method ", stackTraceElement.getClassName(), ".", stackTraceElement.getMethodName(), ", parameter ", str));
            A04(nullPointerException);
            throw nullPointerException;
        }
    }

    public static void A03(String str) {
        C0481aQ aQVar = new C0481aQ(AnonymousClass08.A05("lateinit property ", str, " has not been initialized"));
        A04(aQVar);
        throw aQVar;
    }

    public static void A04(Throwable th) {
        String name = C0514bB.class.getName();
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (name.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
    }

    public static boolean A05(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        if (obj2 == null) {
            return true;
        }
        return false;
    }
}
