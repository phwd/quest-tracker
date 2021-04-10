package X;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.aF  reason: case insensitive filesystem */
public final class C0470aF implements Collection<C0469aE> {
    public final byte[] A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.util.Collection
    public final /* synthetic */ boolean add(C0469aE aEVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.util.Collection] */
    @Override // java.util.Collection
    public final boolean addAll(Collection<? extends C0469aE> collection) {
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

    public /* synthetic */ C0470aF(byte[] bArr) {
        C0514bB.A02(bArr, "storage");
        this.A00 = bArr;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof C0469aE) {
            byte b = ((C0469aE) obj).A00;
            byte[] bArr = this.A00;
            C0514bB.A02(bArr, "$this$contains");
            C0514bB.A02(bArr, "$this$indexOf");
            int length = bArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (b != bArr[i]) {
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
            byte[] r6 = r7.A00
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
            boolean r0 = r1 instanceof X.C0469aE
            if (r0 == 0) goto L_0x003c
            X.aE r1 = (X.C0469aE) r1
            byte r3 = r1.A00
            java.lang.String r0 = "$this$contains"
            X.C0514bB.A02(r6, r0)
            java.lang.String r0 = "$this$indexOf"
            X.C0514bB.A02(r6, r0)
            int r2 = r6.length
            r1 = 0
        L_0x0030:
            if (r1 >= r2) goto L_0x003c
            byte r0 = r6[r1]
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0470aF.containsAll(java.util.Collection):boolean");
    }

    public final boolean equals(Object obj) {
        byte[] bArr = this.A00;
        if (!(obj instanceof C0470aF) || !C0514bB.A05(bArr, ((C0470aF) obj).A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr != null) {
            return Arrays.hashCode(bArr);
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
    public final Iterator<C0469aE> iterator() {
        return new T0(this.A00);
    }

    public final int size() {
        return this.A00.length;
    }

    public final String toString() {
        return AnonymousClass08.A05("UByteArray(storage=", Arrays.toString(this.A00), ")");
    }
}
