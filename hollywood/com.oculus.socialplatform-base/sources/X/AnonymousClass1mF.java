package X;

import java.io.IOException;

/* renamed from: X.1mF  reason: invalid class name */
public class AnonymousClass1mF extends IOException {
    public AnonymousClass1mF(String str) {
        super(str);
    }

    public AnonymousClass1mF(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
