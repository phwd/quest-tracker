package X;

import com.facebook.assistant.oacr.TtsApi;

public final class g9 extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$37";
    public final /* synthetic */ C0740gP A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g9(C0740gP gPVar, String str, String str2) {
        super("ACP: onTtsVoiceSelected");
        this.A00 = gPVar;
        this.A01 = str;
        this.A02 = str2;
    }

    public final void run() {
        TtsApi ttsApi = this.A00.A07;
        if (ttsApi == null) {
            C0139Dd.A0D("AssistantClientPlatform", "Tts API isn't ready, skipping Tts voice selection");
        } else {
            ttsApi.onTtsVoiceSelected(this.A01, this.A02);
        }
    }
}
