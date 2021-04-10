package X;

/* renamed from: X.12r  reason: invalid class name */
public final class AnonymousClass12r implements AnonymousClass0ST {
    public AbstractC02890az A00;
    public volatile AnonymousClass0ST A01 = new C02900b2();

    public final synchronized AnonymousClass0ST A00() {
        return this.A01;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r1 == null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if ((r17 instanceof X.AnonymousClass12o) == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r7 = (X.AnonymousClass139) ((X.AnonymousClass12o) r17).A03(0);
        r6 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r6.hasNext() == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r3 = (android.util.Pair) r6.next();
        r1 = ((java.lang.Long) r3.first).longValue();
        r5 = (X.AnonymousClass0Sn) r3.second;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        if ((r7 instanceof X.AnonymousClass12q) == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r3 = (X.AnonymousClass12q) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        if (r3.A03 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005d, code lost:
        r1 = X.AnonymousClass12q.A00(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0063, code lost:
        if ((r1 & 6) == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        X.AnonymousClass12q.A01(r3, r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
        r1 = r4.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r0 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0075, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0076, code lost:
        r3 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007e, code lost:
        if (r3.hasNext() == false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        r0 = (X.AnonymousClass15H) r3.next();
        r16.logExposure(r0.A01, r0.A00, r0.A02);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0090, code lost:
        r1 = r4.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r0 = java.util.Collections.unmodifiableList(new java.util.ArrayList(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009c, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009d, code lost:
        if (r0 == null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009f, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a7, code lost:
        if (r1.hasNext() == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
        r0 = (X.AnonymousClass13s) r1.next();
        r16.logShadowResult(r0.A02, r0.A00, r0.A01, r0.A04, r0.A05, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bf, code lost:
        r16.isValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c2, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r4 = (X.AnonymousClass12p) r4;
        r2 = r4.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        monitor-enter(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(X.AnonymousClass0ST r16, X.AbstractC02890az r17) {
        /*
        // Method dump skipped, instructions count: 212
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass12r.A01(X.0ST, X.0az):void");
    }

    @Override // X.AnonymousClass0ST
    public final void deleteOldUserData(int i) {
        this.A01.deleteOldUserData(i);
    }

    @Override // X.AnonymousClass0ST
    public final AnonymousClass0SV getLatestHandle() {
        return this.A01.getLatestHandle();
    }

    @Override // X.AnonymousClass0ST
    public final AnonymousClass0Sr getNewOverridesTableIfExists() {
        return this.A01.getNewOverridesTableIfExists();
    }

    @Override // X.AnonymousClass0ST
    public final boolean isFetchNeeded() {
        return this.A01.isFetchNeeded();
    }

    @Override // X.AnonymousClass0ST
    public final boolean isValid() {
        return this.A01.isValid();
    }

    @Override // X.AnonymousClass0ST
    public final void logExposure(String str, String str2, String str3) {
        this.A01.logExposure(str, str2, str3);
    }

    @Override // X.AnonymousClass0ST
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.A01.logShadowResult(str, str2, str3, str4, str5, str6);
    }

    @Override // X.AnonymousClass0ST
    public final String syncFetchReason() {
        return this.A01.syncFetchReason();
    }

    @Override // X.AnonymousClass0ST
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return this.A01.tryUpdateConfigsSynchronously(i);
    }

    @Override // X.AnonymousClass0ST
    public final boolean updateConfigs() {
        return this.A01.updateConfigs();
    }
}
