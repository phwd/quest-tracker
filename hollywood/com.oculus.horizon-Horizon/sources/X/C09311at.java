package X;

import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import java.util.Map;

/* renamed from: X.1at  reason: invalid class name and case insensitive filesystem */
public final class C09311at implements AnonymousClass0RX {
    public AbstractC06600ny A00;
    public volatile AnonymousClass0RX A01 = new AnonymousClass0o1();

    public final synchronized AnonymousClass0RX A00() {
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
        if ((r16 instanceof X.AnonymousClass1ar) == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        r6 = (X.AnonymousClass1b2) ((X.AnonymousClass1ar) r16).A05(0);
        r5 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r5.hasNext() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r3 = (android.util.Pair) r5.next();
        r6.A08(((java.lang.Number) r3.first).longValue(), (X.AnonymousClass0Re) r3.second);
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
        r0 = (X.AnonymousClass1bS) r3.next();
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
        r0 = (X.AnonymousClass1bK) r1.next();
        r15.logShadowResult(r0.A02, r0.A00, r0.A01, r0.A04, r0.A05, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ab, code lost:
        r15.isValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ae, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r4 = (X.C09321au) r4;
        r2 = r4.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        monitor-enter(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(X.AnonymousClass0RX r15, X.AbstractC06600ny r16) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09311at.A01(X.0RX, X.0ny):void");
    }

    @Override // X.AnonymousClass0RX
    public final void deleteOldUserData(int i) {
        this.A01.deleteOldUserData(i);
    }

    @Override // X.AnonymousClass0RX
    public final AnonymousClass0RZ getLatestHandle() {
        return this.A01.getLatestHandle();
    }

    @Override // X.AnonymousClass0RX
    public final AnonymousClass0Ri getNewOverridesTableIfExists() {
        return this.A01.getNewOverridesTableIfExists();
    }

    @Override // X.AnonymousClass0RX
    public final boolean isConsistencyLoggingNeeded(AnonymousClass0RO r2) {
        return this.A01.isConsistencyLoggingNeeded(r2);
    }

    @Override // X.AnonymousClass0RX
    public final boolean isFetchNeeded() {
        return this.A01.isFetchNeeded();
    }

    @Override // X.AnonymousClass0RX
    public final boolean isValid() {
        return this.A01.isValid();
    }

    @Override // X.AnonymousClass0RX
    public final void logConfigs(String str, AnonymousClass0RO r3, Map<String, String> map) {
        this.A01.logConfigs(str, r3, map);
    }

    @Override // X.AnonymousClass0RX
    public final void logExposure(String str, String str2, String str3) {
        this.A01.logExposure(str, str2, str3);
    }

    @Override // X.AnonymousClass0RX
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.A01.logShadowResult(str, str2, str3, str4, str5, str6);
    }

    @Override // X.AnonymousClass0RX
    public final boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return this.A01.registerConfigChangeListener(mobileConfigCxxChangeListener);
    }

    @Override // X.AnonymousClass0RX
    public final boolean saveCurrentParamsMapToDisk() {
        return this.A01.saveCurrentParamsMapToDisk();
    }

    @Override // X.AnonymousClass0RX
    public final String syncFetchReason() {
        return this.A01.syncFetchReason();
    }

    @Override // X.AnonymousClass0RX
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return this.A01.tryUpdateConfigsSynchronously(i);
    }

    @Override // X.AnonymousClass0RX
    public final boolean updateConfigs() {
        return this.A01.updateConfigs();
    }

    @Override // X.AnonymousClass0RX
    public final boolean updateConfigsSynchronouslyWithDefaultUpdater(int i) {
        return this.A01.updateConfigsSynchronouslyWithDefaultUpdater(i);
    }
}
