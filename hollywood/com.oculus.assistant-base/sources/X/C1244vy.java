package X;

import android.os.RemoteException;
import com.oculus.assistant.entry.AssistantEntryManager;

/* renamed from: X.vy  reason: case insensitive filesystem */
public final class C1244vy implements AbstractC0105Aj {
    public final /* synthetic */ AssistantEntryManager A00;

    public C1244vy(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0387Va va = ((C1258wE) ak.A2L()).A00;
        String str = va.A02;
        try {
            va.A00.A41(va.A01, str);
        } catch (RemoteException e) {
            C0139Dd.A0L("AssistantEntryManager", "Got exception when sending onDeactivated", e);
        }
    }
}
