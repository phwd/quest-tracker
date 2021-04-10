package X;

import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class UN extends AnonymousClass9F {
    public final ImmutableSet<EnumC00439y> A00;
    public final File A01;

    @Override // X.AnonymousClass9F
    public final OutputStream A00() throws IOException {
        return new FileOutputStream(this.A01, this.A00.contains(EnumC00439y.APPEND));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Files.asByteSink(");
        sb.append(this.A01);
        sb.append(", ");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public UN(File file, EnumC00439y... r3) {
        this.A01 = file;
        this.A00 = ImmutableSet.A07(r3);
    }
}
