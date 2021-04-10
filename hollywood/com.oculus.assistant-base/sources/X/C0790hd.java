package X;

/* renamed from: X.hd  reason: case insensitive filesystem */
public final class C0790hd implements AbstractC0105Aj {
    public final /* synthetic */ C0648ds A00;

    public C0790hd(C0648ds dsVar) {
        this.A00 = dsVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "busContext_");
        C0514bB.A02(ak, "event");
        C0139Dd.A09("AssistantSettingsManager", "ttsVoiceConfigUpdateSubscriber");
        String userId = this.A00.A02.getUserId();
        C0668el elVar = new C0668el(C00799i.A00.mStructuredLogger.A00("assistant_user_settings"));
        boolean z = elVar instanceof AnonymousClass6E;
        C0668el elVar2 = null;
        C0668el elVar3 = elVar;
        if (!z) {
            elVar3 = null;
        }
        if (elVar3 != null && elVar3.A00.isSampled()) {
            elVar.A00.A1D("assistant_user_id", userId);
            elVar.A00.A1B("setting_category", 1L);
            elVar.A00.A1B("event", Long.valueOf((long) 18));
        }
        if (userId == null) {
            if (z) {
                elVar2 = elVar;
            }
            if (elVar2 != null && elVar2.A00.isSampled()) {
                elVar.A00.A1D("error_message", "userId is null");
                elVar.A00();
            }
            C0112Aq.A00().A01(new C0789hc(false, "userId is null"));
            return;
        }
        AP.A00.submit(new A8(this, ak, userId, elVar));
    }
}
