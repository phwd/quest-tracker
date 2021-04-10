package X;

/* renamed from: X.20w  reason: invalid class name and case insensitive filesystem */
public final class C139020w<T> implements AbstractC12851yS<T> {
    public final AbstractC12851yS<? super AnonymousClass215<T>> A00;

    @Override // X.AbstractC12851yS
    public final void accept(T t) throws Exception {
        AbstractC12851yS<? super AnonymousClass215<T>> r1 = this.A00;
        AnonymousClass219.A01(t, "value is null");
        r1.accept(new AnonymousClass215(t));
    }

    public C139020w(AbstractC12851yS<? super AnonymousClass215<T>> r1) {
        this.A00 = r1;
    }
}
