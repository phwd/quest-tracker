package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItemSchedule;

/* renamed from: DH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DH {
    public boolean A = true;
    public boolean B;
    public boolean C;
    public Bitmap D;
    public int E;
    public int F;
    public boolean G;
    public OfflineItemSchedule H;

    /* renamed from: a  reason: collision with root package name */
    public String f7878a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public long j;
    public long k;
    public boolean l;
    public String m;
    public boolean n;
    public String o;
    public C0288Er0 p = C0288Er0.a();
    public long q;
    public boolean r = true;
    public boolean s;
    public boolean t;
    public OTRProfileID u;
    public boolean v;
    public int w = 0;
    public long x;
    public boolean y;
    public C0788My z;

    public static DH b(DownloadInfo downloadInfo) {
        DH dh = new DH();
        dh.f7878a = downloadInfo.f10658a;
        dh.b = downloadInfo.b;
        dh.c = downloadInfo.c;
        dh.d = downloadInfo.d;
        dh.e = downloadInfo.e;
        dh.f = downloadInfo.f;
        dh.g = downloadInfo.g;
        dh.h = downloadInfo.h;
        dh.i = downloadInfo.i;
        dh.j = downloadInfo.j;
        dh.k = downloadInfo.k;
        dh.m = downloadInfo.l;
        dh.n = downloadInfo.m;
        dh.o = downloadInfo.n;
        dh.l = downloadInfo.o;
        dh.p = downloadInfo.p;
        dh.q = downloadInfo.q;
        dh.y = downloadInfo.y;
        dh.r = downloadInfo.r;
        dh.s = downloadInfo.s;
        dh.t = downloadInfo.t;
        dh.u = downloadInfo.u;
        dh.v = downloadInfo.v;
        dh.w = downloadInfo.w;
        dh.x = downloadInfo.x;
        dh.B = downloadInfo.B;
        dh.C = downloadInfo.C;
        dh.D = downloadInfo.D;
        dh.E = downloadInfo.E;
        dh.F = downloadInfo.F;
        dh.G = downloadInfo.G;
        dh.H = downloadInfo.H;
        return dh;
    }

    public DownloadInfo a() {
        return new DownloadInfo(this, null);
    }
}
