package X;

import android.preference.PreferenceManager;

/* renamed from: X.xH  reason: case insensitive filesystem */
public final /* synthetic */ class C1323xH implements AnonymousClass8U {
    public final /* synthetic */ C0434Xm A00;

    public /* synthetic */ C1323xH(C0434Xm xm) {
        this.A00 = xm;
    }

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r6, AnonymousClass8H r7) {
        C0434Xm xm = this.A00;
        C00799i.A00.logFulfillment("Disabling QA mode");
        YP.A00().A03();
        PreferenceManager.getDefaultSharedPreferences(BX.A00()).edit().putString("overrideServerEndpoint", AnonymousClass8T.A00(AnonymousClass09.A00)).apply();
        xm.A03.A05("Disabling QA mode");
        BX.A00().stopService(YU.A00());
    }
}
