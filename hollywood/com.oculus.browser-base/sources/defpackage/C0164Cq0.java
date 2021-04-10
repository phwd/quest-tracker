package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.oculus.browser.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.PackageManagerUtils;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: Cq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0164Cq0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f7842a = 0;
    public final Context b;
    public final PU0 c;
    public final LongSparseArray d = new LongSparseArray();
    public final LongSparseArray e = new LongSparseArray();
    public final C1322Vq0 f = new C1322Vq0();

    public C0164Cq0(Context context) {
        this.b = context;
        this.c = NU0.f8549a;
    }

    public static void a(C0164Cq0 cq0, long j) {
        Set e2 = e(cq0.c, "PendingOMADownloads");
        HashSet hashSet = (HashSet) e2;
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (C5660xq0.a(str).f11640a == j) {
                hashSet.remove(str);
                DownloadManagerService.J(cq0.c, "PendingOMADownloads", e2, false);
                return;
            }
        }
    }

    public static void b(C0164Cq0 cq0, DownloadItem downloadItem, int i) {
        Objects.requireNonNull(cq0);
        KH r = DownloadManagerService.p().r(downloadItem.c.t);
        if (r != null) {
            OfflineItem a2 = DownloadItem.a(downloadItem);
            a2.F.f8514a = "LEGACY_ANDROID_DOWNLOAD";
            if (i == 1) {
                a2.c0 = 2;
            } else if (i == 2) {
                a2.c0 = 5;
            }
            r.a(a2, null);
        }
    }

    public static String c(C5830yq0 yq0) {
        if (TextUtils.isEmpty((String) yq0.f11703a.get("objectURI"))) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri parse = Uri.parse((String) yq0.f11703a.get("objectURI"));
        for (String str : yq0.b) {
            if (!str.equalsIgnoreCase("application/vnd.oma.drm.message") && !str.equalsIgnoreCase("application/vnd.oma.drm.content") && !str.equalsIgnoreCase("application/vnd.oma.dd+xml") && !str.equalsIgnoreCase("application/vnd.oma.drm.rights+wbxml")) {
                intent.setDataAndType(parse, str);
                if (!PackageManagerUtils.c(intent, 65536).isEmpty()) {
                    return str;
                }
            }
        }
        return null;
    }

    public static long d(C5830yq0 yq0) {
        String str = (String) yq0.f11703a.get("size");
        if (str == null) {
            return 0;
        }
        try {
            return Long.parseLong(str.replace(",", ""));
        } catch (NumberFormatException e2) {
            AbstractC1220Ua0.f("OMADownloadHandler", "Cannot parse size information.", e2);
            return 0;
        }
    }

    public static Set e(PU0 pu0, String str) {
        Set set = DownloadManagerService.F;
        return new HashSet(pu0.j(str));
    }

    public static C5830yq0 g(InputStream inputStream) {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(inputStream, null);
            C5830yq0 yq0 = new C5830yq0();
            ArrayList arrayList = new ArrayList(Arrays.asList("type", "size", "objectURI", "installNotifyURI", "nextURL", "DDVersion", "name", "description", "vendor", "infoURL", "iconURI", "installParam"));
            String str = null;
            StringBuilder sb = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 0) {
                    if (!(yq0.f11703a.isEmpty() && yq0.b.isEmpty())) {
                        return null;
                    }
                } else if (eventType == 2) {
                    String name = newPullParser.getName();
                    if (!arrayList.contains(name)) {
                        continue;
                    } else if (str != null) {
                        AbstractC1220Ua0.f("OMADownloadHandler", "Nested attributes was found in the download descriptor", new Object[0]);
                        return null;
                    } else {
                        sb = new StringBuilder();
                        str = name;
                    }
                } else if (eventType == 3) {
                    if (str == null) {
                        continue;
                    } else if (!str.equals(newPullParser.getName())) {
                        AbstractC1220Ua0.f("OMADownloadHandler", "Nested attributes was found in the download descriptor", new Object[0]);
                        return null;
                    } else {
                        yq0.a(str, sb.toString().trim());
                        str = null;
                        sb = null;
                    }
                } else if (eventType == 4 && str != null) {
                    sb.append(newPullParser.getText());
                }
            }
            return yq0;
        } catch (XmlPullParserException e2) {
            AbstractC1220Ua0.f("OMADownloadHandler", "Failed to parse download descriptor.", e2);
            return null;
        } catch (IOException e3) {
            AbstractC1220Ua0.f("OMADownloadHandler", "Failed to read download descriptor.", e3);
            return null;
        }
    }

    public final void f(DownloadInfo downloadInfo, long j, int i, String str) {
        String str2;
        switch (i) {
            case 1002:
            case 1004:
            case 1005:
                str2 = "954 Loader Error \n\r";
                break;
            case 1003:
            case 1007:
            default:
                str2 = "952 Device Aborted \n\r";
                break;
            case 1006:
                str2 = "901 insufficient memory \n\r";
                break;
            case 1008:
                str2 = "903 Loss of Service \n\r";
                break;
        }
        C5830yq0 yq0 = (C5830yq0) this.e.get(j);
        if (yq0 == null) {
            C5830yq0 yq02 = new C5830yq0();
            yq02.a("installNotifyURI", str);
            if (!i(yq02, downloadInfo, j, str2)) {
                k(yq02);
                return;
            }
            return;
        }
        j(R.string.f56580_resource_name_obfuscated_RES_2131952975, yq0, downloadInfo, str2);
        this.e.remove(j);
    }

    public final void h(long j) {
        if (this.d.size() != 0) {
            this.d.remove(j);
            if (this.d.size() == 0) {
                this.b.unregisterReceiver(this);
            }
        }
    }

    public boolean i(C5830yq0 yq0, DownloadInfo downloadInfo, long j, String str) {
        if (yq0 == null || TextUtils.isEmpty((String) yq0.f11703a.get("installNotifyURI"))) {
            return false;
        }
        C0042Aq0 aq0 = new C0042Aq0(this, yq0, downloadInfo, j, str);
        Executor executor = AbstractC2032cb.f9616a;
        aq0.f();
        ((ExecutorC1463Ya) executor).execute(aq0.e);
        return true;
    }

    public final void j(int i, C5830yq0 yq0, DownloadInfo downloadInfo, String str) {
        DialogInterface$OnClickListenerC4810sq0 sq0 = new DialogInterface$OnClickListenerC4810sq0(this, yq0, downloadInfo, str);
        C2290e4 e4Var = new C2290e4(ApplicationStatus.e, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.d = a4Var.f9407a.getText(i);
        e4Var.e(R.string.f56550_resource_name_obfuscated_RES_2131952972, sq0);
        e4Var.f9828a.k = false;
        e4Var.i();
    }

    public final void k(C5830yq0 yq0) {
        if (!TextUtils.isEmpty((String) yq0.f11703a.get("nextURL"))) {
            String str = (String) yq0.f11703a.get("nextURL");
            Activity activity = ApplicationStatus.e;
            DialogInterface$OnClickListenerC4980tq0 tq0 = new DialogInterface$OnClickListenerC4980tq0(this, str, activity);
            C2246dp1 dp1 = new C2246dp1(activity);
            dp1.g(R.string.f56750_resource_name_obfuscated_RES_2131952992);
            dp1.e(R.string.f56550_resource_name_obfuscated_RES_2131952972, tq0);
            dp1.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, tq0);
            C1598a4 a4Var = dp1.f9828a;
            a4Var.f = str;
            a4Var.k = false;
            dp1.i();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            long longExtra = intent.getLongExtra("extra_download_id", -1);
            if (longExtra != -1) {
                boolean z = true;
                boolean z2 = this.e.get(longExtra) != null;
                Iterator it = ((HashSet) e(this.c, "PendingOMADownloads")).iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (C5660xq0.a((String) it.next()).f11640a == longExtra) {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z2 || z) {
                    DownloadManagerBridge.e(longExtra, new C5320vq0(this, longExtra, null));
                    return;
                }
                DownloadItem downloadItem = (DownloadItem) this.d.get(longExtra);
                if (downloadItem != null) {
                    DownloadManagerBridge.e(longExtra, new C4470qq0(downloadItem));
                    h(longExtra);
                }
            }
        }
    }
}
