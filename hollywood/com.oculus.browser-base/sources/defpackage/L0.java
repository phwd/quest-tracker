package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: L0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class L0 implements YE0 {
    public final AccountManagementFragment F;

    public L0(AccountManagementFragment accountManagementFragment) {
        this.F = accountManagementFragment;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.m1();
    }
}
