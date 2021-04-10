package libcore.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import sun.net.www.ParseUtil;
import sun.net.www.protocol.jar.Handler;

public class ClassPathURLStreamHandler extends Handler {
    private final String fileUri;
    private final JarFile jarFile;

    public ClassPathURLStreamHandler(String str) {
        this.jarFile = new JarFile(str);
        this.fileUri = new File(str).toURI().toString();
    }

    public URL getEntryUrlOrNull(String str) {
        if (this.jarFile.getEntry(str) == null) {
            return null;
        }
        try {
            ParseUtil.encodePath(str, false);
            throw null;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid entry name", e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler, sun.net.www.protocol.jar.Handler
    public URLConnection openConnection(URL url) {
        return new ClassPathURLConnection(url);
    }

    private class ClassPathURLConnection extends JarURLConnection {
        private boolean closed;
        private JarFile connectionJarFile;
        private ZipEntry jarEntry;
        private InputStream jarInput;
        private boolean useCachedJarFile;

        public ClassPathURLConnection(URL url) {
            super(url);
        }

        public void connect() {
            if (!this.connected) {
                this.jarEntry = ClassPathURLStreamHandler.this.jarFile.getEntry(getEntryName());
                if (this.jarEntry != null) {
                    this.useCachedJarFile = getUseCaches();
                    this.connected = true;
                    return;
                }
                throw new FileNotFoundException("URL does not correspond to an entry in the zip file. URL=" + this.url + ", zipfile=" + ClassPathURLStreamHandler.this.jarFile.getName());
            }
        }

        @Override // java.net.JarURLConnection
        public JarFile getJarFile() {
            connect();
            if (this.useCachedJarFile) {
                this.connectionJarFile = ClassPathURLStreamHandler.this.jarFile;
            } else {
                this.connectionJarFile = new JarFile(ClassPathURLStreamHandler.this.jarFile.getName());
            }
            return this.connectionJarFile;
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() {
            if (!this.closed) {
                connect();
                InputStream inputStream = this.jarInput;
                if (inputStream != null) {
                    return inputStream;
                }
                AnonymousClass1 r0 = new FilterInputStream(ClassPathURLStreamHandler.this.jarFile.getInputStream(this.jarEntry)) {
                    /* class libcore.io.ClassPathURLStreamHandler.ClassPathURLConnection.AnonymousClass1 */

                    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
                    public void close() {
                        super.close();
                        if (ClassPathURLConnection.this.connectionJarFile != null && !ClassPathURLConnection.this.useCachedJarFile) {
                            ClassPathURLConnection.this.connectionJarFile.close();
                            ClassPathURLConnection.this.closed = true;
                        }
                    }
                };
                this.jarInput = r0;
                return r0;
            }
            throw new IllegalStateException("JarURLConnection InputStream has been closed");
        }
    }
}
