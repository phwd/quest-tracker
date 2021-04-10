package X;

/* renamed from: X.35  reason: invalid class name */
public final class AnonymousClass35 implements AbstractC0609cs {
    public final AbstractC0609cs A00;
    public final /* synthetic */ C1132tX A01;

    public AnonymousClass35(C1132tX tXVar, AbstractC0609cs csVar) {
        this.A01 = tXVar;
        if (csVar != null) {
            this.A00 = csVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r3, long j) {
        return this.A00.A4c(r3, j);
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A00.A5G();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        C1132tX tXVar = this.A01;
        tXVar.A01.A05(false, tXVar);
        this.A00.close();
    }

    public final String toString() {
        return AnonymousClass08.A06(getClass().getSimpleName(), "(", this.A00.toString(), ")");
    }
}
