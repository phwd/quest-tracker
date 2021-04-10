package defpackage;

import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.RouteDiscoveryPreference;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/* renamed from: Ze0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1537Ze0 extends AbstractC0446Hg0 {
    public final MediaRouter2 i;
    public final C1360Wg0 j;
    public final Map k = new ArrayMap();
    public final MediaRouter2.RouteCallback l = new C1415Xe0(this);
    public final MediaRouter2.TransferCallback m = new C1476Ye0(this);
    public final MediaRouter2.ControllerCallback n = new C1110Se0(this);
    public final Handler o;
    public final Executor p;
    public List q = new ArrayList();
    public Map r = new ArrayMap();

    public C1537Ze0(Context context, C1360Wg0 wg0) {
        super(context, null);
        this.i = MediaRouter2.getInstance(context);
        this.j = wg0;
        Handler handler = new Handler(Looper.getMainLooper());
        this.o = handler;
        this.p = new ExecutorC0866Oe0(handler);
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0202Dg0 c(String str) {
        for (Map.Entry entry : this.k.entrySet()) {
            C1293Ve0 ve0 = (C1293Ve0) entry.getValue();
            if (TextUtils.equals(str, ve0.f)) {
                return ve0;
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0385Gg0 d(String str) {
        return new C1354We0(this, (String) this.r.get(str), null);
    }

    @Override // defpackage.AbstractC0446Hg0
    public AbstractC0385Gg0 e(String str, String str2) {
        String str3 = (String) this.r.get(str);
        for (C1293Ve0 ve0 : this.k.values()) {
            if (TextUtils.equals(str2, ve0.g.getId())) {
                return new C1354We0(this, str3, ve0);
            }
        }
        Log.w("MR2Provider", "Could not find the matching GroupRouteController. routeId=" + str + ", routeGroupId=" + str2);
        return new C1354We0(this, str3, null);
    }

    @Override // defpackage.AbstractC0446Hg0
    public void f(C1052Rf0 rf0) {
        int i2;
        RouteDiscoveryPreference routeDiscoveryPreference;
        C1543Zg0 zg0 = C3246jh0.f10229a;
        if (zg0 == null) {
            i2 = 0;
        } else {
            i2 = zg0.y;
        }
        if (i2 > 0) {
            if (rf0 == null) {
                rf0 = new C1052Rf0(C0629Kg0.f8380a, false);
            }
            rf0.a();
            C0629Kg0 kg0 = rf0.b;
            kg0.a();
            List list = kg0.c;
            list.remove("android.media.intent.category.LIVE_AUDIO");
            C0568Jg0 jg0 = new C0568Jg0();
            jg0.a(list);
            C0629Kg0 c = jg0.c();
            boolean b = rf0.b();
            if (c != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("selector", c.b);
                bundle.putBoolean("activeScan", b);
                MediaRouter2 mediaRouter2 = this.i;
                Executor executor = this.p;
                MediaRouter2.RouteCallback routeCallback = this.l;
                c.a();
                if (!(!c.c.contains(null))) {
                    routeDiscoveryPreference = new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
                } else {
                    boolean z = bundle.getBoolean("activeScan");
                    c.a();
                    routeDiscoveryPreference = new RouteDiscoveryPreference.Builder((List) c.c.stream().map(new C2905hh0()).collect(Collectors.toList()), z).build();
                }
                mediaRouter2.registerRouteCallback(executor, routeCallback, routeDiscoveryPreference);
                this.i.registerTransferCallback(this.p, this.m);
                this.i.registerControllerCallback(this.p, this.n);
                return;
            }
            throw new IllegalArgumentException("selector must not be null");
        }
        this.i.unregisterRouteCallback(this.l);
        this.i.unregisterTransferCallback(this.m);
        this.i.unregisterControllerCallback(this.n);
    }

    public MediaRoute2Info i(String str) {
        if (str == null) {
            return null;
        }
        for (MediaRoute2Info mediaRoute2Info : this.q) {
            if (TextUtils.equals(mediaRoute2Info.getId(), str)) {
                return mediaRoute2Info;
            }
        }
        return null;
    }

    public void j() {
        List list = (List) this.i.getRoutes().stream().distinct().filter(new C0927Pe0()).collect(Collectors.toList());
        if (!list.equals(this.q)) {
            this.q = list;
            this.r.clear();
            for (MediaRoute2Info mediaRoute2Info : this.q) {
                Bundle extras = mediaRoute2Info.getExtras();
                if (extras == null || extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID") == null) {
                    Log.w("MR2Provider", "Cannot find the original route Id. route=" + mediaRoute2Info);
                } else {
                    this.r.put(mediaRoute2Info.getId(), extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"));
                }
            }
            List<C0869Of0> list2 = (List) this.q.stream().map(new C0988Qe0()).filter(new C1049Re0()).collect(Collectors.toList());
            ArrayList arrayList = null;
            if (list2 != null) {
                if (!list2.isEmpty()) {
                    for (C0869Of0 of0 : list2) {
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
                }
                g(new C0507Ig0(arrayList, true));
                return;
            }
            throw new IllegalArgumentException("routes must not be null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0154  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void k(android.media.MediaRouter2.RoutingController r15) {
        /*
        // Method dump skipped, instructions count: 407
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1537Ze0.k(android.media.MediaRouter2$RoutingController):void");
    }
}
