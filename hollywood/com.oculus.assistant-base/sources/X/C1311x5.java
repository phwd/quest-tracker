package X;

/* renamed from: X.x5  reason: case insensitive filesystem */
public final /* synthetic */ class C1311x5 implements AnonymousClass8U {
    public final /* synthetic */ C0434Xm A00;

    public /* synthetic */ C1311x5(C0434Xm xm) {
        this.A00 = xm;
    }

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r6, AnonymousClass8H r7) {
        C0434Xm xm = this.A00;
        C00799i.A00.logFulfillment("Resetting NUX");
        YP.A00().A03();
        Z4.A00.edit().putBoolean("transcription_privacy_acknowledge", false).putBoolean("interaction_privacy_acknowledge", false).putBoolean("initialized_storage_setting", false).apply();
        xm.A03.A05("Resetting NUX");
        BX.A00().stopService(YU.A00());
    }
}
