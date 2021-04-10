package X;

/* renamed from: X.74  reason: invalid class name */
public final class AnonymousClass74 extends C1300wu {
    public final /* synthetic */ YK A00;

    @Override // X.C1300wu
    public final void A06() {
        Z4.A00.edit().putBoolean("transcription_privacy_acknowledge", true).apply();
        C00799i.A00.logNuxEvent("accept_transcription_privacy");
        HandlerC0422Wz.A02();
        C0112Aq.A00().A01(new z6());
    }

    public AnonymousClass74(YK yk) {
        this.A00 = yk;
    }

    @Override // X.C1300wu
    public final void A07() {
        super.A07();
        HandlerC0422Wz.A02();
        C0112Aq.A00().A01(new z7());
        C00799i.A00.logNuxEvent("hide_dialog");
    }
}
