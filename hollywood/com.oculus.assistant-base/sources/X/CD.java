package X;

public final /* synthetic */ class CD extends C0218Kk implements AbstractC0496aj {
    public CD(KB kb) {
        super(0, kb, KB.class, "doExecuteUpdateDelete", "doExecuteUpdateDelete()I");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        X.C0495ai.A00(r2, r1);
     */
    @Override // X.AbstractC0496aj
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object A3M() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.receiver
            X.KB r3 = (X.KB) r3
            android.database.sqlite.SQLiteStatement r2 = X.KB.A00(r3)
            r1 = 0
            int r0 = r2.executeUpdateDelete()     // Catch:{ all -> 0x0018 }
            X.KB.A01(r3)     // Catch:{ all -> 0x0018 }
            X.C0495ai.A00(r2, r1)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L_0x0018:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001a }
        L_0x001a:
            r0 = move-exception
            X.C0495ai.A00(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.CD.A3M():java.lang.Object");
    }
}
