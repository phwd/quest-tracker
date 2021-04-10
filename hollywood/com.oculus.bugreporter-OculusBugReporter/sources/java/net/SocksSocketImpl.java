package java.net;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public class SocksSocketImpl extends PlainSocketImpl implements SocksConsts {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean applicationSetProxy;
    private InputStream cmdIn;
    private OutputStream cmdOut;
    private Socket cmdsock;
    private InetSocketAddress external_address;
    private String server;
    private int serverPort;
    private boolean useV4;

    SocksSocketImpl() {
        this.server = null;
        this.serverPort = SocksConsts.DEFAULT_PORT;
        this.useV4 = false;
        this.cmdsock = null;
        this.cmdIn = null;
        this.cmdOut = null;
    }

    SocksSocketImpl(String server2, int port) {
        this.server = null;
        int i = SocksConsts.DEFAULT_PORT;
        this.serverPort = SocksConsts.DEFAULT_PORT;
        this.useV4 = false;
        this.cmdsock = null;
        this.cmdIn = null;
        this.cmdOut = null;
        this.server = server2;
        this.serverPort = port != -1 ? port : i;
    }

    SocksSocketImpl(Proxy proxy) {
        this.server = null;
        this.serverPort = SocksConsts.DEFAULT_PORT;
        this.useV4 = false;
        this.cmdsock = null;
        this.cmdIn = null;
        this.cmdOut = null;
        SocketAddress a = proxy.address();
        if (a instanceof InetSocketAddress) {
            InetSocketAddress ad = (InetSocketAddress) a;
            this.server = ad.getHostString();
            this.serverPort = ad.getPort();
        }
    }

    /* access modifiers changed from: package-private */
    public void setV4() {
        this.useV4 = true;
    }

    private synchronized void privilegedConnect(final String host, final int port, final int timeout) throws IOException {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                /* class java.net.SocksSocketImpl.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws IOException {
                    SocksSocketImpl.this.superConnectServer(host, port, timeout);
                    SocksSocketImpl socksSocketImpl = SocksSocketImpl.this;
                    socksSocketImpl.cmdIn = socksSocketImpl.getInputStream();
                    SocksSocketImpl socksSocketImpl2 = SocksSocketImpl.this;
                    socksSocketImpl2.cmdOut = socksSocketImpl2.getOutputStream();
                    return null;
                }
            });
        } catch (PrivilegedActionException pae) {
            throw ((IOException) pae.getException());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void superConnectServer(String host, int port, int timeout) throws IOException {
        super.connect(new InetSocketAddress(host, port), timeout);
    }

    private static int remainingMillis(long deadlineMillis) throws IOException {
        if (deadlineMillis == 0) {
            return 0;
        }
        long remaining = deadlineMillis - System.currentTimeMillis();
        if (remaining > 0) {
            return (int) remaining;
        }
        throw new SocketTimeoutException();
    }

    private int readSocksReply(InputStream in, byte[] data) throws IOException {
        return readSocksReply(in, data, 0);
    }

    private int readSocksReply(InputStream in, byte[] data, long deadlineMillis) throws IOException {
        int len = data.length;
        int received = 0;
        int attempts = 0;
        while (received < len && attempts < 3) {
            try {
                int count = ((SocketInputStream) in).read(data, received, len - received, remainingMillis(deadlineMillis));
                if (count >= 0) {
                    received += count;
                    attempts++;
                } else {
                    throw new SocketException("Malformed reply from SOCKS server");
                }
            } catch (SocketTimeoutException e) {
                throw new SocketTimeoutException("Connect timed out");
            }
        }
        return received;
    }

    private boolean authenticate(byte method, InputStream in, BufferedOutputStream out) throws IOException {
        return authenticate(method, in, out, 0);
    }

    private boolean authenticate(byte method, InputStream in, BufferedOutputStream out, long deadlineMillis) throws IOException {
        String userName;
        if (method == 0) {
            return true;
        }
        if (method != 2) {
            return false;
        }
        String password = null;
        final InetAddress addr = InetAddress.getByName(this.server);
        PasswordAuthentication pw = (PasswordAuthentication) AccessController.doPrivileged(new PrivilegedAction<PasswordAuthentication>() {
            /* class java.net.SocksSocketImpl.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public PasswordAuthentication run() {
                return Authenticator.requestPasswordAuthentication(SocksSocketImpl.this.server, addr, SocksSocketImpl.this.serverPort, "SOCKS5", "SOCKS authentication", null);
            }
        });
        if (pw != null) {
            userName = pw.getUserName();
            password = new String(pw.getPassword());
        } else {
            userName = (String) AccessController.doPrivileged(new GetPropertyAction("user.name"));
        }
        if (userName == null) {
            return false;
        }
        out.write(1);
        out.write(userName.length());
        try {
            out.write(userName.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
        }
        if (password != null) {
            out.write(password.length());
            try {
                out.write(password.getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e2) {
            }
        } else {
            out.write(0);
        }
        out.flush();
        byte[] data = new byte[2];
        if (readSocksReply(in, data, deadlineMillis) == 2 && data[1] == 0) {
            return true;
        }
        out.close();
        in.close();
        return false;
    }

    private void connectV4(InputStream in, OutputStream out, InetSocketAddress endpoint, long deadlineMillis) throws IOException {
        if (endpoint.getAddress() instanceof Inet4Address) {
            out.write(4);
            out.write(1);
            out.write((endpoint.getPort() >> 8) & 255);
            out.write((endpoint.getPort() >> 0) & 255);
            out.write(endpoint.getAddress().getAddress());
            try {
                out.write(getUserName().getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
            }
            out.write(0);
            out.flush();
            byte[] data = new byte[8];
            int n = readSocksReply(in, data, deadlineMillis);
            if (n != 8) {
                throw new SocketException("Reply from SOCKS server has bad length: " + n);
            } else if (data[0] == 0 || data[0] == 4) {
                SocketException ex = null;
                switch (data[1]) {
                    case 90:
                        this.external_address = endpoint;
                        break;
                    case 91:
                        ex = new SocketException("SOCKS request rejected");
                        break;
                    case 92:
                        ex = new SocketException("SOCKS server couldn't reach destination");
                        break;
                    case 93:
                        ex = new SocketException("SOCKS authentication failed");
                        break;
                    default:
                        ex = new SocketException("Reply from SOCKS server contains bad status");
                        break;
                }
                if (ex != null) {
                    in.close();
                    out.close();
                    throw ex;
                }
            } else {
                throw new SocketException("Reply from SOCKS server has bad version");
            }
        } else {
            throw new SocketException("SOCKS V4 requires IPv4 only addresses");
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl, java.net.AbstractPlainSocketImpl
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        long deadlineMillis;
        if (timeout == 0) {
            deadlineMillis = 0;
        } else {
            long finish = System.currentTimeMillis() + ((long) timeout);
            deadlineMillis = finish < 0 ? Long.MAX_VALUE : finish;
        }
        SecurityManager security = System.getSecurityManager();
        if (endpoint == null || !(endpoint instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        InetSocketAddress epoint = (InetSocketAddress) endpoint;
        if (security != null) {
            if (epoint.isUnresolved()) {
                security.checkConnect(epoint.getHostName(), epoint.getPort());
            } else {
                security.checkConnect(epoint.getAddress().getHostAddress(), epoint.getPort());
            }
        }
        String str = this.server;
        if (str == null) {
            super.connect(epoint, remainingMillis(deadlineMillis));
            return;
        }
        try {
            privilegedConnect(str, this.serverPort, remainingMillis(deadlineMillis));
            BufferedOutputStream out = new BufferedOutputStream(this.cmdOut, 512);
            InputStream in = this.cmdIn;
            if (!this.useV4) {
                out.write(5);
                out.write(2);
                out.write(0);
                out.write(2);
                out.flush();
                byte[] data = new byte[2];
                if (readSocksReply(in, data, deadlineMillis) == 2) {
                    if (data[0] == 5) {
                        if (data[1] == -1) {
                            throw new SocketException("SOCKS : No acceptable methods");
                        } else if (authenticate(data[1], in, out, deadlineMillis)) {
                            out.write(5);
                            out.write(1);
                            out.write(0);
                            if (epoint.isUnresolved()) {
                                out.write(3);
                                out.write(epoint.getHostName().length());
                                try {
                                    out.write(epoint.getHostName().getBytes("ISO-8859-1"));
                                } catch (UnsupportedEncodingException e) {
                                }
                                out.write((epoint.getPort() >> 8) & 255);
                                out.write((epoint.getPort() >> 0) & 255);
                            } else if (epoint.getAddress() instanceof Inet6Address) {
                                out.write(4);
                                out.write(epoint.getAddress().getAddress());
                                out.write((epoint.getPort() >> 8) & 255);
                                out.write((epoint.getPort() >> 0) & 255);
                            } else {
                                out.write(1);
                                out.write(epoint.getAddress().getAddress());
                                out.write((epoint.getPort() >> 8) & 255);
                                out.write((epoint.getPort() >> 0) & 255);
                            }
                            out.flush();
                            byte[] data2 = new byte[4];
                            if (readSocksReply(in, data2, deadlineMillis) == 4) {
                                SocketException ex = null;
                                switch (data2[1]) {
                                    case 0:
                                        byte b = data2[3];
                                        if (b != 1) {
                                            if (b == 3) {
                                                byte b2 = data2[1];
                                                if (readSocksReply(in, new byte[b2], deadlineMillis) != b2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                } else if (readSocksReply(in, new byte[2], deadlineMillis) != 2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                }
                                            } else if (b != 4) {
                                                ex = new SocketException("Reply from SOCKS server contains wrong code");
                                                break;
                                            } else {
                                                byte b3 = data2[1];
                                                if (readSocksReply(in, new byte[b3], deadlineMillis) != b3) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                } else if (readSocksReply(in, new byte[2], deadlineMillis) != 2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                }
                                            }
                                        } else if (readSocksReply(in, new byte[4], deadlineMillis) != 4) {
                                            throw new SocketException("Reply from SOCKS server badly formatted");
                                        } else if (readSocksReply(in, new byte[2], deadlineMillis) != 2) {
                                            throw new SocketException("Reply from SOCKS server badly formatted");
                                        }
                                        break;
                                    case 1:
                                        ex = new SocketException("SOCKS server general failure");
                                        break;
                                    case 2:
                                        ex = new SocketException("SOCKS: Connection not allowed by ruleset");
                                        break;
                                    case 3:
                                        ex = new SocketException("SOCKS: Network unreachable");
                                        break;
                                    case 4:
                                        ex = new SocketException("SOCKS: Host unreachable");
                                        break;
                                    case 5:
                                        ex = new SocketException("SOCKS: Connection refused");
                                        break;
                                    case 6:
                                        ex = new SocketException("SOCKS: TTL expired");
                                        break;
                                    case 7:
                                        ex = new SocketException("SOCKS: Command not supported");
                                        break;
                                    case 8:
                                        ex = new SocketException("SOCKS: address type not supported");
                                        break;
                                }
                                if (ex == null) {
                                    this.external_address = epoint;
                                    return;
                                }
                                in.close();
                                out.close();
                                throw ex;
                            }
                            throw new SocketException("Reply from SOCKS server has bad length");
                        } else {
                            throw new SocketException("SOCKS : authentication failed");
                        }
                    }
                }
                if (!epoint.isUnresolved()) {
                    connectV4(in, out, epoint, deadlineMillis);
                    return;
                }
                throw new UnknownHostException(epoint.toString());
            } else if (!epoint.isUnresolved()) {
                connectV4(in, out, epoint, deadlineMillis);
            } else {
                throw new UnknownHostException(epoint.toString());
            }
        } catch (IOException e2) {
            throw new SocketException(e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public InetAddress getInetAddress() {
        InetSocketAddress inetSocketAddress = this.external_address;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getAddress();
        }
        return super.getInetAddress();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public int getPort() {
        InetSocketAddress inetSocketAddress = this.external_address;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return super.getPort();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public int getLocalPort() {
        if (this.socket != null) {
            return super.getLocalPort();
        }
        InetSocketAddress inetSocketAddress = this.external_address;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return super.getLocalPort();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl, java.net.AbstractPlainSocketImpl
    public void close() throws IOException {
        Socket socket = this.cmdsock;
        if (socket != null) {
            socket.close();
        }
        this.cmdsock = null;
        super.close();
    }

    private String getUserName() {
        if (!this.applicationSetProxy) {
            return (String) AccessController.doPrivileged(new GetPropertyAction("user.name"));
        }
        try {
            return System.getProperty("user.name");
        } catch (SecurityException e) {
            return "";
        }
    }
}
