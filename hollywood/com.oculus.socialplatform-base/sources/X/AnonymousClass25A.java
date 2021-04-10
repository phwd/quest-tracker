package X;

import javax.net.ssl.SSLException;

/* renamed from: X.25A  reason: invalid class name */
public final class AnonymousClass25A extends Exception {
    public final byte description;
    public final boolean errorTransient = false;
    public final SSLException ex;

    public AnonymousClass25A(byte b, SSLException sSLException) {
        this.description = b;
        this.ex = sSLException;
    }

    public AnonymousClass25A(byte b, SSLException sSLException, boolean z) {
        this.description = b;
        this.ex = sSLException;
    }
}
