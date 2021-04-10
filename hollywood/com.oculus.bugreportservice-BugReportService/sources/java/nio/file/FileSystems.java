package java.nio.file;

import java.net.URI;
import java.nio.file.spi.FileSystemProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.nio.fs.DefaultFileSystemProvider;

public final class FileSystems {

    /* access modifiers changed from: private */
    public static class DefaultFileSystemHolder {
        static final FileSystem defaultFileSystem = defaultFileSystem();

        private static FileSystem defaultFileSystem() {
            return ((FileSystemProvider) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.nio.file.FileSystems.DefaultFileSystemHolder.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public FileSystemProvider run() {
                    return DefaultFileSystemHolder.getDefaultProvider();
                }
            })).getFileSystem(URI.create("file:///"));
        }

        /* access modifiers changed from: private */
        public static FileSystemProvider getDefaultProvider() {
            FileSystemProvider create = DefaultFileSystemProvider.create();
            String property = System.getProperty("java.nio.file.spi.DefaultFileSystemProvider");
            if (property == null) {
                return create;
            }
            FileSystemProvider fileSystemProvider = create;
            for (String str : property.split(",")) {
                try {
                    fileSystemProvider = (FileSystemProvider) Class.forName(str, true, ClassLoader.getSystemClassLoader()).getDeclaredConstructor(FileSystemProvider.class).newInstance(fileSystemProvider);
                    if (!fileSystemProvider.getScheme().equals("file")) {
                        throw new Error("Default provider must use scheme 'file'");
                    }
                } catch (Exception e) {
                    throw new Error(e);
                }
            }
            return fileSystemProvider;
        }
    }

    public static FileSystem getDefault() {
        return DefaultFileSystemHolder.defaultFileSystem;
    }
}
