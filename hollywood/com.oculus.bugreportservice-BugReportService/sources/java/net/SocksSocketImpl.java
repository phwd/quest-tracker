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
    private boolean applicationSetProxy;
    private InputStream cmdIn = null;
    private OutputStream cmdOut = null;
    private Socket cmdsock = null;
    private InetSocketAddress external_address;
    private String server = null;
    private int serverPort = 1080;
    private boolean useV4 = false;

    SocksSocketImpl() {
    }

    SocksSocketImpl(Proxy proxy) {
        SocketAddress address = proxy.address();
        if (address instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
            this.server = inetSocketAddress.getHostString();
            this.serverPort = inetSocketAddress.getPort();
        }
    }

    private synchronized void privilegedConnect(final String str, final int i, final int i2) {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                /* class java.net.SocksSocketImpl.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Void run() {
                    SocksSocketImpl.this.superConnectServer(str, i, i2);
                    SocksSocketImpl socksSocketImpl = SocksSocketImpl.this;
                    socksSocketImpl.cmdIn = socksSocketImpl.getInputStream();
                    SocksSocketImpl socksSocketImpl2 = SocksSocketImpl.this;
                    socksSocketImpl2.cmdOut = socksSocketImpl2.getOutputStream();
                    return null;
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void superConnectServer(String str, int i, int i2) {
        super.connect(new InetSocketAddress(str, i), i2);
    }

    private static int remainingMillis(long j) {
        if (j == 0) {
            return 0;
        }
        long currentTimeMillis = j - System.currentTimeMillis();
        if (currentTimeMillis > 0) {
            return (int) currentTimeMillis;
        }
        throw new SocketTimeoutException();
    }

    private int readSocksReply(InputStream inputStream, byte[] bArr, long j) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length && i2 < 3) {
            try {
                int read = ((SocketInputStream) inputStream).read(bArr, i, length - i, remainingMillis(j));
                if (read >= 0) {
                    i += read;
                    i2++;
                } else {
                    throw new SocketException("Malformed reply from SOCKS server");
                }
            } catch (SocketTimeoutException unused) {
                throw new SocketTimeoutException("Connect timed out");
            }
        }
        return i;
    }

    private boolean authenticate(byte b, InputStream inputStream, BufferedOutputStream bufferedOutputStream, long j) {
        if (b == 0) {
            return true;
        }
        if (b != 2) {
            return false;
        }
        final InetAddress byName = InetAddress.getByName(this.server);
        PasswordAuthentication passwordAuthentication = (PasswordAuthentication) AccessController.doPrivileged(new PrivilegedAction() {
            /* class java.net.SocksSocketImpl.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public PasswordAuthentication run() {
                return Authenticator.requestPasswordAuthentication(SocksSocketImpl.this.server, byName, SocksSocketImpl.this.serverPort, "SOCKS5", "SOCKS authentication", null);
            }
        });
        if (passwordAuthentication == null) {
            String str = (String) AccessController.doPrivileged(new GetPropertyAction("user.name"));
            if (str == null) {
                return false;
            }
            bufferedOutputStream.write(1);
            bufferedOutputStream.write(str.length());
            try {
                bufferedOutputStream.write(str.getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException unused) {
            }
            bufferedOutputStream.write(0);
            bufferedOutputStream.flush();
            byte[] bArr = new byte[2];
            if (readSocksReply(inputStream, bArr, j) == 2 && bArr[1] == 0) {
                return true;
            }
            bufferedOutputStream.close();
            inputStream.close();
            return false;
        }
        passwordAuthentication.getUserName();
        new String(passwordAuthentication.getPassword());
        throw null;
    }

    private void connectV4(InputStream inputStream, OutputStream outputStream, InetSocketAddress inetSocketAddress, long j) {
        if (inetSocketAddress.getAddress() instanceof Inet4Address) {
            outputStream.write(4);
            outputStream.write(1);
            outputStream.write((inetSocketAddress.getPort() >> 8) & 255);
            outputStream.write((inetSocketAddress.getPort() >> 0) & 255);
            outputStream.write(inetSocketAddress.getAddress().getAddress());
            try {
                outputStream.write(getUserName().getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException unused) {
            }
            outputStream.write(0);
            outputStream.flush();
            byte[] bArr = new byte[8];
            int readSocksReply = readSocksReply(inputStream, bArr, j);
            if (readSocksReply != 8) {
                throw new SocketException("Reply from SOCKS server has bad length: " + readSocksReply);
            } else if (bArr[0] == 0 || bArr[0] == 4) {
                SocketException socketException = null;
                switch (bArr[1]) {
                    case 90:
                        this.external_address = inetSocketAddress;
                        break;
                    case 91:
                        socketException = new SocketException("SOCKS request rejected");
                        break;
                    case 92:
                        socketException = new SocketException("SOCKS server couldn't reach destination");
                        break;
                    case 93:
                        socketException = new SocketException("SOCKS authentication failed");
                        break;
                    default:
                        socketException = new SocketException("Reply from SOCKS server contains bad status");
                        break;
                }
                if (socketException != null) {
                    inputStream.close();
                    outputStream.close();
                    throw socketException;
                }
            } else {
                throw new SocketException("Reply from SOCKS server has bad version");
            }
        } else {
            throw new SocketException("SOCKS V4 requires IPv4 only addresses");
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // java.net.SocketImpl, java.net.AbstractPlainSocketImpl
    public void connect(SocketAddress socketAddress, int i) {
        long j;
        SocketException socketException;
        if (i == 0) {
            j = 0;
        } else {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis < 0) {
                currentTimeMillis = Long.MAX_VALUE;
            }
            j = currentTimeMillis;
        }
        SecurityManager securityManager = System.getSecurityManager();
        if (socketAddress == null || !(socketAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        if (securityManager == null) {
            String str = this.server;
            if (str == null) {
                super.connect(inetSocketAddress, remainingMillis(j));
                return;
            }
            try {
                privilegedConnect(str, this.serverPort, remainingMillis(j));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.cmdOut, 512);
                InputStream inputStream = this.cmdIn;
                if (!this.useV4) {
                    bufferedOutputStream.write(5);
                    bufferedOutputStream.write(2);
                    bufferedOutputStream.write(0);
                    bufferedOutputStream.write(2);
                    bufferedOutputStream.flush();
                    byte[] bArr = new byte[2];
                    if (readSocksReply(inputStream, bArr, j) == 2 && bArr[0] == 5) {
                        if (bArr[1] == -1) {
                            throw new SocketException("SOCKS : No acceptable methods");
                        } else if (authenticate(bArr[1], inputStream, bufferedOutputStream, j)) {
                            bufferedOutputStream.write(5);
                            bufferedOutputStream.write(1);
                            bufferedOutputStream.write(0);
                            if (inetSocketAddress.isUnresolved()) {
                                bufferedOutputStream.write(3);
                                bufferedOutputStream.write(inetSocketAddress.getHostName().length());
                                try {
                                    bufferedOutputStream.write(inetSocketAddress.getHostName().getBytes("ISO-8859-1"));
                                } catch (UnsupportedEncodingException unused) {
                                }
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 8) & 255);
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 0) & 255);
                            } else if (inetSocketAddress.getAddress() instanceof Inet6Address) {
                                bufferedOutputStream.write(4);
                                bufferedOutputStream.write(inetSocketAddress.getAddress().getAddress());
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 8) & 255);
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 0) & 255);
                            } else {
                                bufferedOutputStream.write(1);
                                bufferedOutputStream.write(inetSocketAddress.getAddress().getAddress());
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 8) & 255);
                                bufferedOutputStream.write((inetSocketAddress.getPort() >> 0) & 255);
                            }
                            bufferedOutputStream.flush();
                            byte[] bArr2 = new byte[4];
                            if (readSocksReply(inputStream, bArr2, j) == 4) {
                                switch (bArr2[1]) {
                                    case 0:
                                        byte b = bArr2[3];
                                        if (b != 1) {
                                            if (b == 3) {
                                                byte b2 = bArr2[1];
                                                if (readSocksReply(inputStream, new byte[b2], j) != b2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                } else if (readSocksReply(inputStream, new byte[2], j) != 2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                }
                                            } else if (b != 4) {
                                                socketException = new SocketException("Reply from SOCKS server contains wrong code");
                                                break;
                                            } else {
                                                byte b3 = bArr2[1];
                                                if (readSocksReply(inputStream, new byte[b3], j) != b3) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                } else if (readSocksReply(inputStream, new byte[2], j) != 2) {
                                                    throw new SocketException("Reply from SOCKS server badly formatted");
                                                }
                                            }
                                        } else if (readSocksReply(inputStream, new byte[4], j) != 4) {
                                            throw new SocketException("Reply from SOCKS server badly formatted");
                                        } else if (readSocksReply(inputStream, new byte[2], j) != 2) {
                                            throw new SocketException("Reply from SOCKS server badly formatted");
                                        }
                                        socketException = null;
                                        break;
                                    case 1:
                                        socketException = new SocketException("SOCKS server general failure");
                                        break;
                                    case 2:
                                        socketException = new SocketException("SOCKS: Connection not allowed by ruleset");
                                        break;
                                    case 3:
                                        socketException = new SocketException("SOCKS: Network unreachable");
                                        break;
                                    case 4:
                                        socketException = new SocketException("SOCKS: Host unreachable");
                                        break;
                                    case 5:
                                        socketException = new SocketException("SOCKS: Connection refused");
                                        break;
                                    case 6:
                                        socketException = new SocketException("SOCKS: TTL expired");
                                        break;
                                    case 7:
                                        socketException = new SocketException("SOCKS: Command not supported");
                                        break;
                                    case 8:
                                        socketException = new SocketException("SOCKS: address type not supported");
                                        break;
                                    default:
                                        socketException = null;
                                        break;
                                }
                                if (socketException == null) {
                                    this.external_address = inetSocketAddress;
                                    return;
                                }
                                inputStream.close();
                                bufferedOutputStream.close();
                                throw socketException;
                            }
                            throw new SocketException("Reply from SOCKS server has bad length");
                        } else {
                            throw new SocketException("SOCKS : authentication failed");
                        }
                    } else if (!inetSocketAddress.isUnresolved()) {
                        connectV4(inputStream, bufferedOutputStream, inetSocketAddress, j);
                    } else {
                        throw new UnknownHostException(inetSocketAddress.toString());
                    }
                } else if (!inetSocketAddress.isUnresolved()) {
                    connectV4(inputStream, bufferedOutputStream, inetSocketAddress, j);
                } else {
                    throw new UnknownHostException(inetSocketAddress.toString());
                }
            } catch (IOException e) {
                throw new SocketException(e.getMessage());
            }
        } else if (inetSocketAddress.isUnresolved()) {
            securityManager.checkConnect(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
            throw null;
        } else {
            securityManager.checkConnect(inetSocketAddress.getAddress().getHostAddress(), inetSocketAddress.getPort());
            throw null;
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
    public void close() {
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
        } catch (SecurityException unused) {
            return "";
        }
    }
}
