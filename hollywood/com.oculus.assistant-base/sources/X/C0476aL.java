package X;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.aL  reason: case insensitive filesystem */
public final class C0476aL implements Collection<C0475aK> {
    public final long[] A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.util.Collection
    public final /* synthetic */ boolean add(C0475aK aKVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.util.Collection] */
    @Override // java.util.Collection
    public final boolean addAll(Collection<? extends C0475aK> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Object[] toArray() {
        return b8.A00(this);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return b8.A01(this, objArr);
    }

    public /* synthetic */ C0476aL(long[] jArr) {
        C0514bB.A02(jArr, "storage");
        this.A00 = jArr;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof C0475aK) {
            long j = ((C0475aK) obj).A00;
            long[] jArr = this.A00;
            C0514bB.A02(jArr, "$this$contains");
            C0514bB.A02(jArr, "$this$indexOf");
            int length = jArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (j != jArr[i]) {
                    i++;
                } else if (i < 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r3 < 0) goto L_0x003e;
     */
    @Override // java.util.Collection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean containsAll(java.util.Collection r11) {
        /*
            r10 = this;
            long[] r8 = r10.A00
            java.lang.String r0 = "elements"
            X.C0514bB.A02(r11, r0)
            boolean r0 = r11.isEmpty()
            r9 = 1
            if (r0 != 0) goto L_0x003f
            java.util.Iterator r7 = r11.iterator()
        L_0x0012:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x003f
            java.lang.Object r1 = r7.next()
            boolean r0 = r1 instanceof X.C0475aK
            if (r0 == 0) goto L_0x003e
            X.aK r1 = (X.C0475aK) r1
            long r5 = r1.A00
            java.lang.String r0 = "$this$contains"
            X.C0514bB.A02(r8, r0)
            java.lang.String r0 = "$this$indexOf"
            X.C0514bB.A02(r8, r0)
            int r4 = r8.length
            r3 = 0
        L_0x0030:
            if (r3 >= r4) goto L_0x003e
            r1 = r8[r3]
            int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x003b
            if (r3 < 0) goto L_0x003e
            goto L_0x0012
        L_0x003b:
            int r3 = r3 + 1
            goto L_0x0030
        L_0x003e:
            r9 = 0
        L_0x003f:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0476aL.containsAll(java.util.Collection):boolean");
    }

    public final boolean equals(Object obj) {
        long[] jArr = this.A00;
        if (!(obj instanceof C0476aL) || !C0514bB.A05(jArr, ((C0476aL) obj).A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        long[] jArr = this.A00;
        if (jArr != null) {
            return Arrays.hashCode(jArr);
        }
        return 0;
    }

    public final boolean isEmpty() {
        if (this.A00.length == 0) {
            return true;
        }
        return false;
    }

    /* Return type fixed from 'java.util.Iterator' to match base method */
    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator<C0475aK> iterator() {
        return new Fb(this.A00);
    }

    public final int size() {
        return this.A00.length;
    }

    public final String toString() {
        return AnonymousClass08.A05("ULongArray(storage=", Arrays.toString(this.A00), ")");
    }
}
