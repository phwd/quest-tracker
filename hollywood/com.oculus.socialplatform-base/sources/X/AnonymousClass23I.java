package X;

/* renamed from: X.23I  reason: invalid class name */
public class AnonymousClass23I implements AnonymousClass1QM<Boolean> {
    public final /* synthetic */ AnonymousClass22G A00;
    public final /* synthetic */ AnonymousClass22H A01;

    public AnonymousClass23I(AnonymousClass22G r1, AnonymousClass22H r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r1.A0V.get() != false) goto L_0x000f;
     */
    @Override // X.AnonymousClass1QM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean get() {
        /*
            r2 = this;
            X.22H r1 = r2.A01
            boolean r0 = r1.A0X
            if (r0 != 0) goto L_0x000f
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.A0V
            boolean r1 = r0.get()
            r0 = 0
            if (r1 == 0) goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass23I.get():java.lang.Object");
    }
}
