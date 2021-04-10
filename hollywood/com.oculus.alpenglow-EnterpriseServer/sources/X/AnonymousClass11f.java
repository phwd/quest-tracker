package X;

import javax.net.ssl.SSLException;

/* renamed from: X.11f  reason: invalid class name */
public final class AnonymousClass11f extends Exception {
    public final byte description;
    public final boolean errorTransient = false;
    public final SSLException ex;

    public AnonymousClass11f(byte b, SSLException sSLException) {
        this.description = b;
        this.ex = sSLException;
    }

    public AnonymousClass11f(byte b, SSLException sSLException, boolean z) {
        this.description = b;
        this.ex = sSLException;
    }
}
