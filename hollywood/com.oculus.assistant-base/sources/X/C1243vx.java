package X;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.oculus.assistant.entry.AssistantEntryManager;
import java.util.Map;

/* renamed from: X.vx  reason: case insensitive filesystem */
public final class C1243vx implements AbstractC0105Aj {
    public final /* synthetic */ AssistantEntryManager A00;

    public C1243vx(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Map map;
        C1257wD wDVar = (C1257wD) ak.A2L();
        C0387Va va = wDVar.A00;
        String str = va.A02;
        try {
            va.A00.A3p(va.A01, str);
        } catch (DeadObjectException e) {
            C0139Dd.A0O("AssistantEntryManager", "Got dead object exception when sending onActivated", e.toString());
            map = this.A00.A0B;
            map.remove(str);
        } catch (RemoteException e2) {
            C0139Dd.A0L("AssistantEntryManager", "Got exception when sending onActivated", e2);
            AssistantEntryManager assistantEntryManager = this.A00;
            assistantEntryManager.A00.A01(new C1260wG(wDVar.A00));
            map = assistantEntryManager.A0B;
            map.remove(str);
        }
    }
}
