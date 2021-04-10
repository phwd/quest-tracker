package java.net;

import java.util.Map;
import sun.security.util.SecurityConstants;

public abstract class CookieHandler {
    private static CookieHandler cookieHandler;

    public abstract Map get(URI uri, Map map);

    public abstract void put(URI uri, Map map);

    public static synchronized CookieHandler getDefault() {
        CookieHandler cookieHandler2;
        synchronized (CookieHandler.class) {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager == null) {
                cookieHandler2 = cookieHandler;
            } else {
                securityManager.checkPermission(SecurityConstants.GET_COOKIEHANDLER_PERMISSION);
                throw null;
            }
        }
        return cookieHandler2;
    }
}
