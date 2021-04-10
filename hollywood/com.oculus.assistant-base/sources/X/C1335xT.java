package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.xT  reason: case insensitive filesystem */
public final /* synthetic */ class C1335xT implements AbstractC0105Aj {
    public static final /* synthetic */ C1335xT A00 = new C1335xT();

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantLogger assistantLogger = C00799i.A00;
        C0775h2 h2Var = (C0775h2) ak.A2L();
        String str = h2Var.A01;
        String str2 = h2Var.A00;
        assistantLogger.logTranscriptionFeedback(str, str2);
        if (str2.equals("negative")) {
            HandlerC0422Wz.A06.A09(new C00819l(), true);
        }
    }
}
