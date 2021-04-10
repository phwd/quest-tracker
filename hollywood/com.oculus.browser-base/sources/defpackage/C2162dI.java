package defpackage;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadManagerBridge;

/* renamed from: dI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2162dI extends AbstractC2032cb {
    public final ZH i;
    public final Callback j;
    public long k;
    public int l;
    public long m;

    public C2162dI(ZH zh, Callback callback) {
        this.i = zh;
        this.j = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.i.f9335a));
            request.setMimeType(this.i.d);
            try {
                ZH zh = this.i;
                if (zh.h) {
                    String str = zh.b;
                    if (str != null) {
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str);
                    }
                } else {
                    Object obj = DownloadManagerBridge.f10661a;
                    File file = new File(ContextUtils.getApplicationContext().getExternalFilesDir(null), "Download");
                    if (!file.mkdir()) {
                        if (!file.isDirectory()) {
                            AbstractC1220Ua0.a("DownloadDelegate", "Cannot create download directory", new Object[0]);
                            this.l = 1001;
                            return Boolean.FALSE;
                        }
                    }
                    request.setDestinationUri(Uri.fromFile(new File(file, this.i.b)));
                }
                if (this.i.h) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(1);
                } else {
                    request.setNotificationVisibility(0);
                }
                String str2 = this.i.c;
                if (TextUtils.isEmpty(str2)) {
                    str2 = this.i.b;
                }
                request.setDescription(str2);
                request.setTitle(this.i.b);
                request.addRequestHeader("Cookie", this.i.e);
                request.addRequestHeader("referrer", this.i.f);
                request.addRequestHeader("User-Agent", this.i.g);
                Object obj2 = DownloadManagerBridge.f10661a;
                DownloadManager downloadManager = (DownloadManager) ContextUtils.getApplicationContext().getSystemService("download");
                try {
                    this.m = System.currentTimeMillis();
                    this.k = downloadManager.enqueue(request);
                    return Boolean.TRUE;
                } catch (IllegalArgumentException e) {
                    AbstractC1220Ua0.a("DownloadDelegate", "Download failed: " + e, new Object[0]);
                    this.l = 1000;
                    return Boolean.FALSE;
                } catch (RuntimeException e2) {
                    AbstractC1220Ua0.a("DownloadDelegate", "Failed to create target file on the external storage: " + e2, new Object[0]);
                    this.l = 1001;
                    return Boolean.FALSE;
                }
            } catch (IllegalStateException unused) {
                AbstractC1220Ua0.a("DownloadDelegate", "Cannot create download directory", new Object[0]);
                this.l = 1001;
                return Boolean.FALSE;
            }
        } catch (IllegalArgumentException unused2) {
            AbstractC1220Ua0.a("DownloadDelegate", "Cannot download non http or https scheme", new Object[0]);
            this.l = 1002;
            return Boolean.FALSE;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        C1640aI aIVar = new C1640aI();
        aIVar.b = ((Boolean) obj).booleanValue();
        aIVar.c = this.l;
        aIVar.f9423a = this.k;
        aIVar.d = this.m;
        this.j.onResult(aIVar);
    }
}
