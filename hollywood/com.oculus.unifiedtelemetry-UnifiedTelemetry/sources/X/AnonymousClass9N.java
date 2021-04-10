package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.Reader;

@GwtIncompatible
/* renamed from: X.9N  reason: invalid class name */
public abstract class AnonymousClass9N {
    public abstract Reader A00() throws IOException;

    public String A01() throws IOException {
        C00319g r5 = new C00319g(C00319g.A03);
        try {
            Reader A00 = A00();
            if (A00 != null) {
                r5.A02.addFirst(A00);
            }
            StringBuilder sb = new StringBuilder();
            if (A00 != null) {
                char[] cArr = new char[2048];
                while (true) {
                    int read = A00.read(cArr);
                    if (read != -1) {
                        sb.append(cArr, 0, read);
                    } else {
                        String obj = sb.toString();
                        r5.close();
                        return obj;
                    }
                }
            } else {
                throw null;
            }
        } catch (Throwable th) {
            r5.close();
            throw th;
        }
    }
}
