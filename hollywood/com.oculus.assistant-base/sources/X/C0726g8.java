package X;

import com.facebook.assistant.oacr.OacrApi;
import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.messenger.assistant.thrift.AssistantClientMessage;
import com.facebook.messenger.assistant.thrift.AssistantClientMessageHeader;
import com.facebook.messenger.assistant.thrift.AssistantClientRequest;

/* renamed from: X.g8  reason: case insensitive filesystem */
public final class C0726g8 extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$36";
    public final /* synthetic */ C0740gP A00;
    public final /* synthetic */ AssistantClientRequest A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ boolean A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0726g8(C0740gP gPVar, AssistantClientRequest assistantClientRequest, String str, boolean z) {
        super("ACP: sendClientRequest");
        this.A00 = gPVar;
        this.A01 = assistantClientRequest;
        this.A02 = str;
        this.A03 = z;
    }

    public final void run() {
        C0740gP gPVar = this.A00;
        if (gPVar.A05 == null) {
            C0139Dd.A0D("AssistantClientPlatform", "mOacrApi is not ready, ignore sendClientRequest");
        }
        Integer valueOf = Integer.valueOf(gPVar.A15.getAndIncrement());
        C0139Dd.A0I("AssistantClientPlatform", "Create new ClientRequest id: %d, modality: %d", valueOf, 1);
        C0139Dd.A0F("AssistantClientPlatform", "wrapsAssistantClientRequest - start with clientMessageId: %d", valueOf);
        C0867lP lPVar = new C0867lP();
        lPVar.A02(0, valueOf);
        lPVar.A02(1, AnonymousClass8Q.A00(gPVar.A0H, gPVar.A00));
        lPVar.A02(2, gPVar.A0t.A01().A04());
        Object[] A032 = lPVar.A03();
        AssistantClientMessageHeader assistantClientMessageHeader = new AssistantClientMessageHeader();
        assistantClientMessageHeader.A02("com.facebook.messenger.assistant.thrift.AssistantClientMessageHeader", A032);
        C0866lN lNVar = new C0866lN();
        lNVar.A02(0, assistantClientMessageHeader);
        lNVar.A02(1, this.A01);
        Object[] A033 = lNVar.A03();
        AssistantClientMessage assistantClientMessage = new AssistantClientMessage();
        assistantClientMessage.A02("com.facebook.messenger.assistant.thrift.AssistantClientMessage", A033);
        OacrApi oacrApi = gPVar.A05;
        if (oacrApi != null) {
            oacrApi.onEvent(Integer.toString(101), OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AssistantClientMessage", assistantClientMessage), this.A02, this.A03, false);
            return;
        }
        throw null;
    }
}
