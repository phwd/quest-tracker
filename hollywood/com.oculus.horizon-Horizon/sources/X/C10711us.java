package X;

import java.io.Serializable;
import java.util.Arrays;

/* renamed from: X.1us  reason: invalid class name and case insensitive filesystem */
public final class C10711us implements Serializable {
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

    public C10711us(byte[] bArr, long j, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str, byte b) throws AnonymousClass1v5 {
        this.pskVal = bArr;
        this.cipher = "TLS_AES_128_GCM_SHA256";
        this.maxEarlyDataSize = j;
        this.ticketAgeAdd = AnonymousClass1ut.A02(bArr2);
        long A02 = AnonymousClass1ut.A02(bArr3);
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
        return Arrays.equals(this.ticket, ((C10711us) obj).ticket);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.ticket);
    }

    public final byte A00() {
        return this.certsID;
    }
}
