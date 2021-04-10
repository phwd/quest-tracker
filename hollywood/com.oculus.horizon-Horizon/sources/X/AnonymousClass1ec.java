package X;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.1ec  reason: invalid class name */
public class AnonymousClass1ec {
    public static final Charset A06 = Charset.forName("UTF-8");
    public AnonymousClass1fE A00;
    public boolean A01;
    public byte[] A02;
    public byte[] A03;
    public transient int A04;
    public transient String A05;

    public static void A00(int i) throws IOException {
        if (i < 0) {
            throw new EOFException();
        }
    }

    public static final boolean A01(AnonymousClass1ec r2) {
        byte[] bArr = r2.A02;
        if (bArr == null || bArr.length != 4) {
            return false;
        }
        return true;
    }

    public final String A02() {
        String str = this.A05;
        if (str != null) {
            return str;
        }
        try {
            String str2 = new String(this.A03, A06);
            this.A05 = str2;
            return str2;
        } catch (CharacterCodingException e) {
            throw new RuntimeException("Undetected CharacterCodingException", e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0091, code lost:
        if (r2 > 50) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1ec.toString():java.lang.String");
    }

    public AnonymousClass1ec(AnonymousClass1fE r9, List<AnonymousClass1ec> list) throws C09451fe {
        this.A00 = r9;
        this.A01 = true;
        long j = 0;
        for (AnonymousClass1ec r0 : list) {
            j += (long) r0.A03.length;
        }
        if (j < 0 || j > 2147483647L) {
            throw new C09451fe(AnonymousClass1fR.MessageTooBig, "Max frame length has been exceeded.");
        }
        int i = (int) j;
        this.A04 = i;
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (AnonymousClass1ec r2 : list) {
            byte[] bArr2 = r2.A03;
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += r2.A03.length;
        }
        this.A03 = bArr;
        this.A04 = i;
        this.A05 = null;
    }

    public AnonymousClass1ec(AnonymousClass1fE r1, boolean z) {
        this.A00 = r1;
        this.A01 = z;
    }

    public AnonymousClass1ec(AnonymousClass1ec r5) {
        this.A00 = r5.A00;
        this.A01 = r5.A01;
        byte[] bArr = r5.A03;
        this.A03 = bArr;
        this.A04 = bArr.length;
        this.A05 = null;
        byte[] bArr2 = r5.A02;
        if (bArr2 == null || bArr2.length == 4) {
            this.A02 = bArr2;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("MaskingKey ", Arrays.toString(bArr2), " hasn't length 4"));
    }
}
