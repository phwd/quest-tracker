package defpackage;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadManagerBridge;

/* renamed from: XH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XH implements Runnable {
    public final String F;
    public final boolean G;

    public XH(String str, boolean z) {
        this.F = str;
        this.G = z;
    }

    public void run() {
        long j;
        int i;
        String str = this.F;
        boolean z = this.G;
        synchronized (DownloadManagerBridge.f10661a) {
            SharedPreferences c = DownloadManagerBridge.c();
            j = c.getLong(str, -1);
            i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
            if (i != 0) {
                SharedPreferences.Editor edit = c.edit();
                edit.remove(str);
                edit.apply();
            }
        }
        if (i != 0 && !z) {
            ((DownloadManager) ContextUtils.getApplicationContext().getSystemService("download")).remove(j);
        }
    }
}
