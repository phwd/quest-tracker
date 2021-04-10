package X;

import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;

/* renamed from: X.fl  reason: case insensitive filesystem */
public final class C0705fl implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0705fl(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0740gP gPVar = this.A00;
        if (gPVar.A14.get()) {
            C0740gP.A02(gPVar, AssistantErrorType.CLIENT_ERROR, "Assistant is active, can not process TTS request.");
            return;
        }
        l0 l0Var = gPVar.A0B;
        if (l0Var != null) {
            l0Var.A03();
        }
        throw new NullPointerException("isForwadingMode");
    }
}
