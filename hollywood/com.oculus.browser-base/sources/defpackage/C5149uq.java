package defpackage;

import org.chromium.chrome.browser.ChromeBackupWatcher;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* renamed from: uq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5149uq extends AbstractC5779yZ {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChromeBackupWatcher f11438a;

    public C5149uq(ChromeBackupWatcher chromeBackupWatcher) {
        this.f11438a = chromeBackupWatcher;
    }

    @Override // defpackage.AbstractC5779yZ
    public void T(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        this.f11438a.onBackupPrefsChanged();
    }
}
