package X;

import java.io.IOException;

/* renamed from: X.1oI  reason: invalid class name */
public class AnonymousClass1oI extends IOException {
    public AnonymousClass1oI(String str) {
        super(str);
    }

    public AnonymousClass1oI(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
