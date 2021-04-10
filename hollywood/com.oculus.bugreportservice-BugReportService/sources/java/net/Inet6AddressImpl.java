package java.net;

import android.system.ErrnoException;
import android.system.GaiException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import dalvik.system.BlockGuard;
import libcore.io.Libcore;
import libcore.net.InetAddressUtils;

class Inet6AddressImpl implements InetAddressImpl {
    private static final AddressCache addressCache = new AddressCache();
    private static InetAddress anyLocalAddress;
    private static InetAddress[] loopbackAddresses;

    Inet6AddressImpl() {
    }

    @Override // java.net.InetAddressImpl
    public InetAddress[] lookupAllHostAddr(String str, int i) {
        if (str == null || str.isEmpty()) {
            return loopbackAddresses();
        }
        InetAddress parseNumericAddressNoThrowStripOptionalBrackets = InetAddressUtils.parseNumericAddressNoThrowStripOptionalBrackets(str);
        if (parseNumericAddressNoThrowStripOptionalBrackets == null) {
            return lookupHostByName(str, i);
        }
        return new InetAddress[]{parseNumericAddressNoThrowStripOptionalBrackets};
    }

    private static InetAddress[] lookupHostByName(String str, int i) {
        int i2;
        BlockGuard.getThreadPolicy().onNetwork();
        Object obj = addressCache.get(str, i);
        if (obj == null) {
            try {
                StructAddrinfo structAddrinfo = new StructAddrinfo();
                structAddrinfo.ai_flags = OsConstants.AI_ADDRCONFIG;
                structAddrinfo.ai_family = OsConstants.AF_UNSPEC;
                structAddrinfo.ai_socktype = OsConstants.SOCK_STREAM;
                InetAddress[] android_getaddrinfo = Libcore.os.android_getaddrinfo(str, structAddrinfo, i);
                for (InetAddress inetAddress : android_getaddrinfo) {
                    inetAddress.holder().hostName = str;
                    inetAddress.holder().originalHostName = str;
                }
                addressCache.put(str, i, android_getaddrinfo);
                return android_getaddrinfo;
            } catch (GaiException e) {
                if (!(e.getCause() instanceof ErrnoException) || !((i2 = ((ErrnoException) e.getCause()).errno) == OsConstants.EACCES || i2 == OsConstants.EPERM)) {
                    String str2 = "Unable to resolve host \"" + str + "\": " + Libcore.os.gai_strerror(e.error);
                    addressCache.putUnknownHost(str, i, str2);
                    throw e.rethrowAsUnknownHostException(str2);
                }
                throw new SecurityException("Permission denied (missing INTERNET permission?)", e);
            }
        } else if (obj instanceof InetAddress[]) {
            return (InetAddress[]) obj;
        } else {
            throw new UnknownHostException((String) obj);
        }
    }

    @Override // java.net.InetAddressImpl
    public String getHostByAddr(byte[] bArr) {
        BlockGuard.getThreadPolicy().onNetwork();
        return getHostByAddr0(bArr);
    }

    @Override // java.net.InetAddressImpl
    public InetAddress anyLocalAddress() {
        InetAddress inetAddress;
        synchronized (Inet6AddressImpl.class) {
            if (anyLocalAddress == null) {
                Inet6Address inet6Address = new Inet6Address();
                inet6Address.holder().hostName = "::";
                anyLocalAddress = inet6Address;
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

    private String getHostByAddr0(byte[] bArr) {
        InetAddress byAddress = InetAddress.getByAddress(bArr);
        try {
            return Libcore.os.getnameinfo(byAddress, OsConstants.NI_NAMEREQD);
        } catch (GaiException e) {
            UnknownHostException unknownHostException = new UnknownHostException(byAddress.toString());
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }
}
