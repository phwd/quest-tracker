package sun.net.ftp;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceConfigurationError;
import sun.net.ftp.impl.DefaultFtpClientProvider;

public abstract class FtpClientProvider {
    private static final Object lock = new Object();
    private static FtpClientProvider provider = null;

    /* access modifiers changed from: private */
    public static boolean loadProviderAsService() {
        return false;
    }

    public abstract FtpClient createFtpClient();

    protected FtpClientProvider() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("ftpClientProvider"));
            throw null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean loadProviderFromProperty() {
        String property = System.getProperty("sun.net.ftpClientProvider");
        if (property == null) {
            return false;
        }
        try {
            provider = (FtpClientProvider) Class.forName(property, true, null).newInstance();
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SecurityException e) {
            throw new ServiceConfigurationError(e.toString());
        }
    }

    public static FtpClientProvider provider() {
        synchronized (lock) {
            if (provider != null) {
                return provider;
            }
            return (FtpClientProvider) AccessController.doPrivileged(new PrivilegedAction() {
                /* class sun.net.ftp.FtpClientProvider.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Object run() {
                    if (FtpClientProvider.loadProviderFromProperty()) {
                        return FtpClientProvider.provider;
                    }
                    if (FtpClientProvider.loadProviderAsService()) {
                        return FtpClientProvider.provider;
                    }
                    FtpClientProvider unused = FtpClientProvider.provider = new DefaultFtpClientProvider();
                    return FtpClientProvider.provider;
                }
            });
        }
    }
}
