package X;

/* renamed from: X.20x  reason: invalid class name and case insensitive filesystem */
public final class C139120x<T> implements AbstractC12851yS<Throwable> {
    public final AbstractC12851yS<? super AnonymousClass215<T>> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC12851yS
    public final void accept(Throwable th) throws Exception {
        Throwable th2 = th;
        AbstractC12851yS<? super AnonymousClass215<T>> r2 = this.A00;
        AnonymousClass219.A01(th2, "error is null");
        r2.accept(new AnonymousClass215(EnumC139220y.error(th2)));
    }

    public C139120x(AbstractC12851yS<? super AnonymousClass215<T>> r1) {
        this.A00 = r1;
    }
}
