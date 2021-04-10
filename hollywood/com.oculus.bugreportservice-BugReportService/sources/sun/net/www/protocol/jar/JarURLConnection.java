package sun.net.www.protocol.jar;

import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarURLConnection extends java.net.JarURLConnection {
    private static final JarFileFactory factory = JarFileFactory.getInstance();
    private String entryName = getEntryName();
    private JarEntry jarEntry;
    private JarFile jarFile;
    private URL jarFileURL = getJarFileURL();
    private URLConnection jarFileURLConnection = this.jarFileURL.openConnection();

    public JarURLConnection(URL url, Handler handler) {
        super(url);
    }

    @Override // java.net.JarURLConnection
    public JarFile getJarFile() {
        connect();
        return this.jarFile;
    }

    @Override // java.net.JarURLConnection
    public JarEntry getJarEntry() {
        connect();
        return this.jarEntry;
    }

    @Override // java.net.URLConnection
    public Permission getPermission() {
        return this.jarFileURLConnection.getPermission();
    }

    class JarURLInputStream extends FilterInputStream {
        JarURLInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            try {
                super.close();
            } finally {
                if (!JarURLConnection.this.getUseCaches()) {
                    JarURLConnection.this.jarFile.close();
                }
            }
        }
    }

    public void connect() {
        if (!this.connected) {
            this.jarFile = factory.get(getJarFileURL(), getUseCaches());
            if (getUseCaches()) {
                boolean useCaches = this.jarFileURLConnection.getUseCaches();
                this.jarFileURLConnection = factory.getConnection(this.jarFile);
                this.jarFileURLConnection.setUseCaches(useCaches);
            }
            String str = this.entryName;
            if (str != null) {
                this.jarEntry = (JarEntry) this.jarFile.getEntry(str);
                if (this.jarEntry == null) {
                    try {
                        if (!getUseCaches()) {
                            this.jarFile.close();
                        }
                    } catch (Exception unused) {
                    }
                    throw new FileNotFoundException("JAR entry " + this.entryName + " not found in " + this.jarFile.getName());
                }
            }
            this.connected = true;
        }
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() {
        connect();
        if (this.entryName != null) {
            JarEntry jarEntry2 = this.jarEntry;
            if (jarEntry2 != null) {
                return new JarURLInputStream(this.jarFile.getInputStream(jarEntry2));
            }
            throw new FileNotFoundException("JAR entry " + this.entryName + " not found in " + this.jarFile.getName());
        }
        throw new IOException("no entry name specified");
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        return this.jarFileURLConnection.getHeaderField(str);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.jarFileURLConnection.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.jarFileURLConnection.getUseCaches();
    }
}
