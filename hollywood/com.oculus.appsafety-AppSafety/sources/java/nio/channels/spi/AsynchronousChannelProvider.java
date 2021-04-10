package java.nio.channels.spi;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import sun.nio.ch.DefaultAsynchronousChannelProvider;

public abstract class AsynchronousChannelProvider {
    public abstract AsynchronousChannelGroup openAsynchronousChannelGroup(int i, ThreadFactory threadFactory) throws IOException;

    public abstract AsynchronousChannelGroup openAsynchronousChannelGroup(ExecutorService executorService, int i) throws IOException;

    public abstract AsynchronousServerSocketChannel openAsynchronousServerSocketChannel(AsynchronousChannelGroup asynchronousChannelGroup) throws IOException;

    public abstract AsynchronousSocketChannel openAsynchronousSocketChannel(AsynchronousChannelGroup asynchronousChannelGroup) throws IOException;

    private static Void checkPermission() {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            return null;
        }
        sm.checkPermission(new RuntimePermission("asynchronousChannelProvider"));
        return null;
    }

    private AsynchronousChannelProvider(Void ignore) {
    }

    protected AsynchronousChannelProvider() {
        this(checkPermission());
    }

    /* access modifiers changed from: private */
    public static class ProviderHolder {
        static final AsynchronousChannelProvider provider = load();

        private ProviderHolder() {
        }

        private static AsynchronousChannelProvider load() {
            return (AsynchronousChannelProvider) AccessController.doPrivileged(new PrivilegedAction<AsynchronousChannelProvider>() {
                /* class java.nio.channels.spi.AsynchronousChannelProvider.ProviderHolder.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public AsynchronousChannelProvider run() {
                    AsynchronousChannelProvider p = ProviderHolder.loadProviderFromProperty();
                    if (p != null) {
                        return p;
                    }
                    AsynchronousChannelProvider p2 = ProviderHolder.loadProviderAsService();
                    if (p2 != null) {
                        return p2;
                    }
                    return DefaultAsynchronousChannelProvider.create();
                }
            });
        }

        /* access modifiers changed from: private */
        public static AsynchronousChannelProvider loadProviderFromProperty() {
            String cn = System.getProperty("java.nio.channels.spi.AsynchronousChannelProvider");
            if (cn == null) {
                return null;
            }
            try {
                return (AsynchronousChannelProvider) Class.forName(cn, true, ClassLoader.getSystemClassLoader()).newInstance();
            } catch (ClassNotFoundException x) {
                throw new ServiceConfigurationError(null, x);
            } catch (IllegalAccessException x2) {
                throw new ServiceConfigurationError(null, x2);
            } catch (InstantiationException x3) {
                throw new ServiceConfigurationError(null, x3);
            } catch (SecurityException x4) {
                throw new ServiceConfigurationError(null, x4);
            }
        }

        /* access modifiers changed from: private */
        public static AsynchronousChannelProvider loadProviderAsService() {
            Iterator<AsynchronousChannelProvider> i = ServiceLoader.load(AsynchronousChannelProvider.class, ClassLoader.getSystemClassLoader()).iterator();
            do {
                try {
                    if (i.hasNext()) {
                        return i.next();
                    }
                    return null;
                } catch (ServiceConfigurationError sce) {
                    if (!(sce.getCause() instanceof SecurityException)) {
                        throw sce;
                    }
                }
            } while (!(sce.getCause() instanceof SecurityException));
            throw sce;
        }
    }

    public static AsynchronousChannelProvider provider() {
        return ProviderHolder.provider;
    }
}
