package X;

import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.hyperthrift.HyperThriftBase;
import java.nio.ByteBuffer;
import java.util.List;

public final class fw extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$27$3";
    public final /* synthetic */ C0719g0 A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ ByteBuffer A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fw(C0719g0 g0Var, String str, ByteBuffer byteBuffer) {
        super("OacrVoiceInteractionListener: onFinalTranscription");
        this.A00 = g0Var;
        this.A01 = str;
        this.A02 = byteBuffer;
    }

    public final void run() {
        C0740gP gPVar = this.A00.A00;
        if (!gPVar.A13.get()) {
            C0139Dd.A0F("AssistantClientPlatform", "ACP is not in initialized state, ignoring [%s]", this.A01);
            return;
        }
        ByteBuffer byteBuffer = this.A02;
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.position(0);
        byteBuffer.get(bArr);
        try {
            HyperThriftBase deserialize = OacrUtil.deserialize("com.facebook.messenger.assistant.thrift.FinalTranscription", bArr, gPVar.A0A);
            C0139Dd.A0K("AssistantClientPlatform", "FinalTranscription text: %s, isVoiceCommand: %s, shortwaveId: %s, interactionId: %s", deserialize.A00(0), deserialize.A00(5), deserialize.A00(2), deserialize.A00(6));
            C0740gP.A07(gPVar, new C0817iM((String) deserialize.A00(0), ((Boolean) deserialize.A00(5)).booleanValue(), true, ((Number) deserialize.A00(1)).intValue(), (Integer) deserialize.A00(3), (String) deserialize.A00(2), (String) deserialize.A00(6), (List) deserialize.A00(7)));
        } catch (L9 e) {
            C0139Dd.A0O("AssistantClientPlatform", "onFinalTranscription, Failed to deserialize FinalTranscription for interactionId %s: %s", this.A01, e);
        }
    }
}
