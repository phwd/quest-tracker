package X;

/* renamed from: X.20q  reason: invalid class name and case insensitive filesystem */
public final class C138420q<T1, T2, T3, T4, T5, R> implements AbstractC13031yl<Object[], R> {
    public final AnonymousClass21X<T1, T2, T3, T4, T5, R> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: X.21X<T1, T2, T3, T4, T5, R> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC13031yl
    public final Object apply(Object[] objArr) throws Exception {
        Object[] objArr2 = objArr;
        int length = objArr2.length;
        if (length == 5) {
            return this.A00.apply(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("Array of size 5 expected but got ", length));
    }

    public C138420q(AnonymousClass21X<T1, T2, T3, T4, T5, R> r1) {
        this.A00 = r1;
    }
}
