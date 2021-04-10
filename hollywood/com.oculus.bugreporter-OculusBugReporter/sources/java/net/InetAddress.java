package java.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.Serializable;
import libcore.io.Libcore;
import libcore.net.InetAddressUtils;
import sun.net.spi.nameservice.NameService;
import sun.net.util.IPAddressUtil;

public class InetAddress implements Serializable {
    private static final ClassLoader BOOT_CLASSLOADER = Object.class.getClassLoader();
    static final int NETID_UNSET = 0;
    static final InetAddressImpl impl = new Inet6AddressImpl();
    private static final NameService nameService = new NameService() {
        /* class java.net.InetAddress.AnonymousClass1 */

        @Override // sun.net.spi.nameservice.NameService
        public InetAddress[] lookupAllHostAddr(String host, int netId) throws UnknownHostException {
            return InetAddress.impl.lookupAllHostAddr(host, netId);
        }

        @Override // sun.net.spi.nameservice.NameService
        public String getHostByAddr(byte[] addr) throws UnknownHostException {
            return InetAddress.impl.getHostByAddr(addr);
        }
    };
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("hostName", String.class), new ObjectStreamField("address", Integer.TYPE), new ObjectStreamField("family", Integer.TYPE)};
    private static final long serialVersionUID = 3286316764910316507L;
    private transient String canonicalHostName = null;
    transient InetAddressHolder holder = new InetAddressHolder();

    /* access modifiers changed from: package-private */
    public static class InetAddressHolder {
        int address;
        int family;
        String hostName;
        String originalHostName;

        InetAddressHolder() {
        }

        InetAddressHolder(String hostName2, int address2, int family2) {
            this.originalHostName = hostName2;
            this.hostName = hostName2;
            this.address = address2;
            this.family = family2;
        }

        /* access modifiers changed from: package-private */
        public void init(String hostName2, int family2) {
            this.originalHostName = hostName2;
            this.hostName = hostName2;
            if (family2 != -1) {
                this.family = family2;
            }
        }

        /* access modifiers changed from: package-private */
        public String getHostName() {
            return this.hostName;
        }

        /* access modifiers changed from: package-private */
        public String getOriginalHostName() {
            return this.originalHostName;
        }

        /* access modifiers changed from: package-private */
        public int getAddress() {
            return this.address;
        }

        /* access modifiers changed from: package-private */
        public int getFamily() {
            return this.family;
        }
    }

    /* access modifiers changed from: package-private */
    public InetAddressHolder holder() {
        return this.holder;
    }

    InetAddress() {
    }

    private Object readResolve() throws ObjectStreamException {
        return new Inet4Address(holder().getHostName(), holder().getAddress());
    }

    public boolean isMulticastAddress() {
        return false;
    }

    public boolean isAnyLocalAddress() {
        return false;
    }

    public boolean isLoopbackAddress() {
        return false;
    }

    public boolean isLinkLocalAddress() {
        return false;
    }

    public boolean isSiteLocalAddress() {
        return false;
    }

    public boolean isMCGlobal() {
        return false;
    }

    public boolean isMCNodeLocal() {
        return false;
    }

    public boolean isMCLinkLocal() {
        return false;
    }

    public boolean isMCSiteLocal() {
        return false;
    }

    public boolean isMCOrgLocal() {
        return false;
    }

    public boolean isReachable(int timeout) throws IOException {
        return isReachable(null, 0, timeout);
    }

    public boolean isReachable(NetworkInterface netif, int ttl, int timeout) throws IOException {
        if (ttl < 0) {
            throw new IllegalArgumentException("ttl can't be negative");
        } else if (timeout >= 0) {
            return impl.isReachable(this, timeout, netif, ttl);
        } else {
            throw new IllegalArgumentException("timeout can't be negative");
        }
    }

    public boolean isReachableByICMP(int timeout) throws IOException {
        return ((Inet6AddressImpl) impl).icmpEcho(this, timeout, null, 0);
    }

    public String getHostName() {
        if (holder().getHostName() == null) {
            holder().hostName = getHostFromNameService(this);
        }
        return holder().getHostName();
    }

    public String getCanonicalHostName() {
        if (this.canonicalHostName == null) {
            this.canonicalHostName = getHostFromNameService(this);
        }
        return this.canonicalHostName;
    }

    private static String getHostFromNameService(InetAddress addr) {
        try {
            String host = nameService.getHostByAddr(addr.getAddress());
            InetAddress[] arr = nameService.lookupAllHostAddr(host, 0);
            boolean ok = false;
            if (arr != null) {
                int i = 0;
                while (!ok && i < arr.length) {
                    ok = addr.equals(arr[i]);
                    i++;
                }
            }
            if (!ok) {
                return addr.getHostAddress();
            }
            return host;
        } catch (UnknownHostException e) {
            return addr.getHostAddress();
        }
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

    public boolean equals(Object obj) {
        return false;
    }

    public String toString() {
        String hostName = holder().getHostName();
        StringBuilder sb = new StringBuilder();
        sb.append(hostName != null ? hostName : "");
        sb.append("/");
        sb.append(getHostAddress());
        return sb.toString();
    }

    public static InetAddress getByAddress(String host, byte[] addr) throws UnknownHostException {
        return getByAddress(host, addr, -1);
    }

    private static InetAddress getByAddress(String host, byte[] addr, int scopeId) throws UnknownHostException {
        if (host != null && host.length() > 0 && host.charAt(0) == '[' && host.charAt(host.length() - 1) == ']') {
            host = host.substring(1, host.length() - 1);
        }
        if (addr != null) {
            if (addr.length == 4) {
                return new Inet4Address(host, addr);
            }
            if (addr.length == 16) {
                byte[] newAddr = IPAddressUtil.convertFromIPv4MappedAddress(addr);
                if (newAddr != null) {
                    return new Inet4Address(host, newAddr);
                }
                return new Inet6Address(host, addr, scopeId);
            }
        }
        throw new UnknownHostException("addr is of illegal length");
    }

    public static InetAddress getByName(String host) throws UnknownHostException {
        return impl.lookupAllHostAddr(host, 0)[0];
    }

    public static InetAddress[] getAllByName(String host) throws UnknownHostException {
        return (InetAddress[]) impl.lookupAllHostAddr(host, 0).clone();
    }

    public static InetAddress getLoopbackAddress() {
        return impl.loopbackAddresses()[0];
    }

    public static InetAddress getByAddress(byte[] addr) throws UnknownHostException {
        return getByAddress(null, addr);
    }

    public static InetAddress getLocalHost() throws UnknownHostException {
        return impl.lookupAllHostAddr(Libcore.os.uname().nodename, 0)[0];
    }

    static InetAddress anyLocalAddress() {
        return impl.anyLocalAddress();
    }

    private void readObjectNoData(ObjectInputStream s) throws IOException, ClassNotFoundException {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        if (getClass().getClassLoader() == BOOT_CLASSLOADER) {
            ObjectInputStream.GetField gf = s.readFields();
            this.holder = new InetAddressHolder((String) gf.get("hostName", (Object) null), gf.get("address", 0), gf.get("family", 0));
            return;
        }
        throw new SecurityException("invalid address type");
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        if (getClass().getClassLoader() == BOOT_CLASSLOADER) {
            ObjectOutputStream.PutField pf = s.putFields();
            pf.put("hostName", holder().hostName);
            pf.put("address", holder().address);
            pf.put("family", holder().family);
            s.writeFields();
            s.flush();
            return;
        }
        throw new SecurityException("invalid address type");
    }

    @Deprecated
    public static boolean isNumeric(String address) {
        return InetAddressUtils.parseNumericAddressNoThrowStripOptionalBrackets(address) != null;
    }

    @Deprecated
    public static InetAddress parseNumericAddress(String numericAddress) {
        if (numericAddress == null || numericAddress.isEmpty()) {
            return Inet6Address.LOOPBACK;
        }
        InetAddress result = InetAddressUtils.parseNumericAddressNoThrowStripOptionalBrackets(numericAddress);
        if (result != null) {
            return result;
        }
        throw new IllegalArgumentException("Not a numeric address: " + numericAddress);
    }

    public static void clearDnsCache() {
        impl.clearAddressCache();
    }

    public static InetAddress getByNameOnNet(String host, int netId) throws UnknownHostException {
        return impl.lookupAllHostAddr(host, netId)[0];
    }

    public static InetAddress[] getAllByNameOnNet(String host, int netId) throws UnknownHostException {
        return (InetAddress[]) impl.lookupAllHostAddr(host, netId).clone();
    }
}
