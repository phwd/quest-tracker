package X;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* renamed from: X.0ik  reason: invalid class name and case insensitive filesystem */
public final class C02420ik extends AnonymousClass0lB {
    @Nullable
    public final ZipEntry A00;
    public final ZipFile A01;
    public final C02440im[] A02;
    public final /* synthetic */ AnonymousClass0HY A03;

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00e9 A[ADDED_TO_REGION, EDGE_INSN: B:75:0x00e9->B:48:0x00e9 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C02420ik(X.AnonymousClass0HY r19, X.AnonymousClass0T3 r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 325
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02420ik.<init>(X.0HY, X.0T3):void");
    }

    @Override // X.AnonymousClass0lB
    public final AnonymousClass0l8 A00() {
        return new AnonymousClass0l8(this.A02);
    }

    @Override // X.AnonymousClass0lB
    public final AbstractC03090lA A01() throws IOException {
        if (this.A00 == null) {
            return new C02410ij();
        }
        return new C02430il(this);
    }

    @Override // java.io.Closeable, X.AnonymousClass0lB, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }
}
