package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lS  reason: invalid class name */
public final class AnonymousClass1lS implements AnonymousClass1lT {
    public final int A00 = 1;
    public final C05080sU A01;
    public final AbstractC00750Ik<File> A02;
    public final String A03;
    @VisibleForTesting
    public volatile AnonymousClass1m3 A04;

    /* JADX WARN: Incorrect args count in method signature: (ILX/0Ik<Ljava/io/File;>;Ljava/lang/String;Lcom/facebook/cache/common/CacheErrorLogger;)V */
    public AnonymousClass1lS(AbstractC00750Ik r3, String str, C05080sU r5) {
        this.A01 = r5;
        this.A02 = r3;
        this.A03 = str;
        this.A04 = new AnonymousClass1m3(null, null);
    }

    @VisibleForTesting
    private final synchronized AnonymousClass1lT A00() throws IOException {
        AnonymousClass1lT r0;
        File file;
        AnonymousClass1m3 r1 = this.A04;
        if (r1.A00 == null || (file = r1.A01) == null || !file.exists()) {
            if (!(this.A04.A00 == null || this.A04.A01 == null)) {
                AnonymousClass1OV.A00(this.A04.A01);
            }
            File file2 = new File(this.A02.get(), this.A03);
            try {
                C10341lr.A00(file2);
                file2.getAbsolutePath();
                this.A04 = new AnonymousClass1m3(file2, new AnonymousClass1lQ(file2, this.A00, this.A01));
            } catch (AnonymousClass1mF e) {
                throw e;
            }
        }
        r0 = this.A04.A00;
        if (r0 == null) {
            throw null;
        }
        return r0;
    }

    @Override // X.AnonymousClass1lT
    public final Collection<DiskStorage.Entry> A3t() throws IOException {
        return A00().A3t();
    }

    @Override // X.AnonymousClass1lT
    @Nullable
    public final AnonymousClass1m2 A4n(String str, Object obj) throws IOException {
        return A00().A4n(str, obj);
    }

    @Override // X.AnonymousClass1lT
    public final AnonymousClass1mL A5m(String str, Object obj) throws IOException {
        return A00().A5m(str, obj);
    }

    @Override // X.AnonymousClass1lT
    public final boolean A5w() {
        try {
            return A00().A5w();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // X.AnonymousClass1lT
    public final void A8k() {
        try {
            A00().A8k();
        } catch (IOException e) {
            AnonymousClass0J6 r1 = AnonymousClass0J5.A00;
            if (r1.A64(6)) {
                r1.A2i(AnonymousClass1lS.class.getSimpleName(), "purgeUnexpectedResources", e);
            }
        }
    }

    @Override // X.AnonymousClass1lT
    public final long A93(C10361lt r3) throws IOException {
        return A00().A93(r3);
    }
}
