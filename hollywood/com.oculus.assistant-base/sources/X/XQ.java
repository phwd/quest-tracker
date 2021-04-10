package X;

import com.facebook.acra.ErrorReporter;
import com.oculus.assistant.R;
import com.oculus.assistant.service.AssistantService;

public final /* synthetic */ class XQ implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$BjWg7EoQsW6PkovhqQ0BIDFctds";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ XQ(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        C1375yA yAVar;
        AssistantService assistantService = this.A00;
        if (AssistantService.A0I(assistantService, "voice_interaction", false)) {
            AssistantService.A0B(assistantService, "voice_interaction");
            C0139Dd.A09("AssistantService", "maybeStartBufferingAudio");
            if (!YN.A04() || (yAVar = assistantService.A0V) == null || !yAVar.isSourceAvailable()) {
                C1376yB yBVar = assistantService.A0W;
                if (yBVar != null && yBVar.isSourceAvailable()) {
                    C0139Dd.A09("AssistantService", "maybeStartBufferingAudio start");
                    assistantService.A0W.open();
                } else {
                    return;
                }
            } else {
                if (!"hfb_wakeword".equals(assistantService.A0f)) {
                    C0139Dd.A09("AssistantService", "maybeStartBufferingAudio clear audio buffer");
                    YN.A00();
                }
                C0139Dd.A09("AssistantService", "maybeStartBufferingAudio start");
                assistantService.A0V.open();
            }
            YB yb = assistantService.A0Y;
            HandlerC0422Wz wz = HandlerC0422Wz.A06;
            if (wz.A0C()) {
                X0 x0 = new X0();
                x0.A01(R.string.listening);
                x0.A03 = Integer.valueOf((int) ErrorReporter.MAX_ANR_TRACES_TIME_DELTA_MS);
                wz.A0A(x0);
            }
            YB.A01();
            yb.A02 = true;
            YP.A00().A02();
        }
    }
}
