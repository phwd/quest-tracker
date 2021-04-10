package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oO  reason: invalid class name */
public final class AnonymousClass1oO implements AnonymousClass1oV {
    public final int A00 = 1;
    public final C09611oe A01;
    public final AnonymousClass0KW<File> A02;
    public final String A03;
    @VisibleForTesting
    public volatile AnonymousClass1oZ A04;

    /* JADX WARN: Incorrect args count in method signature: (ILX/0KW<Ljava/io/File;>;Ljava/lang/String;Lcom/facebook/cache/common/CacheErrorLogger;)V */
    public AnonymousClass1oO(AnonymousClass0KW r3, String str, C09611oe r5) {
        this.A01 = r5;
        this.A02 = r3;
        this.A03 = str;
        this.A04 = new AnonymousClass1oZ(null, null);
    }

    @VisibleForTesting
    private final synchronized AnonymousClass1oV A00() throws IOException {
        AnonymousClass1oV r0;
        File file;
        AnonymousClass1oZ r1 = this.A04;
        if (r1.A00 == null || (file = r1.A01) == null || !file.exists()) {
            if (!(this.A04.A00 == null || this.A04.A01 == null)) {
                AnonymousClass1gm.A00(this.A04.A01);
            }
            File file2 = new File(this.A02.get(), this.A03);
            try {
                AnonymousClass1oD.A00(file2);
                file2.getAbsolutePath();
                this.A04 = new AnonymousClass1oZ(file2, new AnonymousClass1oA(file2, this.A00, this.A01));
            } catch (AnonymousClass1oI e) {
                throw e;
            }
        }
        r0 = this.A04.A00;
        if (r0 == null) {
            throw null;
        }
        return r0;
    }

    @Override // X.AnonymousClass1oV
    public final Collection<DiskStorage.Entry> A3N() throws IOException {
        return A00().A3N();
    }

    @Override // X.AnonymousClass1oV
    @Nullable
    public final AnonymousClass1oH A4I(String str, Object obj) throws IOException {
        return A00().A4I(str, obj);
    }

    @Override // X.AnonymousClass1oV
    public final AnonymousClass1oM A4r(String str, Object obj) throws IOException {
        return A00().A4r(str, obj);
    }

    @Override // X.AnonymousClass1oV
    public final boolean A4y() {
        try {
            return A00().A4y();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // X.AnonymousClass1oV
    public final void A7g() {
        try {
            A00().A7g();
        } catch (IOException e) {
            AbstractC01090Kc r1 = C01080Kb.A00;
            if (r1.A54(6)) {
                r1.A2K(AnonymousClass1oO.class.getSimpleName(), "purgeUnexpectedResources", e);
            }
        }
    }

    @Override // X.AnonymousClass1oV
    public final long A88(AnonymousClass1oE r3) throws IOException {
        return A00().A88(r3);
    }
}
