package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.os.SettingsManager;
import java.util.concurrent.TimeUnit;

public final class AM extends C1300wu {
    public final /* synthetic */ C1268wO A00;

    public AM(C1268wO wOVar) {
        this.A00 = wOVar;
    }

    @Override // X.X1, X.C1300wu
    public final boolean A42(String str, String str2, Bundle bundle) {
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (C0514bB.A05("NUX_PRIVACY_STEP_TOGGLE_ID", str2) && bundle != null && bundle.containsKey("checked")) {
            C0410Wn.A00.logNuxEvent("nux_vc_toggling_the_privacy_opt_out");
            this.A00.A00 = bundle.getBoolean("checked");
        }
        return super.A42(str, str2, bundle);
    }

    @Override // X.C1300wu
    public final void A06() {
        super.A06();
        AssistantLogger assistantLogger = C0410Wn.A00;
        assistantLogger.logNuxEvent("nux_vc_accept_voicecommand_privacy");
        assistantLogger.logNuxEvent("nux_vc_setup_complete");
        long days = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis());
        SharedPreferences sharedPreferences = Z4.A00;
        sharedPreferences.edit().putLong("user_enrolled_nux_success_day", days).apply();
        sharedPreferences.edit().putBoolean("interaction_privacy_acknowledge", true).apply();
        new SettingsManager(BX.A00()).setBoolean("voice_interaction_storage_enabled", this.A00.A00);
        C0112Aq.A00().A01(new C1290wk());
    }
}
