package X;

/* renamed from: X.20r  reason: invalid class name and case insensitive filesystem */
public final class C138520r<T1, T2, T3, T4, R> implements AbstractC13031yl<Object[], R> {
    public final AnonymousClass21Z<T1, T2, T3, T4, R> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: X.21Z<T1, T2, T3, T4, R> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC13031yl
    public final Object apply(Object[] objArr) throws Exception {
        Object[] objArr2 = objArr;
        int length = objArr2.length;
        if (length == 4) {
            return this.A00.apply(objArr2[0], objArr2[1], objArr2[2], objArr2[3]);
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("Array of size 4 expected but got ", length));
    }

    public C138520r(AnonymousClass21Z<T1, T2, T3, T4, R> r1) {
        this.A00 = r1;
    }
}
