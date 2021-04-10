package X;

import android.content.Intent;
import android.util.Log;
import com.facebook.assistant.oacr.OacrConstants;
import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.xl  reason: case insensitive filesystem */
public final /* synthetic */ class C1353xl implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1353xl(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        AtomicBoolean atomicBoolean = assistantService.A1G;
        if (!atomicBoolean.get()) {
            AssistantInteractionState valueOf = AssistantInteractionState.valueOf(((C0809hx) ak.A2L()).A00.name());
            assistantService.A0b = valueOf;
            if (valueOf == AssistantInteractionState.INACTIVE) {
                assistantService.sendBroadcast(new Intent("com.oculus.assistant.ACTIVATE_WAKEWORD"));
            }
            if (!atomicBoolean.get()) {
                switch (valueOf.ordinal()) {
                    case 0:
                        assistantService.A18.A01(new C1210vP());
                        assistantService.A0j = false;
                        C0398Vv.A04.removeCallbacks(C0398Vv.A05);
                        C0398Vv.A01 = null;
                        HandlerC0422Wz.A01();
                        YP yp = assistantService.A0a;
                        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "endInteraction");
                        YO yo = yp.A01;
                        if (yo != null) {
                            yo.A03(2);
                        }
                        yp.A01 = null;
                        break;
                    case 1:
                        if (assistantService.A0l) {
                            C0139Dd.A09("AssistantService", "Autorecording has started.");
                            assistantService.A0g = OacrConstants.AUTO_SPEECH_DOMAIN;
                            C1412yy yyVar = assistantService.A0c;
                            String str = C00799i.A00.mInteractionId;
                            C0450Yv yv = yyVar.A08;
                            if (yv.A00 != null) {
                                Log.d("MicDataLogger", AnonymousClass08.A04("Already recording ", yv.A05));
                                break;
                            } else {
                                Yt yt = new Yt(yv);
                                yv.A00 = yt;
                                AtomicBoolean atomicBoolean2 = yt.A01;
                                if (atomicBoolean2.get()) {
                                    atomicBoolean2.set(false);
                                    yt.A00.add(new z3(yt.A02, null));
                                }
                                atomicBoolean2.set(true);
                                C1416z4 z4Var = new C1416z4(yt, str);
                                ConcurrentLinkedQueue concurrentLinkedQueue = yt.A00;
                                concurrentLinkedQueue.add(z4Var);
                                concurrentLinkedQueue.add(yt.A02.A07);
                                new Thread(yt).start();
                                break;
                            }
                        }
                        break;
                }
                assistantService.A0Y.A04(valueOf);
            }
        }
    }
}
