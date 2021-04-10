package X;

import X.AbstractC0106Ak;
import X.C0112Aq;
import X.C1284we;
import X.C1287wh;
import X.C1294wo;
import X.l0;
import android.os.CountDownTimer;
import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xN  reason: case insensitive filesystem */
public final /* synthetic */ class C1329xN implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1329xN(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        CountDownTimer countDownTimer = assistantService.A04;
        if (countDownTimer == null) {
            assistantService.A04 = new CountDownTimer(300, 25, ak) {
                /* class com.oculus.assistant.service.AssistantService.AnonymousClass9 */
                public final /* synthetic */ AbstractC0106Ak val$event;

                {
                    this.val$event = r6;
                }

                public void onFinish() {
                    boolean z = ((C1294wo) this.val$event.A2L()).A00;
                    C0112Aq A00 = C0112Aq.A00();
                    if (z) {
                        A00.A01(new C1284we());
                    } else {
                        A00.A01(new C1287wh());
                    }
                }

                public void onTick(long j) {
                    l0 l0Var = AssistantService.this.A05.A0B;
                    if (l0Var != null) {
                        l0Var.A03();
                    }
                }
            };
        } else {
            countDownTimer.cancel();
        }
        assistantService.A04.start();
    }
}
