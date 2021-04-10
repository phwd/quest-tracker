package org.chromium.chrome.browser.webapps;

import android.content.Context;
import android.content.pm.PackageInfo;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebApkHandlerDelegate {

    /* renamed from: a  reason: collision with root package name */
    public long f10802a;

    public WebApkHandlerDelegate(long j) {
        this.f10802a = j;
    }

    public static WebApkHandlerDelegate create(long j) {
        return new WebApkHandlerDelegate(j);
    }

    public void reset() {
        this.f10802a = 0;
    }

    public void retrieveWebApks() {
        if (this.f10802a != 0) {
            Context applicationContext = ContextUtils.getApplicationContext();
            for (PackageInfo packageInfo : applicationContext.getPackageManager().getInstalledPackages(0)) {
                if (AbstractC2612fx1.b(applicationContext, packageInfo.packageName)) {
                    AbstractC3626lu.a(packageInfo.packageName, new Fw1(this, packageInfo));
                }
            }
        }
    }
}
