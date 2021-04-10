package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9P  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9P implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$YBtiv6Sttp4327FpEMlKVpRyvTw";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;

    public /* synthetic */ AnonymousClass9P(AssistantLogger assistantLogger, String str) {
        this.A00 = assistantLogger;
        this.A01 = str;
    }

    public final void run() {
        this.A00.logKeyboardTranscription(this.A01);
    }
}
