package X;

import com.facebook.messenger.assistant.thrift.TtsVoiceSystemConfig;
import com.facebook.messenger.assistant.thrift.TtsVoiceUserConfig;

/* renamed from: X.iR  reason: case insensitive filesystem */
public final class C0821iR implements AbstractC0106Ak {
    public final TtsVoiceSystemConfig A00;
    public final TtsVoiceUserConfig A01;
    public final String A02;

    public C0821iR(TtsVoiceSystemConfig ttsVoiceSystemConfig, TtsVoiceUserConfig ttsVoiceUserConfig, String str) {
        this.A00 = ttsVoiceSystemConfig;
        this.A01 = ttsVoiceUserConfig;
        this.A02 = str;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
