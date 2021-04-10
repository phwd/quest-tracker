package X;

import com.facebook.infer.annotation.NullsafeStrict;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
/* renamed from: X.0Mq  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01860Mq {
    @Nullable
    public AbstractC01860Mq A00;
    @Nullable
    public C01870Mr A01;
    public boolean A02;
    public AtomicInteger A03 = new AtomicInteger(0);

    public final void A02() {
        AbstractC01860Mq r2 = this.A00;
        if (r2 != null) {
            throw new IllegalStateException("Already added to " + r2);
        }
    }

    public final void A03(C01870Mr r4) {
        String str;
        int incrementAndGet = this.A03.incrementAndGet();
        if (incrementAndGet == 1) {
            this.A01 = r4;
            if (!this.A02) {
                this.A02 = true;
                return;
            }
            str = "Internal bug, expected object to be immutable";
        } else {
            str = AnonymousClass006.A01("Acquired object with non-zero initial refCount current = ", incrementAndGet);
        }
        throw new IllegalStateException(str);
    }
}
