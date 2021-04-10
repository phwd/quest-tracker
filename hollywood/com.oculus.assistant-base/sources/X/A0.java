package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.os.SettingsManager;

public final class A0 extends C1300wu {
    @Override // X.C1300wu
    public final void A06() {
        super.A06();
        new SettingsManager(BX.A00()).setBoolean("assistant_wakeword_enabled", true);
        AssistantLogger assistantLogger = C0410Wn.A00;
        assistantLogger.logNuxEvent("nux_vc_wakeword_enabled");
        assistantLogger.logServiceEvent("WakeWord - enabled");
        Z4.A00();
        C0112Aq.A00().A01(new C1290wk());
        C0112Aq.A00().A01(new C1289wj());
    }

    @Override // X.C1300wu
    public final void A07() {
        super.A07();
        Z4.A00();
        C0410Wn.A00.logNuxEvent("nux_vc_wakeword_disabled");
        C0112Aq.A00().A01(new C1299wt());
        C0112Aq.A00().A01(new C1290wk());
        C0112Aq.A00().A01(new C1289wj());
    }
}
