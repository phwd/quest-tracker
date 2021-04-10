package java.nio.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.spi.FileSystemProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import sun.nio.fs.DefaultFileSystemProvider;

public final class FileSystems {
    private FileSystems() {
    }

    /* access modifiers changed from: private */
    public static class DefaultFileSystemHolder {
        static final FileSystem defaultFileSystem = defaultFileSystem();

        private DefaultFileSystemHolder() {
        }

        private static FileSystem defaultFileSystem() {
            return ((FileSystemProvider) AccessController.doPrivileged(new PrivilegedAction<FileSystemProvider>() {
                /* class java.nio.file.FileSystems.DefaultFileSystemHolder.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public FileSystemProvider run() {
                    return DefaultFileSystemHolder.getDefaultProvider();
                }
            })).getFileSystem(URI.create("file:///"));
        }

        /* access modifiers changed from: private */
        public static FileSystemProvider getDefaultProvider() {
            FileSystemProvider provider = DefaultFileSystemProvider.create();
            String propValue = System.getProperty("java.nio.file.spi.DefaultFileSystemProvider");
            if (propValue == null) {
                return provider;
            }
            FileSystemProvider provider2 = provider;
            for (String cn : propValue.split(",")) {
                try {
                    provider2 = (FileSystemProvider) Class.forName(cn, true, ClassLoader.getSystemClassLoader()).getDeclaredConstructor(FileSystemProvider.class).newInstance(provider2);
                    if (!provider2.getScheme().equals("file")) {
                        throw new Error("Default provider must use scheme 'file'");
                    }
                } catch (Exception x) {
                    throw new Error(x);
                }
            }
            return provider2;
        }
    }

    public static FileSystem getDefault() {
        return DefaultFileSystemHolder.defaultFileSystem;
    }

    public static FileSystem getFileSystem(URI uri) {
        String scheme = uri.getScheme();
        for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
            if (scheme.equalsIgnoreCase(provider.getScheme())) {
                return provider.getFileSystem(uri);
            }
        }
        throw new ProviderNotFoundException("Provider \"" + scheme + "\" not found");
    }

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException {
        return newFileSystem(uri, env, null);
    }

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env, ClassLoader loader) throws IOException {
        String scheme = uri.getScheme();
        for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
            if (scheme.equalsIgnoreCase(provider.getScheme())) {
                return provider.newFileSystem(uri, env);
            }
        }
        if (loader != null) {
            Iterator<FileSystemProvider> it = ServiceLoader.load(FileSystemProvider.class, loader).iterator();
            while (it.hasNext()) {
                FileSystemProvider provider2 = it.next();
                if (scheme.equalsIgnoreCase(provider2.getScheme())) {
                    return provider2.newFileSystem(uri, env);
                }
            }
        }
        throw new ProviderNotFoundException("Provider \"" + scheme + "\" not found");
    }

    public static FileSystem newFileSystem(Path path, ClassLoader loader) throws IOException {
        if (path != null) {
            Map<String, ?> env = Collections.emptyMap();
            Iterator<FileSystemProvider> it = FileSystemProvider.installedProviders().iterator();
            while (it.hasNext()) {
                try {
                    return it.next().newFileSystem(path, env);
                } catch (UnsupportedOperationException e) {
                }
            }
            if (loader != null) {
                Iterator<FileSystemProvider> it2 = ServiceLoader.load(FileSystemProvider.class, loader).iterator();
                while (it2.hasNext()) {
                    try {
                        return it2.next().newFileSystem(path, env);
                    } catch (UnsupportedOperationException e2) {
                    }
                }
            }
            throw new ProviderNotFoundException("Provider not found");
        }
        throw new NullPointerException();
    }
}
