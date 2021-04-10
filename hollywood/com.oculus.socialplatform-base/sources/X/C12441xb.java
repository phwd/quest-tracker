package X;

import io.reactivex.annotations.Nullable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: X.1xb  reason: invalid class name and case insensitive filesystem */
public final class C12441xb<E> extends AtomicReferenceArray<E> implements AbstractC13591zq<E> {
    public static final Integer A00 = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    public static final long serialVersionUID = -1296597691183856449L;
    public final AtomicLong consumerIndex = new AtomicLong();
    public final int lookAheadStep;
    public final int mask = (length() - 1);
    public final AtomicLong producerIndex = new AtomicLong();
    public long producerLookAhead;

    public C12441xb(int i) {
        super(1 << (32 - Integer.numberOfLeadingZeros(i - 1)));
        this.lookAheadStep = Math.min(i >> 2, A00.intValue());
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (this.producerIndex.get() == this.consumerIndex.get()) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC13481zf
    public final boolean offer(E e) {
        if (e != null) {
            int i = this.mask;
            long j = this.producerIndex.get();
            int i2 = ((int) j) & i;
            if (j >= this.producerLookAhead) {
                long j2 = ((long) this.lookAheadStep) + j;
                if (get(((int) j2) & i) == null) {
                    this.producerLookAhead = j2;
                } else if (get(i2) != null) {
                    return false;
                }
            }
            lazySet(i2, e);
            this.producerIndex.lazySet(j + 1);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    @Override // X.AbstractC13481zf, X.AbstractC13591zq
    @Nullable
    public final E poll() {
        long j = this.consumerIndex.get();
        int i = this.mask & ((int) j);
        E e = get(i);
        if (e == null) {
            return null;
        }
        this.consumerIndex.lazySet(j + 1);
        lazySet(i, null);
        return e;
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
