package X;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* renamed from: X.0hA  reason: invalid class name and case insensitive filesystem */
public final class C04230hA extends AbstractC03240cV {
    @Nullable
    public final ZipEntry A00;
    public final ZipFile A01;
    public final C04250hC[] A02;
    public final /* synthetic */ AnonymousClass08d A03;

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00df A[ADDED_TO_REGION, EDGE_INSN: B:73:0x00df->B:46:0x00df ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C04230hA(X.AnonymousClass08d r19, X.AnonymousClass0HU r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 325
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04230hA.<init>(X.08d, X.0HU):void");
    }

    @Override // X.AbstractC03240cV
    public final C03220cS A00() {
        return new C03220cS(this.A02);
    }

    @Override // X.AbstractC03240cV
    public final AbstractC03230cU A01() throws IOException {
        if (this.A00 == null) {
            return new AnonymousClass0h9();
        }
        return new C04240hB(this);
    }

    @Override // X.AbstractC03240cV, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }
}
