package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.DataInputStream;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zz  reason: invalid class name and case insensitive filesystem */
public class C02230Zz {
    public int A00;
    public C02080Zc A01;

    public C02230Zz(C02080Zc r1, int i) {
        this.A01 = r1;
        this.A00 = i;
    }

    public final int A00(DataInputStream dataInputStream) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        this.A00 -= 2;
        return dataInputStream.readUnsignedByte() | (readUnsignedByte << 8);
    }

    public final String A01(DataInputStream dataInputStream) throws IOException {
        int A002 = A00(dataInputStream);
        byte[] bArr = new byte[A002];
        dataInputStream.readFully(bArr);
        this.A00 -= A002;
        return new String(bArr, "UTF-8");
    }
}
