package org.chromium.chrome.browser.download;

import J.N;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadItem {

    /* renamed from: a  reason: collision with root package name */
    public final C0788My f10660a;
    public boolean b;
    public DownloadInfo c;
    public long d = -1;
    public long e;
    public long f;
    public boolean g;

    public DownloadItem(boolean z, DownloadInfo downloadInfo) {
        C0788My my = new C0788My();
        this.f10660a = my;
        this.b = z;
        this.c = downloadInfo;
        if (downloadInfo != null) {
            my.f8514a = downloadInfo.z.f8514a;
        }
        my.b = b();
    }

    public static OfflineItem a(DownloadItem downloadItem) {
        OfflineItem offlineItem = new OfflineItem();
        DownloadInfo downloadInfo = downloadItem.c;
        offlineItem.F = downloadInfo.z;
        offlineItem.W = downloadInfo.g;
        offlineItem.G = downloadInfo.e;
        offlineItem.H = downloadInfo.f;
        offlineItem.f10857J = downloadInfo.B;
        offlineItem.L = downloadInfo.C;
        offlineItem.K = false;
        offlineItem.Q = downloadInfo.k;
        offlineItem.f0 = downloadInfo.j;
        offlineItem.d0 = downloadInfo.r;
        offlineItem.Y = downloadInfo.f10658a;
        offlineItem.Z = downloadInfo.i;
        offlineItem.a0 = downloadInfo.t;
        offlineItem.b0 = OTRProfileID.serialize(downloadInfo.u);
        offlineItem.X = downloadInfo.c;
        offlineItem.g0 = downloadInfo.p;
        offlineItem.h0 = downloadInfo.q;
        offlineItem.i0 = downloadInfo.y;
        offlineItem.k0 = downloadInfo.E;
        int i = downloadInfo.F;
        offlineItem.j0 = i;
        offlineItem.M = downloadInfo.G;
        offlineItem.U = downloadInfo.x;
        offlineItem.S = downloadItem.e;
        offlineItem.T = downloadItem.f;
        offlineItem.R = downloadItem.g;
        offlineItem.N = downloadItem.c.w == 1;
        offlineItem.l0 = downloadInfo.H;
        int i2 = downloadInfo.w;
        int i3 = 6;
        if (i2 == 0) {
            if (!downloadInfo.s) {
                i3 = 0;
            }
            offlineItem.c0 = i3;
        } else if (i2 == 1) {
            offlineItem.c0 = downloadInfo.j == 0 ? 5 : 2;
        } else if (i2 == 2) {
            offlineItem.c0 = 3;
        } else if (i2 == 3) {
            int MOENIRAW = N.MOENIRAW(downloadInfo.f10658a, i);
            if (MOENIRAW == 0 || MOENIRAW == 4) {
                offlineItem.c0 = 4;
            } else if (downloadInfo.s) {
                offlineItem.c0 = 6;
            } else {
                BI b2 = DI.f7880a.b(downloadItem.f10660a);
                if (b2 != null && downloadItem.c.w == 3 && b2.f) {
                    offlineItem.c0 = 1;
                } else {
                    offlineItem.c0 = 5;
                }
            }
        }
        int a2 = AbstractC4891tH.a(downloadInfo.c);
        if (a2 == 1) {
            offlineItem.I = 0;
        } else if (a2 == 2) {
            offlineItem.I = 1;
        } else if (a2 == 3) {
            offlineItem.I = 2;
        } else if (a2 == 4) {
            offlineItem.I = 3;
        } else if (a2 != 5) {
            offlineItem.I = 5;
        } else {
            offlineItem.I = 4;
        }
        return offlineItem;
    }

    public static DownloadItem createDownloadItem(DownloadInfo downloadInfo, long j, long j2, boolean z) {
        DownloadItem downloadItem = new DownloadItem(false, downloadInfo);
        downloadItem.e = j;
        downloadItem.f = j2;
        downloadItem.g = z;
        return downloadItem;
    }

    public String b() {
        if (this.b) {
            return String.valueOf(this.d);
        }
        return this.c.l;
    }

    public void c(long j) {
        this.d = j;
        this.f10660a.b = b();
    }
}
