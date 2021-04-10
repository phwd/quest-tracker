package java.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import libcore.io.Libcore;
import sun.net.spi.nameservice.NameService;
import sun.net.util.IPAddressUtil;

public class InetAddress implements Serializable {
    private static final ClassLoader BOOT_CLASSLOADER = Object.class.getClassLoader();
    static final InetAddressImpl impl = new Inet6AddressImpl();
    private static final NameService nameService = new NameService() {
        /* class java.net.InetAddress.AnonymousClass1 */

        @Override // sun.net.spi.nameservice.NameService
        public InetAddress[] lookupAllHostAddr(String str, int i) {
            return InetAddress.impl.lookupAllHostAddr(str, i);
        }

        @Override // sun.net.spi.nameservice.NameService
        public String getHostByAddr(byte[] bArr) {
            return InetAddress.impl.getHostByAddr(bArr);
        }
    };
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("hostName", String.class), new ObjectStreamField("address", Integer.TYPE), new ObjectStreamField("family", Integer.TYPE)};
    private static final long serialVersionUID = 3286316764910316507L;
    private transient String canonicalHostName = null;
    transient InetAddressHolder holder = new InetAddressHolder();

    public boolean equals(Object obj) {
        return false;
    }

    public byte[] getAddress() {
        return null;
    }

    public String getHostAddress() {
        return null;
    }

    public int hashCode() {
        return -1;
    }

    public boolean isAnyLocalAddress() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public static class InetAddressHolder {
        int address;
        int family;
        String hostName;
        String originalHostName;

        InetAddressHolder() {
        }

        /* access modifiers changed from: package-private */
        public void init(String str, int i) {
            this.originalHostName = str;
            this.hostName = str;
            if (i != -1) {
                this.family = i;
            }
        }

        /* access modifiers changed from: package-private */
        public String getHostName() {
            return this.hostName;
        }

        /* access modifiers changed from: package-private */
        public int getAddress() {
            return this.address;
        }
    }

    /* access modifiers changed from: package-private */
    public InetAddressHolder holder() {
        return this.holder;
    }

    InetAddress() {
    }

    private Object readResolve() {
        return new Inet4Address(holder().getHostName(), holder().getAddress());
    }

    public String getHostName() {
        if (holder().getHostName() == null) {
            holder().hostName = getHostFromNameService(this);
        }
        return holder().getHostName();
    }

    private static String getHostFromNameService(InetAddress inetAddress) {
        try {
            String hostByAddr = nameService.getHostByAddr(inetAddress.getAddress());
            boolean z = false;
            InetAddress[] lookupAllHostAddr = nameService.lookupAllHostAddr(hostByAddr, 0);
            if (lookupAllHostAddr != null) {
                int i = 0;
                while (!z && i < lookupAllHostAddr.length) {
                    z = inetAddress.equals(lookupAllHostAddr[i]);
                    i++;
                }
            }
            if (!z) {
                return inetAddress.getHostAddress();
            }
            return hostByAddr;
        } catch (UnknownHostException unused) {
            return inetAddress.getHostAddress();
        }
    }

    public String toString() {
        String hostName = holder().getHostName();
        StringBuilder sb = new StringBuilder();
        if (hostName == null) {
            hostName = "";
        }
        sb.append(hostName);
        sb.append("/");
        sb.append(getHostAddress());
        return sb.toString();
    }

    public static InetAddress getByAddress(String str, byte[] bArr) {
        return getByAddress(str, bArr, -1);
    }

    private static InetAddress getByAddress(String str, byte[] bArr, int i) {
        if (str != null && str.length() > 0 && str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
            str = str.substring(1, str.length() - 1);
        }
        if (bArr != null) {
            if (bArr.length == 4) {
                return new Inet4Address(str, bArr);
            }
            if (bArr.length == 16) {
                byte[] convertFromIPv4MappedAddress = IPAddressUtil.convertFromIPv4MappedAddress(bArr);
                if (convertFromIPv4MappedAddress != null) {
                    return new Inet4Address(str, convertFromIPv4MappedAddress);
                }
                return new Inet6Address(str, bArr, i);
            }
        }
        throw new UnknownHostException("addr is of illegal length");
    }

    public static InetAddress getByName(String str) {
        return impl.lookupAllHostAddr(str, 0)[0];
    }

    public static InetAddress[] getAllByName(String str) {
        return (InetAddress[]) impl.lookupAllHostAddr(str, 0).clone();
    }

    public static InetAddress getLoopbackAddress() {
        return impl.loopbackAddresses()[0];
    }

    public static InetAddress getByAddress(byte[] bArr) {
        return getByAddress(null, bArr);
    }

    public static InetAddress getLocalHost() {
        return impl.lookupAllHostAddr(Libcore.os.uname().nodename, 0)[0];
    }

    static InetAddress anyLocalAddress() {
        return impl.anyLocalAddress();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
        objectInputStream.readFields();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
        objectOutputStream.putFields();
        throw null;
    }
}
