package X;

import com.oculus.assistant.service.typeahead.OculusTypeaheadProvider;

/* renamed from: X.z8  reason: case insensitive filesystem */
public final class C1417z8 implements AbstractC0105Aj {
    public final /* synthetic */ OculusTypeaheadProvider A00;

    public C1417z8(OculusTypeaheadProvider oculusTypeaheadProvider) {
        this.A00 = oculusTypeaheadProvider;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C1255wB wBVar = (C1255wB) ak.A2L();
        if (wBVar.A00.A01.equals("OnTypeaheadSuggestionMessage")) {
            OculusTypeaheadProvider oculusTypeaheadProvider = this.A00;
            if (oculusTypeaheadProvider.A0C.isEmpty()) {
                OculusTypeaheadProvider.A02(oculusTypeaheadProvider);
            }
            oculusTypeaheadProvider.A0C.add(wBVar.A00);
            oculusTypeaheadProvider.A06.A01(new C1257wD(wBVar.A00));
        }
    }
}
