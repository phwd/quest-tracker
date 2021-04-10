package sun.security.x509;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class IPAddressName implements GeneralNameInterface {
    private static final int MASKSIZE = 16;
    private byte[] address;
    private boolean isIPv4;
    private String name;

    public IPAddressName(DerValue derValue) throws IOException {
        this(derValue.getOctetString());
    }

    public IPAddressName(byte[] address2) throws IOException {
        if (address2.length == 4 || address2.length == 8) {
            this.isIPv4 = true;
        } else if (address2.length == 16 || address2.length == 32) {
            this.isIPv4 = false;
        } else {
            throw new IOException("Invalid IPAddressName");
        }
        this.address = address2;
    }

    public IPAddressName(String name2) throws IOException {
        if (name2 == null || name2.length() == 0) {
            throw new IOException("IPAddress cannot be null or empty");
        } else if (name2.charAt(name2.length() - 1) == '/') {
            throw new IOException("Invalid IPAddress: " + name2);
        } else if (name2.indexOf(58) >= 0) {
            parseIPv6(name2);
            this.isIPv4 = false;
        } else if (name2.indexOf(46) >= 0) {
            parseIPv4(name2);
            this.isIPv4 = true;
        } else {
            throw new IOException("Invalid IPAddress: " + name2);
        }
    }

    private void parseIPv4(String name2) throws IOException {
        int slashNdx = name2.indexOf(47);
        if (slashNdx == -1) {
            this.address = InetAddress.getByName(name2).getAddress();
            return;
        }
        this.address = new byte[8];
        byte[] mask = InetAddress.getByName(name2.substring(slashNdx + 1)).getAddress();
        System.arraycopy(InetAddress.getByName(name2.substring(0, slashNdx)).getAddress(), 0, this.address, 0, 4);
        System.arraycopy(mask, 0, this.address, 4, 4);
    }

    private void parseIPv6(String name2) throws IOException {
        int slashNdx = name2.indexOf(47);
        if (slashNdx == -1) {
            this.address = InetAddress.getByName(name2).getAddress();
            return;
        }
        this.address = new byte[32];
        System.arraycopy(InetAddress.getByName(name2.substring(0, slashNdx)).getAddress(), 0, this.address, 0, 16);
        int prefixLen = Integer.parseInt(name2.substring(slashNdx + 1));
        if (prefixLen < 0 || prefixLen > 128) {
            throw new IOException("IPv6Address prefix length (" + prefixLen + ") in out of valid range [0,128]");
        }
        BitArray bitArray = new BitArray(128);
        for (int i = 0; i < prefixLen; i++) {
            bitArray.set(i, true);
        }
        byte[] maskArray = bitArray.toByteArray();
        for (int i2 = 0; i2 < 16; i2++) {
            this.address[i2 + 16] = maskArray[i2];
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 7;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream out) throws IOException {
        out.putOctetString(this.address);
    }

    public String toString() {
        try {
            return "IPAddress: " + getName();
        } catch (IOException e) {
            HexDumpEncoder enc = new HexDumpEncoder();
            return "IPAddress: " + enc.encodeBuffer(this.address);
        }
    }

    public String getName() throws IOException {
        String str = this.name;
        if (str != null) {
            return str;
        }
        if (this.isIPv4) {
            byte[] host = new byte[4];
            System.arraycopy(this.address, 0, host, 0, 4);
            this.name = InetAddress.getByAddress(host).getHostAddress();
            byte[] bArr = this.address;
            if (bArr.length == 8) {
                byte[] mask = new byte[4];
                System.arraycopy(bArr, 4, mask, 0, 4);
                this.name += "/" + InetAddress.getByAddress(mask).getHostAddress();
            }
        } else {
            byte[] host2 = new byte[16];
            System.arraycopy(this.address, 0, host2, 0, 16);
            this.name = InetAddress.getByAddress(host2).getHostAddress();
            if (this.address.length == 32) {
                byte[] maskBytes = new byte[16];
                for (int i = 16; i < 32; i++) {
                    maskBytes[i - 16] = this.address[i];
                }
                BitArray ba = new BitArray(128, maskBytes);
                int i2 = 0;
                while (i2 < 128 && ba.get(i2)) {
                    i2++;
                }
                this.name += "/" + i2;
                while (i2 < 128) {
                    if (!ba.get(i2)) {
                        i2++;
                    } else {
                        throw new IOException("Invalid IPv6 subdomain - set bit " + i2 + " not contiguous");
                    }
                }
            }
        }
        return this.name;
    }

    public byte[] getBytes() {
        return (byte[]) this.address.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IPAddressName)) {
            return false;
        }
        byte[] other = ((IPAddressName) obj).address;
        int length = other.length;
        byte[] bArr = this.address;
        if (length != bArr.length) {
            return false;
        }
        if (!(bArr.length == 8 || bArr.length == 32)) {
            return Arrays.equals(other, bArr);
        }
        int maskLen = this.address.length / 2;
        for (int i = 0; i < maskLen; i++) {
            byte[] bArr2 = this.address;
            if (((byte) (bArr2[i + maskLen] & bArr2[i])) != ((byte) (other[i] & other[i + maskLen]))) {
                return false;
            }
        }
        int i2 = maskLen;
        while (true) {
            byte[] bArr3 = this.address;
            if (i2 >= bArr3.length) {
                return true;
            }
            if (bArr3[i2] != other[i2]) {
                return false;
            }
            i2++;
        }
    }

    public int hashCode() {
        int retval = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.address;
            if (i >= bArr.length) {
                return retval;
            }
            retval += bArr[i] * i;
            i++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008b, code lost:
        if (((byte) (r8[r7] & r8[r7 + r6])) != ((byte) (r8[r7 + r6] & r1[r7]))) goto L_0x008d;
     */
    @Override // sun.security.x509.GeneralNameInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int constrains(sun.security.x509.GeneralNameInterface r13) throws java.lang.UnsupportedOperationException {
        /*
        // Method dump skipped, instructions count: 293
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.IPAddressName.constrains(sun.security.x509.GeneralNameInterface):int");
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int subtreeDepth() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("subtreeDepth() not defined for IPAddressName");
    }
}
