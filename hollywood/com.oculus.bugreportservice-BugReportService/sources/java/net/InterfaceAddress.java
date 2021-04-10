package java.net;

public class InterfaceAddress {
    private InetAddress address = null;
    private Inet4Address broadcast = null;
    private short maskLength = 0;

    InterfaceAddress() {
    }

    InterfaceAddress(InetAddress inetAddress, Inet4Address inet4Address, InetAddress inetAddress2) {
        this.address = inetAddress;
        this.broadcast = inet4Address;
        this.maskLength = countPrefixLength(inetAddress2);
    }

    private short countPrefixLength(InetAddress inetAddress) {
        byte[] address2 = inetAddress.getAddress();
        short s = 0;
        for (byte b : address2) {
            while (b != 0) {
                b = (byte) (b << 1);
                s = (short) (s + 1);
            }
        }
        return s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InterfaceAddress)) {
            return false;
        }
        InterfaceAddress interfaceAddress = (InterfaceAddress) obj;
        InetAddress inetAddress = this.address;
        if (inetAddress != null ? !inetAddress.equals(interfaceAddress.address) : interfaceAddress.address != null) {
            return false;
        }
        Inet4Address inet4Address = this.broadcast;
        if (inet4Address != null ? !inet4Address.equals(interfaceAddress.broadcast) : interfaceAddress.broadcast != null) {
            return false;
        }
        if (this.maskLength != interfaceAddress.maskLength) {
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
        return this.address + "/" + ((int) this.maskLength) + " [" + this.broadcast + "]";
    }
}
