package X;

/* renamed from: X.1zb  reason: invalid class name and case insensitive filesystem */
public final class C13441zb implements AbstractC13031yl<T, R> {
    public final /* synthetic */ C13411zY A00;

    public C13441zb(C13411zY r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: ? super java.lang.Object[] */
    @Override // X.AbstractC13031yl
    public final R apply(T t) throws Exception {
        R r = (R) this.A00.A00.apply(new Object[]{t});
        AnonymousClass219.A01(r, "The zipper returned a null value");
        return r;
    }
}
