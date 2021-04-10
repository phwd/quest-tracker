package X;

import X.AbstractC0106Ak;
import X.C1259wF;
import android.os.Handler;
import android.os.Looper;
import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xY  reason: case insensitive filesystem */
public final /* synthetic */ class C1340xY implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1340xY(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (!assistantService.A1G.get()) {
            C1259wF wFVar = (C1259wF) ak.A2L();
            if (wFVar.A00 == 0) {
                assistantService.A0Y.A06(wFVar.A01);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(ak) {
                    /* class com.oculus.assistant.service.AssistantService.AnonymousClass10 */
                    public final /* synthetic */ AbstractC0106Ak val$event;

                    {
                        this.val$event = r2;
                    }

                    public void run() {
                        AssistantService.this.A0Y.A06(((C1259wF) this.val$event.A2L()).A01);
                    }
                }, (long) wFVar.A00);
            }
        }
    }
}
