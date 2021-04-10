package javax.net.ssl;

import java.net.Socket;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.Locale;
import javax.net.SocketFactory;
import sun.security.action.GetPropertyAction;

public abstract class SSLSocketFactory extends SocketFactory {
    static final boolean DEBUG;
    private static SSLSocketFactory defaultSocketFactory = null;
    private static int lastVersion = -1;

    public abstract Socket createSocket(Socket socket, String str, int i, boolean z);

    static {
        String lowerCase = ((String) AccessController.doPrivileged(new GetPropertyAction("javax.net.debug", ""))).toLowerCase(Locale.ENGLISH);
        DEBUG = (lowerCase.contains("all") || lowerCase.contains("ssl")) ? true : DEBUG;
    }

    private static void log(String str) {
        if (DEBUG) {
            System.out.println(str);
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0048 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0052 A[Catch:{ Exception -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[Catch:{ Exception -> 0x0046 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized javax.net.SocketFactory getDefault() {
        /*
        // Method dump skipped, instructions count: 214
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.net.ssl.SSLSocketFactory.getDefault():javax.net.SocketFactory");
    }

    static String getSecurityProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class javax.net.ssl.SSLSocketFactory.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public String run() {
                String property = Security.getProperty(String.this);
                if (property == null) {
                    return property;
                }
                String trim = property.trim();
                if (trim.length() == 0) {
                    return null;
                }
                return trim;
            }
        });
    }
}
