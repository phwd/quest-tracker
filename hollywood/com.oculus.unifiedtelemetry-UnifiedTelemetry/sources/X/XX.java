package X;

public final class XX implements RU {
    public AbstractC0234Xa A00;
    public volatile RU A01 = new C0235Xd();

    public final synchronized RU A00() {
        return this.A01;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r1 == null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        if ((r16 instanceof X.GA) == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        r6 = (X.XZ) ((X.GA) r16).A05(0);
        r5 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r5.hasNext() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r3 = (android.util.Pair) r5.next();
        r6.A05(((java.lang.Number) r3.first).longValue(), (X.Ra) r3.second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        r1 = r4.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0 = java.util.Collections.unmodifiableSet(new java.util.HashSet(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0062, code lost:
        r3 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006a, code lost:
        if (r3.hasNext() == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        r0 = (X.C0139Ru) r3.next();
        r15.logExposure(r0.A01, r0.A00, r0.A02);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        r1 = r4.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r0 = java.util.Collections.unmodifiableList(new java.util.ArrayList(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0089, code lost:
        if (r0 == null) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        if (r1.hasNext() == false) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        r0 = (X.S1) r1.next();
        r15.logShadowResult(r0.A02, r0.A00, r0.A01, r0.A04, r0.A05, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ab, code lost:
        r15.isValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ae, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r4 = (X.XY) r4;
        r2 = r4.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        monitor-enter(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(X.RU r15, X.AbstractC0234Xa r16) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.XX.A01(X.RU, X.Xa):void");
    }

    @Override // X.RU
    public final void deleteOldUserData(int i) {
        this.A01.deleteOldUserData(i);
    }

    @Override // X.RU
    public final RW getLatestHandle() {
        return this.A01.getLatestHandle();
    }

    @Override // X.RU
    public final Re getNewOverridesTableIfExists() {
        return this.A01.getNewOverridesTableIfExists();
    }

    @Override // X.RU
    public final boolean isFetchNeeded() {
        return this.A01.isFetchNeeded();
    }

    @Override // X.RU
    public final boolean isValid() {
        return this.A01.isValid();
    }

    @Override // X.RU
    public final void logExposure(String str, String str2, String str3) {
        this.A01.logExposure(str, str2, str3);
    }

    @Override // X.RU
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.A01.logShadowResult(str, str2, str3, str4, str5, str6);
    }

    @Override // X.RU
    public final String syncFetchReason() {
        return this.A01.syncFetchReason();
    }

    @Override // X.RU
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return this.A01.tryUpdateConfigsSynchronously(i);
    }

    @Override // X.RU
    public final boolean updateConfigs() {
        return this.A01.updateConfigs();
    }
}
