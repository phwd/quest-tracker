package sun.security.x509;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class IPAddressName implements GeneralNameInterface {
    private byte[] address;
    private boolean isIPv4;
    private String name;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 7;
    }

    public IPAddressName(DerValue derValue) {
        this(derValue.getOctetString());
    }

    public IPAddressName(byte[] bArr) {
        if (bArr.length == 4 || bArr.length == 8) {
            this.isIPv4 = true;
        } else if (bArr.length == 16 || bArr.length == 32) {
            this.isIPv4 = false;
        } else {
            throw new IOException("Invalid IPAddressName");
        }
        this.address = bArr;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putOctetString(this.address);
    }

    public String toString() {
        try {
            return "IPAddress: " + getName();
        } catch (IOException unused) {
            HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
            new StringBuilder().append("IPAddress: ");
            hexDumpEncoder.encodeBuffer(this.address);
            throw null;
        }
    }

    public String getName() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        int i = 0;
        if (this.isIPv4) {
            byte[] bArr = new byte[4];
            System.arraycopy(this.address, 0, bArr, 0, 4);
            this.name = InetAddress.getByAddress(bArr).getHostAddress();
            byte[] bArr2 = this.address;
            if (bArr2.length == 8) {
                byte[] bArr3 = new byte[4];
                System.arraycopy(bArr2, 4, bArr3, 0, 4);
                this.name += "/" + InetAddress.getByAddress(bArr3).getHostAddress();
            }
        } else {
            byte[] bArr4 = new byte[16];
            System.arraycopy(this.address, 0, bArr4, 0, 16);
            this.name = InetAddress.getByAddress(bArr4).getHostAddress();
            if (this.address.length == 32) {
                byte[] bArr5 = new byte[16];
                for (int i2 = 16; i2 < 32; i2++) {
                    bArr5[i2 - 16] = this.address[i2];
                }
                BitArray bitArray = new BitArray(128, bArr5);
                while (i < 128 && bitArray.get(i)) {
                    i++;
                }
                this.name += "/" + i;
                while (i < 128) {
                    if (!bitArray.get(i)) {
                        i++;
                    } else {
                        throw new IOException("Invalid IPv6 subdomain - set bit " + i + " not contiguous");
                    }
                }
            }
        }
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IPAddressName)) {
            return false;
        }
        byte[] bArr = ((IPAddressName) obj).address;
        int length = bArr.length;
        byte[] bArr2 = this.address;
        if (length != bArr2.length) {
            return false;
        }
        if (!(bArr2.length == 8 || bArr2.length == 32)) {
            return Arrays.equals(bArr, bArr2);
        }
        int length2 = this.address.length / 2;
        for (int i = 0; i < length2; i++) {
            byte[] bArr3 = this.address;
            int i2 = i + length2;
            if (((byte) (bArr3[i2] & bArr3[i])) != ((byte) (bArr[i] & bArr[i2]))) {
                return false;
            }
        }
        while (true) {
            byte[] bArr4 = this.address;
            if (length2 >= bArr4.length) {
                return true;
            }
            if (bArr4[length2] != bArr[length2]) {
                return false;
            }
            length2++;
        }
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.address;
            if (i >= bArr.length) {
                return i2;
            }
            i2 += bArr[i] * i;
            i++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
        if (((byte) (r10[r3] & r10[r12])) != ((byte) (r10[r12] & r15[r3]))) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a5, code lost:
        if (r9 != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ae, code lost:
        if (r6 != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00da, code lost:
        if (r2 == r0) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f2, code lost:
        if (r2 == r0) goto L_0x00b0;
     */
    @Override // sun.security.x509.GeneralNameInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int constrains(sun.security.x509.GeneralNameInterface r15) {
        /*
        // Method dump skipped, instructions count: 246
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.IPAddressName.constrains(sun.security.x509.GeneralNameInterface):int");
    }
}
