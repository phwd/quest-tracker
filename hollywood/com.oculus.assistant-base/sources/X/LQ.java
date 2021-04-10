package X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class LQ {
    public final void A00(byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            int i3 = 0 + i2;
            int i4 = i - i2;
            InputStream inputStream = ((C0992qC) this).A00;
            if (inputStream != null) {
                try {
                    int read = inputStream.read(bArr, i3, i4);
                    if (read < 0) {
                        throw new C0993qD(4);
                    } else if (read > 0) {
                        i2 += read;
                    } else {
                        StringBuilder sb = new StringBuilder("Cannot read. Remote side has closed. Tried to read ");
                        sb.append(i);
                        sb.append(" bytes, but only got ");
                        sb.append(i2);
                        sb.append(" bytes.");
                        throw new C0993qD(sb.toString());
                    }
                } catch (IOException e) {
                    throw new C0993qD(e);
                }
            } else {
                throw new C0993qD(1, "Cannot read from null inputStream");
            }
        }
    }

    public final void A01(byte[] bArr, int i, int i2) {
        OutputStream outputStream = ((C0992qC) this).A01;
        if (outputStream != null) {
            try {
                outputStream.write(bArr, i, i2);
            } catch (IOException e) {
                throw new C0993qD(e);
            }
        } else {
            throw new C0993qD(1, "Cannot write to null outputStream");
        }
    }
}
