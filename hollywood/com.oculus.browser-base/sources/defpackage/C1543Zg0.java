package defpackage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.VolumeProvider;
import android.os.Build;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.util.Log;
import com.oculus.os.PackageMetadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: Zg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1543Zg0 implements M51 {
    public C1299Vg0 A;
    public C0571Jh0 B;
    public C0933Pg0 C = new C0933Pg0(this);
    public C0994Qg0 D = new C0994Qg0(this);

    /* renamed from: a  reason: collision with root package name */
    public final Context f9359a;
    public final boolean b;
    public final C1537Ze0 c;
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final Map f = new HashMap();
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public final DL0 i = new DL0();
    public final C1421Xg0 j = new C1421Xg0(this);
    public final HandlerC1055Rg0 k = new HandlerC1055Rg0(this);
    public final N51 l;
    public final boolean m;
    public C3417kh0 n = new C3417kh0(new RunnableC0872Og0(this));
    public C5412wL0 o;
    public C2392eh0 p;
    public C2392eh0 q;
    public C2392eh0 r;
    public AbstractC0385Gg0 s;
    public C2392eh0 t;
    public AbstractC0385Gg0 u;
    public final Map v = new HashMap();
    public C1052Rf0 w;
    public C1052Rf0 x;
    public int y;
    public C2392eh0 z;

    public C1543Zg0(Context context) {
        this.f9359a = context;
        WeakHashMap weakHashMap = C3010iG.f10127a;
        synchronized (weakHashMap) {
            if (((C3010iG) weakHashMap.get(context)) == null) {
                weakHashMap.put(context, new C3010iG(context));
            }
        }
        this.m = ((ActivityManager) context.getSystemService("activity")).isLowRamDevice();
        boolean z2 = false;
        if (Build.VERSION.SDK_INT >= 30) {
            int i2 = AbstractC2054ci0.f9626a;
            Intent intent = new Intent(context, AbstractC2054ci0.class);
            intent.setPackage(context.getPackageName());
            this.b = context.getPackageManager().queryBroadcastReceivers(intent, 0).size() > 0 ? true : z2;
        } else {
            this.b = false;
        }
        if (this.b) {
            this.c = new C1537Ze0(context, new C1360Wg0(this, null));
        } else {
            this.c = null;
        }
        this.l = new F51(context, this);
    }

    public void a(AbstractC0446Hg0 hg0) {
        if (d(hg0) == null) {
            C2051ch0 ch0 = new C2051ch0(hg0);
            this.g.add(ch0);
            this.k.b(PackageMetadata.Signature.Algorithm.ECDSA_SHA_256, ch0);
            o(ch0, hg0.g);
            C1421Xg0 xg0 = this.j;
            C3246jh0.b();
            hg0.d = xg0;
            hg0.h(this.w);
        }
    }

    public String b(C2051ch0 ch0, String str) {
        String flattenToShortString = ch0.c.f8030a.flattenToShortString();
        String g2 = AbstractC2531fV.g(flattenToShortString, ":", str);
        if (e(g2) < 0) {
            this.f.put(new C1754aw0(flattenToShortString, str), g2);
            return g2;
        }
        Log.w("MediaRouter", "Either " + str + " isn't unique in " + flattenToShortString + " or we're trying to assign a unique ID for an already added route");
        int i2 = 2;
        while (true) {
            String format = String.format(Locale.US, "%s_%d", g2, Integer.valueOf(i2));
            if (e(format) < 0) {
                this.f.put(new C1754aw0(flattenToShortString, str), format);
                return format;
            }
            i2++;
        }
    }

    public C2392eh0 c() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            C2392eh0 eh0 = (C2392eh0) it.next();
            if (eh0 != this.p && h(eh0) && eh0.g()) {
                return eh0;
            }
        }
        return this.p;
    }

    public final C2051ch0 d(AbstractC0446Hg0 hg0) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((C2051ch0) this.g.get(i2)).f9625a == hg0) {
                return (C2051ch0) this.g.get(i2);
            }
        }
        return null;
    }

    public final int e(String str) {
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((C2392eh0) this.e.get(i2)).c.equals(str)) {
                return i2;
            }
        }
        return -1;
    }

    public C2392eh0 f() {
        C2392eh0 eh0 = this.p;
        if (eh0 != null) {
            return eh0;
        }
        throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
    }

    public C2392eh0 g() {
        C2392eh0 eh0 = this.r;
        if (eh0 != null) {
            return eh0;
        }
        throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
    }

    public final boolean h(C2392eh0 eh0) {
        return eh0.d() == this.l && eh0.n("android.media.intent.category.LIVE_AUDIO") && !eh0.n("android.media.intent.category.LIVE_VIDEO");
    }

    public void i() {
        if (this.r.f()) {
            List<C2392eh0> c2 = this.r.c();
            HashSet hashSet = new HashSet();
            for (C2392eh0 eh0 : c2) {
                hashSet.add(eh0.c);
            }
            Iterator it = this.v.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!hashSet.contains(entry.getKey())) {
                    AbstractC0385Gg0 gg0 = (AbstractC0385Gg0) entry.getValue();
                    gg0.h(0);
                    gg0.d();
                    it.remove();
                }
            }
            for (C2392eh0 eh02 : c2) {
                if (!this.v.containsKey(eh02.c)) {
                    AbstractC0385Gg0 e2 = eh02.d().e(eh02.b, this.r.b);
                    e2.e();
                    this.v.put(eh02.c, e2);
                }
            }
        }
    }

    public void j(C2392eh0 eh0, int i2) {
        if (this.r != null) {
            C1880bh0 bh0 = new C1880bh0(this, i2);
            this.z = this.r;
            bh0.a();
            this.k.c(263, this.r, i2);
            this.s = null;
            this.v.clear();
            this.r = null;
        }
    }

    public void k(C2392eh0 eh0, int i2) {
        C1537Ze0 ze0;
        if (!this.e.contains(eh0)) {
            Log.w("MediaRouter", "Ignoring attempt to select removed route: " + eh0);
        } else if (!eh0.g) {
            Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + eh0);
        } else if (Build.VERSION.SDK_INT < 30 || eh0.d() != (ze0 = this.c) || this.r == eh0) {
            l(eh0, i2);
        } else {
            String str = eh0.b;
            MediaRoute2Info i3 = ze0.i(str);
            if (i3 == null) {
                Log.w("MR2Provider", "transferTo: Specified route not found. routeId=" + str);
                return;
            }
            ze0.i.transferTo(i3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((defpackage.C3246jh0.f10229a.f() == r9) != false) goto L_0x001e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void l(defpackage.C2392eh0 r9, int r10) {
        /*
        // Method dump skipped, instructions count: 392
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1543Zg0.l(eh0, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0102, code lost:
        if (r20.x.b() == r2) goto L_0x0123;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x00a8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m() {
        /*
        // Method dump skipped, instructions count: 386
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1543Zg0.m():void");
    }

    public final void n() {
        MediaRouter2.RoutingController routingController;
        C2392eh0 eh0 = this.r;
        if (eh0 != null) {
            DL0 dl0 = this.i;
            dl0.f7884a = eh0.o;
            dl0.b = eh0.p;
            dl0.c = eh0.n;
            dl0.d = eh0.l;
            dl0.e = eh0.k;
            String str = null;
            if (!this.b || eh0.d() != this.c) {
                this.i.f = null;
            } else {
                DL0 dl02 = this.i;
                AbstractC0385Gg0 gg0 = this.s;
                if ((gg0 instanceof C1293Ve0) && (routingController = ((C1293Ve0) gg0).g) != null) {
                    str = routingController.getId();
                }
                dl02.f = str;
            }
            int size = this.h.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                AbstractC1482Yg0 yg0 = (AbstractC1482Yg0) this.h.get(i3);
                yg0.f9288a.a(yg0.b.i);
            }
            if (this.A == null) {
                return;
            }
            if (this.r == f() || this.r == this.q) {
                this.A.a();
                return;
            }
            DL0 dl03 = this.i;
            if (dl03.c == 1) {
                i2 = 2;
            }
            C1299Vg0 vg0 = this.A;
            int i4 = dl03.b;
            int i5 = dl03.f7884a;
            String str2 = dl03.f;
            C0571Jh0 jh0 = vg0.f9098a;
            if (jh0 != null) {
                C1238Ug0 ug0 = vg0.b;
                if (ug0 != null && i2 == 0 && i4 == 0) {
                    ug0.d = i5;
                    ((VolumeProvider) ug0.a()).setCurrentVolume(i5);
                    AbstractC0266Eh0 eh02 = ug0.e;
                    if (eh02 != null) {
                        AbstractC0327Fh0 fh0 = eh02.f7974a;
                        if (fh0.c == ug0) {
                            fh0.o(new ParcelableVolumeInfo(fh0.f8033a, fh0.b, ug0.f9042a, ug0.b, ug0.d));
                            return;
                        }
                        return;
                    }
                    return;
                }
                C1238Ug0 ug02 = new C1238Ug0(vg0, i2, i4, i5, str2);
                vg0.b = ug02;
                jh0.b.n(ug02);
                return;
            }
            return;
        }
        C1299Vg0 vg02 = this.A;
        if (vg02 != null) {
            vg02.a();
        }
    }

    public final void o(C2051ch0 ch0, C0507Ig0 ig0) {
        boolean z2;
        boolean z3;
        int i2;
        int i3 = 0;
        if (ch0.d != ig0) {
            ch0.d = ig0;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (ig0 == null || (!ig0.b() && ig0 != this.l.g)) {
                Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + ig0);
                z3 = false;
            } else {
                List<C0869Of0> list = ig0.f8240a;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                boolean z4 = false;
                int i4 = 0;
                for (C0869Of0 of0 : list) {
                    if (of0 == null || !of0.r()) {
                        Log.w("MediaRouter", "Ignoring invalid system route descriptor: " + of0);
                    } else {
                        String i5 = of0.i();
                        int size = ch0.b.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size) {
                                i6 = -1;
                                break;
                            } else if (((C2392eh0) ch0.b.get(i6)).b.equals(i5)) {
                                break;
                            } else {
                                i6++;
                            }
                        }
                        if (i6 < 0) {
                            C2392eh0 eh0 = new C2392eh0(ch0, i5, b(ch0, i5));
                            i2 = i4 + 1;
                            ch0.b.add(i4, eh0);
                            this.e.add(eh0);
                            if (of0.g().size() > 0) {
                                arrayList.add(new C1754aw0(eh0, of0));
                            } else {
                                eh0.j(of0);
                                this.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_256, eh0);
                            }
                        } else if (i6 < i4) {
                            Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + of0);
                        } else {
                            C2392eh0 eh02 = (C2392eh0) ch0.b.get(i6);
                            i2 = i4 + 1;
                            Collections.swap(ch0.b, i6, i4);
                            if (of0.g().size() > 0) {
                                arrayList2.add(new C1754aw0(eh02, of0));
                            } else if (p(eh02, of0) != 0 && eh02 == this.r) {
                                z4 = true;
                            }
                        }
                        i4 = i2;
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C1754aw0 aw0 = (C1754aw0) it.next();
                    C2392eh0 eh03 = (C2392eh0) aw0.f9500a;
                    eh03.j((C0869Of0) aw0.b);
                    this.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_256, eh03);
                }
                Iterator it2 = arrayList2.iterator();
                boolean z5 = z4;
                while (it2.hasNext()) {
                    C1754aw0 aw02 = (C1754aw0) it2.next();
                    C2392eh0 eh04 = (C2392eh0) aw02.f9500a;
                    if (p(eh04, (C0869Of0) aw02.b) != 0 && eh04 == this.r) {
                        z5 = true;
                    }
                }
                z3 = z5;
                i3 = i4;
            }
            for (int size2 = ch0.b.size() - 1; size2 >= i3; size2--) {
                C2392eh0 eh05 = (C2392eh0) ch0.b.get(size2);
                eh05.j(null);
                this.e.remove(eh05);
            }
            q(z3);
            for (int size3 = ch0.b.size() - 1; size3 >= i3; size3--) {
                this.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_512, (C2392eh0) ch0.b.remove(size3));
            }
            this.k.b(515, ch0);
        }
    }

    public int p(C2392eh0 eh0, C0869Of0 of0) {
        int j2 = eh0.j(of0);
        if (j2 != 0) {
            if ((j2 & 1) != 0) {
                this.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_256, eh0);
            }
            if ((j2 & 2) != 0) {
                this.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_512, eh0);
            }
            if ((j2 & 4) != 0) {
                this.k.b(261, eh0);
            }
        }
        return j2;
    }

    public void q(boolean z2) {
        C2392eh0 eh0 = this.p;
        if (eh0 != null && !eh0.g()) {
            StringBuilder i2 = AbstractC2531fV.i("Clearing the default route because it is no longer selectable: ");
            i2.append(this.p);
            Log.i("MediaRouter", i2.toString());
            this.p = null;
        }
        if (this.p == null && !this.e.isEmpty()) {
            Iterator it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C2392eh0 eh02 = (C2392eh0) it.next();
                if ((eh02.d() == this.l && eh02.b.equals("DEFAULT_ROUTE")) && eh02.g()) {
                    this.p = eh02;
                    StringBuilder i3 = AbstractC2531fV.i("Found default route: ");
                    i3.append(this.p);
                    Log.i("MediaRouter", i3.toString());
                    break;
                }
            }
        }
        C2392eh0 eh03 = this.q;
        if (eh03 != null && !eh03.g()) {
            StringBuilder i4 = AbstractC2531fV.i("Clearing the bluetooth route because it is no longer selectable: ");
            i4.append(this.q);
            Log.i("MediaRouter", i4.toString());
            this.q = null;
        }
        if (this.q == null && !this.e.isEmpty()) {
            Iterator it2 = this.e.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                C2392eh0 eh04 = (C2392eh0) it2.next();
                if (h(eh04) && eh04.g()) {
                    this.q = eh04;
                    StringBuilder i5 = AbstractC2531fV.i("Found bluetooth route: ");
                    i5.append(this.q);
                    Log.i("MediaRouter", i5.toString());
                    break;
                }
            }
        }
        C2392eh0 eh05 = this.r;
        if (eh05 == null || !eh05.g) {
            StringBuilder i6 = AbstractC2531fV.i("Unselecting the current route because it is no longer selectable: ");
            i6.append(this.r);
            Log.i("MediaRouter", i6.toString());
            l(c(), 0);
        } else if (z2) {
            i();
            n();
        }
    }
}
