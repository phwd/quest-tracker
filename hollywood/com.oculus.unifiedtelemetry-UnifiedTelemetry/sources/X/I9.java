package X;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class I9 implements Z9 {
    public static final ThreadLocal<byte[]> A03 = new H2();
    public static final ThreadLocal<char[]> A04 = new H3();
    public final Fw A00;
    public final Object A01;
    @Nullable
    public volatile AbstractC0081Fy A02;

    public abstract AbstractC0081Fy A00();

    public abstract void A02();

    public abstract void A03();

    public abstract void A04(Writer writer) throws IOException;

    @Override // X.ST
    public final boolean A3G() {
        return false;
    }

    @Override // X.ST
    public final void A5v(Writer writer) throws IOException {
        A05(writer, false);
    }

    @Override // X.Z9
    public final boolean A2z() {
        boolean z;
        AbstractC0081Fy fy = this.A02;
        if (fy != null) {
            synchronized (fy) {
                z = false;
                if (fy.A01 == this) {
                    z = true;
                }
            }
            if (!z) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // X.ST
    public final void A3R() {
        if (this.A02 != null) {
            this.A02.A07(this);
            A02();
            AbstractC0081Fy fy = this.A02;
            fy.A07(this);
            fy.A05();
            return;
        }
        throw new IllegalStateException("mBatchLock is null, and it should be locked");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{lockKey=");
        sb.append(this.A01);
        sb.append(";hasLock=");
        sb.append(A2z());
        sb.append("}");
        return sb.toString();
    }

    @Override // X.Z9
    public final void unlock() {
        if (this.A02 != null) {
            this.A02.A07(this);
            this.A02.A08(this);
            A03();
            this.A02.A03();
            this.A02 = null;
            return;
        }
        throw new IllegalStateException("mBatchLock is null, and it should be locked");
    }

    public I9(Fw fw, Object obj) {
        this.A00 = fw;
        this.A01 = obj;
    }

    public final void A01() {
        this.A02 = A00();
        AbstractC0081Fy fy = this.A02;
        synchronized (fy) {
            if (fy.A09()) {
                Mu.A06("BatchLockState", "Attempting to lock a deleted entry: %s (owned by %s)", fy.A02, fy.A01);
            }
            AbstractC0081Fy.A00(fy, this);
            while (fy.A01 != null) {
                try {
                    fy.wait();
                } catch (InterruptedException unused) {
                }
            }
            fy.A04();
            fy.A01 = this;
        }
    }

    public final void A05(Writer writer, boolean z) throws IOException {
        if (!A2z()) {
            A01();
        }
        A04(writer);
        G2 g2 = new G2(writer);
        g2.A01 = true;
        g2.A02 = true;
        Fw fw = this.A00;
        G2.A01(g2);
        g2.A00 = true;
        Writer writer2 = g2.A03;
        writer2.write(93);
        if (!z) {
            writer2.write(44);
            YE A022 = fw.A01.A02();
            try {
                fw.A00.A00(A022);
                YD.A00().A06(writer2, A022);
            } finally {
                A022.A02();
            }
        }
        writer2.write(125);
        writer.flush();
    }
}
