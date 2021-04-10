package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pV  reason: invalid class name */
public final class AnonymousClass1pV {
    public final C09891pv A00;
    public final AbstractC10131rK A01;

    public final C10021qp A00(InputStream inputStream, int i) throws IOException {
        C09991ql r1 = new C09991ql(this.A01, i);
        try {
            this.A00.A00(inputStream, r1);
            return r1.A00();
        } finally {
            r1.close();
        }
    }

    public AnonymousClass1pV(AbstractC10131rK r1, C09891pv r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
