package X;

import com.oculus.assistant.entry.AssistantEntryManager;
import java.util.List;
import java.util.Map;

/* renamed from: X.w0  reason: case insensitive filesystem */
public final class C1246w0 implements AbstractC0105Aj {
    public final /* synthetic */ AssistantEntryManager A00;

    public C1246w0(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantEntryManager assistantEntryManager = this.A00;
        Map map = assistantEntryManager.A0C;
        for (C0387Va va : map.values()) {
            assistantEntryManager.A00.A01(new C1255wB(va));
        }
        List<AbstractC0106Ak> list = assistantEntryManager.A0A;
        for (AbstractC0106Ak ak2 : list) {
            assistantEntryManager.A00.A01(ak2);
        }
        map.clear();
        list.clear();
        assistantEntryManager.A05 = true;
    }
}
