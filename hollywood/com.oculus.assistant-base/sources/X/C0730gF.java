package X;

/* renamed from: X.gF  reason: case insensitive filesystem */
public final class C0730gF extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$44";
    public final /* synthetic */ C0740gP A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0730gF(C0740gP gPVar) {
        super("ACP: sendCallStateUpdate");
        this.A00 = gPVar;
    }

    public final void run() {
        if (this.A00.A05 == null) {
            C0139Dd.A09("AssistantClientPlatform", "sendCallStateUpdate: OacrApi not initialized, skip");
            return;
        }
        throw new NullPointerException("isPrivateCallInProgress");
    }
}
