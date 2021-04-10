package X;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;

public final class Z1 extends GG<File> {
    public final int A00;
    public final Fz A01;
    public final File A02;
    public final String A03 = UUID.randomUUID().toString();

    /* JADX WARNING: Can't wrap try/catch for region: R(6:21|22|23|24|25|(3:46|27|28)) */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
        r2.A08(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0100, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x008c */
    @Override // X.GG
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.GF A04(@javax.annotation.Nullable java.lang.String r10, @javax.annotation.Nullable X.GF r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 267
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Z1.A04(java.lang.String, X.GF):X.GF");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.GG
    @Nullable
    public final File A05() {
        Z2 z2 = super.A02;
        if (z2 == null) {
            return null;
        }
        return z2.A00;
    }

    public Z1(int i, int i2, Fx fx, MG mg, File file, Fz fz, int i3) {
        super(i, i2, fx, mg);
        this.A02 = file;
        this.A01 = fz;
        this.A00 = i3;
    }

    private Z2 A00(File file, AbstractC0081Fy fy) throws IOException {
        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            return new Z2(this, file, fy);
        }
        StringBuilder sb = new StringBuilder("Unable to create parent directories for: ");
        sb.append(file);
        throw new IOException(sb.toString());
    }
}
