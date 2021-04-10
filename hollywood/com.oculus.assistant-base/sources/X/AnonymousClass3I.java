package X;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

/* renamed from: X.3I  reason: invalid class name */
public final class AnonymousClass3I extends AbstractC1409yv {
    public final w1 A00;
    public final ZP A01;

    /* JADX WARNING: Removed duplicated region for block: B:146:0x030a  */
    @Override // X.AbstractC1409yv
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(X.AnonymousClass8F r25, org.json.JSONObject r26) {
        /*
        // Method dump skipped, instructions count: 818
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass3I.A02(X.8F, org.json.JSONObject):void");
    }

    public AnonymousClass3I(w1 w1Var) {
        String message;
        this.A00 = w1Var;
        ZP zp = new ZP(BX.A00());
        this.A01 = zp;
        ZK zk = new ZK();
        if (!zp.A0I.get()) {
            try {
                zp.A02 = zk;
                C1420zC zCVar = new C1420zC(zp, System.currentTimeMillis(), zk);
                GO go = zp.A0C;
                if (go != null) {
                    String A012 = go.A01();
                    if (A012 == null) {
                        message = "Can't fetch client params_map.txt";
                    } else {
                        String A002 = GO.A00(go);
                        Uri withAppendedPath = Uri.withAppendedPath(GS.A0C, A002);
                        Context context = go.A00;
                        GK gk = new GK(go, new Handler(context.getMainLooper()), withAppendedPath, zCVar);
                        try {
                            context.getContentResolver().registerContentObserver(withAppendedPath, false, gk);
                            context.getContentResolver().registerContentObserver(Uri.withAppendedPath(GS.A0A, A002), false, gk);
                            context.getContentResolver().registerContentObserver(Uri.withAppendedPath(GS.A0B, A002), false, gk);
                            context.getContentResolver().registerContentObserver(Uri.withAppendedPath(GS.A09, A002), false, gk);
                            C0139Dd.A0B("MobileConfigBaseClient", "Start thread to subscribe with paramsMapContent file descriptor from mobileconfig service");
                            new Thread(new GL(go, A012)).start();
                            return;
                        } catch (SecurityException e) {
                            message = e.getMessage();
                        }
                    }
                    zCVar.A00(message);
                }
            } catch (Exception e2) {
                ZT zt = new ZT(zp.A0A);
                ZT.A0B(zt, AnonymousClass09.A01);
                ZT.A0A(zt, e2);
                ZT.A09(zt);
                C0139Dd.A0L("DeviceConfigTelemetryLogger", "subscribe() threw an exception", e2);
            }
        }
    }
}
