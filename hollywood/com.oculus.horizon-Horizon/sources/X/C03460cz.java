package X;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0cz  reason: invalid class name and case insensitive filesystem */
public final class C03460cz extends AnonymousClass0tv {
    public final File A00;

    @Override // X.AnonymousClass0tv
    public final InputStream A00() throws IOException {
        return new FileInputStream(this.A00);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Files.asByteSource(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public C03460cz(File file) {
        this.A00 = file;
    }
}
