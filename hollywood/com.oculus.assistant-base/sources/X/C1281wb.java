package X;

import com.oculus.assistant.R;

/* renamed from: X.wb  reason: case insensitive filesystem */
public final class C1281wb extends AbstractC0400Wb {
    public final boolean A00;
    public final X1 A01 = new C1280wa(this);

    @Override // X.AbstractC0400Wb
    public final void A05() {
        X1 x1 = this.A01;
        AY ay = HandlerC0422Wz.A05;
        if (x1 != null) {
            ay.A01.remove(x1);
        }
    }

    @Override // X.AbstractC0400Wb
    public final void A06() {
        HandlerC0422Wz wz = HandlerC0422Wz.A06;
        X0 x0 = new X0(null, null, null, null, 15, null);
        x0.A01(R.string.voice_selection_notification);
        x0.A02(R.string.voice_selection_getting_voices);
        wz.A0A(x0);
        HandlerC0422Wz.A04(this.A01);
        Vu.A03(Vu.A01());
    }

    public C1281wb() {
        W0 A002 = W0.A00();
        C0514bB.A01(A002, "AssistantConfig.get()");
        this.A00 = A002.A00.getBoolean("enable_voice_selection", false);
    }
}
