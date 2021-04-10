package X;

/* renamed from: X.gG  reason: case insensitive filesystem */
public final class C0731gG extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$45$1$1";
    public final /* synthetic */ C0732gH A00;
    public final /* synthetic */ String A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0731gG(C0732gH gHVar, String str) {
        super("AudioReader-OnError");
        this.A00 = gHVar;
        this.A01 = str;
    }

    public final void run() {
        C0732gH gHVar = this.A00;
        C0740gP.A04(gHVar.A00.A00, new C0813iG(AnonymousClass09.A00, String.format("AudioReader error for interaction %s: ", gHVar.A01, this.A01)));
    }
}
