package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: I0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class I0 implements YE0 {
    public final AccountManagementFragment F;

    public I0(AccountManagementFragment accountManagementFragment) {
        this.F = accountManagementFragment;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.l1();
    }
}
