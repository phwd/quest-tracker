package X;

import android.os.Bundle;
import com.oculus.assistant.service.typeahead.OculusTypeaheadProvider;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.zA  reason: case insensitive filesystem */
public final class C1418zA implements AbstractC0105Aj {
    public final /* synthetic */ OculusTypeaheadProvider A00;

    public C1418zA(OculusTypeaheadProvider oculusTypeaheadProvider) {
        this.A00 = oculusTypeaheadProvider;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        String obj;
        OculusTypeaheadProvider oculusTypeaheadProvider = this.A00;
        if (!oculusTypeaheadProvider.A0C.isEmpty()) {
            C0776h5 h5Var = (C0776h5) ak.A2L();
            Bundle bundle = new Bundle();
            bundle.putString("suggestedWord", h5Var.A00);
            String[] strArr = h5Var.A01;
            bundle.putStringArray("otherSuggestionsList", strArr);
            List asList = Arrays.asList(strArr);
            StringBuilder sb = new StringBuilder();
            Iterator it = asList.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                while (true) {
                    sb.append(it.next());
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append((CharSequence) ";");
                }
                obj = sb.toString();
            }
            bundle.putString("otherSuggestions", obj);
            bundle.putBooleanArray("fromSupplementalDict", h5Var.A02);
            oculusTypeaheadProvider.A06.A01(new C1256wC("OnTypeaheadSuggestionMessage", bundle, oculusTypeaheadProvider.A0C));
        }
    }
}
