package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.ChromeBackupWatcher;

/* renamed from: tq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4979tq implements OU0 {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeBackupWatcher f11373a;

    public C4979tq(ChromeBackupWatcher chromeBackupWatcher) {
        this.f11373a = chromeBackupWatcher;
    }

    @Override // defpackage.OU0
    public void a(String str) {
        ChromeBackupWatcher chromeBackupWatcher = this.f11373a;
        Objects.requireNonNull(chromeBackupWatcher);
        for (String str2 : C4809sq.f11304a) {
            if (str.equals(str2)) {
                chromeBackupWatcher.onBackupPrefsChanged();
                return;
            }
        }
    }
}
