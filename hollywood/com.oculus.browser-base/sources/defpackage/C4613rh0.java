package defpackage;

import android.media.MediaRouter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import java.util.ArrayList;

/* renamed from: rh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4613rh0 extends AbstractC3759mh0 {
    public C4613rh0(AbstractC4443qh0 qh0) {
        super(qh0);
    }

    public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        Display display;
        K51 k51 = (K51) ((AbstractC4443qh0) this.f10440a);
        int m = k51.m(routeInfo);
        if (m >= 0) {
            H51 h51 = (H51) k51.s.get(m);
            ArrayList<? extends Parcelable> arrayList = null;
            try {
                display = routeInfo.getPresentationDisplay();
            } catch (NoSuchMethodError e) {
                Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
                display = null;
            }
            int displayId = display != null ? display.getDisplayId() : -1;
            if (displayId != h51.c.m()) {
                C0869Of0 of0 = h51.c;
                if (of0 != null) {
                    Bundle bundle = new Bundle(of0.f8639a);
                    ArrayList<String> arrayList2 = !of0.g().isEmpty() ? new ArrayList<>(of0.g()) : null;
                    of0.a();
                    if (!of0.c.isEmpty()) {
                        arrayList = new ArrayList<>(of0.c);
                    }
                    bundle.putInt("presentationDisplayId", displayId);
                    if (arrayList != null) {
                        bundle.putParcelableArrayList("controlFilters", arrayList);
                    }
                    if (arrayList2 != null) {
                        bundle.putStringArrayList("groupMemberIds", arrayList2);
                    }
                    h51.c = new C0869Of0(bundle);
                    k51.s();
                    return;
                }
                throw new IllegalArgumentException("descriptor must not be null");
            }
        }
    }
}
