package X;

/* renamed from: X.1zG  reason: invalid class name and case insensitive filesystem */
public final class C13271zG<T1, T2, R> implements AbstractC13031yl<Object[], R> {
    public final AbstractC13321zO<? super T1, ? super T2, ? extends R> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC13031yl
    public final Object apply(Object[] objArr) throws Exception {
        Object[] objArr2 = objArr;
        int length = objArr2.length;
        if (length == 2) {
            return this.A00.apply(objArr2[0], objArr2[1]);
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("Array of size 2 expected but got ", length));
    }

    public C13271zG(AbstractC13321zO<? super T1, ? super T2, ? extends R> r1) {
        this.A00 = r1;
    }
}
