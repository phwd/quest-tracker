package X;

import com.facebook.infer.annotation.NullsafeStrict;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
public abstract class MF {
    @Nullable
    public MF A00;
    @Nullable
    public MG A01;
    @Nullable
    public YD A02;
    public boolean A03;
    public AtomicInteger A04 = new AtomicInteger(0);
    public boolean A05 = false;

    public abstract void A06();

    public abstract void A07();

    public abstract void A08();

    public abstract void A09(int i);

    private final void A00() {
        if (this.A04.get() == 0) {
            A08();
            MG mg = this.A01;
            if (mg != null) {
                A09(mg.A00);
            }
            A06();
            this.A03 = false;
            this.A05 = false;
            this.A02 = null;
            this.A00 = null;
            if (this.A01 != null) {
                A07();
                return;
            }
            return;
        }
        throw new IllegalStateException("Releasing object with non-zero refCount.");
    }

    public final void A02() {
        String str;
        int decrementAndGet = this.A04.decrementAndGet();
        if (decrementAndGet != 1) {
            if (decrementAndGet >= 0) {
                MF mf = this.A00;
                if (mf == null) {
                    A00();
                    return;
                }
                StringBuilder sb = new StringBuilder("Trying to release, when added to ");
                sb.append(mf);
                str = sb.toString();
            } else {
                str = "release() has been called with refCount == 0";
            }
            throw new IllegalStateException(str);
        }
    }

    public final void A03() {
        String str;
        if (!this.A05) {
            MF mf = this.A00;
            if (mf != null) {
                StringBuilder sb = new StringBuilder("Already added to ");
                sb.append(mf);
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
        int decrementAndGet = this.A04.decrementAndGet();
        if (decrementAndGet == 1) {
            this.A05 = true;
            this.A00 = null;
        } else if (decrementAndGet >= 0) {
            A00();
        } else {
            throw new IllegalStateException("releaseFromParent() has been called with refCount == 0");
        }
    }

    public final void A05(MG mg) {
        String str;
        int incrementAndGet = this.A04.incrementAndGet();
        if (incrementAndGet == 1) {
            this.A01 = mg;
            if (!this.A03) {
                this.A03 = true;
                return;
            }
            str = "Internal bug, expected object to be immutable";
        } else {
            str = AnonymousClass06.A01("Acquired object with non-zero initial refCount current = ", incrementAndGet);
        }
        throw new IllegalStateException(str);
    }
}
