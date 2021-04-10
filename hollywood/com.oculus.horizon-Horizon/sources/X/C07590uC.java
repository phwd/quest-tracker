package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Beta
@GwtIncompatible
/* renamed from: X.0uC  reason: invalid class name and case insensitive filesystem */
public final class C07590uC {
    public static final TreeTraverser<File> A00 = new C03480d2();
    public static final SuccessorsFunction<File> A01 = new AnonymousClass0d1();

    public static void A00(File file, File file2) throws IOException {
        StringBuilder sb;
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (!file.renameTo(file2)) {
            Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
            C03460cz r2 = new C03460cz(file);
            C03470d0 r1 = new C03470d0(file2, new EnumC07580uB[0]);
            AnonymousClass0u6 r3 = new AnonymousClass0u6(AnonymousClass0u6.A03);
            try {
                InputStream A002 = r2.A00();
                if (A002 != null) {
                    r3.A02.addFirst(A002);
                }
                OutputStream A003 = r1.A00();
                if (A003 != null) {
                    r3.A02.addFirst(A003);
                }
                if (A002 == null || A003 == null) {
                    throw null;
                }
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = A002.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    A003.write(bArr, 0, read);
                }
                r3.close();
                if (!file.delete()) {
                    if (!file2.delete()) {
                        sb = new StringBuilder();
                        sb.append("Unable to delete ");
                        sb.append(file2);
                    } else {
                        sb = new StringBuilder();
                        sb.append("Unable to delete ");
                        sb.append(file);
                    }
                    throw new IOException(sb.toString());
                }
            } catch (Throwable th) {
                r3.close();
                throw th;
            }
        }
    }
}
