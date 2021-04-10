package java.net;

import android.system.OsConstants;

public final class Inet4Address extends InetAddress {
    public static final InetAddress ALL = new Inet4Address((String) null, new byte[]{-1, -1, -1, -1});
    public static final InetAddress ANY = new Inet4Address((String) null, new byte[]{0, 0, 0, 0});
    public static final InetAddress LOOPBACK = new Inet4Address("localhost", new byte[]{Byte.MAX_VALUE, 0, 0, 1});
    private static final long serialVersionUID = 3286316764910316507L;

    Inet4Address() {
        holder().hostName = null;
        holder().address = 0;
        holder().family = OsConstants.AF_INET;
    }

    Inet4Address(String str, byte[] bArr) {
        holder().hostName = str;
        holder().family = OsConstants.AF_INET;
        if (bArr != null && bArr.length == 4) {
            holder().address = ((bArr[0] << 24) & -16777216) | (bArr[3] & 255) | ((bArr[2] << 8) & 65280) | ((bArr[1] << 16) & 16711680);
        }
        holder().originalHostName = str;
    }

    Inet4Address(String str, int i) {
        holder().hostName = str;
        holder().family = OsConstants.AF_INET;
        holder().address = i;
        holder().originalHostName = str;
    }

    private Object writeReplace() {
        InetAddress inetAddress = new InetAddress();
        inetAddress.holder().hostName = holder().getHostName();
        inetAddress.holder().address = holder().getAddress();
        inetAddress.holder().family = 2;
        return inetAddress;
    }

    @Override // java.net.InetAddress
    public boolean isAnyLocalAddress() {
        return holder().getAddress() == 0;
    }

    @Override // java.net.InetAddress
    public byte[] getAddress() {
        int address = holder().getAddress();
        return new byte[]{(byte) ((address >>> 24) & 255), (byte) ((address >>> 16) & 255), (byte) ((address >>> 8) & 255), (byte) (address & 255)};
    }

    @Override // java.net.InetAddress
    public String getHostAddress() {
        return numericToTextFormat(getAddress());
    }

    @Override // java.net.InetAddress
    public int hashCode() {
        return holder().getAddress();
    }

    @Override // java.net.InetAddress
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Inet4Address) && ((InetAddress) obj).holder().getAddress() == holder().getAddress();
    }

    static String numericToTextFormat(byte[] bArr) {
        return (bArr[0] & 255) + "." + (bArr[1] & 255) + "." + (bArr[2] & 255) + "." + (bArr[3] & 255);
    }
}
