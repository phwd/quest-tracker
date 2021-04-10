package X;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.oculus.assistant.entry.AssistantEntryManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.vz  reason: case insensitive filesystem */
public final class C1245vz implements AbstractC0105Aj {
    public final /* synthetic */ AssistantEntryManager A00;

    public C1245vz(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Throwable e;
        String str;
        C1256wC wCVar = (C1256wC) ak.A2L();
        String str2 = wCVar.A01;
        C0139Dd.A0F("AssistantEntryManager", "Sending %s", str2);
        Bundle bundle = wCVar.A00;
        List<C0387Va> list = wCVar.A02;
        ArrayList arrayList = new ArrayList();
        for (C0387Va va : list) {
            bundle.putString("subscriberKey", va.A02);
            try {
                va.A00.A4H(str2, bundle);
            } catch (DeadObjectException e2) {
                e = e2;
                str = "Got dead object exception when sending onMessage";
            } catch (RemoteException e3) {
                e = e3;
                str = "Got exception when sending onMessage";
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            AssistantEntryManager assistantEntryManager = this.A00;
            Map map = assistantEntryManager.A0B;
            if (map.containsKey(next)) {
                assistantEntryManager.A00.A01(new C1260wG((C0387Va) map.get(next)));
                map.remove(next);
            }
        }
        return;
        C0139Dd.A0L("AssistantEntryManager", str, e);
        arrayList.add(va.A02);
    }
}
