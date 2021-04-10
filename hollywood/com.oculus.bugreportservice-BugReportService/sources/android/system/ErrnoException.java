package android.system;

import java.io.IOException;
import java.net.SocketException;
import libcore.io.Libcore;

public final class ErrnoException extends Exception {
    public final int errno;
    private final String functionName;

    public ErrnoException(String str, int i) {
        this.functionName = str;
        this.errno = i;
    }

    public ErrnoException(String str, int i, Throwable th) {
        super(th);
        this.functionName = str;
        this.errno = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String errnoName = OsConstants.errnoName(this.errno);
        if (errnoName == null) {
            errnoName = "errno " + this.errno;
        }
        return this.functionName + " failed: " + errnoName + " (" + Libcore.os.strerror(this.errno) + ")";
    }

    public IOException rethrowAsIOException() {
        IOException iOException = new IOException(getMessage());
        iOException.initCause(this);
        throw iOException;
    }

    public SocketException rethrowAsSocketException() {
        throw new SocketException(getMessage(), this);
    }
}
