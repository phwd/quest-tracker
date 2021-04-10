package org.chromium.chrome.browser.download;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.download.DownloadCollectionBridge;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadManagerService implements YG, AbstractC0524In0, AI, AbstractC1818bH0 {
    public static final Set F = new HashSet();
    public static DownloadManagerService G;
    public final PU0 H;
    public final HashMap I = new HashMap(4, 0.75f);

    /* renamed from: J  reason: collision with root package name */
    public final D51 f10662J;
    public final long K;
    public final Handler L;
    public final List M = new ArrayList();
    public final C1322Vq0 N = new C1322Vq0();
    public C0164Cq0 O;
    public FI P;
    public KH Q;
    public KH R;
    public long S;
    public C0646Kn0 T;
    public boolean U;
    public int V = -1;
    public boolean W;

    public DownloadManagerService(D51 d51, Handler handler, long j) {
        Context applicationContext = ContextUtils.getApplicationContext();
        PU0 pu0 = NU0.f8549a;
        this.H = pu0;
        this.f10662J = d51;
        this.K = j;
        this.L = handler;
        this.P = new FI();
        this.O = new C0164Cq0(applicationContext);
        C2842hH hHVar = new C2842hH();
        List list = DownloadCollectionBridge.f10838a;
        P21 f0 = P21.f0();
        try {
            DownloadCollectionBridge.b = hHVar;
            f0.close();
            if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                DownloadController.f10655a = this;
            }
            handler.postDelayed(new RunnableC2332eI(this), 10000);
            pu0.l("DownloadUmaEntry");
            C0164Cq0 cq0 = this.O;
            cq0.c.f8694a.a("PendingOMADownloads");
            if (AbstractC3983nz.f10523a.contains("PendingOMADownloads")) {
                Iterator it = ((HashSet) C0164Cq0.e(cq0.c, "PendingOMADownloads")).iterator();
                while (it.hasNext()) {
                    C5660xq0 a2 = C5660xq0.a((String) it.next());
                    long j2 = a2.f11640a;
                    DownloadManagerBridge.e(j2, new C5320vq0(cq0, j2, a2.b));
                }
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static void D(Context context, String str, boolean z, boolean z2, String str2, long j, String str3, String str4, int i, String str5) {
        C3699mI mIVar = new C3699mI(str, j, z, str3, str4, str5, context, i, str2, z2);
        Executor executor = AbstractC2032cb.f9616a;
        mIVar.f();
        ((ExecutorC1463Ya) executor).execute(mIVar.e);
    }

    public static void F(Context context, int i) {
        if (!DownloadUtils.e(null, null, i)) {
            Intent intent = new Intent("android.intent.action.VIEW_DOWNLOADS");
            intent.setFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                AbstractC1220Ua0.a("DownloadService", "Cannot find Downloads app", e);
            }
        }
    }

    public static void J(PU0 pu0, String str, Set set, boolean z) {
        boolean z2 = true;
        if (set.isEmpty()) {
            if (z) {
                pu0.f8694a.a(str);
                SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
                edit.remove(str);
                z2 = edit.commit();
            } else {
                pu0.l(str);
            }
        } else if (z) {
            pu0.f8694a.a(str);
            z2 = AbstractC3983nz.f10523a.edit().putStringSet(str, set).commit();
        } else {
            pu0.q(str, set);
        }
        if (!z2) {
            AbstractC1220Ua0.a("DownloadService", AbstractC2531fV.f("Failed to write DownloadInfo ", str), new Object[0]);
        }
    }

    public static boolean m(DownloadItem downloadItem, boolean z) {
        DownloadInfo downloadInfo = downloadItem.c;
        Intent s = s(downloadInfo.g, downloadItem.d, z, null, null, downloadInfo.c);
        if (s == null) {
            return false;
        }
        return C3198jN.k(s, true);
    }

    public static SharedPreferences o() {
        return ContextUtils.getApplicationContext().getSharedPreferences("DownloadRetryCount", 0);
    }

    public static void onDownloadItemCanceled(DownloadItem downloadItem, boolean z) {
        p().A(downloadItem, z ? 1007 : 1009);
    }

    public static DownloadManagerService p() {
        Object obj = ThreadUtils.f10596a;
        if (G == null) {
            G = new DownloadManagerService(new D51(), new Handler(), 1000);
        }
        return G;
    }

    public static Intent s(String str, long j, boolean z, String str2, String str3, String str4) {
        Uri uri;
        Uri uri2;
        Intent intent;
        if (j != -1) {
            C1820bI d = DownloadManagerBridge.d(j);
            if (str4 == null) {
                str4 = d.c;
            }
            if (str == null) {
                uri = d.d;
            } else {
                uri = DownloadUtils.b(str);
            }
            if (uri == null || Uri.EMPTY.equals(uri)) {
                return null;
            }
            if (str == null) {
                uri2 = uri;
            } else {
                uri2 = Uri.fromFile(new File(str));
            }
            if (z) {
                return AbstractC2395ei0.b(uri2, uri, str4, true);
            }
            return AbstractC2395ei0.a(uri, str4, str2, str3);
        } else if (!ContentUriUtils.e(str)) {
            return null;
        } else {
            Uri parse = Uri.parse(str);
            if (str4 == null) {
                Cursor query = ContextUtils.getApplicationContext().getContentResolver().query(parse, null, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() != 0) {
                            query.moveToNext();
                            str4 = query.getString(query.getColumnIndex("mime_type"));
                            query.close();
                            query.close();
                        }
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                }
                if (query == null) {
                    return null;
                }
                query.close();
                return null;
            }
            if (z) {
                intent = AbstractC2395ei0.b(parse, parse, str4, true);
            } else {
                intent = AbstractC2395ei0.a(parse, str4, str2, str3);
            }
            return intent;
        }
        throw th;
    }

    public static boolean w(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).isActiveNetworkMetered();
    }

    public void A(DownloadItem downloadItem, int i) {
        String str;
        String str2 = downloadItem.c.e;
        Context applicationContext = ContextUtils.getApplicationContext();
        switch (i) {
            case 1001:
                str = applicationContext.getString(R.string.f50960_resource_name_obfuscated_RES_2131952413, str2);
                break;
            case 1002:
            case 1005:
                str = applicationContext.getString(R.string.f50990_resource_name_obfuscated_RES_2131952416, str2);
                break;
            case 1003:
            default:
                str = applicationContext.getString(R.string.f51010_resource_name_obfuscated_RES_2131952418, str2);
                break;
            case 1004:
            case 1008:
                str = applicationContext.getString(R.string.f50980_resource_name_obfuscated_RES_2131952415, str2);
                break;
            case 1006:
                str = applicationContext.getString(R.string.f50970_resource_name_obfuscated_RES_2131952414, str2);
                break;
            case 1007:
                str = applicationContext.getString(R.string.f51000_resource_name_obfuscated_RES_2131952417, str2);
                break;
            case 1009:
                str = applicationContext.getString(R.string.f50950_resource_name_obfuscated_RES_2131952412, str2);
                break;
        }
        if (this.P.e() != null) {
            FI fi = this.P;
            boolean z = i == 1009;
            boolean z2 = downloadItem.c.t;
            Objects.requireNonNull(fi);
            DownloadManagerService p = p();
            KH kh = z2 ? p.R : p.Q;
            if (!((kh == null || kh.P == null) ? false : true) && fi.e() != null) {
                C4076oY0 c = C4076oY0.c(str, fi, 1, 10);
                c.i = false;
                c.j = 7000;
                if (z) {
                    c.d = ContextUtils.getApplicationContext().getString(R.string.f56690_resource_name_obfuscated_RES_2131952986);
                    c.e = null;
                }
                fi.e().l(c);
                return;
            }
            return;
        }
        C1184Ti1.b(ContextUtils.getApplicationContext(), str, 0).b.show();
    }

    public void B(DownloadInfo downloadInfo, boolean z) {
        int i;
        C4383qI qIVar;
        NetworkInfo activeNetworkInfo;
        DownloadItem downloadItem = new DownloadItem(false, downloadInfo);
        if (!downloadInfo.r) {
            i = 2;
        } else {
            if (z) {
                String b = downloadItem.b();
                if (!CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative")) {
                    if (this.M.isEmpty()) {
                        this.T = new C0646Kn0(this, new C5582xL0());
                    }
                    if (!this.M.contains(b)) {
                        this.M.add(b);
                    }
                }
            }
            i = 4;
        }
        L(downloadItem, i);
        K(downloadItem);
        if (CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative") || (qIVar = (C4383qI) this.I.get(downloadItem.b())) == null || !z || (activeNetworkInfo = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        if (qIVar.b || !w(ContextUtils.getApplicationContext())) {
            G(downloadItem.b());
            this.L.postDelayed(new RunnableC2503fI(this, downloadItem), this.K);
        }
    }

    public void C(DownloadInfo downloadInfo) {
        DownloadItem downloadItem = new DownloadItem(false, downloadInfo);
        if (downloadInfo.s) {
            G(downloadItem.b());
        }
        L(downloadItem, 0);
        K(downloadItem);
        I();
    }

    public void E(DownloadInfo downloadInfo, long j, int i) {
        D(ContextUtils.getApplicationContext(), downloadInfo.g, N.M4t0L845(downloadInfo.c), downloadInfo.t, downloadInfo.l, j, downloadInfo.i, downloadInfo.h, i, downloadInfo.c);
    }

    public final void G(String str) {
        C0646Kn0 kn0;
        if (!CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative") && !this.M.isEmpty()) {
            this.M.remove(str);
            if (this.M.isEmpty() && (kn0 = this.T) != null) {
                kn0.d();
                this.T = null;
            }
        }
    }

    public final void H(String str) {
        this.I.remove(str);
        G(str);
        F.remove(str);
    }

    public void I() {
        if (!this.U) {
            this.U = true;
            ArrayList arrayList = new ArrayList();
            for (C4383qI qIVar : this.I.values()) {
                if (qIVar.f) {
                    arrayList.add(qIVar);
                }
            }
            if (arrayList.isEmpty()) {
                this.U = false;
                return;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                N((C4383qI) arrayList.get(i));
            }
            this.L.postDelayed(new RunnableC2674gI(this), this.K);
        }
    }

    public final void K(DownloadItem downloadItem) {
        KH r = r(downloadItem.c.t);
        if (r != null && !r.F) {
            OfflineItem a2 = DownloadItem.a(downloadItem);
            if (r.l(a2)) {
                if (downloadItem.c.w == 1) {
                    if (r.f8356J.containsKey(downloadItem.f10660a)) {
                        DownloadManagerService p = p();
                        FH fh = new FH(r, downloadItem);
                        Objects.requireNonNull(p);
                        C4041oI oIVar = new C4041oI(p, downloadItem, fh);
                        try {
                            Executor executor = AbstractC2032cb.f9616a;
                            oIVar.f();
                            ((ExecutorC1463Ya) executor).execute(oIVar.e);
                        } catch (RejectedExecutionException unused) {
                            AbstractC1220Ua0.a("DownloadService", "Thread limit reached, reschedule notification update later.", new Object[0]);
                        }
                    }
                } else if (a2.c0 == 3) {
                    r.d(a2.F);
                } else {
                    r.f(a2, false, false, false);
                }
            }
        }
    }

    public final void L(DownloadItem downloadItem, int i) {
        boolean z = i == 1 && N.M4t0L845(downloadItem.c.c);
        String b = downloadItem.b();
        C4383qI qIVar = (C4383qI) this.I.get(b);
        DownloadInfo downloadInfo = downloadItem.c;
        long j = downloadInfo.j;
        if (qIVar != null) {
            qIVar.d = i;
            qIVar.c = downloadItem;
            qIVar.f = true;
            qIVar.e = this.M.contains(b);
            qIVar.g = z;
            if (i != 0) {
                if (i == 1 || i == 2 || i == 3) {
                    n(b, true);
                    n(b, false);
                    N(qIVar);
                    F.remove(b);
                } else if (i == 4) {
                    N(qIVar);
                }
            } else if (downloadItem.c.s) {
                N(qIVar);
            }
        } else if (!downloadInfo.s) {
            C4383qI qIVar2 = new C4383qI(System.currentTimeMillis(), w(ContextUtils.getApplicationContext()), downloadItem, i);
            qIVar2.f = true;
            qIVar2.g = z;
            this.I.put(b, qIVar2);
            F.add(b);
            if (i != 0) {
                N(qIVar2);
            }
        }
    }

    public void M(String str, boolean z) {
        ProfileKey profileKey;
        if (!TextUtils.isEmpty(str)) {
            long t = t();
            if (!z) {
                profileKey = ProfileKey.a();
            } else {
                profileKey = Profile.b().c().d();
            }
            N.M2cL0nU9(t, this, str, profileKey);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void N(defpackage.C4383qI r9) {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.DownloadManagerService.N(qI):void");
    }

    @Override // defpackage.AbstractC0524In0
    public void a(int i) {
        C0646Kn0 kn0;
        if (!CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative") && !this.M.isEmpty() && i != 6) {
            boolean w = w(ContextUtils.getApplicationContext());
            Iterator it = new ArrayList(this.M).iterator();
            while (it.hasNext()) {
                C4383qI qIVar = (C4383qI) this.I.get((String) it.next());
                if (qIVar != null && (qIVar.b || !w)) {
                    DownloadItem downloadItem = qIVar.c;
                    G(downloadItem.b());
                    this.L.postDelayed(new RunnableC2503fI(this, downloadItem), this.K);
                }
            }
            if (this.M.isEmpty() && (kn0 = this.T) != null) {
                kn0.d();
                this.T = null;
            }
        }
    }

    public final void addDownloadItemToList(List list, DownloadItem downloadItem) {
        list.add(downloadItem);
    }

    @Override // defpackage.AbstractC0524In0
    public void b(long j, int i) {
    }

    @Override // defpackage.AbstractC0524In0
    public void c(int i) {
    }

    public final List createDownloadItemList() {
        return new ArrayList();
    }

    @Override // defpackage.AbstractC0524In0
    public void d(long[] jArr) {
    }

    @Override // defpackage.AI
    public void e() {
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
        ProfileManager.f10754a.c(this);
        N.MFfdOo0Y(this.S, this, profile);
    }

    @Override // defpackage.AI
    public void g(C0788My my, OTRProfileID oTRProfileID) {
        N.MmztvsiA(t(), this, my.b, Z00.d(oTRProfileID));
        C4383qI qIVar = (C4383qI) this.I.get(my.b);
        if (qIVar != null) {
            int i = qIVar.d;
            if (i == 4 || i == 0) {
                DH b = DH.b(qIVar.c.c);
                b.s = true;
                b.j = -1;
                C(b.a());
            }
        }
    }

    @Override // defpackage.AI
    public void h(C0788My my, OTRProfileID oTRProfileID) {
        N.MV30ev0v(t(), this, my.b, Z00.d(oTRProfileID));
        C4383qI qIVar = (C4383qI) this.I.get(my.b);
        if (qIVar != null) {
            y(DH.b(qIVar.c.c).a());
            H(my.b);
            return;
        }
        D51 d51 = this.f10662J;
        d51.k(my);
        d51.b().e(my);
        KH r = r(oTRProfileID != null);
        if (r != null && !r.F) {
            r.d(my);
        }
    }

    public final void handleOMADownload(DownloadItem downloadItem, long j) {
        C0164Cq0 cq0 = this.O;
        DownloadInfo downloadInfo = downloadItem.c;
        Objects.requireNonNull(cq0);
        C6000zq0 zq0 = new C6000zq0(cq0, downloadInfo, j);
        Executor executor = AbstractC2032cb.f9616a;
        zq0.f();
        ((ExecutorC1463Ya) executor).execute(zq0.e);
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
    }

    @Override // defpackage.AI
    public void j(C0788My my, DownloadItem downloadItem, boolean z) {
        C4383qI qIVar = (C4383qI) this.I.get(downloadItem.b());
        if (qIVar == null || qIVar.d != 0 || qIVar.c.c.s) {
            AbstractC5404wI.a(z ? 2 : 4);
            if (qIVar == null) {
                Set set = F;
                if (!set.contains(downloadItem.b())) {
                    set.add(downloadItem.b());
                    AbstractC5404wI.a(1);
                }
                L(downloadItem, 0);
                qIVar = (C4383qI) this.I.get(downloadItem.b());
            }
            if (z) {
                if (!qIVar.b) {
                    qIVar.b = w(ContextUtils.getApplicationContext());
                }
                String b = downloadItem.b();
                v(q(b, true, false));
                v(q(b, true, true));
                n(downloadItem.b(), true);
            } else {
                int i = o().getInt(downloadItem.b(), 0);
                if (this.V < 0) {
                    this.V = N.M3NaDnJv();
                }
                if (i >= this.V) {
                    G(downloadItem.b());
                    B(downloadItem.c, false);
                    return;
                }
                String b2 = downloadItem.b();
                v(q(b2, false, false));
                v(q(b2, false, true));
            }
            if (ProfileManager.b || !downloadItem.c.t) {
                N.MieiRHrs(t(), this, downloadItem.b(), Z00.d(downloadItem.c.u), z);
            }
        }
    }

    @Override // defpackage.AbstractC0524In0
    public void k(long j) {
    }

    @Override // defpackage.AbstractC0524In0
    public void l(long j) {
    }

    public final void n(String str, boolean z) {
        SharedPreferences o = o();
        String q = q(str, !z, false);
        int min = Math.min(o.getInt(q, 0), 200);
        SharedPreferences.Editor edit = o.edit();
        edit.remove(q);
        if (z) {
            AbstractC3100ip1.f10165a.d("MobileDownload.ResumptionsCount.Automatic", min);
        } else {
            AbstractC3100ip1.f10165a.d("MobileDownload.ResumptionsCount.Manual", min);
            String q2 = q(str, false, true);
            AbstractC3100ip1.f10165a.d("MobileDownload.ResumptionsCount.Total", Math.min(o.getInt(q2, 0), 500));
            edit.remove(q2);
        }
        edit.apply();
    }

    public final void onAllDownloadsRetrieved(List list, ProfileKey profileKey) {
        boolean z = profileKey.f10753a;
        Iterator it = this.N.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC4212pI) uq0.next()).c(list, z);
        }
        PrefService a2 = Wr1.a(Profile.b());
        if (N.MzIXnlkD(a2.f10883a, "download.show_missing_sd_card_error_android")) {
            C4721sH sHVar = AbstractC4550rH.f11194a;
            sHVar.a(new C3186jI(this, sHVar, list, a2));
        }
    }

    public final void onDownloadItemCreated(DownloadItem downloadItem) {
        Iterator it = this.N.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4212pI) uq0.next()).b(downloadItem);
            } else {
                return;
            }
        }
    }

    public final void onDownloadItemRemoved(String str, boolean z) {
        KH kh = z ? this.R : this.Q;
        if (kh != null) {
            C0788My a2 = U70.a(false, str);
            if (!kh.F) {
                kh.d(a2);
            }
        }
        Iterator it = this.N.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4212pI) uq0.next()).e(str, z);
            } else {
                return;
            }
        }
    }

    public final void onDownloadItemUpdated(DownloadItem downloadItem) {
        Iterator it = this.N.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4212pI) uq0.next()).d(downloadItem);
            } else {
                return;
            }
        }
    }

    public void onResumptionFailed(String str) {
        D51 d51 = this.f10662J;
        DH dh = new DH();
        dh.m = str;
        dh.F = 1;
        d51.e(dh.a());
        this.I.remove(str);
        G(str);
        F.remove(str);
        AbstractC5404wI.a(3);
    }

    public final void openDownloadItem(DownloadItem downloadItem, int i) {
        DownloadInfo downloadInfo = downloadItem.c;
        if (!DownloadUtils.d(downloadInfo.g, downloadInfo.c, downloadInfo.l, downloadInfo.t, downloadInfo.i, downloadInfo.h, i)) {
            F(ContextUtils.getApplicationContext(), i);
        }
    }

    public final String q(String str, boolean z, boolean z2) {
        if (z2) {
            return AbstractC2531fV.f(str, ".Total");
        }
        return z ? AbstractC2531fV.f(str, ".Manual") : str;
    }

    public KH r(boolean z) {
        return z ? this.R : this.Q;
    }

    public final long t() {
        if (this.S == 0) {
            boolean z = ProfileManager.b;
            this.S = N.MeJ$lv4P(this, z);
            if (!z) {
                ProfileManager.f10754a.b(this);
            }
        }
        return this.S;
    }

    public final void u(DownloadItem downloadItem) {
        if (MimeUtils.isOMADownloadDescription(downloadItem.c.c)) {
            C0164Cq0 cq0 = this.O;
            DownloadInfo downloadInfo = downloadItem.c;
            long j = downloadItem.d;
            Objects.requireNonNull(cq0);
            C6000zq0 zq0 = new C6000zq0(cq0, downloadInfo, j);
            Executor executor = AbstractC2032cb.f9616a;
            zq0.f();
            ((ExecutorC1463Ya) executor).execute(zq0.e);
            return;
        }
        E(downloadItem.c, downloadItem.d, 7);
    }

    public final void v(String str) {
        SharedPreferences o = o();
        int i = o.getInt(str, 0);
        SharedPreferences.Editor edit = o.edit();
        edit.putInt(str, i + 1);
        edit.apply();
    }

    public boolean x(String str) {
        return N.M4t0L845(str);
    }

    public void y(DownloadInfo downloadInfo) {
        DH b = DH.b(downloadInfo);
        b.w = 2;
        DownloadItem downloadItem = new DownloadItem(false, b.a());
        G(downloadItem.b());
        L(new DownloadItem(false, downloadInfo), 3);
        K(downloadItem);
    }

    public void z(DownloadItem downloadItem, C1640aI aIVar) {
        downloadItem.e = aIVar.d;
        downloadItem.c(aIVar.f9423a);
        if (!aIVar.b) {
            A(downloadItem, aIVar.c);
        } else {
            r(downloadItem.c.t).f(null, true, false, false);
        }
    }
}
