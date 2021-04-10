package defpackage;

import J.N;
import android.os.Bundle;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;
import org.chromium.chrome.browser.sync.ui.PassphraseTypeDialogFragment;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: Kb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0614Kb0 implements Runnable {
    public final ManageSyncSettings F;

    public RunnableC0614Kb0(ManageSyncSettings manageSyncSettings) {
        this.F = manageSyncSettings;
    }

    public void run() {
        ManageSyncSettings manageSyncSettings = this.F;
        if (manageSyncSettings.H0.h()) {
            if (manageSyncSettings.H0.j()) {
                C0317Fe fe = new C0317Fe(manageSyncSettings.W);
                PassphraseDialogFragment passphraseDialogFragment = new PassphraseDialogFragment();
                passphraseDialogFragment.b1(manageSyncSettings, -1);
                passphraseDialogFragment.j1(fe, "enter_password");
                return;
            }
            ProfileSyncService profileSyncService = manageSyncSettings.H0;
            if (N.M8uQ8DWG(profileSyncService.e, profileSyncService)) {
                CoreAccountInfo m = AbstractC2531fV.m(C5949zZ.a(), 1);
                if (m != null) {
                    AbstractC4175p51.h(manageSyncSettings, m, 1);
                    return;
                }
                return;
            }
            C0317Fe fe2 = new C0317Fe(manageSyncSettings.W);
            int e = manageSyncSettings.H0.e();
            ProfileSyncService profileSyncService2 = manageSyncSettings.H0;
            long MaVJ6PiJ = N.MaVJ6PiJ(profileSyncService2.e, profileSyncService2);
            ProfileSyncService profileSyncService3 = manageSyncSettings.H0;
            boolean MNBk3pKK = N.MNBk3pKK(profileSyncService3.e, profileSyncService3);
            PassphraseTypeDialogFragment passphraseTypeDialogFragment = new PassphraseTypeDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg_current_type", e);
            bundle.putLong("arg_passphrase_time", MaVJ6PiJ);
            bundle.putBoolean("arg_is_encrypt_everything_allowed", MNBk3pKK);
            passphraseTypeDialogFragment.U0(bundle);
            passphraseTypeDialogFragment.j1(fe2, "password_type");
            passphraseTypeDialogFragment.b1(manageSyncSettings, -1);
        }
    }
}
