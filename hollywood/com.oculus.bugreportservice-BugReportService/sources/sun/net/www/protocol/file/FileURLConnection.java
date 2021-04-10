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
    List files;
    private boolean initializedHeaders = false;
    InputStream is;
    boolean isDirectory = false;
    long lastModified = 0;
    long length = -1;
    Permission permission;

    protected FileURLConnection(URL url, File file2) {
        super(url);
        this.file = file2;
    }

    public void connect() {
        if (!this.connected) {
            try {
                this.filename = this.file.toString();
                this.isDirectory = this.file.isDirectory();
                if (this.isDirectory) {
                    String[] list = this.file.list();
                    if (list != null) {
                        this.files = Arrays.asList(list);
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
        } catch (IOException unused) {
        }
        if (!this.initializedHeaders || !this.exists) {
            this.length = this.file.length();
            this.lastModified = this.file.lastModified();
            if (!this.isDirectory) {
                this.contentType = java.net.URLConnection.getFileNameMap().getContentTypeFor(this.filename);
                String str = this.contentType;
                if (str != null) {
                    this.properties.add(CONTENT_TYPE, str);
                }
                this.properties.add(CONTENT_LENGTH, String.valueOf(this.length));
                long j = this.lastModified;
                if (j != 0) {
                    Date date = new Date(j);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    this.properties.add(LAST_MODIFIED, simpleDateFormat.format(date));
                }
            } else {
                this.properties.add(CONTENT_TYPE, TEXT_PLAIN);
            }
            this.initializedHeaders = true;
        }
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public String getHeaderField(String str) {
        initializeHeaders();
        return super.getHeaderField(str);
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        initializeHeaders();
        return this.lastModified;
    }

    @Override // java.net.URLConnection
    public synchronized InputStream getInputStream() {
        connect();
        if (this.is == null) {
            if (this.isDirectory) {
                java.net.URLConnection.getFileNameMap();
                StringBuffer stringBuffer = new StringBuffer();
                if (this.files != null) {
                    Collections.sort(this.files, Collator.getInstance());
                    for (int i = 0; i < this.files.size(); i++) {
                        stringBuffer.append((String) this.files.get(i));
                        stringBuffer.append("\n");
                    }
                    this.is = new ByteArrayInputStream(stringBuffer.toString().getBytes());
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
    public Permission getPermission() {
        if (this.permission == null) {
            String decode = ParseUtil.decode(this.url.getPath());
            char c = File.separatorChar;
            if (c == '/') {
                this.permission = new FilePermission(decode, "read");
            } else {
                this.permission = new FilePermission(decode.replace('/', c), "read");
            }
        }
        return this.permission;
    }
}
