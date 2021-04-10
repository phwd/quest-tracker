package defpackage;

import J.N;
import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.download.DownloadInfoBarController$DownloadProgressInfoBarData;
import org.chromium.chrome.browser.download.MimeUtils;
import org.chromium.chrome.browser.infobar.DownloadProgressInfoBar;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;

/* renamed from: KH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KH implements AbstractC3789mr0 {
    public final boolean F = N.M09VlOh_("UseDownloadOfflineContentProvider");
    public final boolean G;
    public final Handler H;
    public final DownloadProgressInfoBar.Client I = new JH(this, null);

    /* renamed from: J  reason: collision with root package name */
    public final LinkedHashMap f8356J = new LinkedHashMap();
    public final Set K = new HashSet();
    public final Set L = new HashSet();
    public final Map M = new HashMap();
    public int N = 0;
    public Runnable O;
    public DownloadProgressInfoBar P;
    public DownloadInfoBarController$DownloadProgressInfoBarData Q;
    public AbstractC3478l10 R = new HH(this);

    public KH(boolean z) {
        Handler handler = new Handler();
        this.H = handler;
        this.G = z;
        handler.post(new EH(this));
    }

    public static ChromeActivity h() {
        if (!ApplicationStatus.hasVisibleActivities()) {
            return null;
        }
        Activity activity = ApplicationStatus.e;
        if (activity instanceof ChromeActivity) {
            return (ChromeActivity) activity;
        }
        return null;
    }

    @Override // defpackage.AbstractC3789mr0
    public void a(OfflineItem offlineItem, UpdateDelta updateDelta) {
        if (l(offlineItem)) {
            if (updateDelta != null && !updateDelta.f10860a && offlineItem.c0 == 2) {
                return;
            }
            if (offlineItem.c0 == 3) {
                d(offlineItem.F);
            } else {
                f(offlineItem, false, false, false);
            }
        }
    }

    public final void b(Integer... numArr) {
        HashSet hashSet = new HashSet(Arrays.asList(numArr));
        ArrayList arrayList = new ArrayList();
        for (C0788My my : this.f8356J.keySet()) {
            OfflineItem offlineItem = (OfflineItem) this.f8356J.get(my);
            if (offlineItem != null) {
                Iterator it = hashSet.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((Integer) it.next()).intValue() == g(offlineItem)) {
                            arrayList.add(my);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C0788My my2 = (C0788My) it2.next();
            this.f8356J.remove(my2);
            this.M.remove(my2);
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void c(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OfflineItem offlineItem = (OfflineItem) it.next();
            if (l(offlineItem)) {
                f(offlineItem, false, false, false);
            }
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void d(C0788My my) {
        if (this.K.contains(my)) {
            this.f8356J.remove(my);
            this.M.remove(my);
            f(null, false, false, true);
        }
    }

    public void e() {
        Tab tab;
        DownloadProgressInfoBar downloadProgressInfoBar = this.P;
        if (downloadProgressInfoBar != null) {
            long j = downloadProgressInfoBar.H;
            if (j == 0) {
                tab = null;
            } else {
                tab = (Tab) N.Mv$tV_xY(j, downloadProgressInfoBar);
            }
            if (tab != null) {
                W w = C3649m10.F;
                ((C3649m10) tab.M().c(C3649m10.class)).f10392J.c(this.R);
            }
            DownloadProgressInfoBar downloadProgressInfoBar2 = this.P;
            downloadProgressInfoBar2.I.a(false);
            downloadProgressInfoBar2.j();
            this.P = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b5, code lost:
        if (r14 != 3) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c3, code lost:
        if (r10.f8213a == 1) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00e1, code lost:
        if (r30 == false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00e9, code lost:
        if (r26.f8356J.size() != 0) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00f9, code lost:
        if (r10.f8213a == 0) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0106, code lost:
        if (r10.f8213a == 1) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0108, code lost:
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x010d, code lost:
        if (r11 != false) goto L_0x010f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0115 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03c7  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0469  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0479  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x049e  */
    /* JADX WARNING: Removed duplicated region for block: B:307:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0112  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(org.chromium.components.offline_items_collection.OfflineItem r27, boolean r28, boolean r29, boolean r30) {
        /*
        // Method dump skipped, instructions count: 1278
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.KH.f(org.chromium.components.offline_items_collection.OfflineItem, boolean, boolean, boolean):void");
    }

    public final int g(OfflineItem offlineItem) {
        if (offlineItem.l0 != null) {
            return 3;
        }
        int i = offlineItem.c0;
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 5 ? -1 : 1;
        }
        return 0;
    }

    public final Tab i() {
        Tab K0;
        ChromeActivity h = h();
        if (h == null || (K0 = h.K0()) == null || K0.a() != this.G) {
            return null;
        }
        return K0;
    }

    public final IH j() {
        IH ih = new IH(null);
        for (OfflineItem offlineItem : this.f8356J.values()) {
            if (offlineItem.l0 != null) {
                ih.e++;
            } else {
                int i = offlineItem.c0;
                if (i == 0) {
                    ih.f8213a++;
                } else if (i == 1) {
                    ih.b++;
                } else if (i == 2) {
                    ih.d++;
                } else if (i != 3 && i == 5) {
                    ih.c++;
                }
            }
        }
        return ih;
    }

    public final boolean k(OfflineItem offlineItem) {
        if (!N.M6bsIDpc("DownloadProgressInfoBar", "speeding_up_message_enabled", false) || offlineItem == null || !offlineItem.L) {
            return false;
        }
        return true;
    }

    public final boolean l(OfflineItem offlineItem) {
        if (offlineItem.f10857J || offlineItem.a0 != this.G || offlineItem.K || offlineItem.i0) {
            return false;
        }
        if ((!U70.b(offlineItem.F) || !TextUtils.isEmpty(offlineItem.W)) && !MimeUtils.canAutoOpenMimeType(offlineItem.X)) {
            return true;
        }
        return false;
    }

    public final void m(DownloadInfoBarController$DownloadProgressInfoBarData downloadInfoBarController$DownloadProgressInfoBarData) {
        if (this.P == null) {
        }
    }
}
