package X;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/* renamed from: X.0be  reason: invalid class name and case insensitive filesystem */
public final class C02860be {
    public static final Charset A02 = Charset.forName("UTF-8");
    public int A00 = 0;
    public ByteArrayOutputStream A01;

    public final void A00(String str, byte b) throws C02850bd {
        String str2;
        if (this.A00 < 255) {
            byte[] bytes = str.getBytes(A02);
            int length = bytes.length;
            if (length <= 255) {
                this.A01.write(b);
                this.A01.write(length & 255);
                this.A01.write(bytes, 0, length);
                this.A00++;
                return;
            }
            str2 = "String size (UTF-8 encoded) cannot exceed 255";
        } else {
            str2 = "Total number of entries cannot exceed 255";
        }
        throw new C02850bd(str2);
    }

    public C02860be() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.A01 = byteArrayOutputStream;
        byteArrayOutputStream.write(-5);
        this.A01.write(0);
    }
}
