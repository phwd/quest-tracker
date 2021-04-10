package X;

/* renamed from: X.oE  reason: case insensitive filesystem */
public final class C0892oE implements AbstractC0162Fh {
    public C0515bC A00;
    public volatile AbstractC0162Fh A01 = new C0889o9();

    public final synchronized AbstractC0162Fh A00() {
        return this.A01;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        if (r1 == null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r16 == null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r6 = (X.AbstractC0890oC) r16.A05(0);
        r5 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        if (r5.hasNext() == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        r3 = (android.util.Pair) r5.next();
        r6.A06(((java.lang.Number) r3.first).longValue(), (X.EnumC0165Fq) r3.second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r1 = r4.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005e, code lost:
        r3 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0066, code lost:
        if (r3.hasNext() == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        r0 = (X.G4) r3.next();
        r15.logExposure(r0.A01, r0.A00, r0.A02);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
        r1 = r4.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0 = java.util.Collections.unmodifiableList(new java.util.ArrayList(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0084, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0085, code lost:
        if (r0 == null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0087, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
        if (r1.hasNext() == false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0091, code lost:
        r0 = (X.G8) r1.next();
        r15.logShadowResult(r0.A02, r0.A00, r0.A01, r0.A04, r0.A05, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a7, code lost:
        X.C0139Dd.A08("Updated managerHolder (java -> cpp): %s", java.lang.Boolean.valueOf(r15.isValid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r4 = (X.C0891oD) r4;
        r2 = r4.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        monitor-enter(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(X.AbstractC0162Fh r15, X.C0515bC r16) {
        /*
        // Method dump skipped, instructions count: 208
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0892oE.A01(X.Fh, X.bC):void");
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0163Fj getLatestHandle() {
        return this.A01.getLatestHandle();
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTable() {
        return this.A01.getNewOverridesTable();
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTableIfExists() {
        return this.A01.getNewOverridesTableIfExists();
    }

    @Override // X.AbstractC0162Fh
    public final boolean isFetchNeeded() {
        return this.A01.isFetchNeeded();
    }

    @Override // X.AbstractC0162Fh
    public final boolean isValid() {
        return this.A01.isValid();
    }

    @Override // X.AbstractC0162Fh
    public final void logExposure(String str, String str2, String str3) {
        this.A01.logExposure(str, str2, str3);
    }

    @Override // X.AbstractC0162Fh
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.A01.logShadowResult(str, str2, str3, str4, str5, str6);
    }

    @Override // X.AbstractC0162Fh
    public final String syncFetchReason() {
        return this.A01.syncFetchReason();
    }

    @Override // X.AbstractC0162Fh
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return this.A01.tryUpdateConfigsSynchronously(i);
    }
}
