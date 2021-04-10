package X;

import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0d0  reason: invalid class name and case insensitive filesystem */
public final class C03470d0 extends AnonymousClass0tu {
    public final ImmutableSet<EnumC07580uB> A00;
    public final File A01;

    @Override // X.AnonymousClass0tu
    public final OutputStream A00() throws IOException {
        return new FileOutputStream(this.A01, this.A00.contains(EnumC07580uB.APPEND));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Files.asByteSink(");
        sb.append(this.A01);
        sb.append(", ");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public C03470d0(File file, EnumC07580uB... r3) {
        this.A01 = file;
        this.A00 = ImmutableSet.A09(r3);
    }
}
