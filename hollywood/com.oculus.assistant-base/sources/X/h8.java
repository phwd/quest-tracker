package X;

import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;

public final class h8 implements AbstractC0105Aj {
    public final /* synthetic */ ByteLMWordTypeaheadProvider A00;

    public h8(ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider) {
        this.A00 = byteLMWordTypeaheadProvider;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantLogger assistantLogger = C00799i.A00;
        C0777h6 h6Var = (C0777h6) ak.A2L();
        assistantLogger.logSmartKeyboardInteractionsOneSession(h6Var.A08, this.A00.A05.A07, h6Var.A09, h6Var.A02, h6Var.A03, h6Var.A06, h6Var.A01, h6Var.A04, h6Var.A07, h6Var.A00, h6Var.A05, h6Var.A0A);
    }
}
