package X;

import android.content.res.Resources;

/* renamed from: X.wS  reason: case insensitive filesystem */
public final class C1272wS implements AbstractC0105Aj {
    public final /* synthetic */ C0403Wf A00;

    public C1272wS(C0403Wf wf) {
        this.A00 = wf;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Integer num;
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0403Wf wf = this.A00;
        WO wo = wf.A0A;
        Resources resources = wf.A09.getResources();
        C0514bB.A01(resources, "ctx.resources");
        C0514bB.A01(ak, "event");
        Integer num2 = ((C1298ws) ak.A2L()).A00;
        C0514bB.A02(resources, "resources");
        C0514bB.A02(num2, "eventVoiceCommandId");
        AbstractC0400Wb wb = wo.A01;
        if (wb != null && C0514bB.A05("voice_command_step", wb.A03())) {
            AbstractC1279wZ wZVar = (AbstractC1279wZ) wb;
            if (!(wZVar instanceof A1)) {
                num = ((A7) wZVar).A01;
            } else {
                num = AnonymousClass09.A00;
            }
            if (num == num2) {
                WS ws = wo.A05;
                ws.A00 = true;
                ws.A01.cancel();
                HandlerC0422Wz.A06.A09(wZVar.A0A(), false);
                if (wZVar instanceof A7) {
                    wo.A04.postDelayed(new WM(wZVar, wo, resources), 1200);
                    return;
                }
                return;
            }
            WO.A02(wo, wZVar, resources, AnonymousClass09.A01);
        }
    }
}
