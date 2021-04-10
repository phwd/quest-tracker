package defpackage;

import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: MF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MF1 extends AbstractC2838hF1 {
    public static final String e = GF1.e("com.google.cast.media");
    public final QF1 A;
    public final QF1 B;
    public long f;
    public MediaStatus g;
    public Long h;
    public C4555rI1 i;
    public final QF1 j;
    public final QF1 k;
    public final QF1 l;
    public final QF1 m;
    public final QF1 n;
    public final QF1 o;
    public final QF1 p;
    public final QF1 q;
    public final QF1 r;
    public final QF1 s;
    public final QF1 t;
    public final QF1 u;
    public final QF1 v;
    public final QF1 w;
    public final QF1 x;
    public final QF1 y = new QF1(86400000);
    public final QF1 z;

    public MF1() {
        super(e, "MediaControlChannel");
        QF1 qf1 = new QF1(86400000);
        this.j = qf1;
        QF1 qf12 = new QF1(86400000);
        this.k = qf12;
        QF1 qf13 = new QF1(86400000);
        this.l = qf13;
        QF1 qf14 = new QF1(86400000);
        this.m = qf14;
        QF1 qf15 = new QF1(10000);
        this.n = qf15;
        QF1 qf16 = new QF1(86400000);
        this.o = qf16;
        QF1 qf17 = new QF1(86400000);
        this.p = qf17;
        QF1 qf18 = new QF1(86400000);
        this.q = qf18;
        QF1 qf19 = new QF1(86400000);
        this.r = qf19;
        QF1 qf110 = new QF1(86400000);
        this.s = qf110;
        QF1 qf111 = new QF1(86400000);
        this.t = qf111;
        QF1 qf112 = new QF1(86400000);
        this.u = qf112;
        QF1 qf113 = new QF1(86400000);
        this.v = qf113;
        QF1 qf114 = new QF1(86400000);
        this.w = qf114;
        QF1 qf115 = new QF1(86400000);
        this.x = qf115;
        QF1 qf116 = new QF1(86400000);
        this.z = qf116;
        QF1 qf117 = new QF1(86400000);
        this.A = qf117;
        QF1 qf118 = new QF1(86400000);
        this.B = qf118;
        this.d.add(qf1);
        this.d.add(qf12);
        this.d.add(qf13);
        this.d.add(qf14);
        this.d.add(qf15);
        this.d.add(qf16);
        this.d.add(qf17);
        this.d.add(qf18);
        this.d.add(qf19);
        this.d.add(qf110);
        this.d.add(qf111);
        this.d.add(qf112);
        this.d.add(qf113);
        this.d.add(qf114);
        this.d.add(qf115);
        this.d.add(qf116);
        this.d.add(qf116);
        this.d.add(qf117);
        this.d.add(qf118);
        n();
    }

    public static int[] l(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            iArr[i2] = jSONArray.getInt(i2);
        }
        return iArr;
    }

    public final long c() {
        C3921ne0 ne0;
        MediaInfo d = d();
        long j2 = 0;
        if (d == null) {
            return 0;
        }
        Long l2 = this.h;
        if (l2 != null) {
            if (l2.equals(4294967296000L)) {
                if (this.g.Z != null) {
                    long longValue = this.h.longValue();
                    MediaStatus mediaStatus = this.g;
                    if (!(mediaStatus == null || (ne0 = mediaStatus.Z) == null)) {
                        long j3 = ne0.c;
                        j2 = !ne0.e ? j(1.0d, j3, -1) : j3;
                    }
                    return Math.min(longValue, j2);
                } else if (e() >= 0) {
                    return Math.min(this.h.longValue(), e());
                }
            }
            return this.h.longValue();
        } else if (this.f == 0) {
            return 0;
        } else {
            MediaStatus mediaStatus2 = this.g;
            double d2 = mediaStatus2.I;
            long j4 = mediaStatus2.L;
            return (d2 == 0.0d || mediaStatus2.f9645J != 2) ? j4 : j(d2, j4, d.f9642J);
        }
    }

    public final MediaInfo d() {
        MediaStatus mediaStatus = this.g;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.F;
    }

    public final long e() {
        MediaInfo d = d();
        if (d != null) {
            return d.f9642J;
        }
        return 0;
    }

    public final void f() {
        C4555rI1 ri1 = this.i;
        if (ri1 != null) {
            Objects.requireNonNull(ri1.f11197a);
            for (C2490fD1 fd1 : ri1.f11197a.h) {
                fd1.a(false);
            }
            for (GL0 gl0 : ri1.f11197a.i) {
                gl0.a();
            }
        }
    }

    public final void g() {
        C4555rI1 ri1 = this.i;
        if (ri1 != null) {
            for (C2490fD1 fd1 : ri1.f11197a.h) {
                fd1.a(false);
            }
            for (GL0 gl0 : ri1.f11197a.i) {
                Objects.requireNonNull(gl0);
            }
        }
    }

    public final void h() {
        C4555rI1 ri1 = this.i;
        if (ri1 != null) {
            for (C2490fD1 fd1 : ri1.f11197a.h) {
                fd1.a(false);
            }
            for (GL0 gl0 : ri1.f11197a.i) {
                Objects.requireNonNull(gl0);
            }
        }
    }

    public final void i() {
        C4555rI1 ri1 = this.i;
        if (ri1 != null) {
            Objects.requireNonNull(ri1.f11197a);
            ML0 ml0 = ri1.f11197a;
            Iterator it = ml0.j.values().iterator();
            if (it.hasNext()) {
                C5859z.a(it.next());
                if (ml0.g()) {
                    Objects.requireNonNull(null);
                    throw null;
                } else if (!ml0.g()) {
                    Objects.requireNonNull(null);
                    throw null;
                } else {
                    Objects.requireNonNull(null);
                    throw null;
                }
            } else {
                for (C2490fD1 fd1 : ri1.f11197a.h) {
                    fd1.a(false);
                }
                for (GL0 gl0 : ri1.f11197a.i) {
                    gl0.b();
                }
            }
        }
    }

    public final long j(double d, long j2, long j3) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f;
        if (elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        if (elapsedRealtime == 0) {
            return j2;
        }
        long j4 = j2 + ((long) (((double) elapsedRealtime) * d));
        if (j3 > 0 && j4 > j3) {
            return j3;
        }
        if (j4 < 0) {
            return 0;
        }
        return j4;
    }

    public final long k(RF1 rf1, int i2, long j2, MediaQueueItem[] mediaQueueItemArr, int i3, Integer num, JSONObject jSONObject) {
        int i4 = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
        if (i4 == 0 || j2 >= 0) {
            JSONObject jSONObject2 = new JSONObject();
            long b = b();
            try {
                jSONObject2.put("requestId", b);
                jSONObject2.put("type", "QUEUE_UPDATE");
                jSONObject2.put("mediaSessionId", o());
                if (i2 != 0) {
                    jSONObject2.put("currentItemId", i2);
                }
                if (i3 != 0) {
                    jSONObject2.put("jump", i3);
                }
                String a2 = SF1.a(null);
                if (a2 != null) {
                    jSONObject2.put("repeatMode", a2);
                }
                if (i4 != 0) {
                    jSONObject2.put("currentTime", ((double) j2) / 1000.0d);
                }
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
            } catch (JSONException unused) {
            }
            a(jSONObject2.toString(), b, null);
            this.u.c(b, rf1);
            return b;
        }
        throw new IllegalArgumentException(AbstractC2531fV.u(53, "playPosition cannot be negative: ", j2));
    }

    public final void m() {
        synchronized (this.d) {
            for (QF1 qf1 : this.d) {
                qf1.d(2002);
            }
        }
        n();
    }

    public final void n() {
        this.f = 0;
        this.g = null;
        for (QF1 qf1 : this.d) {
            qf1.d(2002);
        }
    }

    public final long o() {
        MediaStatus mediaStatus = this.g;
        if (mediaStatus != null) {
            return mediaStatus.G;
        }
        throw new PF1();
    }
}
