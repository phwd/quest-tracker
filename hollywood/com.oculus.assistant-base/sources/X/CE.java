package X;

public final /* synthetic */ class CE extends C0218Kk implements AbstractC0496aj {
    public CE(KB kb) {
        super(0, kb, KB.class, "doExecuteInsert", "doExecuteInsert()J");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        X.C0495ai.A00(r3, r1);
     */
    @Override // X.AbstractC0496aj
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object A3M() {
        /*
            r5 = this;
            java.lang.Object r4 = r5.receiver
            X.KB r4 = (X.KB) r4
            android.database.sqlite.SQLiteStatement r3 = X.KB.A00(r4)
            r2 = 0
            long r0 = r3.executeInsert()     // Catch:{ all -> 0x0018 }
            X.KB.A01(r4)     // Catch:{ all -> 0x0018 }
            X.C0495ai.A00(r3, r2)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            return r0
        L_0x0018:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001a }
        L_0x001a:
            r0 = move-exception
            X.C0495ai.A00(r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.CE.A3M():java.lang.Object");
    }
}
