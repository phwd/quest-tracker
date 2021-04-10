package X;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class UM extends AnonymousClass9I {
    public final File A00;

    @Override // X.AnonymousClass9I
    public final Optional<Long> A00() {
        File file = this.A00;
        if (!file.isFile()) {
            return Absent.INSTANCE;
        }
        Long valueOf = Long.valueOf(file.length());
        if (valueOf != null) {
            return new Present(valueOf);
        }
        throw null;
    }

    @Override // X.AnonymousClass9I
    public final /* bridge */ /* synthetic */ InputStream A01() throws IOException {
        return new FileInputStream(this.A00);
    }

    @Override // X.AnonymousClass9I
    public final byte[] A02() throws IOException {
        C00319g r3 = new C00319g(C00319g.A03);
        try {
            FileInputStream fileInputStream = new FileInputStream(this.A00);
            r3.A02.addFirst(fileInputStream);
            byte[] A002 = AnonymousClass9K.A00(fileInputStream, fileInputStream.getChannel().size());
            r3.close();
            return A002;
        } catch (Throwable th) {
            r3.close();
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Files.asByteSource(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public UM(File file) {
        if (file != null) {
            this.A00 = file;
            return;
        }
        throw null;
    }
}
