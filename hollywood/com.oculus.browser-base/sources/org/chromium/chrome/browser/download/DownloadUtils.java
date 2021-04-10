package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.PathUtils;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.DeviceFormFactor;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadUtils {
    public static void a(Context context, Tab tab) {
        C0593Jr0 jr0 = new C0593Jr0(context, tab);
        if (tab.p()) {
            OfflinePageBridge a2 = OfflinePageBridge.a(Profile.a(tab.l()));
            N.MNR_O1IV(a2.f10718a, a2, tab.l(), "async_loading", tab.s(), 1, jr0.a());
        } else {
            N.MgaTXnFG(tab, jr0.a());
            AbstractC3364kK0.g("OfflinePages.SavePage.PercentLoaded", Math.round(tab.H() * 100.0f), 101);
        }
        Um1.a(Profile.a(tab.l())).notifyEvent("download_page_started");
    }

    public static Uri b(String str) {
        File file;
        if (ContentUriUtils.e(str)) {
            return Uri.parse(str);
        }
        boolean b = C4721sH.b(str);
        if (!N.M09VlOh_("DownloadFileProvider") || !b) {
            return AbstractC3375kQ.d(new File(str));
        }
        String[] strArr = DownloadFileProvider.f10657J;
        if (ContentUriUtils.e(str)) {
            return Uri.parse(str);
        }
        if (TextUtils.isEmpty(str)) {
            return Uri.EMPTY;
        }
        String downloadsDirectory = PathUtils.getDownloadsDirectory();
        if (TextUtils.isEmpty(downloadsDirectory)) {
            file = null;
        } else {
            File file2 = new File(downloadsDirectory);
            if (!file2.exists()) {
                try {
                    file2.mkdirs();
                } catch (SecurityException e) {
                    AbstractC1220Ua0.a("DownloadDirectory", "Exception when creating download directory.", e);
                }
            }
            file = file2;
        }
        if (str.indexOf(file.getAbsolutePath()) == 0 && str.length() > file.getAbsolutePath().length()) {
            return DownloadFileProvider.d("download", str.substring(file.getAbsolutePath().length() + 1));
        }
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT > 29) {
            for (String str2 : PathUtils.getExternalDownloadVolumesNames()) {
                arrayList.add(new File(str2));
            }
        } else {
            String[] allPrivateDownloadsDirectories = PathUtils.getAllPrivateDownloadsDirectories();
            for (int i = 1; i < allPrivateDownloadsDirectories.length; i++) {
                arrayList.add(new File(allPrivateDownloadsDirectories[i]));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file3 = (File) it.next();
            if (file3 != null && str.startsWith(file3.getAbsolutePath())) {
                return DownloadFileProvider.d("download_external", str.substring(file3.getAbsolutePath().length() + 1));
            }
        }
        return Uri.EMPTY;
    }

    public static boolean c(Tab tab) {
        if (tab == null || tab.a() || !N.MXyz2Okt(tab.s())) {
            return false;
        }
        if (tab.p()) {
            OfflinePageBridge a2 = OfflinePageBridge.a(Profile.a(tab.l()));
            return N.Mvkx0jqI(a2.f10718a, a2, tab.l());
        } else if (AbstractC2254ds0.f(tab)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean d(String str, String str2, String str3, boolean z, String str4, String str5, int i) {
        Uri uri;
        AbstractC4724sI.a(i, str2);
        Context applicationContext = ContextUtils.getApplicationContext();
        DownloadManagerService p = DownloadManagerService.p();
        if (p.x(str2)) {
            Uri b = b(str);
            S20.y(AbstractC2395ei0.b(!ContentUriUtils.e(str) ? Uri.fromFile(new File(str)) : b, b, Intent.normalizeMimeType(str2), true), null);
            p.M(str3, z);
            return true;
        }
        try {
            if (ContentUriUtils.e(str)) {
                uri = Uri.parse(str);
            } else {
                uri = b(str);
            }
            applicationContext.startActivity(AbstractC2395ei0.a(uri, str2, str4, str5));
            p.M(str3, z);
            return true;
        } catch (Exception e) {
            AbstractC1220Ua0.a("download", "Cannot start activity to open file", e);
            if ("application/zip".equals(str2)) {
                try {
                    if (applicationContext.getPackageManager().getPackageInfo("com.android.documentsui", 1) != null) {
                        Intent intent = new Intent("android.intent.action.VIEW_DOWNLOADS");
                        intent.addFlags(268435456);
                        intent.setPackage("com.android.documentsui");
                        applicationContext.startActivity(intent);
                        return true;
                    }
                } catch (Exception e2) {
                    AbstractC1220Ua0.a("download", "Cannot find files app for openning zip files", e2);
                }
            }
            if (i != 8) {
                C1184Ti1.b(applicationContext, applicationContext.getString(R.string.f50930_resource_name_obfuscated_RES_2131952410), 0).b.show();
            }
            return false;
        }
    }

    public static boolean e(Activity activity, Tab tab, int i) {
        return showDownloadManager(activity, tab, i, false);
    }

    public static String getUriStringForPath(String str) {
        if (ContentUriUtils.e(str)) {
            return str;
        }
        Uri b = b(str);
        return b != null ? b.toString() : new String();
    }

    public static void openDownload(String str, String str2, String str3, boolean z, String str4, String str5, int i) {
        if (!d(str, str2, str3, z, str4, str5, i)) {
            e(null, null, i);
        }
    }

    public static boolean showDownloadManager(Activity activity, Tab tab, int i, boolean z) {
        boolean z2;
        Tab tab2;
        Profile profile;
        if (activity == null) {
            activity = ApplicationStatus.e;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        if (tab != null || !(activity instanceof AbstractActivityC2601fu)) {
            tab2 = tab;
            z2 = DeviceFormFactor.a(activity != null ? activity : applicationContext);
        } else {
            AbstractActivityC2601fu fuVar = (AbstractActivityC2601fu) activity;
            tab2 = fuVar.K0();
            z2 = fuVar.h0;
        }
        if (z2) {
            LoadUrlParams loadUrlParams = new LoadUrlParams("chrome-native://downloads/", 0);
            if (tab2 == null || !tab2.isInitialized()) {
                new B61(false).b(loadUrlParams, 2, null);
            } else {
                tab2.c(loadUrlParams);
                Intent a2 = AbstractC0409Gr.a(tab2.getId());
                a2.addFlags(268435456);
                U20.q(applicationContext, a2);
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(applicationContext, LG.class);
            intent.putExtra("org.chromium.chrome.browser.download.SHOW_PREFETCHED_CONTENT", z);
            if (tab2 != null) {
                intent.putExtra("org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", tab2.a());
                intent.putExtra("org.chromium.chrome.browser.download.OTR_PROFILE_ID", OTRProfileID.serialize(Profile.a(tab2.l()).f10752a));
            }
            if (activity == null) {
                intent.addFlags(268435456);
                applicationContext.startActivity(intent);
            } else {
                intent.addFlags(671088640);
                intent.putExtra("org.chromium.chrome.browser.parent_component", activity.getComponentName());
                activity.startActivity(intent);
            }
        }
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            if (tab2 == null) {
                profile = Profile.b();
            } else {
                profile = Profile.a(tab2.l());
            }
            Um1.a(profile).notifyEvent("download_home_opened");
        }
        AbstractC3364kK0.g("Android.DownloadPage.OpenSource", i, 12);
        return true;
    }
}
