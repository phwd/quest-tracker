package X;

import android.preference.PreferenceManager;

/* renamed from: X.xJ  reason: case insensitive filesystem */
public final /* synthetic */ class C1325xJ implements AnonymousClass8U {
    public final /* synthetic */ C0434Xm A00;

    public /* synthetic */ C1325xJ(C0434Xm xm) {
        this.A00 = xm;
    }

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r6, AnonymousClass8H r7) {
        C0434Xm xm = this.A00;
        C00799i.A00.logFulfillment("Enabling QA mode");
        YP.A00().A03();
        PreferenceManager.getDefaultSharedPreferences(BX.A00()).edit().putString("overrideServerEndpoint", AnonymousClass8T.A00(AnonymousClass09.A01)).apply();
        xm.A03.A05("Enabling QA mode");
        BX.A00().stopService(YU.A00());
    }
}
