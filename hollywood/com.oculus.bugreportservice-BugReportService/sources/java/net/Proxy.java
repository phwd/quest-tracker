package java.net;

public class Proxy {
    public static final Proxy NO_PROXY = new Proxy();
    private SocketAddress sa;
    private Type type;

    public enum Type {
        DIRECT,
        HTTP,
        SOCKS
    }

    private Proxy() {
        this.type = Type.DIRECT;
        this.sa = null;
    }

    public Proxy(Type type2, SocketAddress socketAddress) {
        if (type2 == Type.DIRECT || !(socketAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("type " + type2 + " is not compatible with address " + socketAddress);
        }
        this.type = type2;
        this.sa = socketAddress;
    }

    public Type type() {
        return this.type;
    }

    public SocketAddress address() {
        return this.sa;
    }

    public String toString() {
        if (type() == Type.DIRECT) {
            return "DIRECT";
        }
        return type() + " @ " + address();
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof Proxy)) {
            Proxy proxy = (Proxy) obj;
            if (proxy.type() == type()) {
                if (address() != null) {
                    return address().equals(proxy.address());
                }
                if (proxy.address() == null) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (address() == null) {
            return type().hashCode();
        }
        return type().hashCode() + address().hashCode();
    }
}
