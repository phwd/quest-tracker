package org.chromium.chrome.browser.download;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItemSchedule;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DownloadInfo {
    public final boolean A;
    public final boolean B;
    public final boolean C;
    public final Bitmap D;
    public final int E;
    public final int F;
    public final boolean G;
    public final OfflineItemSchedule H;

    /* renamed from: a  reason: collision with root package name */
    public final String f10658a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final long j;
    public final long k;
    public final String l;
    public final boolean m;
    public final String n;
    public final boolean o;
    public final C0288Er0 p;
    public final long q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final OTRProfileID u;
    public final boolean v;
    public final int w;
    public final long x;
    public final boolean y;
    public final C0788My z;

    public DownloadInfo(DH dh, CH ch) {
        this.f10658a = dh.f7878a;
        this.b = dh.b;
        this.c = dh.c;
        this.d = dh.d;
        this.e = dh.e;
        this.f = dh.f;
        this.g = dh.g;
        this.h = dh.h;
        this.i = dh.i;
        this.j = dh.j;
        this.k = dh.k;
        String str = dh.m;
        this.l = str;
        this.m = dh.n;
        this.o = dh.l;
        this.n = dh.o;
        this.p = dh.p;
        this.q = dh.q;
        this.r = dh.r;
        this.s = dh.s;
        this.t = dh.t;
        this.u = dh.u;
        boolean z2 = dh.v;
        this.v = z2;
        this.w = dh.w;
        this.x = dh.x;
        this.y = dh.y;
        C0788My my = dh.z;
        if (my != null) {
            this.z = my;
        } else {
            this.z = U70.a(z2, str);
        }
        this.A = dh.A;
        this.B = dh.B;
        this.C = dh.C;
        this.D = dh.D;
        this.E = dh.E;
        this.F = dh.F;
        this.G = dh.G;
        this.H = dh.H;
    }

    public static DownloadInfo createDownloadInfo(String str, String str2, String str3, String str4, String str5, long j2, long j3, boolean z2, OTRProfileID oTRProfileID, int i2, int i3, boolean z3, boolean z4, boolean z5, boolean z6, String str6, String str7, long j4, long j5, boolean z7, int i4, OfflineItemSchedule offlineItemSchedule) {
        Long l2;
        String remapGenericMimeType = MimeUtils.remapGenericMimeType(str5, str4, str2);
        if (i3 == -1) {
            l2 = null;
        } else {
            l2 = Long.valueOf(j3);
        }
        C0288Er0 er0 = new C0288Er0(j2, l2, 0);
        DH dh = new DH();
        dh.j = j2;
        dh.k = j3;
        dh.f = str2;
        dh.m = str;
        dh.e = str2;
        dh.g = str3;
        dh.n = z4;
        dh.t = z2;
        dh.u = oTRProfileID;
        dh.s = z3;
        dh.r = z5;
        dh.C = z6;
        dh.c = remapGenericMimeType;
        dh.i = str6;
        dh.p = er0;
        dh.h = str7;
        dh.w = i2;
        dh.q = j4;
        dh.x = j5;
        dh.y = z7;
        dh.f7878a = str4;
        dh.F = i4;
        dh.H = offlineItemSchedule;
        return dh.a();
    }
}
