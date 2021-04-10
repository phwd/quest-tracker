package sun.net.www.protocol.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Permission;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import sun.net.ProgressMonitor;
import sun.net.ProgressSource;
import sun.net.www.MessageHeader;
import sun.net.www.MeteredStream;
import sun.net.www.ParseUtil;
import sun.net.www.URLConnection;

public class FileURLConnection extends URLConnection {
    static String CONTENT_LENGTH = "content-length";
    static String CONTENT_TYPE = "content-type";
    static String LAST_MODIFIED = "last-modified";
    static String TEXT_PLAIN = "text/plain";
    String contentType;
    boolean exists = false;
    File file;
    String filename;
    List<String> files;
    private boolean initializedHeaders = false;
    InputStream is;
    boolean isDirectory = false;
    long lastModified = 0;
    long length = -1;
    Permission permission;

    protected FileURLConnection(URL u, File file2) {
        super(u);
        this.file = file2;
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        if (!this.connected) {
            try {
                this.filename = this.file.toString();
                this.isDirectory = this.file.isDirectory();
                if (this.isDirectory) {
                    String[] fileList = this.file.list();
                    if (fileList != null) {
                        this.files = Arrays.asList(fileList);
                    } else {
                        throw new FileNotFoundException(this.filename + " exists, but is not accessible");
                    }
                } else {
                    this.is = new BufferedInputStream(new FileInputStream(this.filename));
                    if (ProgressMonitor.getDefault().shouldMeterInput(this.url, "GET")) {
                        this.is = new MeteredStream(this.is, new ProgressSource(this.url, "GET", this.file.length()), this.file.length());
                    }
                }
                this.connected = true;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    private void initializeHeaders() {
        try {
            connect();
            this.exists = this.file.exists();
        } catch (IOException e) {
        }
        if (!this.initializedHeaders || !this.exists) {
            this.length = this.file.length();
            this.lastModified = this.file.lastModified();
            if (!this.isDirectory) {
                this.contentType = java.net.URLConnection.getFileNameMap().getContentTypeFor(this.filename);
                if (this.contentType != null) {
                    this.properties.add(CONTENT_TYPE, this.contentType);
                }
                this.properties.add(CONTENT_LENGTH, String.valueOf(this.length));
                long j = this.lastModified;
                if (j != 0) {
                    Date date = new Date(j);
                    SimpleDateFormat fo = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    fo.setTimeZone(TimeZone.getTimeZone("GMT"));
                    this.properties.add(LAST_MODIFIED, fo.format(date));
                }
            } else {
                this.properties.add(CONTENT_TYPE, TEXT_PLAIN);
            }
            this.initializedHeaders = true;
        }
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public String getHeaderField(String name) {
        initializeHeaders();
        return super.getHeaderField(name);
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public String getHeaderField(int n) {
        initializeHeaders();
        return super.getHeaderField(n);
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public int getContentLength() {
        initializeHeaders();
        long j = this.length;
        if (j > 2147483647L) {
            return -1;
        }
        return (int) j;
    }

    @Override // java.net.URLConnection
    public long getContentLengthLong() {
        initializeHeaders();
        return this.length;
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public String getHeaderFieldKey(int n) {
        initializeHeaders();
        return super.getHeaderFieldKey(n);
    }

    @Override // sun.net.www.URLConnection
    public MessageHeader getProperties() {
        initializeHeaders();
        return super.getProperties();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        initializeHeaders();
        return this.lastModified;
    }

    @Override // java.net.URLConnection
    public synchronized InputStream getInputStream() throws IOException {
        connect();
        if (this.is == null) {
            if (this.isDirectory) {
                java.net.URLConnection.getFileNameMap();
                StringBuffer buf = new StringBuffer();
                if (this.files != null) {
                    Collections.sort(this.files, Collator.getInstance());
                    for (int i = 0; i < this.files.size(); i++) {
                        buf.append(this.files.get(i));
                        buf.append("\n");
                    }
                    this.is = new ByteArrayInputStream(buf.toString().getBytes());
                } else {
                    throw new FileNotFoundException(this.filename);
                }
            } else {
                throw new FileNotFoundException(this.filename);
            }
        }
        return this.is;
    }

    @Override // java.net.URLConnection
    public Permission getPermission() throws IOException {
        if (this.permission == null) {
            String decodedPath = ParseUtil.decode(this.url.getPath());
            if (File.separatorChar == '/') {
                this.permission = new FilePermission(decodedPath, "read");
            } else {
                this.permission = new FilePermission(decodedPath.replace('/', File.separatorChar), "read");
            }
        }
        return this.permission;
    }
}
