package X;

import java.io.Serializable;
import java.util.Arrays;

/* renamed from: X.1np  reason: invalid class name */
public final class AnonymousClass1np implements Serializable {
    public static final long serialVersionUID = 84430101;
    public final byte certsID;
    public final String cipher;
    public final long maxEarlyDataSize;
    public final byte[] pskVal;
    public final String sni;
    public final byte[] ticket;
    public final long ticketAgeAdd;
    public final long ticketIssuedTime;
    public final long ticketLifetime;
    public boolean useTestTime = false;

    public AnonymousClass1np(byte[] bArr, long j, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str, byte b) throws AnonymousClass25A {
        this.pskVal = bArr;
        this.cipher = "TLS_AES_128_GCM_SHA256";
        this.maxEarlyDataSize = j;
        this.ticketAgeAdd = AnonymousClass24K.A02(bArr2);
        long A02 = AnonymousClass24K.A02(bArr3);
        this.ticketLifetime = (A02 > 172800 ? 172800 : A02) * 1000;
        this.ticket = bArr4;
        this.ticketIssuedTime = System.currentTimeMillis();
        this.sni = str;
        this.certsID = b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.ticket, ((AnonymousClass1np) obj).ticket);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.ticket);
    }
}
