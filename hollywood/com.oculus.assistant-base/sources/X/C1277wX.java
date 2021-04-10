package X;

import android.content.res.Resources;

/* renamed from: X.wX  reason: case insensitive filesystem */
public final class C1277wX implements AbstractC0105Aj {
    public final /* synthetic */ C0403Wf A00;

    public C1277wX(C0403Wf wf) {
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
        if (wo.A02) {
            C0112Aq.A00().A01(new C1284we(AnonymousClass09.A00, true));
            return;
        }
        wo.A02 = true;
        AbstractC0400Wb wb = wo.A01;
        if (wb != null && C0514bB.A05("voice_command_step", wb.A03())) {
            WO.A02(wo, (AbstractC1279wZ) wb, resources, AnonymousClass09.A0M);
        }
    }
}
