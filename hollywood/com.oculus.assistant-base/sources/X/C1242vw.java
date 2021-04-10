package X;

import com.oculus.assistant.entry.AssistantEntryManager;

/* renamed from: X.vw  reason: case insensitive filesystem */
public final class C1242vw implements W8 {
    public final /* synthetic */ AssistantEntryManager A00;

    public C1242vw(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    @Override // X.W8
    public final void A3w() {
        AssistantEntryManager assistantEntryManager = this.A00;
        w1 w1Var = new w1(assistantEntryManager);
        assistantEntryManager.A01 = w1Var;
        C0112Aq aq = assistantEntryManager.A00;
        if (assistantEntryManager.A03.A00() != null) {
            assistantEntryManager.A03.A00();
        }
        aq.A01(new C1261wH(w1Var));
    }

    @Override // X.W8
    public final void onError(Exception exc) {
        this.A00.A00.A01(new C1262wI(exc));
    }
}
