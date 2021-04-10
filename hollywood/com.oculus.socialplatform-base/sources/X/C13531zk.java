package X;

import androidx.recyclerview.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1zk  reason: invalid class name and case insensitive filesystem */
public final class C13531zk<T> extends AtomicLong implements AbstractC12551xm {
    public static final long serialVersionUID = 3562861878281475070L;
    public final AbstractC13581zp<? super T> downstream;
    public final C13511zi<T> parent;

    @Override // X.AbstractC12551xm
    public final void cancel() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.A01(this);
        }
    }

    public C13531zk(AbstractC13581zp<? super T> r1, C13511zi<T> r2) {
        this.downstream = r1;
        this.parent = r2;
    }

    @Override // X.AbstractC12551xm
    public final void request(long j) {
        long j2;
        long j3;
        if (EnumC12531xk.validate(j)) {
            do {
                j2 = get();
                if (j2 != Long.MIN_VALUE && j2 != RecyclerView.FOREVER_NS) {
                    j3 = j2 + j;
                    if (j3 < 0) {
                        j3 = RecyclerView.FOREVER_NS;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(j2, j3));
        }
    }
}
