package sun.net.www.protocol.ftp;

import android.icu.text.DateFormat;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketPermission;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.StringTokenizer;
import libcore.net.NetworkSecurityPolicy;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpLoginException;
import sun.net.ftp.FtpProtocolException;
import sun.net.www.ParseUtil;
import sun.net.www.URLConnection;
import sun.security.action.GetPropertyAction;
import sun.security.util.SecurityConstants;

public class FtpURLConnection extends URLConnection {
    static final int ASCII = 1;
    static final int BIN = 2;
    static final int DIR = 3;
    static final int NONE = 0;
    private int connectTimeout;
    String filename;
    FtpClient ftp;
    String fullpath;
    String host;
    private Proxy instProxy;
    InputStream is;
    OutputStream os;
    String password;
    String pathname;
    Permission permission;
    int port;
    private int readTimeout;
    int type;
    String user;

    protected class FtpInputStream extends FilterInputStream {
        FtpClient ftp;

        FtpInputStream(FtpClient cl, InputStream fd) {
            super(new BufferedInputStream(fd));
            this.ftp = cl;
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            super.close();
            FtpClient ftpClient = this.ftp;
            if (ftpClient != null) {
                ftpClient.close();
            }
        }
    }

    protected class FtpOutputStream extends FilterOutputStream {
        FtpClient ftp;

        FtpOutputStream(FtpClient cl, OutputStream fd) {
            super(fd);
            this.ftp = cl;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            FtpClient ftpClient = this.ftp;
            if (ftpClient != null) {
                ftpClient.close();
            }
        }
    }

    public FtpURLConnection(URL url) throws IOException {
        this(url, null);
    }

    FtpURLConnection(URL url, Proxy p) throws IOException {
        super(url);
        String str;
        this.is = null;
        this.os = null;
        this.ftp = null;
        this.type = 0;
        this.connectTimeout = -1;
        this.readTimeout = -1;
        this.instProxy = p;
        this.host = url.getHost();
        this.port = url.getPort();
        String userInfo = url.getUserInfo();
        if (!NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cleartext traffic not permitted: ");
            sb.append(url.getProtocol());
            sb.append("://");
            sb.append(this.host);
            if (url.getPort() >= 0) {
                str = ":" + url.getPort();
            } else {
                str = "";
            }
            sb.append(str);
            throw new IOException(sb.toString());
        } else if (userInfo != null) {
            int delimiter = userInfo.indexOf(58);
            if (delimiter == -1) {
                this.user = ParseUtil.decode(userInfo);
                this.password = null;
                return;
            }
            this.user = ParseUtil.decode(userInfo.substring(0, delimiter));
            this.password = ParseUtil.decode(userInfo.substring(delimiter + 1));
        }
    }

    private void setTimeouts() {
        FtpClient ftpClient = this.ftp;
        if (ftpClient != null) {
            int i = this.connectTimeout;
            if (i >= 0) {
                ftpClient.setConnectTimeout(i);
            }
            int i2 = this.readTimeout;
            if (i2 >= 0) {
                this.ftp.setReadTimeout(i2);
            }
        }
    }

    @Override // java.net.URLConnection
    public synchronized void connect() throws IOException {
        if (!this.connected) {
            Proxy p = null;
            if (this.instProxy == null) {
                ProxySelector sel = (ProxySelector) AccessController.doPrivileged(new PrivilegedAction<ProxySelector>() {
                    /* class sun.net.www.protocol.ftp.FtpURLConnection.AnonymousClass1 */

                    @Override // java.security.PrivilegedAction
                    public ProxySelector run() {
                        return ProxySelector.getDefault();
                    }
                });
                if (sel != null) {
                    URI uri = ParseUtil.toURI(this.url);
                    Iterator<Proxy> it = sel.select(uri).iterator();
                    while (it.hasNext() && (p = it.next()) != null && p != Proxy.NO_PROXY && p.type() != Proxy.Type.SOCKS) {
                        if (p.type() != Proxy.Type.HTTP || !(p.address() instanceof InetSocketAddress)) {
                            sel.connectFailed(uri, p.address(), new IOException("Wrong proxy type"));
                        } else {
                            sel.connectFailed(uri, p.address(), new IOException("FTP connections over HTTP proxy not supported"));
                        }
                    }
                }
            } else {
                p = this.instProxy;
            }
            if (this.user == null) {
                this.user = "anonymous";
                this.password = (String) AccessController.doPrivileged(new GetPropertyAction("ftp.protocol.user", "Java" + ((String) AccessController.doPrivileged(new GetPropertyAction("java.version"))) + "@"));
            }
            try {
                this.ftp = FtpClient.create();
                if (p != null) {
                    this.ftp.setProxy(p);
                }
                setTimeouts();
                if (this.port != -1) {
                    this.ftp.connect(new InetSocketAddress(this.host, this.port));
                } else {
                    this.ftp.connect(new InetSocketAddress(this.host, FtpClient.defaultPort()));
                }
                try {
                    this.ftp.login(this.user, this.password == null ? null : this.password.toCharArray());
                    this.connected = true;
                } catch (FtpProtocolException e) {
                    this.ftp.close();
                    throw new FtpLoginException("Invalid username/password");
                }
            } catch (UnknownHostException e2) {
                throw e2;
            } catch (FtpProtocolException fe) {
                throw new IOException(fe);
            }
        }
    }

