package X;

import android.os.PowerManager;

/* renamed from: X.x6  reason: case insensitive filesystem */
public final /* synthetic */ class C1312x6 implements AnonymousClass8U {
    public static final /* synthetic */ C1312x6 A00 = new C1312x6();

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r3, AnonymousClass8H r4) {
        C00799i.A00.logFulfillment("Restarting");
        YP.A00().A03();
        ((PowerManager) BX.A00().getSystemService("power")).reboot(null);
    }
}
