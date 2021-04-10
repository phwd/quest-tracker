package sun.net.www.protocol.ftp;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
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

public class FtpURLConnection extends URLConnection {
    private int connectTimeout = -1;
    String filename;
    FtpClient ftp = null;
    String fullpath;
    String host;
    private Proxy instProxy;
    InputStream is = null;
    OutputStream os = null;
    String password;
    String pathname;
    Permission permission;
    int port;
    private int readTimeout = -1;
    int type = 0;
    String user;

    protected class FtpInputStream extends FilterInputStream {
        FtpClient ftp;

        FtpInputStream(FtpClient ftpClient, InputStream inputStream) {
            super(new BufferedInputStream(inputStream));
            this.ftp = ftpClient;
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            super.close();
            FtpClient ftpClient = this.ftp;
            if (ftpClient != null) {
                ftpClient.close();
            }
        }
    }

    FtpURLConnection(URL url, Proxy proxy) {
        super(url);
        String str;
        this.instProxy = proxy;
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
            int indexOf = userInfo.indexOf(58);
            if (indexOf == -1) {
                this.user = ParseUtil.decode(userInfo);
                this.password = null;
                return;
            }
            this.user = ParseUtil.decode(userInfo.substring(0, indexOf));
            this.password = ParseUtil.decode(userInfo.substring(indexOf + 1));
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

    public synchronized void connect() {
        Proxy proxy;
        if (!this.connected) {
            char[] cArr = null;
            if (this.instProxy == null) {
                ProxySelector proxySelector = (ProxySelector) AccessController.doPrivileged(new PrivilegedAction() {
                    /* class sun.net.www.protocol.ftp.FtpURLConnection.AnonymousClass1 */

                    @Override // java.security.PrivilegedAction
                    public ProxySelector run() {
                        return ProxySelector.getDefault();
                    }
                });
                if (proxySelector != null) {
                    URI uri = ParseUtil.toURI(this.url);
                    Iterator it = proxySelector.select(uri).iterator();
                    proxy = null;
                    while (it.hasNext() && (proxy = (Proxy) it.next()) != null && proxy != Proxy.NO_PROXY && proxy.type() != Proxy.Type.SOCKS) {
                        if (proxy.type() != Proxy.Type.HTTP || !(proxy.address() instanceof InetSocketAddress)) {
                            proxySelector.connectFailed(uri, proxy.address(), new IOException("Wrong proxy type"));
                        } else {
                            proxySelector.connectFailed(uri, proxy.address(), new IOException("FTP connections over HTTP proxy not supported"));
                        }
                    }
                } else {
                    proxy = null;
                }
            } else {
                proxy = this.instProxy;
            }
            if (this.user == null) {
                this.user = "anonymous";
                this.password = (String) AccessController.doPrivileged(new GetPropertyAction("ftp.protocol.user", "Java" + ((String) AccessController.doPrivileged(new GetPropertyAction("java.version"))) + "@"));
            }
            try {
                this.ftp = FtpClient.create();
                if (proxy != null) {
                    this.ftp.setProxy(proxy);
                }
                setTimeouts();
                if (this.port != -1) {
                    this.ftp.connect(new InetSocketAddress(this.host, this.port));
                } else {
                    this.ftp.connect(new InetSocketAddress(this.host, FtpClient.defaultPort()));
                }
                try {
                    FtpClient ftpClient = this.ftp;
                    String str = this.user;
                    if (this.password != null) {
                        cArr = this.password.toCharArray();
                    }
                    ftpClient.login(str, cArr);
                    this.connected = true;
                } catch (FtpProtocolException unused) {
                    this.ftp.close();
                    throw new FtpLoginException("Invalid username/password");
                }
            } catch (UnknownHostException e) {
                throw e;
            } catch (FtpProtocolException e2) {
                throw new IOException(e2);
            }
        }
    }

    private void decodePath(String str) {
        int indexOf = str.indexOf(";type=");
        if (indexOf >= 0) {
            String substring = str.substring(indexOf + 6, str.length());
            if ("i".equalsIgnoreCase(substring)) {
                this.type = 2;
            }
            if ("a".equalsIgnoreCase(substring)) {
                this.type = 1;
            }
            if ("d".equalsIgnoreCase(substring)) {
                this.type = 3;
            }
            str = str.substring(0, indexOf);
        }
        if (str != null && str.length() > 1 && str.charAt(0) == '/') {
            str = str.substring(1);
        }
        if (str == null || str.length() == 0) {
            str = "./";
        }
        if (!str.endsWith("/")) {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf > 0) {
                this.filename = str.substring(lastIndexOf + 1, str.length());
                this.filename = ParseUtil.decode(this.filename);
                this.pathname = str.substring(0, lastIndexOf);
            } else {
                this.filename = ParseUtil.decode(str);
                this.pathname = null;
            }
        } else {
            this.pathname = str.substring(0, str.length() - 1);
            this.filename = null;
        }
        if (this.pathname != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.pathname);
            sb.append("/");
            String str2 = this.filename;
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            this.fullpath = sb.toString();
            return;
        }
        this.fullpath = this.filename;
    }

    private void cd(String str) {
        if (str != null && !str.isEmpty()) {
            if (str.indexOf(47) == -1) {
                this.ftp.changeDirectory(ParseUtil.decode(str));
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, "/");
            while (stringTokenizer.hasMoreTokens()) {
                this.ftp.changeDirectory(ParseUtil.decode(stringTokenizer.nextToken()));
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x00e8 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009a A[Catch:{ Exception -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dd  */
    @Override // java.net.URLConnection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream getInputStream() {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.www.protocol.ftp.FtpURLConnection.getInputStream():java.io.InputStream");
    }

    @Override // java.net.URLConnection
    public Permission getPermission() {
        if (this.permission == null) {
            int port2 = this.url.getPort();
            if (port2 < 0) {
                port2 = FtpClient.defaultPort();
            }
            this.permission = new SocketPermission(this.host + ":" + port2, "connect");
        }
        return this.permission;
    }
}
