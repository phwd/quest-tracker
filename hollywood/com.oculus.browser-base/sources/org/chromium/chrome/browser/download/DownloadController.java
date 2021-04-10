package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.download.DownloadCollectionBridge;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadController {

    /* renamed from: a  reason: collision with root package name */
    public static YG f10655a;

    public static void a(DownloadInfo downloadInfo) {
        DownloadManagerService p = DownloadManagerService.p();
        DownloadItem downloadItem = new DownloadItem(true, downloadInfo);
        Objects.requireNonNull(p);
        ZH zh = new ZH();
        DownloadInfo downloadInfo2 = downloadItem.c;
        zh.f9335a = downloadInfo2.f10658a;
        zh.b = downloadInfo2.e;
        zh.c = downloadInfo2.f;
        zh.d = downloadInfo2.c;
        zh.e = downloadInfo2.d;
        zh.f = downloadInfo2.h;
        zh.g = downloadInfo2.b;
        zh.h = true;
        C2845hI hIVar = new C2845hI(p, downloadItem);
        Object obj = DownloadManagerBridge.f10661a;
        C2162dI dIVar = new C2162dI(zh, hIVar);
        Executor executor = AbstractC2032cb.f9616a;
        dIVar.f();
        ((ExecutorC1463Ya) executor).execute(dIVar.e);
    }

    public static Oy1 b() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext instanceof Oy1) {
            return (Oy1) applicationContext;
        }
        if (applicationContext instanceof ContextWrapper) {
            return (Oy1) ((ContextWrapper) applicationContext).getBaseContext();
        }
        return null;
    }

    public static void c(Callback callback) {
        Activity activity = ApplicationStatus.e;
        WG wg = new WG(callback);
        String str = "android.permission.WRITE_EXTERNAL_STORAGE";
        if (activity instanceof ChromeActivity) {
            C2971i3 i3Var = ((ChromeActivity) activity).b0;
            if (i3Var == null) {
                i3Var = null;
            }
            if (i3Var == null) {
                callback.onResult(Pair.create(Boolean.FALSE, null));
            } else if (i3Var.hasPermission(str)) {
                callback.onResult(Pair.create(Boolean.TRUE, null));
            } else if (!i3Var.canRequestPermission(str)) {
                Boolean bool = Boolean.FALSE;
                if (i3Var.E(str)) {
                    str = null;
                }
                callback.onResult(Pair.create(bool, str));
            } else {
                AbstractC4005o6.b(activity, R.string.f55030_resource_name_obfuscated_RES_2131952820, new XG(i3Var, wg), new RunnableC0884Ol((AbstractC0823Nl) callback, Pair.create(Boolean.FALSE, null)));
            }
        } else {
            Oy1 b = b();
            if (b == null || !b.i().canRequestPermission(str)) {
                callback.onResult(Pair.create(Boolean.FALSE, null));
            } else {
                b.i().i(new String[]{str}, wg);
            }
        }
    }

    public static boolean closeTabIfBlank(Tab tab) {
        if (tab == null) {
            return true;
        }
        WebContents l = tab.l();
        if (!(l == null || l.f().x())) {
            return false;
        }
        AbstractC0124Ca1 X = ((TabImpl) tab).X();
        if (X == null) {
            return true;
        }
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) X;
        if (ea1.l(tab.a()).getCount() == 1) {
            return false;
        }
        ea1.h(tab);
        return true;
    }

    public static void enqueueAndroidDownloadManagerRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        DH dh = new DH();
        dh.f7878a = str;
        dh.b = str2;
        dh.e = str3;
        dh.c = str4;
        dh.d = str5;
        dh.h = str6;
        dh.l = true;
        a(dh.a());
    }

    public static boolean hasFileAccess() {
        List list = DownloadCollectionBridge.f10838a;
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        Activity activity = ApplicationStatus.e;
        WindowAndroid windowAndroid = null;
        if (activity instanceof ChromeActivity) {
            windowAndroid = ((ChromeActivity) activity).b0;
        } else {
            Oy1 b = b();
            if (b != null) {
                windowAndroid = b.i();
            }
        }
        if (windowAndroid != null || BuildInfo.a()) {
            return windowAndroid.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        return false;
    }

    public static void onDownloadCancelled(DownloadInfo downloadInfo) {
        YG yg = f10655a;
        if (yg != null) {
            ((DownloadManagerService) yg).y(downloadInfo);
        }
    }

    public static void onDownloadCompleted(DownloadInfo downloadInfo) {
        int i;
        String str = downloadInfo.g;
        if (str != null && !str.isEmpty()) {
            AbstractC4550rH.f11194a.a(new C4553rI(str));
        }
        String str2 = downloadInfo.g;
        String str3 = downloadInfo.c;
        if (!TextUtils.isEmpty(str2) && str3 != null && str3.startsWith("image/") && Build.VERSION.SDK_INT < 29) {
            AbstractC4550rH.f11194a.a(new C1485Yh0(str2));
        }
        YG yg = f10655a;
        if (yg != null) {
            DownloadManagerService downloadManagerService = (DownloadManagerService) yg;
            Objects.requireNonNull(downloadManagerService);
            String str4 = downloadInfo.c;
            if (downloadInfo.j == 0) {
                i = 2;
            } else {
                str4 = MimeUtils.remapGenericMimeType(str4, downloadInfo.i, downloadInfo.e);
                i = 1;
            }
            DH b = DH.b(downloadInfo);
            b.c = str4;
            DownloadItem downloadItem = new DownloadItem(false, b.a());
            downloadItem.c(DownloadManagerBridge.b(downloadInfo.l));
            downloadManagerService.L(downloadItem, i);
            downloadManagerService.K(downloadItem);
        }
    }

    public static void onDownloadInterrupted(DownloadInfo downloadInfo, boolean z) {
        YG yg = f10655a;
        if (yg != null) {
            ((DownloadManagerService) yg).B(downloadInfo, z);
        }
    }

    public static void onDownloadStarted() {
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f() && !N.M09VlOh_("DownloadProgressInfoBar")) {
            Context applicationContext = ContextUtils.getApplicationContext();
            C1184Ti1.b(applicationContext, applicationContext.getResources().getText(R.string.f51710_resource_name_obfuscated_RES_2131952488), 0).b.show();
        }
    }

    public static void onDownloadUpdated(DownloadInfo downloadInfo) {
        YG yg = f10655a;
        if (yg != null) {
            ((DownloadManagerService) yg).C(downloadInfo);
        }
    }

    public static void requestFileAccess(long j) {
        c(new UG(j));
    }
}
