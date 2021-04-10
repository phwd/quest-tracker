package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaRouter;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: J51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class J51 extends N51 implements AbstractC3588lh0, AbstractC4101oh0 {
    public static final ArrayList i;
    public static final ArrayList j;
    public final M51 k;
    public final Object l;
    public final Object m;
    public final Object n;
    public final Object o;
    public int p;
    public boolean q;
    public boolean r;
    public final ArrayList s = new ArrayList();
    public final ArrayList t = new ArrayList();

    static {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
        ArrayList arrayList = new ArrayList();
        i = arrayList;
        arrayList.add(intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
        ArrayList arrayList2 = new ArrayList();
        j = arrayList2;
        arrayList2.add(intentFilter2);
    }

    public J51(Context context, M51 m51) {
        super(context);
        this.k = m51;
        Object systemService = context.getSystemService("media_router");
        this.l = systemService;
        this.m = new C4613rh0((K51) this);
        this.n = new C4272ph0(this);
        this.o = ((MediaRouter) systemService).createRouteCategory((CharSequence) context.getResources().getString(R.string.f55390_resource_name_obfuscated_RES_2131952856), false);
        w();
    }

    @Override // defpackage.AbstractC4101oh0
    public void a(Object obj, int i2) {
        I51 q2 = q(obj);
        if (q2 != null) {
            q2.f8199a.l(i2);
        }
    }

    @Override // defpackage.AbstractC4101oh0
    public void b(Object obj, int i2) {
        I51 q2 = q(obj);
        if (q2 != null) {
            q2.f8199a.k(i2);
        }
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0385Gg0 d(String str) {
        int n2 = n(str);
        if (n2 >= 0) {
            return new G51(((H51) this.s.get(n2)).f8136a);
        }
        return null;
    }

    @Override // defpackage.AbstractC0446Hg0
    public void f(C1052Rf0 rf0) {
        boolean z;
        int i2 = 0;
        if (rf0 != null) {
            rf0.a();
            C0629Kg0 kg0 = rf0.b;
            kg0.a();
            List list = kg0.c;
            int size = list.size();
            int i3 = 0;
            while (i2 < size) {
                String str = (String) list.get(i2);
                if (str.equals("android.media.intent.category.LIVE_AUDIO")) {
                    i3 |= 1;
                } else {
                    i3 = str.equals("android.media.intent.category.LIVE_VIDEO") ? i3 | 2 : i3 | 8388608;
                }
                i2++;
            }
            z = rf0.b();
            i2 = i3;
        } else {
            z = false;
        }
        if (this.p != i2 || this.q != z) {
            this.p = i2;
            this.q = z;
            w();
        }
    }

    @Override // defpackage.N51
    public void i(C2392eh0 eh0) {
        if (eh0.d() != this) {
            MediaRouter.UserRouteInfo createUserRoute = ((MediaRouter) this.l).createUserRoute((MediaRouter.RouteCategory) this.o);
            I51 i51 = new I51(eh0, createUserRoute);
            createUserRoute.setTag(i51);
            AbstractC3930nh0.a(createUserRoute, this.n);
            x(i51);
            this.t.add(i51);
            ((MediaRouter) this.l).addUserRoute(createUserRoute);
            return;
        }
        int m2 = m(((MediaRouter) this.l).getSelectedRoute(8388611));
        if (m2 >= 0 && ((H51) this.s.get(m2)).b.equals(eh0.b)) {
            eh0.m();
        }
    }

    @Override // defpackage.N51
    public void j(C2392eh0 eh0) {
        int o2;
        if (eh0.d() != this && (o2 = o(eh0)) >= 0) {
            I51 i51 = (I51) this.t.remove(o2);
            ((MediaRouter.RouteInfo) i51.b).setTag(null);
            AbstractC3930nh0.a(i51.b, null);
            ((MediaRouter) this.l).removeUserRoute((MediaRouter.UserRouteInfo) i51.b);
        }
    }

    @Override // defpackage.N51
    public void k(C2392eh0 eh0) {
        if (eh0.h()) {
            if (eh0.d() != this) {
                int o2 = o(eh0);
                if (o2 >= 0) {
                    t(((I51) this.t.get(o2)).b);
                    return;
                }
                return;
            }
            int n2 = n(eh0.b);
            if (n2 >= 0) {
                t(((H51) this.s.get(n2)).f8136a);
            }
        }
    }

    public final boolean l(Object obj) {
        String str;
        String format;
        if (q(obj) != null || m(obj) >= 0) {
            return false;
        }
        if (p() == obj) {
            str = "DEFAULT_ROUTE";
        } else {
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            CharSequence name = ((MediaRouter.RouteInfo) obj).getName(this.f8172a);
            objArr[0] = Integer.valueOf((name != null ? name.toString() : "").hashCode());
            str = String.format(locale, "ROUTE_%08x", objArr);
        }
        if (n(str) >= 0) {
            int i2 = 2;
            while (true) {
                format = String.format(Locale.US, "%s_%d", str, Integer.valueOf(i2));
                if (n(format) < 0) {
                    break;
                }
                i2++;
            }
            str = format;
        }
        H51 h51 = new H51(obj, str);
        v(h51);
        this.s.add(h51);
        return true;
    }

    public int m(Object obj) {
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((H51) this.s.get(i2)).f8136a == obj) {
                return i2;
            }
        }
        return -1;
    }

    public int n(String str) {
        int size = this.s.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((H51) this.s.get(i2)).b.equals(str)) {
                return i2;
            }
        }
        return -1;
    }

    public int o(C2392eh0 eh0) {
        int size = this.t.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((I51) this.t.get(i2)).f8199a == eh0) {
                return i2;
            }
        }
        return -1;
    }

    public abstract Object p();

    public I51 q(Object obj) {
        Object tag = ((MediaRouter.RouteInfo) obj).getTag();
        if (tag instanceof I51) {
            return (I51) tag;
        }
        return null;
    }

    public void r(H51 h51, C0808Nf0 nf0) {
        int supportedTypes = ((MediaRouter.RouteInfo) h51.f8136a).getSupportedTypes();
        if ((supportedTypes & 1) != 0) {
            nf0.a(i);
        }
        if ((supportedTypes & 2) != 0) {
            nf0.a(j);
        }
        nf0.d(((MediaRouter.RouteInfo) h51.f8136a).getPlaybackType());
        nf0.f8563a.putInt("playbackStream", ((MediaRouter.RouteInfo) h51.f8136a).getPlaybackStream());
        nf0.e(((MediaRouter.RouteInfo) h51.f8136a).getVolume());
        nf0.g(((MediaRouter.RouteInfo) h51.f8136a).getVolumeMax());
        nf0.f(((MediaRouter.RouteInfo) h51.f8136a).getVolumeHandling());
    }

    public void s() {
        int size = this.s.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            C0869Of0 of0 = ((H51) this.s.get(i2)).c;
            if (of0 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                } else if (arrayList.contains(of0)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                arrayList.add(of0);
            } else {
                throw new IllegalArgumentException("route must not be null");
            }
        }
        g(new C0507Ig0(arrayList, false));
    }

    public abstract void t(Object obj);

    public abstract void u();

    public void v(H51 h51) {
        String str = h51.b;
        CharSequence name = ((MediaRouter.RouteInfo) h51.f8136a).getName(this.f8172a);
        C0808Nf0 nf0 = new C0808Nf0(str, name != null ? name.toString() : "");
        r(h51, nf0);
        h51.c = nf0.b();
    }

    public final void w() {
        u();
        MediaRouter mediaRouter = (MediaRouter) this.l;
        int routeCount = mediaRouter.getRouteCount();
        ArrayList arrayList = new ArrayList(routeCount);
        boolean z = false;
        for (int i2 = 0; i2 < routeCount; i2++) {
            arrayList.add(mediaRouter.getRouteAt(i2));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            z |= l(it.next());
        }
        if (z) {
            s();
        }
    }

    public void x(I51 i51) {
        ((MediaRouter.UserRouteInfo) i51.b).setName(i51.f8199a.d);
        ((MediaRouter.UserRouteInfo) i51.b).setPlaybackType(i51.f8199a.k);
        ((MediaRouter.UserRouteInfo) i51.b).setPlaybackStream(i51.f8199a.l);
        ((MediaRouter.UserRouteInfo) i51.b).setVolume(i51.f8199a.o);
        ((MediaRouter.UserRouteInfo) i51.b).setVolumeMax(i51.f8199a.p);
        ((MediaRouter.UserRouteInfo) i51.b).setVolumeHandling(i51.f8199a.n);
    }
}
