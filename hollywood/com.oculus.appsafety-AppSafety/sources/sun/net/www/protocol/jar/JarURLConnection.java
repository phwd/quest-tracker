package sun.net.www.protocol.jar;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarURLConnection extends java.net.JarURLConnection {
    private static final boolean debug = false;
    private static final JarFileFactory factory = JarFileFactory.getInstance();
    private String contentType;
    private String entryName = getEntryName();
    private JarEntry jarEntry;
    private JarFile jarFile;
    private URL jarFileURL = getJarFileURL();
    private URLConnection jarFileURLConnection = this.jarFileURL.openConnection();
    private Permission permission;

    public JarURLConnection(URL url, Handler handler) throws MalformedURLException, IOException {
        super(url);
    }

    @Override // java.net.JarURLConnection
    public JarFile getJarFile() throws IOException {
        connect();
        return this.jarFile;
    }

    @Override // java.net.JarURLConnection
    public JarEntry getJarEntry() throws IOException {
        connect();
        return this.jarEntry;
    }

    @Override // java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.jarFileURLConnection.getPermission();
    }

    class JarURLInputStream extends FilterInputStream {
        JarURLInputStream(InputStream src) {
            super(src);
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            try {
                super.close();
            } finally {
                if (!JarURLConnection.this.getUseCaches()) {
                    JarURLConnection.this.jarFile.close();
                }
            }
        }
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        if (!this.connected) {
            this.jarFile = factory.get(getJarFileURL(), getUseCaches());
            if (getUseCaches()) {
                boolean oldUseCaches = this.jarFileURLConnection.getUseCaches();
                this.jarFileURLConnection = factory.getConnection(this.jarFile);
                this.jarFileURLConnection.setUseCaches(oldUseCaches);
            }
            String str = this.entryName;
            if (str != null) {
                this.jarEntry = (JarEntry) this.jarFile.getEntry(str);
                if (this.jarEntry == null) {
                    try {
                        if (!getUseCaches()) {
                            this.jarFile.close();
                        }
                    } catch (Exception e) {
                    }
                    throw new FileNotFoundException("JAR entry " + this.entryName + " not found in " + this.jarFile.getName());
                }
            }
            this.connected = true;
        }
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
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
    public int getContentLength() {
        long result = getContentLengthLong();
        if (result > 2147483647L) {
            return -1;
        }
        return (int) result;
    }

    @Override // java.net.URLConnection
    public long getContentLengthLong() {
        try {
            connect();
            if (this.jarEntry == null) {
                return this.jarFileURLConnection.getContentLengthLong();
            }
            return getJarEntry().getSize();
        } catch (IOException e) {
            return -1;
        }
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        connect();
        if (this.entryName == null) {
            return this.jarFile;
        }
        return super.getContent();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        if (this.contentType == null) {
            if (this.entryName == null) {
                this.contentType = "x-java/jar";
            } else {
                try {
                    connect();
                    InputStream in = this.jarFile.getInputStream(this.jarEntry);
                    this.contentType = guessContentTypeFromStream(new BufferedInputStream(in));
                    in.close();
                } catch (IOException e) {
                }
            }
            if (this.contentType == null) {
                this.contentType = guessContentTypeFromName(this.entryName);
            }
            if (this.contentType == null) {
                this.contentType = "content/unknown";
            }
        }
        return this.contentType;
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String name) {
        return this.jarFileURLConnection.getHeaderField(name);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String key, String value) {
        this.jarFileURLConnection.setRequestProperty(key, value);
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String key) {
        return this.jarFileURLConnection.getRequestProperty(key);
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String key, String value) {
        this.jarFileURLConnection.addRequestProperty(key, value);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.jarFileURLConnection.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean allowuserinteraction) {
        this.jarFileURLConnection.setAllowUserInteraction(allowuserinteraction);
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.jarFileURLConnection.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean usecaches) {
        this.jarFileURLConnection.setUseCaches(usecaches);
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.jarFileURLConnection.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long ifmodifiedsince) {
        this.jarFileURLConnection.setIfModifiedSince(ifmodifiedsince);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean defaultusecaches) {
        this.jarFileURLConnection.setDefaultUseCaches(defaultusecaches);
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.jarFileURLConnection.getDefaultUseCaches();
    }
}
