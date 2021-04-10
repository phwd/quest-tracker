package libcore.io;

import android.system.ErrnoException;
import android.system.Int32Ref;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructGroupReq;
import android.system.StructLinger;
import android.system.StructPollfd;
import android.system.StructTimeval;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.NoRouteToHostException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import libcore.util.ArrayUtils;

public final class IoBridge {
    private static boolean booleanFromInt(int i) {
        return i != 0;
    }

    public static int available(FileDescriptor fileDescriptor) {
        try {
            Int32Ref int32Ref = new Int32Ref(0);
            Libcore.os.ioctlInt(fileDescriptor, OsConstants.FIONREAD, int32Ref);
            if (int32Ref.value < 0) {
                int32Ref.value = 0;
            }
            return int32Ref.value;
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.ENOTTY) {
                return 0;
            }
            throw e.rethrowAsIOException();
        }
    }

    public static void bind(FileDescriptor fileDescriptor, InetAddress inetAddress, int i) {
        if (inetAddress instanceof Inet6Address) {
            Inet6Address inet6Address = (Inet6Address) inetAddress;
            if (inet6Address.getScopeId() == 0 && inet6Address.isLinkLocalAddress()) {
                NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(inetAddress);
                if (byInetAddress != null) {
                    try {
                        inetAddress = Inet6Address.getByAddress(inetAddress.getHostName(), inetAddress.getAddress(), byInetAddress.getIndex());
                    } catch (UnknownHostException e) {
                        throw new AssertionError(e);
                    }
                } else {
                    throw new SocketException("Can't bind to a link-local address without a scope id: " + inetAddress);
                }
            }
        }
        try {
            Libcore.os.bind(fileDescriptor, inetAddress, i);
        } catch (ErrnoException e2) {
            int i2 = e2.errno;
            if (i2 == OsConstants.EADDRINUSE || i2 == OsConstants.EADDRNOTAVAIL || i2 == OsConstants.EPERM || i2 == OsConstants.EACCES) {
                throw new BindException(e2.getMessage(), e2);
            }
            throw new SocketException(e2.getMessage(), e2);
        }
    }

    public static void connect(FileDescriptor fileDescriptor, InetAddress inetAddress, int i, int i2) {
        try {
            connectErrno(fileDescriptor, inetAddress, i, i2);
        } catch (ErrnoException e) {
            int i3 = e.errno;
            if (i3 == OsConstants.EHOSTUNREACH) {
                throw new NoRouteToHostException("Host unreachable");
            } else if (i3 == OsConstants.EADDRNOTAVAIL) {
                throw new NoRouteToHostException("Address not available");
            } else {
                throw new ConnectException(createMessageForException(fileDescriptor, inetAddress, i, i2, e), e);
            }
        } catch (SocketException e2) {
            throw e2;
        } catch (SocketTimeoutException e3) {
            throw e3;
        } catch (IOException e4) {
            throw new SocketException(e4);
        }
    }

    private static void connectErrno(FileDescriptor fileDescriptor, InetAddress inetAddress, int i, int i2) {
        int millis;
        if (i2 <= 0) {
            Libcore.os.connect(fileDescriptor, inetAddress, i);
            return;
        }
        IoUtils.setBlocking(fileDescriptor, false);
        long nanoTime = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos((long) i2);
        try {
            Libcore.os.connect(fileDescriptor, inetAddress, i);
            IoUtils.setBlocking(fileDescriptor, true);
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.EINPROGRESS) {
                do {
                    millis = (int) TimeUnit.NANOSECONDS.toMillis(nanoTime - System.nanoTime());
                    if (millis <= 0) {
                        throw new SocketTimeoutException(createMessageForException(fileDescriptor, inetAddress, i, i2, null));
                    }
                } while (!isConnected(fileDescriptor, inetAddress, i, i2, millis));
                IoUtils.setBlocking(fileDescriptor, true);
                return;
            }
            throw e;
        }
    }

    private static String createMessageForException(FileDescriptor fileDescriptor, InetAddress inetAddress, int i, int i2, Exception exc) {
        InetSocketAddress inetSocketAddress;
        try {
            inetSocketAddress = getLocalInetSocketAddress(fileDescriptor);
        } catch (SocketException unused) {
            inetSocketAddress = null;
        }
        StringBuilder sb = new StringBuilder("failed to connect");
        sb.append(" to ");
        sb.append(inetAddress);
        sb.append(" (port ");
        sb.append(i);
        sb.append(")");
        if (inetSocketAddress != null) {
            sb.append(" from ");
            sb.append(inetSocketAddress.getAddress());
            sb.append(" (port ");
            sb.append(inetSocketAddress.getPort());
            sb.append(")");
        }
        if (i2 > 0) {
            sb.append(" after ");
            sb.append(i2);
            sb.append("ms");
        }
        if (exc != null) {
            sb.append(": ");
            sb.append(exc.getMessage());
        }
        return sb.toString();
    }

    public static void closeAndSignalBlockedThreads(FileDescriptor fileDescriptor) {
        if (fileDescriptor != null && fileDescriptor.valid()) {
            FileDescriptor release$ = fileDescriptor.release$();
            AsynchronousCloseMonitor.signalBlockedThreads(release$);
            try {
                Libcore.os.close(release$);
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        }
    }

    public static boolean isConnected(FileDescriptor fileDescriptor, InetAddress inetAddress, int i, int i2, int i3) {
        try {
            StructPollfd[] structPollfdArr = {new StructPollfd()};
            structPollfdArr[0].fd = fileDescriptor;
            structPollfdArr[0].events = (short) OsConstants.POLLOUT;
            if (Libcore.os.poll(structPollfdArr, i3) == 0) {
                return false;
            }
            int i4 = Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_ERROR);
            if (i4 == 0) {
                return true;
            }
            throw new ErrnoException("isConnected", i4);
        } catch (ErrnoException e) {
            if (fileDescriptor.valid()) {
                String createMessageForException = createMessageForException(fileDescriptor, inetAddress, i, i2, e);
                if (e.errno == OsConstants.ETIMEDOUT) {
                    SocketTimeoutException socketTimeoutException = new SocketTimeoutException(createMessageForException);
                    socketTimeoutException.initCause(e);
                    throw socketTimeoutException;
                }
                throw new ConnectException(createMessageForException, e);
            }
            throw new SocketException("Socket closed");
        }
    }

    public static Object getSocketOption(FileDescriptor fileDescriptor, int i) {
        try {
            return getSocketOptionErrno(fileDescriptor, i);
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }

    private static Object getSocketOptionErrno(FileDescriptor fileDescriptor, int i) {
        if (i == 1) {
            return Boolean.valueOf(booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_TCP, OsConstants.TCP_NODELAY)));
        }
        if (i == 8) {
            return Boolean.valueOf(booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_KEEPALIVE)));
        }
        if (i == 25) {
            return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_UNICAST_HOPS));
        }
        if (i == 128) {
            StructLinger structLinger = Libcore.os.getsockoptLinger(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER);
            if (!structLinger.isOn()) {
                return false;
            }
            return Integer.valueOf(structLinger.l_linger);
        } else if (i == 4102) {
            return Integer.valueOf((int) Libcore.os.getsockoptTimeval(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_RCVTIMEO).toMillis());
        } else {
            if (i == 3) {
                return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_TCLASS));
            }
            if (i == 4) {
                return Boolean.valueOf(booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_REUSEADDR)));
            }
            if (i != 31) {
                if (i == 32) {
                    return Boolean.valueOf(booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_BROADCAST)));
                }
                switch (i) {
                    case 15:
                        return ((InetSocketAddress) Libcore.os.getsockname(fileDescriptor)).getAddress();
                    case 16:
                        break;
                    case 17:
                        return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_HOPS));
                    case 18:
                        return Boolean.valueOf(!booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_LOOP)));
                    default:
                        switch (i) {
                            case 4097:
                                return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_SNDBUF));
                            case 4098:
                                return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_RCVBUF));
                            case 4099:
                                return Boolean.valueOf(booleanFromInt(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_OOBINLINE)));
                            default:
                                throw new SocketException("Unknown socket option: " + i);
                        }
                }
            }
            return Integer.valueOf(Libcore.os.getsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_IF));
        }
    }

    public static void setSocketOption(FileDescriptor fileDescriptor, int i, Object obj) {
        try {
            setSocketOptionErrno(fileDescriptor, i, obj);
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }

    private static void setSocketOptionErrno(FileDescriptor fileDescriptor, int i, Object obj) {
        int i2 = 1;
        if (i == 1) {
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_TCP, OsConstants.TCP_NODELAY, ((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (i == 8) {
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_KEEPALIVE, ((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (i == 25) {
            Integer num = (Integer) obj;
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_TTL, num.intValue());
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_UNICAST_HOPS, num.intValue());
        } else if (i == 128) {
            int i3 = 0;
            if (obj instanceof Integer) {
                i3 = Math.min(((Integer) obj).intValue(), 65535);
            } else {
                i2 = 0;
            }
            Libcore.os.setsockoptLinger(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER, new StructLinger(i2, i3));
        } else if (i == 4102) {
            Libcore.os.setsockoptTimeval(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_RCVTIMEO, StructTimeval.fromMillis((long) ((Integer) obj).intValue()));
        } else if (i == 3) {
            Integer num2 = (Integer) obj;
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_TOS, num2.intValue());
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_TCLASS, num2.intValue());
        } else if (i == 4) {
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_REUSEADDR, ((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (i == 31) {
            Integer num3 = (Integer) obj;
            Libcore.os.setsockoptIpMreqn(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_MULTICAST_IF, num3.intValue());
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_IF, num3.intValue());
        } else if (i != 32) {
            switch (i) {
                case 16:
                    NetworkInterface byInetAddress = NetworkInterface.getByInetAddress((InetAddress) obj);
                    if (byInetAddress != null) {
                        Libcore.os.setsockoptIpMreqn(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_MULTICAST_IF, byInetAddress.getIndex());
                        Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_IF, byInetAddress.getIndex());
                        return;
                    }
                    throw new SocketException("bad argument for IP_MULTICAST_IF : address not bound to any interface");
                case 17:
                    Integer num4 = (Integer) obj;
                    Libcore.os.setsockoptByte(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_MULTICAST_TTL, num4.intValue());
                    Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_HOPS, num4.intValue());
                    return;
                case 18:
                    int i4 = !((Boolean) obj).booleanValue();
                    Libcore.os.setsockoptByte(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_MULTICAST_LOOP, i4);
                    Libcore.os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IPV6, OsConstants.IPV6_MULTICAST_LOOP, i4);
                    return;
                case 19:
                case 20:
                    StructGroupReq structGroupReq = (StructGroupReq) obj;
                    Libcore.os.setsockoptGroupReq(fileDescriptor, structGroupReq.gr_group instanceof Inet4Address ? OsConstants.IPPROTO_IP : OsConstants.IPPROTO_IPV6, i == 19 ? OsConstants.MCAST_JOIN_GROUP : OsConstants.MCAST_LEAVE_GROUP, structGroupReq);
                    return;
                default:
                    switch (i) {
                        case 4097:
                            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_SNDBUF, ((Integer) obj).intValue());
                            return;
                        case 4098:
                            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_RCVBUF, ((Integer) obj).intValue());
                            return;
                        case 4099:
                            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_OOBINLINE, ((Boolean) obj).booleanValue() ? 1 : 0);
                            return;
                        default:
                            throw new SocketException("Unknown socket option: " + i);
                    }
            }
        } else {
            Libcore.os.setsockoptInt(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_BROADCAST, ((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static FileDescriptor open(String str, int i) {
        try {
            FileDescriptor open = Libcore.os.open(str, i, (OsConstants.O_ACCMODE & i) == OsConstants.O_RDONLY ? 0 : 384);
            if (!OsConstants.S_ISDIR(Libcore.os.fstat(open).st_mode)) {
                return open;
            }
            throw new ErrnoException("open", OsConstants.EISDIR);
        } catch (ErrnoException e) {
            if (0 != 0) {
                try {
                    closeAndSignalBlockedThreads(null);
                } catch (IOException unused) {
                }
            }
            FileNotFoundException fileNotFoundException = new FileNotFoundException(str + ": " + e.getMessage());
            fileNotFoundException.initCause(e);
            throw fileNotFoundException;
        }
    }

    public static int read(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        ArrayUtils.throwsIfOutOfBounds(bArr.length, i, i2);
        if (i2 == 0) {
            return 0;
        }
        try {
            int read = Libcore.os.read(fileDescriptor, bArr, i, i2);
            if (read == 0) {
                return -1;
            }
            return read;
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.EAGAIN) {
                return 0;
            }
            throw e.rethrowAsIOException();
        }
    }

    public static void write(FileDescriptor fileDescriptor, byte[] bArr, int i, int i2) {
        ArrayUtils.throwsIfOutOfBounds(bArr.length, i, i2);
        if (i2 != 0) {
            while (i2 > 0) {
                try {
                    int write = Libcore.os.write(fileDescriptor, bArr, i, i2);
                    i2 -= write;
                    i += write;
                } catch (ErrnoException e) {
                    throw e.rethrowAsIOException();
                }
            }
        }
    }

    public static FileDescriptor socket(int i, int i2, int i3) {
        try {
            return Libcore.os.socket(i, i2, i3);
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }

    public static void poll(FileDescriptor fileDescriptor, int i, int i2) {
        StructPollfd[] structPollfdArr = {new StructPollfd()};
        structPollfdArr[0].fd = fileDescriptor;
        structPollfdArr[0].events = (short) i;
        try {
            if (Os.poll(structPollfdArr, i2) == 0) {
                throw new SocketTimeoutException("Poll timed out");
            }
        } catch (ErrnoException e) {
            e.rethrowAsSocketException();
        }
    }

    public static InetSocketAddress getLocalInetSocketAddress(FileDescriptor fileDescriptor) {
        try {
            SocketAddress socketAddress = Libcore.os.getsockname(fileDescriptor);
            if (socketAddress != null) {
                if (!(socketAddress instanceof InetSocketAddress)) {
                    throw new SocketException("Socket assumed to be pending closure: Expected sockname to be an InetSocketAddress, got " + socketAddress.getClass());
                }
            }
            return (InetSocketAddress) socketAddress;
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }
}
