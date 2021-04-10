package oculus.internal.license.refresh;

import android.app.AlarmManager;
import android.app.PendingIntent;
import oculus.internal.license.store.DbWrapper;
import oculus.internal.license.store.LicenseStoreObserver;

public class RefreshNotifier implements LicenseStoreObserver {
    private final AlarmManager am;
    private long currentAlarmTargetTime = Long.MAX_VALUE;
    private final DbWrapper db;
    private final int expiryMargin;
    private final PendingIntent intentToSend;

    public static RefreshNotifier getInstance(AlarmManager am2, DbWrapper db2, PendingIntent intentToSend2, int expiryMargin2) {
        RefreshNotifier rn = new RefreshNotifier(am2, db2, intentToSend2, expiryMargin2);
        rn.scheduleNext();
        return rn;
    }

    protected RefreshNotifier(AlarmManager am2, DbWrapper db2, PendingIntent intentToSend2, int expiryMargin2) {
        this.db = db2;
        this.am = am2;
        this.intentToSend = intentToSend2;
        this.expiryMargin = expiryMargin2;
    }

    @Override // oculus.internal.license.store.LicenseStoreObserver
    public void onChanged(boolean successful) {
        if (successful) {
            scheduleNext();
        }
    }

    /* access modifiers changed from: protected */
    public void scheduleNext() {
        long targetTime;
        long newMinimumExpiry = queryMinimumExpiry(this.db);
        if (newMinimumExpiry == Long.MAX_VALUE) {
            targetTime = Long.MAX_VALUE;
        } else {
            targetTime = newMinimumExpiry - ((long) this.expiryMargin);
        }
        long j = this.currentAlarmTargetTime;
        if (targetTime != j) {
            if (j != Long.MAX_VALUE) {
                this.am.cancel(this.intentToSend);
            }
            if (targetTime != Long.MAX_VALUE) {
                this.am.set(1, 1000 * targetTime, this.intentToSend);
                this.currentAlarmTargetTime = targetTime;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0054, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        if (r1 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static long queryMinimumExpiry(oculus.internal.license.store.DbWrapper r11) {
        /*
            java.lang.String r0 = "expires"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]
            r1 = 0
            r4[r1] = r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = " > 0"
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = " ASC"
            r1.append(r2)
            java.lang.String r9 = r1.toString()
            java.lang.String r3 = "licenses"
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r10 = "1"
            r2 = r11
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0049
            int r2 = r1.getColumnIndex(r0)     // Catch:{ all -> 0x0052 }
            long r2 = r1.getLong(r2)     // Catch:{ all -> 0x0052 }
            r1.close()
            return r2
        L_0x0049:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r1.close()
            return r2
        L_0x0052:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r3 = move-exception
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x005f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.license.refresh.RefreshNotifier.queryMinimumExpiry(oculus.internal.license.store.DbWrapper):long");
    }
}
