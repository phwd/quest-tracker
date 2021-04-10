package X;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ZM {
    public final ZO A00 = new ZO();
    public final AtomicBoolean A01 = new AtomicBoolean(false);
    public final Thread A02;
    public final /* synthetic */ ZP A03;

    public ZM(ZP zp) {
        this.A03 = zp;
        ZN zn = new ZN(zp, this);
        this.A02 = zn;
        zn.setName("DeviceConfig-AsyncFetch");
        this.A02.start();
    }

    public static Integer A00(ZM zm, String str, Map map, Object obj) {
        ZP zp = zm.A03;
        if (zp.A02 != null) {
            ZR A002 = ZP.A00(zp, map, str);
            if (A002 == null || !A002.A06) {
                return AnonymousClass09.A01;
            }
            A002.A06 = true;
            Object obj2 = A002.A02;
            if (obj2 == null) {
                ZT.A07(zp.A0A, "Current value is null", str);
            } else {
                obj2.equals(obj);
            }
        }
        return AnonymousClass09.A0C;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:11|(1:13)(1:17)|14|(3:16|18|19)|20|21|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        X.C0139Dd.A0L("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A01(X.ZM r10, java.util.Set r11, boolean r12) {
        /*
            boolean r0 = r11.isEmpty()
            if (r0 == 0) goto L_0x0008
            r0 = 0
            return r0
        L_0x0008:
            java.util.Iterator r3 = r11.iterator()
        L_0x000c:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x005e
            java.lang.Object r7 = r3.next()
            java.lang.String r7 = (java.lang.String) r7
            X.ZP r0 = r10.A03
            X.GO r0 = r0.A0C
            java.lang.String r2 = "MobileConfigBaseClient"
            android.content.Context r0 = r0.A00
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r5 = X.GS.A08
            android.content.ContentProviderClient r4 = r0.acquireUnstableContentProviderClient(r5)
            if (r4 != 0) goto L_0x0032
            java.lang.String r0 = "Content provider for the mobileconfig service not found"
            X.C0139Dd.A0A(r2, r0)     // Catch:{ all -> 0x0050 }
            goto L_0x000c
        L_0x0032:
            r0 = 1
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch:{ all -> 0x0050 }
            if (r12 == 0) goto L_0x0046
            java.lang.String r1 = "1"
        L_0x0039:
            r0 = 0
            r6[r0] = r1     // Catch:{ all -> 0x0050 }
            java.lang.String[] r8 = new java.lang.String[r0]     // Catch:{ all -> 0x0050 }
            r9 = 0
            android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x004c
            goto L_0x0049
        L_0x0046:
            java.lang.String r1 = "0"
            goto L_0x0039
        L_0x0049:
            r0.close()     // Catch:{ Exception -> 0x004c }
        L_0x004c:
            r4.close()     // Catch:{ RemoteException | SecurityException -> 0x0057 }
            goto L_0x000c
        L_0x0050:
            r0 = move-exception
            if (r4 == 0) goto L_0x0056
            r4.close()     // Catch:{ all -> 0x0056 }
        L_0x0056:
            throw r0
        L_0x0057:
            r1 = move-exception
            java.lang.String r0 = "Could not find mobileconfigservice; is the service running?"
            X.C0139Dd.A0L(r2, r0, r1)
            goto L_0x000c
        L_0x005e:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ZM.A01(X.ZM, java.util.Set, boolean):boolean");
    }
}
