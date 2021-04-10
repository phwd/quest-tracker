package X;

import android.util.Base64;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* renamed from: X.1RM  reason: invalid class name */
public final class AnonymousClass1RM {
    public final String A00 = "/tmp/";

    public final void A00(byte[] bArr, Object obj) {
        String str = this.A00;
        if (str != null && (obj instanceof AnonymousClass1no)) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(AnonymousClass006.A09(str, "/", Base64.encodeToString(bArr, 0)));
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
            } catch (IOException unused3) {
                return;
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }
}
