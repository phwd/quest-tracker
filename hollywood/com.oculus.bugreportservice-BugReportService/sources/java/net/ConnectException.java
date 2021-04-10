package java.net;

public class ConnectException extends SocketException {
    private static final long serialVersionUID = 3831404271622369215L;

    public ConnectException(String str) {
        super(str);
    }

    public ConnectException() {
    }

    public ConnectException(String str, Throwable th) {
        super(str, th);
    }
}
