package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9T  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9T implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$cweA6qwec7Kb9FvUI-HwCL5dOAU";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;

    public /* synthetic */ AnonymousClass9T(AssistantLogger assistantLogger, String str) {
        this.A00 = assistantLogger;
        this.A01 = str;
    }

    public final void run() {
        this.A00.logNuxEvent(this.A01);
    }
}
