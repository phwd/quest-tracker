package java.net;

import java.io.InputStream;
import java.util.Map;

public abstract class CacheResponse {
    public abstract InputStream getBody();

    public abstract Map getHeaders();
}
