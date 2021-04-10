package X;

import com.facebook.assistant.oacr.OacrApi;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.gA  reason: case insensitive filesystem */
public final class C0727gA extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$38";
    public final /* synthetic */ C0740gP A00;
    public final /* synthetic */ C0815iI A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0727gA(C0740gP gPVar, C0815iI iIVar) {
        super("ACP: onEventMessage");
        this.A00 = gPVar;
        this.A01 = iIVar;
    }

    public final void run() {
        OacrApi oacrApi = this.A00.A05;
        if (oacrApi != null) {
            C0815iI iIVar = this.A01;
            oacrApi.onEvent(iIVar.A00, iIVar.A01, OacrConstants.AUTO_SPEECH_DOMAIN, false, false);
        }
    }
}
