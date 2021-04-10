package org.chromium.chrome.browser;

import android.app.backup.BackupManager;
import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBackupWatcher {

    /* renamed from: a  reason: collision with root package name */
    public BackupManager f10601a;

    public ChromeBackupWatcher() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext != null) {
            this.f10601a = new BackupManager(applicationContext);
            PU0 pu0 = NU0.f8549a;
            if (!pu0.d("first_backup_done", false)) {
                P21 f0 = P21.f0();
                try {
                    this.f10601a.dataChanged();
                    f0.close();
                    pu0.m("first_backup_done", true);
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            C4979tq tqVar = new C4979tq(this);
            LU0 lu0 = new LU0(tqVar);
            pu0.b.put(tqVar, lu0);
            AbstractC3983nz.f10523a.registerOnSharedPreferenceChangeListener(lu0);
            IdentityManager c = C5949zZ.a().c(Profile.b());
            c.b.b(new C5149uq(this));
            return;
        }
        return;
        throw th;
    }

    public static ChromeBackupWatcher createChromeBackupWatcher() {
        return new ChromeBackupWatcher();
    }

    public final void onBackupPrefsChanged() {
        P21 f0 = P21.f0();
        try {
            this.f10601a.dataChanged();
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
