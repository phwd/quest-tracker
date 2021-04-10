package X;

import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.Mf  reason: case insensitive filesystem */
public abstract class AbstractC0104Mf<E> extends Rj<Multiset.Entry<E>> {
    public final void clear() {
        AbstractC0120Qz qz;
        if (!(this instanceof C00199i)) {
            qz = ((C00219k) this).A00;
        } else {
            qz = ((C00199i) this).A00;
        }
        qz.clear();
    }

    public final boolean contains(@NullableDecl Object obj) {
        AbstractC0120Qz qz;
        if (!(obj instanceof Mh)) {
            return false;
        }
        Mh mh = (Mh) obj;
        if (mh.A00() <= 0) {
            return false;
        }
        if (!(this instanceof C00199i)) {
            qz = ((C00219k) this).A00;
        } else {
            qz = ((C00199i) this).A00;
        }
        if (qz.A1D(mh.A01()) == mh.A00()) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [X.Qz] */
    /* JADX WARN: Type inference failed for: r0v5, types: [X.NO] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean remove(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof X.Mh
            r4 = 0
            if (r0 == 0) goto L_0x0024
            X.Mh r6 = (X.Mh) r6
            java.lang.Object r3 = r6.A01()
            int r2 = r6.A00()
            if (r2 == 0) goto L_0x0024
            r1 = r5
            boolean r0 = r5 instanceof X.C00199i
            if (r0 != 0) goto L_0x001f
            X.9k r1 = (X.C00219k) r1
            X.NO r0 = r1.A00
        L_0x001a:
            boolean r0 = r0.A3Y(r3, r2, r4)
            return r0
        L_0x001f:
            X.9i r1 = (X.C00199i) r1
            X.6q r0 = r1.A00
            goto L_0x001a
        L_0x0024:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC0104Mf.remove(java.lang.Object):boolean");
    }
}
