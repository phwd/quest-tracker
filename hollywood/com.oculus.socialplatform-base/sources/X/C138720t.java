package X;

/* renamed from: X.20t  reason: invalid class name and case insensitive filesystem */
public final class C138720t<T1, T2, T3, R> implements AbstractC13031yl<Object[], R> {
    public final AbstractC139621c<T1, T2, T3, R> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.21c<T1, T2, T3, R> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC13031yl
    public final Object apply(Object[] objArr) throws Exception {
        Object[] objArr2 = objArr;
        int length = objArr2.length;
        if (length == 3) {
            return this.A00.apply(objArr2[0], objArr2[1], objArr2[2]);
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("Array of size 3 expected but got ", length));
    }

    public C138720t(AbstractC139621c<T1, T2, T3, R> r1) {
        this.A00 = r1;
    }
}
