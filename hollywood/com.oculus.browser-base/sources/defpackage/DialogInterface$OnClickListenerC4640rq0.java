package defpackage;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerBridge;

/* renamed from: rq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC4640rq0 implements DialogInterface.OnClickListener {
    public final C0164Cq0 F;
    public final long G;
    public final DownloadInfo H;
    public final C5830yq0 I;

    public DialogInterface$OnClickListenerC4640rq0(C0164Cq0 cq0, long j, DownloadInfo downloadInfo, C5830yq0 yq0) {
        this.F = cq0;
        this.G = j;
        this.H = downloadInfo;
        this.I = yq0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str;
        C0164Cq0 cq0 = this.F;
        long j = this.G;
        DownloadInfo downloadInfo = this.H;
        C5830yq0 yq0 = this.I;
        Objects.requireNonNull(cq0);
        if (i != -1) {
            cq0.i(yq0, downloadInfo, -1, "902 User Cancelled \n\r");
        } else if (yq0 != null) {
            Iterator it = yq0.b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = null;
                    break;
                }
                str = (String) it.next();
                if (!str.equalsIgnoreCase("application/vnd.oma.drm.message")) {
                    if (str.equalsIgnoreCase("application/vnd.oma.drm.content")) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (str == null) {
                str = C0164Cq0.c(yq0);
            }
            String str2 = (String) yq0.f11703a.get("name");
            String str3 = (String) yq0.f11703a.get("objectURI");
            if (TextUtils.isEmpty(str2)) {
                str2 = URLUtil.guessFileName(str3, null, str);
            }
            DH b = DH.b(downloadInfo);
            b.e = str2;
            b.f7878a = str3;
            b.c = str;
            b.f = (String) yq0.f11703a.get("description");
            b.j = C0164Cq0.d(yq0);
            DownloadInfo a2 = b.a();
            DownloadItem downloadItem = new DownloadItem(true, a2);
            downloadItem.d = j;
            downloadItem.f10660a.b = downloadItem.b();
            ZH zh = new ZH();
            zh.b = str2;
            zh.f9335a = str3;
            zh.d = str;
            zh.c = (String) yq0.f11703a.get("description");
            zh.e = a2.d;
            zh.f = a2.h;
            zh.g = a2.b;
            zh.h = TextUtils.isEmpty((String) yq0.f11703a.get("installNotifyURI"));
            C5150uq0 uq0 = new C5150uq0(cq0, downloadItem);
            Object obj = DownloadManagerBridge.f10661a;
            C2162dI dIVar = new C2162dI(zh, uq0);
            Executor executor = AbstractC2032cb.f9616a;
            dIVar.f();
            ((ExecutorC1463Ya) executor).execute(dIVar.e);
            cq0.e.put(j, yq0);
        }
    }
}
