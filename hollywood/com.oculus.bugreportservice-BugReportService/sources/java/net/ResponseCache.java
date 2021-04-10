package java.net;

import java.util.Map;
import sun.security.util.SecurityConstants;

public abstract class ResponseCache {
    private static ResponseCache theResponseCache;

    public abstract CacheResponse get(URI uri, String str, Map map);

    public abstract CacheRequest put(URI uri, URLConnection uRLConnection);

    public static synchronized ResponseCache getDefault() {
        ResponseCache responseCache;
        synchronized (ResponseCache.class) {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager == null) {
                responseCache = theResponseCache;
            } else {
                securityManager.checkPermission(SecurityConstants.GET_RESPONSECACHE_PERMISSION);
                throw null;
            }
        }
        return responseCache;
    }
}
