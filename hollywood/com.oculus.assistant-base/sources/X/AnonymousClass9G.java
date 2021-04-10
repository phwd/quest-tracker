package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9G  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9G implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$2a-ASAr8ftnHT_R6fxE6oFUU1lI";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;

    public /* synthetic */ AnonymousClass9G(AssistantLogger assistantLogger, String str, String str2) {
        this.A00 = assistantLogger;
        this.A01 = str;
        this.A02 = str2;
    }

    public final void run() {
        this.A00.logAppCommmandEvent(this.A01, this.A02);
    }
}
