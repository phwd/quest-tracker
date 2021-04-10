package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.chrome.browser.banners.AppBannerInProductHelpControllerProvider;
import org.chromium.chrome.browser.history.HistoryDeletionBridge;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.SelectFileDialog;

/* renamed from: Kq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0651Kq implements Runnable {
    public void run() {
        OG0 a2 = OG0.a();
        Objects.requireNonNull(a2);
        ThreadUtils.a();
        if (!a2.d) {
            Object obj = ThreadUtils.f10596a;
            boolean e = DataReductionProxySettings.d().e();
            PU0 pu0 = NU0.f8549a;
            pu0.m("BANDWIDTH_REDUCTION_PROXY_ENABLED", e);
            pu0.f8694a.a("data_reduction_site_breakdown_allowed_date");
            if (!AbstractC3983nz.f10523a.contains("data_reduction_site_breakdown_allowed_date")) {
                long b = DataReductionProxySettings.d().b() + 2592000000L;
                long currentTimeMillis = System.currentTimeMillis();
                if (b <= currentTimeMillis) {
                    b = currentTimeMillis;
                }
                pu0.o("data_reduction_site_breakdown_allowed_date", b);
            }
            C2760gq a3 = C2760gq.a();
            Objects.requireNonNull(a3);
            if (!a3.d) {
                a3.d = true;
                Objects.requireNonNull(a3.c);
                ApplicationStatus.h.b(new C2589fq(a3));
            }
            long h = pu0.h("com.google.android.apps.chrome.ChromeMobileApplication.BOOT_TIMESTAMP", 0);
            long currentTimeMillis2 = System.currentTimeMillis() - SystemClock.uptimeMillis();
            if (Math.abs(currentTimeMillis2 - h) > 1000) {
                N.M4n4n4_y();
                pu0.o("com.google.android.apps.chrome.ChromeMobileApplication.BOOT_TIMESTAMP", currentTimeMillis2);
            }
            AppHooks.get().a();
            AppBannerInProductHelpControllerProvider.b = new C3011iG0();
            if (C0835Nr.F == null) {
                C0835Nr nr = new C0835Nr();
                C0835Nr.F = nr;
                ApplicationLifetime.f10600a.b(nr);
            }
            Clipboard.getInstance().e = new C4654rv();
            BinderC5899zD.c = new C4378qG0();
            SelectFileDialog.d = new CG0(a2);
            AbstractC5853yy.f11714a = new C4548rG0();
            Object obj2 = AbstractC3378kR0.f10278a;
            synchronized (AbstractC3378kR0.b) {
                if (AbstractC3378kR0.d == null) {
                    AbstractC3378kR0.d = new C3207jR0(null);
                    TemplateUrlService a4 = AbstractC0444Hf1.a();
                    a4.i(AbstractC3378kR0.d);
                    a4.b.b(AbstractC3378kR0.d);
                    if (!a4.g()) {
                        a4.h();
                    }
                    AbstractC3378kR0.a().a();
                    LocaleManager.getInstance().d();
                }
            }
            if (HistoryDeletionBridge.f10679a == null) {
                HistoryDeletionBridge.f10679a = new HistoryDeletionBridge();
            }
            HistoryDeletionBridge historyDeletionBridge = HistoryDeletionBridge.f10679a;
            historyDeletionBridge.b.b(new C0484Hy(new C4719sG0()));
            a2.d = true;
        }
    }
}
