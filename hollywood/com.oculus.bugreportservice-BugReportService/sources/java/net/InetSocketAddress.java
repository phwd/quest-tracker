package java.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import sun.misc.Unsafe;

public class InetSocketAddress extends SocketAddress {
    private static final long FIELDS_OFFSET;
    private static final Unsafe UNSAFE;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("hostname", String.class), new ObjectStreamField("addr", InetAddress.class), new ObjectStreamField("port", Integer.TYPE)};
    private static final long serialVersionUID = 5076001401234631237L;
    private final transient InetSocketAddressHolder holder;

    /* access modifiers changed from: private */
    public static class InetSocketAddressHolder {
        private InetAddress addr;
        private String hostname;
        private int port;

        private InetSocketAddressHolder(String str, InetAddress inetAddress, int i) {
            this.hostname = str;
            this.addr = inetAddress;
            this.port = i;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int getPort() {
            return this.port;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private InetAddress getAddress() {
            return this.addr;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getHostName() {
            String str = this.hostname;
            if (str != null) {
                return str;
            }
            InetAddress inetAddress = this.addr;
            if (inetAddress != null) {
                return inetAddress.getHostName();
            }
            return null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getHostString() {
            String str = this.hostname;
            if (str != null) {
                return str;
            }
            InetAddress inetAddress = this.addr;
            if (inetAddress == null) {
                return null;
            }
            if (inetAddress.holder().getHostName() != null) {
                return this.addr.holder().getHostName();
            }
            return this.addr.getHostAddress();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean isUnresolved() {
            return this.addr == null;
        }

        public String toString() {
            if (isUnresolved()) {
                return this.hostname + ":" + this.port;
            }
            return this.addr.toString() + ":" + this.port;
        }

        public final boolean equals(Object obj) {
            boolean z;
            if (obj == null || !(obj instanceof InetSocketAddressHolder)) {
                return false;
            }
            InetSocketAddressHolder inetSocketAddressHolder = (InetSocketAddressHolder) obj;
            InetAddress inetAddress = this.addr;
            if (inetAddress != null) {
                z = inetAddress.equals(inetSocketAddressHolder.addr);
            } else {
                String str = this.hostname;
                z = str == null ? inetSocketAddressHolder.addr == null && inetSocketAddressHolder.hostname == null : !(inetSocketAddressHolder.addr != null || !str.equalsIgnoreCase(inetSocketAddressHolder.hostname));
            }
            if (!z || this.port != inetSocketAddressHolder.port) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            InetAddress inetAddress = this.addr;
            if (inetAddress != null) {
                hashCode = inetAddress.hashCode();
                i = this.port;
            } else {
                String str = this.hostname;
                if (str == null) {
                    return this.port;
                }
                hashCode = str.toLowerCase().hashCode();
                i = this.port;
            }
            return hashCode + i;
        }
    }

    private static int checkPort(int i) {
        if (i >= 0 && i <= 65535) {
            return i;
        }
        throw new IllegalArgumentException("port out of range:" + i);
    }

    private static String checkHost(String str) {
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("hostname can't be null");
    }

    public InetSocketAddress() {
        this.holder = new InetSocketAddressHolder(null, null, 0);
    }

    public InetSocketAddress(int i) {
        this((InetAddress) null, i);
    }

    public InetSocketAddress(InetAddress inetAddress, int i) {
        inetAddress = inetAddress == null ? Inet6Address.ANY : inetAddress;
        checkPort(i);
        this.holder = new InetSocketAddressHolder(null, inetAddress, i);
    }

    public InetSocketAddress(String str, int i) {
        InetAddress inetAddress;
        checkHost(str);
        try {
            inetAddress = InetAddress.getByName(str);
            str = null;
        } catch (UnknownHostException unused) {
            inetAddress = null;
        }
        checkPort(i);
        this.holder = new InetSocketAddressHolder(str, inetAddress, i);
    }

    private InetSocketAddress(int i, String str) {
        this.holder = new InetSocketAddressHolder(str, null, i);
    }

    public static InetSocketAddress createUnresolved(String str, int i) {
        checkPort(i);
        checkHost(str);
        return new InetSocketAddress(i, str);
    }

    static {
        try {
            Unsafe unsafe = Unsafe.getUnsafe();
            FIELDS_OFFSET = unsafe.objectFieldOffset(InetSocketAddress.class.getDeclaredField("holder"));
            UNSAFE = unsafe;
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    public final int getPort() {
        return this.holder.getPort();
    }

    public final InetAddress getAddress() {
        return this.holder.getAddress();
    }

    public final String getHostName() {
        return this.holder.getHostName();
    }

    public final String getHostString() {
        return this.holder.getHostString();
    }

    public final boolean isUnresolved() {
        return this.holder.isUnresolved();
    }

    public String toString() {
        return this.holder.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof InetSocketAddress)) {
            return false;
        }
        return this.holder.equals(((InetSocketAddress) obj).holder);
    }

    public final int hashCode() {
        return this.holder.hashCode();
    }
}
