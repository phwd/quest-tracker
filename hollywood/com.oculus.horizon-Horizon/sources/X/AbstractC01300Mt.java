package X;

import com.facebook.infer.annotation.NullsafeStrict;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
/* renamed from: X.0Mt  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01300Mt {
    @Nullable
    public AbstractC01300Mt A00;
    @Nullable
    public C01310Mu A01;
    @Nullable
    public AnonymousClass0pR A02;
    public AtomicInteger A03 = new AtomicInteger(0);
    public boolean A04;
    public boolean A05 = false;

    public abstract void A06();

    public abstract void A07();

    public abstract void A08();

    public abstract void A09(int i);

    public static final void A00(AbstractC01300Mt r1) {
        if (r1.A03.get() == 0) {
            r1.A08();
            C01310Mu r0 = r1.A01;
            if (r0 != null) {
                r1.A09(r0.A00);
            }
            r1.A06();
            r1.A04 = false;
            r1.A05 = false;
            r1.A02 = null;
            r1.A00 = null;
            if (r1.A01 != null) {
                r1.A07();
                return;
            }
            return;
        }
        throw new IllegalStateException("Releasing object with non-zero refCount.");
    }

    public final void A03() {
        String str;
        if (!this.A05) {
            AbstractC01300Mt r2 = this.A00;
            if (r2 != null) {
                StringBuilder sb = new StringBuilder("Already added to ");
                sb.append(r2);
                str = sb.toString();
            } else {
                return;
            }
        } else {
            str = "Attempting to re-attach a detached ParamsCollection";
        }
        throw new IllegalStateException(str);
    }

    public final void A04() {
        int decrementAndGet = this.A03.decrementAndGet();
        if (decrementAndGet == 1) {
            this.A05 = true;
            this.A00 = null;
        } else if (decrementAndGet >= 0) {
            A00(this);
        } else {
            throw new IllegalStateException("releaseFromParent() has been called with refCount == 0");
        }
    }

    public final void A05(C01310Mu r4) {
        String str;
        int incrementAndGet = this.A03.incrementAndGet();
        if (incrementAndGet == 1) {
            this.A01 = r4;
            if (!this.A04) {
                this.A04 = true;
                return;
            }
            str = "Internal bug, expected object to be immutable";
        } else {
            str = AnonymousClass006.A01("Acquired object with non-zero initial refCount current = ", incrementAndGet);
        }
        throw new IllegalStateException(str);
    }
}
