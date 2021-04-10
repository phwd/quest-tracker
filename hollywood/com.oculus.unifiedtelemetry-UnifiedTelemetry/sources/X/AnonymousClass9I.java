package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;

@GwtIncompatible
/* renamed from: X.9I  reason: invalid class name */
public abstract class AnonymousClass9I {
    public abstract InputStream A01() throws IOException;

    public byte[] A02() throws IOException {
        byte[] bArr;
        C00319g r2 = new C00319g(C00319g.A03);
        try {
            InputStream A01 = A01();
            if (A01 != null) {
                r2.A02.addFirst(A01);
            }
            Optional<Long> A00 = A00();
            if (A00.isPresent()) {
                bArr = AnonymousClass9K.A00(A01, A00.get().longValue());
            } else if (A01 != null) {
                bArr = AnonymousClass9K.A01(A01, new ArrayDeque(20), 0);
            } else {
                throw null;
            }
            r2.close();
            return bArr;
        } catch (Throwable th) {
            r2.close();
            throw th;
        }
    }

    @Beta
    public Optional<Long> A00() {
        return Absent.INSTANCE;
    }
}
