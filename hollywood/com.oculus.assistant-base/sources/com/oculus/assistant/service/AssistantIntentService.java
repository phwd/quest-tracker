package com.oculus.assistant.service;

import X.AnonymousClass08;
import X.C00799i;
import X.C1300wu;
import X.HandlerC0422Wz;
import X.Vu;
import X.Z4;
import android.app.IntentService;
import android.net.Uri;
import com.oculus.assistant.R;
import com.oculus.os.SettingsManager;

public class AssistantIntentService extends IntentService {
    public AssistantIntentService() {
        super("AssistantIntentService");
    }

    private void A00(Uri uri) {
        if (!Boolean.parseBoolean(uri.getQueryParameter("enabled"))) {
            return;
        }
        if (Z4.A00.getBoolean("interaction_privacy_acknowledge", false)) {
            C1300wu wuVar = new C1300wu();
            wuVar.A0G("dlg-ww-enabled");
            wuVar.A0C(R.string.enable_ww_title);
            wuVar.A09(R.string.enable_ww_description);
            wuVar.A0A(R.string.enable_ww_restart);
            wuVar.A0B(R.string.cancel_button_text);
            wuVar.A0E("system-dialog");
            HandlerC0422Wz.A06.A09(wuVar, true);
            C00799i.A00.logServiceEvent("WakeWord - enabled");
            return;
        }
        new SettingsManager(getApplicationContext()).setBoolean("assistant_wakeword_enabled", false);
        C00799i.A00.logServiceEvent(AnonymousClass08.A04("assistant_route_activation_source_", "WakeWordToggleWithoutNUX"));
        Vu.A03(Vu.A02("WakeWordToggleWithoutNUX"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onHandleIntent(android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 222
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.assistant.service.AssistantIntentService.onHandleIntent(android.content.Intent):void");
    }
}
