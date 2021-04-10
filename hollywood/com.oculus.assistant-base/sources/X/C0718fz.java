package X;

/* renamed from: X.fz  reason: case insensitive filesystem */
public final class C0718fz extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$27$6";
    public final /* synthetic */ C0719g0 A00;
    public final /* synthetic */ boolean A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0718fz(C0719g0 g0Var, boolean z) {
        super("OacrVoiceInteraction: onKeywordVerificationComplete");
        this.A00 = g0Var;
        this.A01 = z;
    }

    public final void run() {
        Integer num;
        C0740gP gPVar = this.A00.A00;
        if (gPVar.A13.get()) {
            if (this.A01) {
                num = AnonymousClass09.A00;
            } else {
                num = AnonymousClass09.A01;
            }
            C0740gP.A05(gPVar, new C0814iH(num));
        }
    }
}
