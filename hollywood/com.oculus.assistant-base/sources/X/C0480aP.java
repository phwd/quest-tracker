package X;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.aP  reason: case insensitive filesystem */
public final class C0480aP implements Collection<C0479aO> {
    public final short[] A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.util.Collection
    public final /* synthetic */ boolean add(C0479aO aOVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.util.Collection] */
    @Override // java.util.Collection
    public final boolean addAll(Collection<? extends C0479aO> collection) {
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

    public /* synthetic */ C0480aP(short[] sArr) {
        C0514bB.A02(sArr, "storage");
        this.A00 = sArr;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof C0479aO) {
            short s = ((C0479aO) obj).A00;
            short[] sArr = this.A00;
            C0514bB.A02(sArr, "$this$contains");
            C0514bB.A02(sArr, "$this$indexOf");
            int length = sArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (s != sArr[i]) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        if (r1 < 0) goto L_0x003c;
     */
    @Override // java.util.Collection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean containsAll(java.util.Collection r8) {
        /*
            r7 = this;
            short[] r6 = r7.A00
            java.lang.String r0 = "elements"
            X.C0514bB.A02(r8, r0)
            boolean r0 = r8.isEmpty()
            r5 = 1
            if (r0 != 0) goto L_0x003d
            java.util.Iterator r4 = r8.iterator()
        L_0x0012:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r1 = r4.next()
            boolean r0 = r1 instanceof X.C0479aO
            if (r0 == 0) goto L_0x003c
            X.aO r1 = (X.C0479aO) r1
            short r3 = r1.A00
            java.lang.String r0 = "$this$contains"
            X.C0514bB.A02(r6, r0)
            java.lang.String r0 = "$this$indexOf"
            X.C0514bB.A02(r6, r0)
            int r2 = r6.length
            r1 = 0
        L_0x0030:
            if (r1 >= r2) goto L_0x003c
            short r0 = r6[r1]
            if (r3 != r0) goto L_0x0039
            if (r1 < 0) goto L_0x003c
            goto L_0x0012
        L_0x0039:
            int r1 = r1 + 1
            goto L_0x0030
        L_0x003c:
            r5 = 0
        L_0x003d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0480aP.containsAll(java.util.Collection):boolean");
    }

    public final boolean equals(Object obj) {
        short[] sArr = this.A00;
        if (!(obj instanceof C0480aP) || !C0514bB.A05(sArr, ((C0480aP) obj).A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        short[] sArr = this.A00;
        if (sArr != null) {
            return Arrays.hashCode(sArr);
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
    public final Iterator<C0479aO> iterator() {
        return new Dc(this.A00);
    }

    public final int size() {
        return this.A00.length;
    }

    public final String toString() {
        return AnonymousClass08.A05("UShortArray(storage=", Arrays.toString(this.A00), ")");
    }
}
