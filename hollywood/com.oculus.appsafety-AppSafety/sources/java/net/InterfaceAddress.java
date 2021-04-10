package java.net;

public class InterfaceAddress {
    private InetAddress address = null;
    private Inet4Address broadcast = null;
    private short maskLength = 0;

    InterfaceAddress() {
    }

    InterfaceAddress(InetAddress address2, Inet4Address broadcast2, InetAddress netmask) {
        this.address = address2;
        this.broadcast = broadcast2;
        this.maskLength = countPrefixLength(netmask);
    }

    private short countPrefixLength(InetAddress netmask) {
        short count = 0;
        byte[] address2 = netmask.getAddress();
        for (byte b : address2) {
            while (b != 0) {
                b = (byte) (b << 1);
                count = (short) (count + 1);
            }
        }
        return count;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public InetAddress getBroadcast() {
        return this.broadcast;
    }

    public short getNetworkPrefixLength() {
        return this.maskLength;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InterfaceAddress)) {
            return false;
        }
        InterfaceAddress cmp = (InterfaceAddress) obj;
        InetAddress inetAddress = this.address;
        if (inetAddress != null ? !inetAddress.equals(cmp.address) : cmp.address != null) {
            return false;
        }
        Inet4Address inet4Address = this.broadcast;
        if (inet4Address != null ? !inet4Address.equals(cmp.broadcast) : cmp.broadcast != null) {
            return false;
        }
        if (this.maskLength != cmp.maskLength) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.address.hashCode();
        Inet4Address inet4Address = this.broadcast;
        return hashCode + (inet4Address != null ? inet4Address.hashCode() : 0) + this.maskLength;
    }

    public String toString() {
        return ((Object) this.address) + "/" + ((int) this.maskLength) + " [" + ((Object) this.broadcast) + "]";
    }
}
