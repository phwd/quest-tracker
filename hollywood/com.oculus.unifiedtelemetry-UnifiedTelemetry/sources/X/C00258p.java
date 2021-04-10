package X;

import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;

@VisibleForTesting
/* renamed from: X.8p  reason: invalid class name and case insensitive filesystem */
public class C00258p extends I9 {
    public final File A00;
    public final Fz<Object> A01;

    @Override // X.I9
    public final void A03() {
    }

    @Override // X.I9
    public final AbstractC0081Fy A00() {
        return this.A01.A02(this.A00);
    }

    @Override // X.I9
    public final void A02() {
        File file = this.A00;
        if (!file.delete()) {
            Mu.A06("FileBatchPayloadIterator", "Failed to remove %s", file);
        }
    }

    @Override // X.I9
    public final void A04(Writer writer) throws IOException {
        C0087Gv gv = new C0087Gv(new FileInputStream(this.A00), ByteBuffer.wrap(I9.A03.get()));
        try {
            char[] cArr = I9.A04.get();
            while (true) {
                int read = gv.read(cArr);
                if (read != -1) {
                    writer.write(cArr, 0, read);
                } else {
                    return;
                }
            }
        } finally {
            gv.close();
        }
    }

    public C00258p(Fw fw, File file, Fz<Object> fz) {
        super(fw, file);
        this.A00 = file;
        this.A01 = fz;
    }
}
