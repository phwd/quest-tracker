package defpackage;

import org.chromium.chrome.browser.webapps.WebApkUpdateManager$WebApkUpdateCallback;

/* renamed from: cx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2100cx1 implements WebApkUpdateManager$WebApkUpdateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Xx1 f9732a;
    public final Runnable b;

    public C2100cx1(Xx1 xx1, Runnable runnable) {
        this.f9732a = xx1;
        this.b = runnable;
    }

    @Override // org.chromium.chrome.browser.webapps.WebApkUpdateManager$WebApkUpdateCallback
    public void onResultFromNative(int i, boolean z) {
        Xx1 xx1 = this.f9732a;
        Runnable runnable = this.b;
        boolean z2 = false;
        xx1.f(false);
        xx1.c.edit().putBoolean("update_scheduled", false).apply();
        xx1.c.edit().putLong("last_update_request_complete_time", System.currentTimeMillis()).apply();
        if (i == 0) {
            z2 = true;
        }
        xx1.c.edit().putBoolean("did_last_update_request_succeed", z2).apply();
        xx1.c.edit().putBoolean("relax_updates", z).apply();
        xx1.c.edit().putInt("last_requested_shell_apk_version", 122).apply();
        xx1.a();
        runnable.run();
    }
}
