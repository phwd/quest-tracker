package X;

import android.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

/* renamed from: X.0mh  reason: invalid class name and case insensitive filesystem */
public final class C06200mh {
    public final String A00 = "/tmp/";

    public final void A00(byte[] bArr) {
        String A07 = AnonymousClass006.A07(this.A00, "/", Base64.encodeToString(bArr, 0));
        if (!new File(A07).delete()) {
            System.out.println(AnonymousClass006.A05("Could not delete the session file ", A07));
        }
    }

    public final void A01(byte[] bArr, Object obj) {
        String str = this.A00;
        if (str != null && (obj instanceof C01810Xo)) {
            String A07 = AnonymousClass006.A07(str, "/", Base64.encodeToString(bArr, 0));
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(A07);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    try {
                        objectOutputStream.writeObject(obj);
                        objectOutputStream.close();
                        fileOutputStream.close();
                        return;
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                }
            } catch (IOException e) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder("Error during put session ");
                sb.append(A07);
                sb.append(" : ");
                sb.append(e);
                printStream.println(sb.toString());
                return;
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }
}