    private void decodePath(String path) {
        int i = path.indexOf(";type=");
        if (i >= 0) {
            String s1 = path.substring(i + 6, path.length());
            if ("i".equalsIgnoreCase(s1)) {
                this.type = 2;
            }
            if ("a".equalsIgnoreCase(s1)) {
                this.type = 1;
            }
            if (DateFormat.DAY.equalsIgnoreCase(s1)) {
                this.type = 3;
            }
            path = path.substring(0, i);
        }
        if (path != null && path.length() > 1 && path.charAt(0) == '/') {
            path = path.substring(1);
        }
        if (path == null || path.length() == 0) {
            path = "./";
        }
        if (!path.endsWith("/")) {
            int i2 = path.lastIndexOf(47);
            if (i2 > 0) {
                this.filename = path.substring(i2 + 1, path.length());
                this.filename = ParseUtil.decode(this.filename);
                this.pathname = path.substring(0, i2);
            } else {
                this.filename = ParseUtil.decode(path);
                this.pathname = null;
            }
        } else {
            this.pathname = path.substring(0, path.length() - 1);
            this.filename = null;
        }
        if (this.pathname != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.pathname);
            sb.append("/");
            String str = this.filename;
            if (str == null) {
                str = "";
            }
            sb.append(str);
            this.fullpath = sb.toString();
            return;
        }
        this.fullpath = this.filename;
    }

    private void cd(String path) throws FtpProtocolException, IOException {
        if (path != null && !path.isEmpty()) {
            if (path.indexOf(47) == -1) {
                this.ftp.changeDirectory(ParseUtil.decode(path));
                return;
            }
            StringTokenizer token = new StringTokenizer(path, "/");
            while (token.hasMoreTokens()) {
                this.ftp.changeDirectory(ParseUtil.decode(token.nextToken()));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f A[Catch:{ Exception -> 0x00c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ce  */
    @Override // java.net.URLConnection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream getInputStream() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 313
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.www.protocol.ftp.FtpURLConnection.getInputStream():java.io.InputStream");
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        if (!this.connected) {
            connect();
        }
        if (this.is == null) {
            OutputStream outputStream = this.os;
            if (outputStream != null) {
                return outputStream;
            }
            decodePath(this.url.getPath());
            String str = this.filename;
            if (str == null || str.length() == 0) {
                throw new IOException("illegal filename for a PUT");
            }
            try {
                if (this.pathname != null) {
                    cd(this.pathname);
                }
                if (this.type == 1) {
                    this.ftp.setAsciiType();
                } else {
                    this.ftp.setBinaryType();
                }
                this.os = new FtpOutputStream(this.ftp, this.ftp.putFileStream(this.filename, false));
                return this.os;
            } catch (FtpProtocolException e) {
                throw new IOException(e);
            }
        } else {
            throw new IOException("Already opened for input");
        }
    }

    /* access modifiers changed from: package-private */
    public String guessContentTypeFromFilename(String fname) {
        return guessContentTypeFromName(fname);
    }

    @Override // java.net.URLConnection
    public Permission getPermission() {
        if (this.permission == null) {
            int urlport = this.url.getPort();
            int urlport2 = urlport < 0 ? FtpClient.defaultPort() : urlport;
            this.permission = new SocketPermission(this.host + ":" + urlport2, SecurityConstants.SOCKET_CONNECT_ACTION);
        }
        return this.permission;
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public void setRequestProperty(String key, String value) {
        super.setRequestProperty(key, value);
        if (!"type".equals(key)) {
            return;
        }
        if ("i".equalsIgnoreCase(value)) {
            this.type = 2;
        } else if ("a".equalsIgnoreCase(value)) {
            this.type = 1;
        } else if (DateFormat.DAY.equalsIgnoreCase(value)) {
            this.type = 3;
        } else {
            throw new IllegalArgumentException("Value of '" + key + "' request property was '" + value + "' when it must be either 'i', 'a' or 'd'");
        }
    }

    @Override // java.net.URLConnection, sun.net.www.URLConnection
    public String getRequestProperty(String key) {
        String value = super.getRequestProperty(key);
        if (value != null || !"type".equals(key)) {
            return value;
        }
        int i = this.type;
        return i == 1 ? "a" : i == 3 ? DateFormat.DAY : "i";
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int timeout) {
        if (timeout >= 0) {
            this.connectTimeout = timeout;
            return;
        }
        throw new IllegalArgumentException("timeouts can't be negative");
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        int i = this.connectTimeout;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int timeout) {
        if (timeout >= 0) {
            this.readTimeout = timeout;
            return;
        }
        throw new IllegalArgumentException("timeouts can't be negative");
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        int i = this.readTimeout;
        if (i < 0) {
            return 0;
        }
        return i;
    }
}
