package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TimerTask;

/* renamed from: Le0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0684Le0 {

    /* renamed from: a  reason: collision with root package name */
    public final NF1 f8431a = new NF1("MediaQueue");
    public long b;
    public final ML0 c;
    public boolean d;
    public List e;
    public final SparseIntArray f;
    public LruCache g;
    public final List h;
    public final Deque i;
    public final int j;
    public final Handler k;
    public TimerTask l;
    public DB0 m;
    public DB0 n;
    public BM0 o;
    public BM0 p;
    public C0562Je0 q;
    public TS0 r;
    public Set s = new HashSet();

    public C0684Le0(ML0 ml0) {
        this.c = ml0;
        this.j = Math.max(20, 1);
        NF1 nf1 = C1557Zm.f9368a;
        SE0.e("Must be called from the main thread.");
        C2922hn c2 = C1557Zm.b.b().c();
        this.e = new ArrayList();
        this.f = new SparseIntArray();
        this.h = new ArrayList();
        this.i = new ArrayDeque(20);
        this.k = new HandlerC2841hG1(Looper.getMainLooper());
        this.g = new GH1(this, 20);
        this.l = new C5573xH1(this);
        this.o = new C0440He0(this, null);
        this.p = new C0501Ie0(this, null);
        this.q = new C0562Je0(this);
        this.r = new C0623Ke0(this, null);
        SE0.e("Must be called from the main thread.");
        C1557Zm.b.b().a(this.r, C2922hn.class);
        if (c2 != null && c2.a()) {
            d(c2.f());
        }
    }

    public static void c(C0684Le0 le0, int[] iArr) {
        Iterator it = le0.s.iterator();
        if (it.hasNext()) {
            C5859z.a(it.next());
            throw null;
        }
    }

    public static long e(ML0 ml0) {
        int i2;
        MediaStatus d2 = ml0.d();
        if (d2 == null) {
            return 0;
        }
        MediaInfo mediaInfo = d2.F;
        if (mediaInfo == null) {
            i2 = -1;
        } else {
            i2 = mediaInfo.G;
        }
        if (MediaStatus.E(d2.f9645J, d2.K, d2.Q, i2)) {
            return 0;
        }
        return d2.G;
    }

    public static void f(C0684Le0 le0) {
        le0.f.clear();
        for (int i2 = 0; i2 < le0.e.size(); i2++) {
            le0.f.put(((Integer) le0.e.get(i2)).intValue(), i2);
        }
    }

    public final void a() {
        i();
        this.e.clear();
        this.f.clear();
        this.g.evictAll();
        this.h.clear();
        this.k.removeCallbacks(this.l);
        this.i.clear();
        DB0 db0 = this.n;
        if (db0 != null) {
            db0.a();
            this.n = null;
        }
        DB0 db02 = this.m;
        if (db02 != null) {
            db02.a();
            this.m = null;
        }
        k();
        j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [DB0] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r5 = this;
            java.lang.String r0 = "Must be called from the main thread."
            defpackage.SE0.e(r0)
            boolean r1 = r5.d
            if (r1 == 0) goto L_0x004e
            long r1 = r5.b
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0012
            goto L_0x004e
        L_0x0012:
            DB0 r1 = r5.n
            if (r1 == 0) goto L_0x0017
            return
        L_0x0017:
            r2 = 0
            if (r1 == 0) goto L_0x001f
            r1.a()
            r5.n = r2
        L_0x001f:
            DB0 r1 = r5.m
            if (r1 == 0) goto L_0x0028
            r1.a()
            r5.m = r2
        L_0x0028:
            ML0 r1 = r5.c
            java.util.Objects.requireNonNull(r1)
            defpackage.SE0.e(r0)
            boolean r0 = r1.w()
            if (r0 != 0) goto L_0x003d
            r0 = 17
            DB0 r0 = defpackage.ML0.s(r0, r2)
            goto L_0x0047
        L_0x003d:
            iD1 r0 = new iD1
            YV r2 = r1.g
            r0.<init>(r1, r2)
            r1.t(r0)
        L_0x0047:
            r5.n = r0
            BM0 r1 = r5.p
            r0.b(r1)
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0684Le0.b():void");
    }

    public final void d(ML0 ml0) {
        if (ml0 != null && this.c == ml0) {
            this.d = true;
            C0562Je0 je0 = this.q;
            SE0.e("Must be called from the main thread.");
            if (je0 != null) {
                ml0.i.add(je0);
            }
            long e2 = e(ml0);
            this.b = e2;
            if (e2 != 0) {
                b();
            }
        }
    }

    public final void g() {
        this.k.removeCallbacks(this.l);
        this.k.postDelayed(this.l, 500);
    }

    public final void h() {
        ML0 ml0 = this.c;
        C0562Je0 je0 = this.q;
        Objects.requireNonNull(ml0);
        SE0.e("Must be called from the main thread.");
        if (je0 != null) {
            ml0.i.remove(je0);
        }
        this.d = false;
    }

    public final void i() {
        Iterator it = this.s.iterator();
        if (it.hasNext()) {
            C5859z.a(it.next());
            throw null;
        }
    }

    public final void j() {
        Iterator it = this.s.iterator();
        if (it.hasNext()) {
            C5859z.a(it.next());
            throw null;
        }
    }

    public final void k() {
        Iterator it = this.s.iterator();
        if (it.hasNext()) {
            C5859z.a(it.next());
            throw null;
        }
    }
}
