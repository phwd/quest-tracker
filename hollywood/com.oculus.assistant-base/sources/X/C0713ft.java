package X;

import com.facebook.assistant.oacr.Oacr;
import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.messenger.assistant.thrift.TtsVoiceSystemConfig;
import com.facebook.messenger.assistant.thrift.TtsVoiceUserConfig;
import java.nio.ByteBuffer;

/* renamed from: X.ft  reason: case insensitive filesystem */
public final class C0713ft implements Oacr.TtsVoiceConfigurationCallback {
    public final /* synthetic */ C0740gP A00;

    public C0713ft(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.TtsVoiceConfigurationCallback
    public final void onConfigUpdate(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        Object[] objArr;
        String str;
        C0139Dd.A09("AssistantClientPlatform_NativeExecutor", "onConfigUpdate");
        try {
            C0740gP gPVar = this.A00;
            try {
                gPVar.A0h.A01(new C0821iR((TtsVoiceSystemConfig) OacrUtil.deserialize("com.facebook.messenger.assistant.thrift.TtsVoiceSystemConfig", byteBuffer, gPVar.A0A), (TtsVoiceUserConfig) OacrUtil.deserialize("com.facebook.messenger.assistant.thrift.TtsVoiceUserConfig", byteBuffer2, gPVar.A0A), gPVar.A0D));
            } catch (RuntimeException e) {
                objArr = new Object[]{e.getMessage(), e};
                str = "Failed to deserialize TtsVoiceUserConfig: %s";
                C0139Dd.A0O("AssistantClientPlatform", str, objArr);
            }
        } catch (RuntimeException e2) {
            objArr = new Object[]{e2.getMessage(), e2};
            str = "Failed to deserialize TtsVoiceSystemConfig: %s";
            C0139Dd.A0O("AssistantClientPlatform", str, objArr);
        }
    }
}
