package X;

import io.reactivex.annotations.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xa  reason: invalid class name and case insensitive filesystem */
public final class C12431xa<T> implements AbstractC13591zq<T> {
    public final AtomicReference<C12461xd<T>> A00 = new AtomicReference<>();
    public final AtomicReference<C12461xd<T>> A01 = new AtomicReference<>();

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (this.A00.get() == this.A01.get()) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC13481zf
    public final boolean offer(T t) {
        if (t != null) {
            C12461xd<T> r1 = new C12461xd<>(t);
            this.A01.getAndSet(r1).lazySet(r1);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, E] */
    @Override // X.AbstractC13481zf, X.AbstractC13591zq
    @Nullable
    public final T poll() {
        AtomicReference<C12461xd<T>> atomicReference = this.A00;
        C12461xd<T> r1 = atomicReference.get();
        C12461xd<T> r2 = (C12461xd) r1.get();
        if (r2 != null) {
            E e = r2.value;
            r2.value = null;
            atomicReference.lazySet(r2);
        } else if (r1 == this.A01.get()) {
            return null;
        } else {
            do {
                r2 = (C12461xd) r1.get();
            } while (r2 == null);
        }
        E e2 = r2.value;
        r2.value = null;
        atomicReference.lazySet(r2);
        return e2;
    }

    public C12431xa() {
        C12461xd<T> r1 = new C12461xd<>();
        this.A00.lazySet(r1);
        this.A01.getAndSet(r1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    @Override // X.AbstractC13481zf
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void clear() {
        /*
            r1 = this;
        L_0x0000:
            java.lang.Object r0 = r1.poll()
            if (r0 == 0) goto L_0x000d
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x000d
            goto L_0x0000
        L_0x000d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C12431xa.clear():void");
    }
}
