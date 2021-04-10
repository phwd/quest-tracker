package X;

import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;

/* renamed from: X.ky  reason: case insensitive filesystem */
public final class C0861ky {
    public final /* synthetic */ l0 A00;

    public C0861ky(l0 l0Var) {
        this.A00 = l0Var;
    }

    public final void A00(Exception exc) {
        C0139Dd.A0L("StreamingTtsPlayer", "Error", exc);
        g1 g1Var = this.A00.A00;
        if (g1Var != null) {
            g1Var.A00.A0B = null;
            C00879r.A00.A07(AssistantErrorType.TTS_FAILED);
        }
    }
}
