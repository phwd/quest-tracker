package X;

import android.content.res.Resources;

/* renamed from: X.wT  reason: case insensitive filesystem */
public final class C1273wT implements AbstractC0105Aj {
    public final /* synthetic */ C0403Wf A00;

    public C1273wT(C0403Wf wf) {
        this.A00 = wf;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0514bB.A02(ak, "<anonymous parameter 1>");
        C0403Wf wf = this.A00;
        WO wo = wf.A0A;
        Resources resources = wf.A09.getResources();
        C0514bB.A01(resources, "ctx.resources");
        C0514bB.A02(resources, "resources");
        AbstractC0400Wb wb = wo.A01;
        if (wb != null && C0514bB.A05("voice_command_step", wb.A03())) {
            WO.A02(wo, (AbstractC1279wZ) wb, resources, AnonymousClass09.A01);
        }
    }
}
