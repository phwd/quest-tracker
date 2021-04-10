package sun.util.logging;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Date;

public class LoggingSupport {
    private static final LoggingProxy proxy = ((LoggingProxy) AccessController.doPrivileged(new PrivilegedAction() {
        /* class sun.util.logging.LoggingSupport.AnonymousClass1 */

        @Override // java.security.PrivilegedAction
        public LoggingProxy run() {
            try {
                Field declaredField = Class.forName("java.util.logging.LoggingProxyImpl", true, null).getDeclaredField("INSTANCE");
                declaredField.setAccessible(true);
                return (LoggingProxy) declaredField.get(null);
            } catch (ClassNotFoundException unused) {
                return null;
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }));

    public static boolean isAvailable() {
        return proxy != null;
    }

    private static void ensureAvailable() {
        if (proxy == null) {
            throw new AssertionError((Object) "Should not here");
        }
    }

    public static Object getLogger(String str) {
        ensureAvailable();
        return proxy.getLogger(str);
    }

    public static void setLevel(Object obj, Object obj2) {
        ensureAvailable();
        proxy.setLevel(obj, obj2);
    }

    public static boolean isLoggable(Object obj, Object obj2) {
        ensureAvailable();
        return proxy.isLoggable(obj, obj2);
    }

    public static void log(Object obj, Object obj2, String str) {
        ensureAvailable();
        proxy.log(obj, obj2, str);
    }

    public static Object parseLevel(String str) {
        ensureAvailable();
        return proxy.parseLevel(str);
    }

    static String getSimpleFormat(boolean z) {
        LoggingProxy loggingProxy;
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.util.logging.LoggingSupport.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty("java.util.logging.SimpleFormatter.format");
            }
        });
        if (z && (loggingProxy = proxy) != null && str == null) {
            str = loggingProxy.getProperty("java.util.logging.SimpleFormatter.format");
        }
        if (str == null) {
            return "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";
        }
        try {
            String.format(str, new Date(), "", "", "", "", "");
            return str;
        } catch (IllegalArgumentException unused) {
            return "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";
        }
    }
}
