package X;

import android.os.Handler;
import android.os.Looper;

/* renamed from: X.0Ax  reason: invalid class name and case insensitive filesystem */
public class HandlerC00590Ax extends Handler {
    public final /* synthetic */ AnonymousClass0B0 A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC00590Ax(AnonymousClass0B0 r1, Looper looper) {
        super(looper);
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        r5 = r7[r6];
        r4 = r5.A01.size();
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        if (r3 >= r4) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r1 = r5.A01.get(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        if (r1.A01 != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        r1.A03.onReceive(r9.A00, r5.A00);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r6 >= r8) goto L_0x000b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r11) {
        /*
            r10 = this;
            int r1 = r11.what
            r0 = 1
            if (r1 == r0) goto L_0x0009
            super.handleMessage(r11)
            return
        L_0x0009:
            X.0B0 r9 = r10.A00
        L_0x000b:
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<X.0Az>> r1 = r9.A02
            monitor-enter(r1)
            java.util.ArrayList<X.0Ay> r0 = r9.A01     // Catch:{ all -> 0x004b }
            int r8 = r0.size()     // Catch:{ all -> 0x004b }
            if (r8 > 0) goto L_0x0018
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            goto L_0x004a
        L_0x0018:
            X.0Ay[] r7 = new X.AnonymousClass0Ay[r8]     // Catch:{ all -> 0x004b }
            r0.toArray(r7)     // Catch:{ all -> 0x004b }
            r0.clear()     // Catch:{ all -> 0x004b }
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            r6 = 0
        L_0x0022:
            r5 = r7[r6]
            java.util.ArrayList<X.0Az> r0 = r5.A01
            int r4 = r0.size()
            r3 = 0
        L_0x002b:
            if (r3 >= r4) goto L_0x0045
            java.util.ArrayList<X.0Az> r0 = r5.A01
            java.lang.Object r1 = r0.get(r3)
            X.0Az r1 = (X.AnonymousClass0Az) r1
            boolean r0 = r1.A01
            if (r0 != 0) goto L_0x0042
            android.content.BroadcastReceiver r2 = r1.A03
            android.content.Context r1 = r9.A00
            android.content.Intent r0 = r5.A00
            r2.onReceive(r1, r0)
        L_0x0042:
            int r3 = r3 + 1
            goto L_0x002b
        L_0x0045:
            int r6 = r6 + 1
            if (r6 >= r8) goto L_0x000b
            goto L_0x0022
        L_0x004a:
            return
        L_0x004b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.HandlerC00590Ax.handleMessage(android.os.Message):void");
    }
}
