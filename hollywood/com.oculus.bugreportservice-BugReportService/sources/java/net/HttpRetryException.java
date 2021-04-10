package java.net;

import java.io.IOException;

public class HttpRetryException extends IOException {
    private static final long serialVersionUID = -9186022286469111381L;
    private String location;
    private int responseCode;

    public HttpRetryException(String str, int i) {
        super(str);
        this.responseCode = i;
    }
}
