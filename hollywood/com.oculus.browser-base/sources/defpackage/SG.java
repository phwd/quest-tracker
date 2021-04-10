package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: SG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SG extends PK {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ C0788My G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ TG I;

    public SG(TG tg, Intent intent, C0788My my, boolean z) {
        this.I = tg;
        this.F = intent;
        this.G = my;
        this.H = z;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public boolean N() {
        if (U70.b(this.G) && CachedFeatureFlags.isEnabled("ServiceManagerForDownload") && !"org.chromium.chrome.browser.download.DOWNLOAD_OPEN".equals(this.F.getAction())) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        boolean z;
        char c;
        DownloadItem downloadItem;
        int indexOf;
        TG tg = this.I;
        tg.d.postDelayed(tg.e, 5000);
        if ("org.chromium.chrome.browser.download.DOWNLOAD_RESUME".equals(this.F.getAction()) && U70.b(this.G)) {
            AbstractC5404wI.a(this.H ? 5 : 6);
        }
        N.MpMcd8D3(((BrowserStartupControllerImpl) AbstractC4280pk.a()).f(), U20.d(this.F, "org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", false));
        TG tg2 = this.I;
        Intent intent = this.F;
        Objects.requireNonNull(tg2);
        String action = intent.getAction();
        List list = AbstractC5404wI.f11536a;
        if (C2474f80.f9900a.f() && (indexOf = AbstractC5404wI.f11536a.indexOf(action)) != -1) {
            AbstractC3364kK0.g("Android.DownloadManager.NotificationInteraction", indexOf, AbstractC5404wI.f11536a.size());
        }
        C0788My h = TG.h(intent);
        action.hashCode();
        if (action.equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED")) {
            Context applicationContext = ContextUtils.getApplicationContext();
            if (ContentUriUtils.e(U20.n(intent, "DownloadFilePath"))) {
                tg2.j(applicationContext, intent, -1, h);
                return;
            }
            long[] longArrayExtra = intent.getLongArrayExtra("extra_click_download_ids");
            if (longArrayExtra == null || longArrayExtra.length == 0) {
                DownloadManagerService.F(applicationContext, 3);
                return;
            }
            long j = longArrayExtra[0];
            DownloadManagerBridge.e(j, new QG(tg2, applicationContext, intent, j, h));
        } else if (!action.equals("org.chromium.chrome.browser.download.DOWNLOAD_OPEN")) {
            BI b = tg2.b.b(TG.h(intent));
            if (b == null) {
                z = U20.d(intent, "org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", false);
            } else {
                z = b.c;
            }
            OTRProfileID a2 = OTRProfileID.a(U20.l(intent.getExtras(), "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
            if (z && a2 == null) {
                a2 = (OTRProfileID) N.M7eLuem4();
            }
            AI i = TG.i(h);
            Objects.requireNonNull(i);
            Objects.requireNonNull(h);
            switch (action.hashCode()) {
                case -1114842727:
                    if (action.equals("org.chromium.chrome.browser.download.DOWNLOAD_PAUSE")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -572788969:
                    if (action.equals("org.chromium.chrome.browser.download.DOWNLOAD_CANCEL")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -139491126:
                    if (action.equals("org.chromium.chrome.browser.download.DOWNLOAD_RESUME")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    i.g(h, a2);
                    break;
                case 1:
                    boolean b2 = U70.b(h);
                    int intExtra = intent.getIntExtra("org.chromium.chrome.browser.download.OfflineItemsStateAtCancel", -1);
                    if (intExtra != -1 && C2474f80.f9900a.f()) {
                        if (b2) {
                            AbstractC3364kK0.g("Android.OfflineItems.StateAtCancel.Downloads", intExtra, 4);
                        } else {
                            AbstractC3364kK0.g("Android.OfflineItems.StateAtCancel.OfflinePages", intExtra, 4);
                        }
                    }
                    AbstractC3364kK0.g("Android.DownloadManager.Cancel", 1, 3);
                    i.h(h, a2);
                    break;
                case 2:
                    if (b != null) {
                        DH dh = new DH();
                        C0788My my = b.g;
                        dh.m = my.b;
                        dh.v = U70.c(my);
                        dh.e = b.e;
                        dh.t = b.c;
                        dh.j = -1;
                        dh.z = b.g;
                        dh.B = b.h;
                        downloadItem = new DownloadItem(false, dh.a());
                    } else {
                        DH dh2 = new DH();
                        dh2.m = h.b;
                        dh2.t = z;
                        downloadItem = new DownloadItem(false, dh2.a());
                    }
                    i.j(h, downloadItem, !U20.d(intent, "org.chromium.chrome.browser.download.IS_AUTO_RESUMPTION", false));
                    break;
            }
            i.e();
        } else if (h != null) {
            C1916bt0 bt0 = new C1916bt0(1);
            bt0.f9568a = U20.d(intent, "org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", false);
            AbstractC3276jr0.a().G.k(bt0, h);
        }
    }
}
