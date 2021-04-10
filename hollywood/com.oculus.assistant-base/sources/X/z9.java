package X;

import com.facebook.assistant.clientplatform.keyboard.learning.LearningListener;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.LiteLMSuggestionPredictor;
import com.oculus.assistant.service.typeahead.OculusTypeaheadProvider;

public final class z9 implements AbstractC0105Aj {
    public final /* synthetic */ OculusTypeaheadProvider A00;

    public z9(OculusTypeaheadProvider oculusTypeaheadProvider) {
        this.A00 = oculusTypeaheadProvider;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C1260wG wGVar = (C1260wG) ak.A2L();
        C0387Va va = wGVar.A00;
        if (va.A01.equals("OnTypeaheadSuggestionMessage")) {
            OculusTypeaheadProvider oculusTypeaheadProvider = this.A00;
            if (oculusTypeaheadProvider.A0C.remove(va)) {
                oculusTypeaheadProvider.A06.A01(new C1258wE(wGVar.A00));
            }
            if (oculusTypeaheadProvider.A0C.isEmpty()) {
                ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider = oculusTypeaheadProvider.A02;
                if (byteLMWordTypeaheadProvider != null) {
                    byteLMWordTypeaheadProvider.A3g();
                    LiteLMSuggestionPredictor liteLMSuggestionPredictor = oculusTypeaheadProvider.A02.A04;
                    synchronized (liteLMSuggestionPredictor) {
                        C0616d1 d1Var = liteLMSuggestionPredictor.A01;
                        if (d1Var != null) {
                            d1Var.A00.resetNative();
                            liteLMSuggestionPredictor.A01 = null;
                        }
                    }
                    oculusTypeaheadProvider.A02 = null;
                }
                LearningListener learningListener = oculusTypeaheadProvider.A01;
                if (learningListener != null) {
                    learningListener.A3g();
                    oculusTypeaheadProvider.A01 = null;
                }
            }
        }
    }
}
