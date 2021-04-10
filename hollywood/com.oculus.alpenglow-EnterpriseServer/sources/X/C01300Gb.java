package X;

import android.database.DatabaseErrorHandler;

/* renamed from: X.0Gb  reason: invalid class name and case insensitive filesystem */
public class C01300Gb implements DatabaseErrorHandler {
    public final /* synthetic */ AnonymousClass0GR A00;
    public final /* synthetic */ C03350c9[] A01;

    public C01300Gb(AnonymousClass0GR r1, C03350c9[] r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (r0 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
        if (r1.hasNext() != false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        X.AnonymousClass0GR.A00((java.lang.String) r1.next().second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        X.AnonymousClass0GR.A00(r3.getPath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006f, code lost:
        throw r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0030 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d A[ExcHandler: all (r2v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r0 
      PHI: (r0v7 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) = (r0v6 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v14 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v14 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) binds: [B:8:0x002c, B:10:0x0030, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:8:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCorruption(android.database.sqlite.SQLiteDatabase r5) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01300Gb.onCorruption(android.database.sqlite.SQLiteDatabase):void");
    }
}
