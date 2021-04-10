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

    public Proxy(Type type2, SocketAddress sa2) {
        if (type2 == Type.DIRECT || !(sa2 instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("type " + ((Object) type2) + " is not compatible with address " + ((Object) sa2));
        }
        this.type = type2;
        this.sa = sa2;
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
        return ((Object) type()) + " @ " + ((Object) address());
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Proxy)) {
            return false;
        }
        Proxy p = (Proxy) obj;
        if (p.type() != type()) {
            return false;
        }
        if (address() != null) {
            return address().equals(p.address());
        }
        if (p.address() == null) {
            return true;
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
