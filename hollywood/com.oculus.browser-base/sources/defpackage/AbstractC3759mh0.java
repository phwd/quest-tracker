package defpackage;

import android.media.MediaRouter;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: mh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3759mh0 extends MediaRouter.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3588lh0 f10440a;

    public AbstractC3759mh0(AbstractC3588lh0 lh0) {
        this.f10440a = lh0;
    }

    public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        J51 j51 = (J51) this.f10440a;
        if (j51.l(routeInfo)) {
            j51.s();
        }
    }

    public void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int m;
        J51 j51 = (J51) this.f10440a;
        if (j51.q(routeInfo) == null && (m = j51.m(routeInfo)) >= 0) {
            j51.v((H51) j51.s.get(m));
            j51.s();
        }
    }

    public void onRouteGrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup, int i) {
        Objects.requireNonNull((J51) this.f10440a);
    }

    public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int m;
        J51 j51 = (J51) this.f10440a;
        if (j51.q(routeInfo) == null && (m = j51.m(routeInfo)) >= 0) {
            j51.s.remove(m);
            j51.s();
        }
    }

    public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
        C2392eh0 a2;
        J51 j51 = (J51) this.f10440a;
        if (routeInfo == ((MediaRouter) j51.l).getSelectedRoute(8388611)) {
            I51 q = j51.q(routeInfo);
            if (q != null) {
                q.f8199a.m();
                return;
            }
            int m = j51.m(routeInfo);
            if (m >= 0) {
                M51 m51 = j51.k;
                String str = ((H51) j51.s.get(m)).b;
                C1543Zg0 zg0 = (C1543Zg0) m51;
                zg0.k.removeMessages(262);
                C2051ch0 d = zg0.d(zg0.l);
                if (d != null && (a2 = d.a(str)) != null) {
                    a2.m();
                }
            }
        }
    }

    public void onRouteUngrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup) {
        Objects.requireNonNull((J51) this.f10440a);
    }

    public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
        Objects.requireNonNull((J51) this.f10440a);
    }

    public void onRouteVolumeChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int m;
        J51 j51 = (J51) this.f10440a;
        if (j51.q(routeInfo) == null && (m = j51.m(routeInfo)) >= 0) {
            H51 h51 = (H51) j51.s.get(m);
            int volume = routeInfo.getVolume();
            if (volume != h51.c.n()) {
                C0869Of0 of0 = h51.c;
                if (of0 != null) {
                    Bundle bundle = new Bundle(of0.f8639a);
                    ArrayList<? extends Parcelable> arrayList = null;
                    ArrayList<String> arrayList2 = !of0.g().isEmpty() ? new ArrayList<>(of0.g()) : null;
                    of0.a();
                    if (!of0.c.isEmpty()) {
                        arrayList = new ArrayList<>(of0.c);
                    }
                    bundle.putInt("volume", volume);
                    if (arrayList != null) {
                        bundle.putParcelableArrayList("controlFilters", arrayList);
                    }
                    if (arrayList2 != null) {
                        bundle.putStringArrayList("groupMemberIds", arrayList2);
                    }
                    h51.c = new C0869Of0(bundle);
                    j51.s();
                    return;
                }
                throw new IllegalArgumentException("descriptor must not be null");
            }
        }
    }
}
