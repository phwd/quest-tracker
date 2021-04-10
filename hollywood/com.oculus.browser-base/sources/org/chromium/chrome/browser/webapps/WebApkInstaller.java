package org.chromium.chrome.browser.webapps;

import J.N;
import android.graphics.Bitmap;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebApkInstaller {

    /* renamed from: a  reason: collision with root package name */
    public long f10803a;
    public final String b = "";

    public WebApkInstaller(long j) {
        this.f10803a = j;
        Objects.requireNonNull(AppHooks.get());
        Objects.requireNonNull(AppHooks.get());
    }

    public static WebApkInstaller create(long j) {
        return new WebApkInstaller(j);
    }

    public final void a(int i) {
        long j = this.f10803a;
        if (j != 0) {
            N.MQ3SBZxh(j, this, i);
        }
    }

    public final void checkFreeSpace() {
        Jw1 jw1 = new Jw1(this);
        Executor executor = AbstractC2032cb.f9616a;
        jw1.f();
        ((ExecutorC1463Ya) executor).execute(jw1.e);
    }

    public final void destroy() {
        this.f10803a = 0;
    }

    public final String getWebApkServerUrl() {
        return this.b;
    }

    public final void installWebApkAsync(String str, int i, String str2, String str3, int i2, Bitmap bitmap) {
        if (AbstractC4652ru0.b(ContextUtils.getApplicationContext(), str)) {
            a(0);
            return;
        }
        a(1);
        AbstractC3364kK0.g("WebApk.Install.GooglePlayInstallResult", 1, 16);
    }

    public final void updateAsync(String str, int i, String str2, String str3) {
        a(1);
    }
}
