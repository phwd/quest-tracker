package java.net;

import java.io.OutputStream;

public abstract class CacheRequest {
    public abstract void abort();

    public abstract OutputStream getBody();
}
