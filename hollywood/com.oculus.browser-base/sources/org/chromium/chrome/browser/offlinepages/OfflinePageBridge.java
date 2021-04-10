package org.chromium.chrome.browser.offlinepages;

import J.N;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflinePageBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10718a;
    public boolean b;
    public final C1322Vq0 c = new C1322Vq0();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface SavePageCallback {
        void onSavePageDone(int i, String str, long j);
    }

    public OfflinePageBridge(long j) {
        this.f10718a = j;
    }

    public static OfflinePageBridge a(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        if (profile == null) {
            return null;
        }
        return (OfflinePageBridge) N.MspGcmXb(profile.d());
    }

    public static OfflinePageBridge create(long j) {
        return new OfflinePageBridge(j);
    }

    public static ClientId createClientId(String str, String str2) {
        return new ClientId(str, str2);
    }

    public static DeletedPageInfo createDeletedPageInfo(long j, String str, String str2, String str3) {
        return new DeletedPageInfo(j, str, str2, str3);
    }

    public static LoadUrlParams createLoadUrlParams(String str, String str2, String str3) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            loadUrlParams.e = hashMap;
            loadUrlParams.f = AbstractC2531fV.g(str2, ": ", str3);
        }
        return loadUrlParams;
    }

    public static void createOfflinePageAndAddToList(List list, String str, long j, String str2, String str3, String str4, String str5, long j2, long j3, int i, long j4, String str6) {
        list.add(createOfflinePageItem(str, j, str2, str3, str4, str5, j2, j3, i, j4, str6));
    }

    public static OfflinePageItem createOfflinePageItem(String str, long j, String str2, String str3, String str4, String str5, long j2, long j3, int i, long j4, String str6) {
        return new OfflinePageItem(str, j, str2, str3, str4, str5, j2, j3, i, j4, str6);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getEncodedOriginApp(org.chromium.chrome.browser.tab.Tab r6) {
        /*
            android.content.Context r0 = org.chromium.base.ContextUtils.getApplicationContext()
            java.lang.String r6 = defpackage.T51.k(r6)
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0013
            r6 = 0
        L_0x0011:
            r0 = r2
            goto L_0x001e
        L_0x0013:
            java.lang.String[] r0 = defpackage.C0593Jr0.b(r0, r6)
            if (r0 != 0) goto L_0x001b
            r6 = r0
            goto L_0x0011
        L_0x001b:
            r5 = r0
            r0 = r6
            r6 = r5
        L_0x001e:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r3 = 0
            if (r1 != 0) goto L_0x002a
            if (r6 != 0) goto L_0x0028
            goto L_0x002a
        L_0x0028:
            r1 = r3
            goto L_0x002b
        L_0x002a:
            r1 = 1
        L_0x002b:
            if (r1 == 0) goto L_0x002e
            goto L_0x004f
        L_0x002e:
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
            int r2 = r6.length
        L_0x0034:
            if (r3 >= r2) goto L_0x003e
            r4 = r6[r3]
            r1.put(r4)
            int r3 = r3 + 1
            goto L_0x0034
        L_0x003e:
            org.json.JSONArray r6 = new org.json.JSONArray
            r6.<init>()
            org.json.JSONArray r6 = r6.put(r0)
            org.json.JSONArray r6 = r6.put(r1)
            java.lang.String r2 = r6.toString()
        L_0x004f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.offlinepages.OfflinePageBridge.getEncodedOriginApp(org.chromium.chrome.browser.tab.Tab):java.lang.String");
    }

    public void b(WebContents webContents, ClientId clientId, SavePageCallback savePageCallback) {
        C0593Jr0 jr0;
        ChromeActivity J0 = ChromeActivity.J0(webContents);
        if (J0 == null || J0.K0() == null) {
            jr0 = new C0593Jr0();
        } else {
            jr0 = new C0593Jr0(ContextUtils.getApplicationContext(), J0.K0());
        }
        N.MD7l7nn$(this.f10718a, this, savePageCallback, webContents, clientId.f10716a, clientId.b, jr0.a());
    }

    public void offlinePageAdded(OfflinePageItem offlinePageItem) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C1364Wh1) ((AbstractC0471Hr0) uq0.next())).b(false);
            } else {
                return;
            }
        }
    }

    public void offlinePageBridgeDestroyed() {
        Object obj = ThreadUtils.f10596a;
        this.b = false;
        this.f10718a = 0;
        this.c.clear();
    }

    public void offlinePageDeleted(DeletedPageInfo deletedPageInfo) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                C1364Wh1 wh1 = (C1364Wh1) ((AbstractC0471Hr0) uq0.next());
                Iterator it2 = ((ArrayList) wh1.a()).iterator();
                while (it2.hasNext()) {
                    AbstractC2422er0 er0 = (AbstractC2422er0) it2.next();
                    Objects.requireNonNull(er0);
                    Long l = ((C0815Nh1) er0).f;
                    if (l != null && l.longValue() == deletedPageInfo.f10717a) {
                        wh1.c(er0, null);
                    }
                }
            } else {
                return;
            }
        }
    }

    public void offlinePageModelLoaded() {
        this.b = true;
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C1364Wh1) ((AbstractC0471Hr0) uq0.next())).b(false);
            } else {
                return;
            }
        }
    }
}
