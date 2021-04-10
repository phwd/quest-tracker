package X;

import android.content.res.Resources;
import java.io.IOException;
import java.util.List;

/* renamed from: X.wR  reason: case insensitive filesystem */
public final class C1271wR implements AbstractC0105Aj {
    public final /* synthetic */ C0403Wf A00;

    public C1271wR(C0403Wf wf) {
        this.A00 = wf;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        List list;
        Integer num;
        boolean z;
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0514bB.A02(ak, "<anonymous parameter 1>");
        C0403Wf wf = this.A00;
        WO wo = wf.A0A;
        wo.A02 = false;
        WS ws = wo.A05;
        ws.A00 = true;
        ws.A01.cancel();
        do {
            int i = wo.A00 + 1;
            wo.A00 = i;
            list = wo.A06;
            if (i >= list.size()) {
                break;
            }
            AbstractC0400Wb wb = (AbstractC0400Wb) list.get(wo.A00);
            if (!(wb instanceof C00959z)) {
                if (!(wb instanceof C1281wb)) {
                    break;
                }
                z = ((C1281wb) wb).A00;
                continue;
            } else {
                z = ((C00959z) wb).A00;
                continue;
            }
        } while (!z);
        if (wo.A00 < list.size()) {
            AbstractC0400Wb wb2 = (AbstractC0400Wb) list.get(wo.A00);
            wo.A01 = wb2;
            if (wb2 != null && C0514bB.A05("voice_command_step", wb2.A03())) {
                AbstractC1279wZ wZVar = (AbstractC1279wZ) wb2;
                HandlerC0422Wz.A02.post(RunnableC0417Wu.A00);
                if (!(wZVar instanceof A1)) {
                    num = ((A7) wZVar).A01;
                } else {
                    num = AnonymousClass09.A00;
                }
                Z4.A01(num);
            }
            C0410Wn.A00.logNuxEvent("nux_vc_next_page");
            try {
                Resources resources = wf.A09.getResources();
                C0514bB.A01(resources, "ctx.resources");
                wo.A04(resources);
            } catch (IOException unused) {
            }
        } else {
            wo.A01 = null;
            HandlerC0422Wz.A02();
            wf.A07.A01(new C1284we(AnonymousClass09.A00, true));
        }
    }
}
