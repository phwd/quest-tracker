package X;

import com.facebook.debug.tracer.Tracer;
import com.oculus.assistant.R;
import com.oculus.assistant.service.AssistantService;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.xW  reason: case insensitive filesystem */
public final /* synthetic */ class C1338xW implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1338xW(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        AtomicBoolean atomicBoolean = assistantService.A1G;
        if (!atomicBoolean.get()) {
            boolean z = ((C0807hv) ak.A2L()).A00;
            if (!atomicBoolean.get()) {
                if (assistantService.A0l) {
                    C0139Dd.A09("AssistantService", "Autorecording has stopped.");
                    C0139Dd.A0F("AssistantService", "Stored recording as %s", assistantService.A0h);
                    C00799i.A00.logRecording(assistantService.A0h);
                    C1412yy yyVar = assistantService.A0c;
                    String str = assistantService.A0h;
                    C0450Yv yv = yyVar.A08;
                    Yt yt = yv.A00;
                    if (yt != null) {
                        yt.A01.set(false);
                        yt.A00.add(new z3(yt.A02, str));
                        yv.A00 = null;
                    }
                }
                try {
                    assistantService.A0c.close();
                } catch (IOException e) {
                    C0139Dd.A0L("AssistantService", "Failed to close when shutting down mic in processing: ", e);
                }
                YB yb = assistantService.A0Y;
                if (yb.A03) {
                    if (Z4.A02()) {
                        Tracer.A01("updateAttentionState with message");
                        try {
                            if (R.drawable.ic_attn_error != yb.A00) {
                                yb.A00 = R.drawable.ic_attn_error;
                                HandlerC0422Wz wz = HandlerC0422Wz.A06;
                                X0 x0 = new X0();
                                x0.A01(R.string.assistant_nux_ood_message);
                                x0.A02(R.string.assistant_nux_ood_sub_message);
                                x0.A00(R.drawable.ic_attn_error);
                                wz.A0A(x0);
                            }
                        } finally {
                            Tracer.A00();
                        }
                    } else {
                        YB.A02(yb, R.drawable.ic_attn_error);
                    }
                    yb.A03 = false;
                } else {
                    YB.A02(yb, R.drawable.ic_attn_success);
                }
                C0441Yl.A00().A03();
                if (!yb.A0A.A02(new C1360xs(z))) {
                    yb.A0B.A02(new C1364xw(z));
                }
                if (assistantService.A0i) {
                    assistantService.A0i = false;
                    assistantService.A0K();
                }
            }
            HandlerC0422Wz.A07(false);
        }
    }
}
