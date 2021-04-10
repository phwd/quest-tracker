package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import java.util.Map;

/* renamed from: X.9M  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9M implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$QF-xCuoB5jcaqSsJVb4K7LrhFTQ";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ Map A03;

    public /* synthetic */ AnonymousClass9M(AssistantLogger assistantLogger, String str, String str2, Map map) {
        this.A00 = assistantLogger;
        this.A01 = str;
        this.A02 = str2;
        this.A03 = map;
    }

    public final void run() {
        this.A00.logOACREvent(this.A01, this.A02, this.A03);
    }
}
