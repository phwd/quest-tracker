package libcore.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
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

    public ClassPathURLStreamHandler(String jarFileName) throws IOException {
        this.jarFile = new JarFile(jarFileName);
        this.fileUri = new File(jarFileName).toURI().toString();
    }

    public URL getEntryUrlOrNull(String entryName) {
        if (this.jarFile.getEntry(entryName) == null) {
            return null;
        }
        try {
            String encodedName = ParseUtil.encodePath(entryName, false);
            return new URL("jar", null, -1, this.fileUri + "!/" + encodedName, this);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid entry name", e);
        }
    }

    public boolean isEntryStored(String entryName) {
        ZipEntry entry = this.jarFile.getEntry(entryName);
        return entry != null && entry.getMethod() == 0;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler, sun.net.www.protocol.jar.Handler
    public URLConnection openConnection(URL url) throws IOException {
        return new ClassPathURLConnection(url);
    }

    public void close() throws IOException {
        this.jarFile.close();
    }

    private class ClassPathURLConnection extends JarURLConnection {
        private boolean closed;
        private JarFile connectionJarFile;
        private ZipEntry jarEntry;
        private InputStream jarInput;
        private boolean useCachedJarFile;

        public ClassPathURLConnection(URL url) throws MalformedURLException {
            super(url);
        }

        @Override // java.net.URLConnection
        public void connect() throws IOException {
            if (!this.connected) {
                this.jarEntry = ClassPathURLStreamHandler.this.jarFile.getEntry(getEntryName());
                if (this.jarEntry != null) {
                    this.useCachedJarFile = getUseCaches();
                    this.connected = true;
                    return;
                }
                throw new FileNotFoundException("URL does not correspond to an entry in the zip file. URL=" + ((Object) this.url) + ", zipfile=" + ClassPathURLStreamHandler.this.jarFile.getName());
            }
        }

        @Override // java.net.JarURLConnection
        public JarFile getJarFile() throws IOException {
            connect();
            if (this.useCachedJarFile) {
                this.connectionJarFile = ClassPathURLStreamHandler.this.jarFile;
            } else {
                this.connectionJarFile = new JarFile(ClassPathURLStreamHandler.this.jarFile.getName());
            }
            return this.connectionJarFile;
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() throws IOException {
            if (!this.closed) {
                connect();
                InputStream inputStream = this.jarInput;
                if (inputStream != null) {
                    return inputStream;
                }
                AnonymousClass1 r0 = new FilterInputStream(ClassPathURLStreamHandler.this.jarFile.getInputStream(this.jarEntry)) {
                    /* class libcore.io.ClassPathURLStreamHandler.ClassPathURLConnection.AnonymousClass1 */

                    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
                    public void close() throws IOException {
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

        @Override // java.net.URLConnection
        public String getContentType() {
            String cType = guessContentTypeFromName(getEntryName());
            if (cType == null) {
                return "content/unknown";
            }
            return cType;
        }

        @Override // java.net.URLConnection
        public int getContentLength() {
            try {
                connect();
                return (int) getJarEntry().getSize();
            } catch (IOException e) {
                return -1;
            }
        }
    }
}
