package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.DataInputStream;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.236  reason: invalid class name */
public class AnonymousClass236 {
    public int A00;
    public AnonymousClass23P A01;

    public AnonymousClass236(AnonymousClass23P r1, int i) {
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
