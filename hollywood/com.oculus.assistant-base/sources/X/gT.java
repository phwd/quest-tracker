package X;

public final class gT extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.OacrBusClient$OacrBusSubscriber$onEvent$1";
    public final /* synthetic */ AbstractC0744gU A00;
    public final /* synthetic */ C0104Ai A01;
    public final /* synthetic */ AbstractC0106Ak A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public gT(AbstractC0744gU gUVar, C0104Ai ai, AbstractC0106Ak ak) {
        super("OacrBusSubscriber");
        this.A00 = gUVar;
        this.A01 = ai;
        this.A02 = ak;
    }

    public final void run() {
        AbstractC0744gU gUVar = this.A00;
        C0104Ai ai = this.A01;
        AbstractC0106Ak ak = this.A02;
        if (gUVar instanceof C0652dx) {
            C0514bB.A02(ai, "busContext");
            C0514bB.A02(ak, "event");
            C0822iS iSVar = (C0822iS) ak.A2L();
            String str = iSVar.A01;
            C0139Dd.A0G("OacrBusClient", "onOacrTtsVoiceSelectedMessage: %s", str);
            C0740gP.A16.post(new g9(((C0652dx) gUVar).A00.A01, iSVar.A00, str));
        } else if (!(gUVar instanceof C0653dy)) {
            C0514bB.A02(ai, "busContext");
            C0514bB.A02(ak, "event");
            C0139Dd.A09("OacrBusClient", "onOacrOnEventMessage");
            C0740gP.A16.post(new C0727gA(((C0655e0) gUVar).A00.A01, (C0815iI) ak.A2L()));
        } else {
            C0514bB.A02(ai, "busContext");
            C0514bB.A02(ak, "event");
            C0139Dd.A09("OacrBusClient", "onOacrSendClientRequestMessage");
            C0740gP gPVar = ((C0653dy) gUVar).A00.A01;
            C0816iL iLVar = (C0816iL) ak.A2L();
            C0740gP.A16.post(new C0726g8(gPVar, iLVar.A00, iLVar.A01, iLVar.A02));
        }
    }
}
