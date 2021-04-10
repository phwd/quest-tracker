package java.net;

import android.system.ErrnoException;
import android.system.GaiException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import dalvik.system.BlockGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Enumeration;
import libcore.io.IoBridge;
import libcore.io.Libcore;
import libcore.net.InetAddressUtils;

class Inet6AddressImpl implements InetAddressImpl {
    private static final AddressCache addressCache = new AddressCache();
    private static InetAddress anyLocalAddress;
    private static InetAddress[] loopbackAddresses;

    Inet6AddressImpl() {
    }

    @Override // java.net.InetAddressImpl
    public InetAddress[] lookupAllHostAddr(String host, int netId) throws UnknownHostException {
        if (host == null || host.isEmpty()) {
            return loopbackAddresses();
        }
        InetAddress result = InetAddressUtils.parseNumericAddressNoThrowStripOptionalBrackets(host);
        if (result == null) {
            return lookupHostByName(host, netId);
        }
        return new InetAddress[]{result};
    }

    private static InetAddress[] lookupHostByName(String host, int netId) throws UnknownHostException {
        int errno;
        BlockGuard.getThreadPolicy().onNetwork();
        Object cachedResult = addressCache.get(host, netId);
        if (cachedResult == null) {
            try {
                StructAddrinfo hints = new StructAddrinfo();
                hints.ai_flags = OsConstants.AI_ADDRCONFIG;
                hints.ai_family = OsConstants.AF_UNSPEC;
                hints.ai_socktype = OsConstants.SOCK_STREAM;
                InetAddress[] addresses = Libcore.os.android_getaddrinfo(host, hints, netId);
                for (InetAddress address : addresses) {
                    address.holder().hostName = host;
                    address.holder().originalHostName = host;
                }
                addressCache.put(host, netId, addresses);
                return addresses;
            } catch (GaiException gaiException) {
                if (!(gaiException.getCause() instanceof ErrnoException) || !((errno = ((ErrnoException) gaiException.getCause()).errno) == OsConstants.EACCES || errno == OsConstants.EPERM)) {
                    String detailMessage = "Unable to resolve host \"" + host + "\": " + Libcore.os.gai_strerror(gaiException.error);
                    addressCache.putUnknownHost(host, netId, detailMessage);
                    throw gaiException.rethrowAsUnknownHostException(detailMessage);
                }
                throw new SecurityException("Permission denied (missing INTERNET permission?)", gaiException);
            }
        } else if (cachedResult instanceof InetAddress[]) {
            return (InetAddress[]) cachedResult;
        } else {
            throw new UnknownHostException((String) cachedResult);
        }
    }

    @Override // java.net.InetAddressImpl
    public String getHostByAddr(byte[] addr) throws UnknownHostException {
        BlockGuard.getThreadPolicy().onNetwork();
        return getHostByAddr0(addr);
    }

    @Override // java.net.InetAddressImpl
    public void clearAddressCache() {
        addressCache.clear();
    }

    @Override // java.net.InetAddressImpl
    public boolean isReachable(InetAddress addr, int timeout, NetworkInterface netif, int ttl) throws IOException {
        InetAddress sourceAddr = null;
        if (netif != null) {
            Enumeration<InetAddress> it = netif.getInetAddresses();
            while (true) {
                if (!it.hasMoreElements()) {
                    break;
                }
                InetAddress inetaddr = it.nextElement();
                if (inetaddr.getClass().isInstance(addr)) {
                    sourceAddr = inetaddr;
                    break;
                }
            }
            if (sourceAddr == null) {
                return false;
            }
        }
        if (icmpEcho(addr, timeout, sourceAddr, ttl)) {
            return true;
        }
        return tcpEcho(addr, timeout, sourceAddr, ttl);
    }

    private boolean tcpEcho(InetAddress addr, int timeout, InetAddress sourceAddr, int ttl) throws IOException {
        FileDescriptor fd = null;
        boolean z = true;
        try {
            fd = IoBridge.socket(OsConstants.AF_INET6, OsConstants.SOCK_STREAM, 0);
            if (ttl > 0) {
                IoBridge.setSocketOption(fd, 25, Integer.valueOf(ttl));
            }
            if (sourceAddr != null) {
                IoBridge.bind(fd, sourceAddr, 0);
            }
            IoBridge.connect(fd, addr, 7, timeout);
            return true;
        } catch (IOException e) {
            Throwable cause = e.getCause();
            if (!(cause instanceof ErrnoException) || ((ErrnoException) cause).errno != OsConstants.ECONNREFUSED) {
                z = false;
            }
            return z;
        } finally {
            IoBridge.closeAndSignalBlockedThreads(fd);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0104 A[SYNTHETIC, Splitter:B:74:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0110 A[SYNTHETIC, Splitter:B:81:0x0110] */
    /* JADX WARNING: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean icmpEcho(java.net.InetAddress r25, int r26, java.net.InetAddress r27, int r28) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 281
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.Inet6AddressImpl.icmpEcho(java.net.InetAddress, int, java.net.InetAddress, int):boolean");
    }

    @Override // java.net.InetAddressImpl
    public InetAddress anyLocalAddress() {
        InetAddress inetAddress;
        synchronized (Inet6AddressImpl.class) {
            if (anyLocalAddress == null) {
                Inet6Address anyAddress = new Inet6Address();
                anyAddress.holder().hostName = "::";
                anyLocalAddress = anyAddress;
            }
            inetAddress = anyLocalAddress;
        }
        return inetAddress;
    }

    @Override // java.net.InetAddressImpl
    public InetAddress[] loopbackAddresses() {
        InetAddress[] inetAddressArr;
        synchronized (Inet6AddressImpl.class) {
            if (loopbackAddresses == null) {
                loopbackAddresses = new InetAddress[]{Inet6Address.LOOPBACK, Inet4Address.LOOPBACK};
            }
            inetAddressArr = loopbackAddresses;
        }
        return inetAddressArr;
    }

    private String getHostByAddr0(byte[] addr) throws UnknownHostException {
        InetAddress hostaddr = InetAddress.getByAddress(addr);
        try {
            return Libcore.os.getnameinfo(hostaddr, OsConstants.NI_NAMEREQD);
        } catch (GaiException e) {
            UnknownHostException uhe = new UnknownHostException(hostaddr.toString());
            uhe.initCause(e);
            throw uhe;
        }
    }
}
