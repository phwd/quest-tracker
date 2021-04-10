package X;

import javax.net.ssl.SSLException;

/* renamed from: X.1v5  reason: invalid class name */
public final class AnonymousClass1v5 extends Exception {
    public final byte description;
    public final boolean errorTransient = false;
    public final SSLException ex;

    public AnonymousClass1v5(byte b, SSLException sSLException) {
        this.description = b;
        this.ex = sSLException;
    }

    public AnonymousClass1v5(byte b, SSLException sSLException, boolean z) {
        this.description = b;
        this.ex = sSLException;
    }
}
