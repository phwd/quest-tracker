package X;

import android.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

/* renamed from: X.10j  reason: invalid class name */
public final class AnonymousClass10j {
    public final String A00 = "/tmp/";

    public final void A00(byte[] bArr) {
        String A07 = AnonymousClass006.A07(this.A00, "/", Base64.encodeToString(bArr, 0));
        if (!new File(A07).delete()) {
            System.out.println(AnonymousClass006.A05("Could not delete the session file ", A07));
        }
    }

    public final void A01(byte[] bArr, Object obj) {
        String str = this.A00;
        if (str != null && (obj instanceof C098916y)) {
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
                printStream.println("Error during put session " + A07 + " : " + e);
                return;
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }
}
