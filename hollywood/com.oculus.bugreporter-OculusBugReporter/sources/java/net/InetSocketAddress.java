package java.net;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
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

        private InetSocketAddressHolder(String hostname2, InetAddress addr2, int port2) {
            this.hostname = hostname2;
            this.addr = addr2;
            this.port = port2;
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
            boolean sameIP;
            if (obj == null || !(obj instanceof InetSocketAddressHolder)) {
                return false;
            }
            InetSocketAddressHolder that = (InetSocketAddressHolder) obj;
            InetAddress inetAddress = this.addr;
            if (inetAddress != null) {
                sameIP = inetAddress.equals(that.addr);
            } else {
                String str = this.hostname;
                if (str != null) {
                    sameIP = that.addr == null && str.equalsIgnoreCase(that.hostname);
                } else {
                    sameIP = that.addr == null && that.hostname == null;
                }
            }
            if (!sameIP || this.port != that.port) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            InetAddress inetAddress = this.addr;
            if (inetAddress != null) {
                return inetAddress.hashCode() + this.port;
            }
            String str = this.hostname;
            if (str != null) {
                return str.toLowerCase().hashCode() + this.port;
            }
            return this.port;
        }
    }

    private static int checkPort(int port) {
        if (port >= 0 && port <= 65535) {
            return port;
        }
        throw new IllegalArgumentException("port out of range:" + port);
    }

    private static String checkHost(String hostname) {
        if (hostname != null) {
            return hostname;
        }
        throw new IllegalArgumentException("hostname can't be null");
    }

    public InetSocketAddress() {
        this.holder = new InetSocketAddressHolder(null, null, 0);
    }

    public InetSocketAddress(int port) {
        this((InetAddress) null, port);
    }

    public InetSocketAddress(InetAddress addr, int port) {
        this.holder = new InetSocketAddressHolder(null, addr == null ? Inet6Address.ANY : addr, checkPort(port));
    }

    public InetSocketAddress(String hostname, int port) {
        checkHost(hostname);
        InetAddress addr = null;
        String host = null;
        try {
            addr = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            host = hostname;
        }
        this.holder = new InetSocketAddressHolder(host, addr, checkPort(port));
    }

    private InetSocketAddress(int port, String hostname) {
        this.holder = new InetSocketAddressHolder(hostname, null, port);
    }

    public static InetSocketAddress createUnresolved(String host, int port) {
        return new InetSocketAddress(checkPort(port), checkHost(host));
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField pfields = out.putFields();
        pfields.put("hostname", this.holder.hostname);
        pfields.put("addr", this.holder.addr);
        pfields.put("port", this.holder.port);
        out.writeFields();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField oisFields = in.readFields();
        String oisHostname = (String) oisFields.get("hostname", (Object) null);
        InetAddress oisAddr = (InetAddress) oisFields.get("addr", (Object) null);
        int oisPort = oisFields.get("port", -1);
        checkPort(oisPort);
        if (oisHostname == null && oisAddr == null) {
            throw new InvalidObjectException("hostname and addr can't both be null");
        }
        UNSAFE.putObject(this, FIELDS_OFFSET, new InetSocketAddressHolder(oisHostname, oisAddr, oisPort));
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("Stream data required");
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
