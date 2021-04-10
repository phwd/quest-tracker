package defpackage;

import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: O41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class O41 implements Runnable {
    public final SyncAndServicesSettings F;

    public O41(SyncAndServicesSettings syncAndServicesSettings) {
        this.F = syncAndServicesSettings;
    }

    public void run() {
        CoreAccountInfo m;
        SyncAndServicesSettings syncAndServicesSettings = this.F;
        int i = syncAndServicesSettings.e1;
        if (i != -1) {
            if (i == 0) {
                U20.q(syncAndServicesSettings.u(), new Intent("android.settings.SYNC_SETTINGS"));
            } else if (i == 1) {
                AccountManagerFacadeProvider.getInstance().b(CoreAccountInfo.a(C5949zZ.a().c(Profile.b()).b(1)), syncAndServicesSettings.u(), null);
            } else if (i == 5) {
                Intent intent = new Intent("android.intent.action.VIEW");
                StringBuilder i2 = AbstractC2531fV.i("market://details?id=");
                i2.append(ContextUtils.getApplicationContext().getPackageName());
                intent.setData(Uri.parse(i2.toString()));
                syncAndServicesSettings.c1(intent);
            } else if (i == 128) {
                C5949zZ.a().d(Profile.b()).h(3, new T41(CoreAccountInfo.a(C5949zZ.a().c(Profile.b()).b(1))), false);
            } else if (i == 2) {
                C0317Fe fe = new C0317Fe(syncAndServicesSettings.W);
                PassphraseDialogFragment passphraseDialogFragment = new PassphraseDialogFragment();
                passphraseDialogFragment.b1(syncAndServicesSettings, -1);
                passphraseDialogFragment.j1(fe, "enter_password");
            } else if ((i == 3 || i == 4) && (m = AbstractC2531fV.m(C5949zZ.a(), 1)) != null) {
                AbstractC4175p51.h(syncAndServicesSettings, m, 1);
            }
        }
    }
}
