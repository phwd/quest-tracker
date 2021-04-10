package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.assistant.R;
import com.oculus.assistant.assistantutils.SystemUXUtil;

public final class QO extends AnonymousClass7Z {
    public final /* synthetic */ Y7 A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QO(Y7 y7) {
        super(y7);
        this.A00 = y7;
    }

    @Override // X.C1300wu
    public final void A06() {
        super.A06();
        Z4.A00.edit().putBoolean("interaction_privacy_acknowledge", true).apply();
        AssistantLogger assistantLogger = C00799i.A00;
        assistantLogger.logNuxEvent("accept_voicecommand_privacy");
        AnonymousClass7Z r3 = this.A00.A02;
        r3.A09(R.string.nux_setup_complete_with_shortcut_description);
        r3.A01.putString("tertiary", C1300wu.A02(R.string.nux_finish_without_trying));
        HandlerC0422Wz.A06.A09(r3, true);
        assistantLogger.logNuxEvent("show_setup_complete_after_privacy");
    }

    @Override // X.C1300wu
    public final void A08() {
        super.A08();
        HandlerC0422Wz.A02();
        BX.A00().sendBroadcast(SystemUXUtil.A03(ZG.SETTINGS, false, "/assistant"));
        C00799i.A00.logNuxEvent("open_settings");
    }
}
