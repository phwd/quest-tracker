package java.net;

import java.io.IOException;

public class SocketException extends IOException {
    private static final long serialVersionUID = -5935874303556886934L;

    public SocketException(String str) {
        super(str);
    }

    public SocketException() {
    }

    public SocketException(Throwable th) {
        super(th);
    }

    public SocketException(String str, Throwable th) {
        super(str, th);
    }
}
