package defpackage;

import J.N;
import android.content.ContentResolver;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.webapps.WebApkInstaller;

/* renamed from: Jw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Jw1 extends AbstractC2032cb {
    public final /* synthetic */ WebApkInstaller i;

    public Jw1(WebApkInstaller webApkInstaller) {
        this.i = webApkInstaller;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long availableBytes = statFs.getAvailableBytes();
        long totalBytes = statFs.getTotalBytes();
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        long min = (availableBytes - Math.min(Settings.Global.getLong(contentResolver, "sys_storage_threshold_max_bytes", 524288000), (totalBytes * ((long) Settings.Global.getInt(contentResolver, "sys_storage_threshold_percentage", 10))) / 100)) + 104857600;
        if (min > 0) {
            return 0;
        }
        if (AbstractC1929bx1.a(ContextUtils.getApplicationContext().getCacheDir()) + min > 0) {
            return 1;
        }
        return 2;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        WebApkInstaller webApkInstaller = this.i;
        N.Mz0ZUPry(webApkInstaller.f10803a, webApkInstaller, ((Integer) obj).intValue());
    }
}
