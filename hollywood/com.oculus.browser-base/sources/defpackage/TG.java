package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: TG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TG extends AbstractC1677aZ0 {
    public final EI b = DI.f7880a;
    public final C5234vI c = AbstractC5064uI.f11404a;
    public final Handler d = new Handler();
    public final Runnable e = new RG(this);

    public static C0788My h(Intent intent) {
        if (!intent.hasExtra("org.chromium.chrome.browser.download.DownloadContentId_Id") || !intent.hasExtra("org.chromium.chrome.browser.download.DownloadContentId_Namespace")) {
            return null;
        }
        return new C0788My(U20.n(intent, "org.chromium.chrome.browser.download.DownloadContentId_Namespace"), U20.n(intent, "org.chromium.chrome.browser.download.DownloadContentId_Id"));
    }

    public static AI i(C0788My my) {
        if (!U70.b(my) || N.M09VlOh_("UseDownloadOfflineContentProvider")) {
            return AbstractC3276jr0.a();
        }
        return DownloadManagerService.p();
    }

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e  */
    @Override // defpackage.AbstractC1677aZ0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Intent r29, int r30, int r31) {
        /*
        // Method dump skipped, instructions count: 346
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.TG.e(android.content.Intent, int, int):int");
    }

    public final void j(Context context, Intent intent, long j, C0788My my) {
        String str;
        String str2;
        String n = U20.n(intent, "DownloadFilePath");
        boolean d2 = U20.d(intent, "IsSupportedMimeType", false);
        boolean d3 = U20.d(intent, "org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", false);
        Uri uri = (Uri) U20.k(intent, "android.intent.extra.ORIGINATING_URI");
        Uri uri2 = (Uri) U20.k(intent, "android.intent.extra.REFERRER");
        String str3 = my.b;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toString();
        }
        if (uri2 == null) {
            str2 = null;
        } else {
            str2 = uri2.toString();
        }
        DownloadManagerService.D(context, n, d2, d3, str3, j, str, str2, 3, null);
    }
}
