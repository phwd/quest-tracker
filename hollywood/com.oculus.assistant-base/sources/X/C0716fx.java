package X;

import com.facebook.assistant.oacr.InteractionUtils;
import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.messenger.assistant.thrift.AssistantServerMessage;
import java.nio.ByteBuffer;

/* renamed from: X.fx  reason: case insensitive filesystem */
public final class C0716fx extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$27$4";
    public final /* synthetic */ C0719g0 A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ ByteBuffer A03;
    public final /* synthetic */ boolean A04;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0716fx(C0719g0 g0Var, String str, boolean z, ByteBuffer byteBuffer, String str2) {
        super("OacrVoiceInteractionListener: onAction");
        this.A00 = g0Var;
        this.A01 = str;
        this.A04 = z;
        this.A03 = byteBuffer;
        this.A02 = str2;
    }

    public final void run() {
        C0740gP gPVar = this.A00.A00;
        if (!gPVar.A13.get()) {
            C0139Dd.A0F("AssistantClientPlatform", "ACP is not in initialized state, ignoring [%s]", this.A01);
            return;
        }
        boolean z = this.A04;
        if (z) {
            l0 l0Var = new l0(C0740gP.A16, gPVar.A0H.A02);
            gPVar.A0B = l0Var;
            l0Var.A04(gPVar.A01);
        }
        ByteBuffer byteBuffer = this.A03;
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.position(0);
        byteBuffer.get(bArr);
        try {
            AssistantServerMessage assistantServerMessage = (AssistantServerMessage) OacrUtil.deserialize("com.facebook.messenger.assistant.thrift.AssistantServerMessage", bArr, gPVar.A0A);
            C0139Dd.A0F("AssistantClientPlatform", "serverMessage actions: %s", assistantServerMessage.toString());
            C0740gP.A06(gPVar, new iK(InteractionUtils.constructAssistantResponse(assistantServerMessage, this.A01, gPVar.A0t.A01().A04().toString(), z, gPVar.A0B), this.A02));
        } catch (L9 e) {
            String str = this.A01;
            C0139Dd.A0P("AssistantClientPlatform", "onAction, interactionId %s", e, str);
            C0740gP.A04(gPVar, new C0813iG(AnonymousClass09.A0K, String.format("Failed to deserialize AssistantServerMessage for interaction %s: %s", str, e.toString())));
        }
    }
}
