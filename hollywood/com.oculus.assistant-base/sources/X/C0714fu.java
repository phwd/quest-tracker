package X;

/* renamed from: X.fu  reason: case insensitive filesystem */
public final class C0714fu extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$27$1";
    public final /* synthetic */ C0719g0 A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0714fu(C0719g0 g0Var, String str, String str2) {
        super("OacrVoiceInteractionListener: onError");
        this.A00 = g0Var;
        this.A02 = str;
        this.A01 = str2;
    }

    public final void run() {
        C0740gP gPVar = this.A00.A00;
        if (gPVar.A13.get()) {
            String str = this.A02;
            C0139Dd.A0F("AssistantClientPlatform", "Interaction with id %s encountered an error.", str);
            C0740gP.A04(gPVar, new C0813iG(AnonymousClass09.A0L, String.format("Interaction %s had error: %s", str, this.A01)));
        }
    }
}
