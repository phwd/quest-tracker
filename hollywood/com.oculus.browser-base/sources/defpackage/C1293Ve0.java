package defpackage;

import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Ve0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1293Ve0 extends AbstractC0202Dg0 {
    public final String f;
    public final MediaRouter2.RoutingController g;
    public final Messenger h;
    public final Messenger i;
    public final SparseArray j = new SparseArray();
    public final Handler k;
    public AtomicInteger l = new AtomicInteger(1);
    public final Runnable m = new RunnableC1171Te0(this);
    public int n = -1;
    public final /* synthetic */ C1537Ze0 o;

    public C1293Ve0(C1537Ze0 ze0, MediaRouter2.RoutingController routingController, String str) {
        Messenger messenger;
        this.o = ze0;
        this.g = routingController;
        this.f = str;
        Bundle controlHints = routingController.getControlHints();
        Messenger messenger2 = null;
        if (controlHints == null) {
            messenger = null;
        } else {
            messenger = (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
        }
        this.h = messenger;
        this.i = messenger != null ? new Messenger(new HandlerC1232Ue0(this)) : messenger2;
        this.k = new Handler(Looper.getMainLooper());
    }

    @Override // defpackage.AbstractC0385Gg0
    public void d() {
        this.g.release();
    }

    @Override // defpackage.AbstractC0385Gg0
    public void f(int i2) {
        MediaRouter2.RoutingController routingController = this.g;
        if (routingController != null) {
            routingController.setVolume(i2);
            this.n = i2;
            this.k.removeCallbacks(this.m);
            this.k.postDelayed(this.m, 1000);
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void i(int i2) {
        MediaRouter2.RoutingController routingController = this.g;
        if (routingController != null) {
            int i3 = this.n;
            if (i3 < 0) {
                i3 = routingController.getVolume();
            }
            int max = Math.max(0, Math.min(i3 + i2, this.g.getVolumeMax()));
            this.n = max;
            this.g.setVolume(max);
            this.k.removeCallbacks(this.m);
            this.k.postDelayed(this.m, 1000);
        }
    }

    @Override // defpackage.AbstractC0202Dg0
    public void m(String str) {
        if (str == null || str.isEmpty()) {
            Log.w("MR2Provider", "onAddMemberRoute: Ignoring null or empty routeId.");
            return;
        }
        MediaRoute2Info i2 = this.o.i(str);
        if (i2 == null) {
            Log.w("MR2Provider", "onAddMemberRoute: Specified route not found. routeId=" + str);
            return;
        }
        this.g.selectRoute(i2);
    }

    @Override // defpackage.AbstractC0202Dg0
    public void n(String str) {
        if (str == null || str.isEmpty()) {
            Log.w("MR2Provider", "onRemoveMemberRoute: Ignoring null or empty routeId.");
            return;
        }
        MediaRoute2Info i2 = this.o.i(str);
        if (i2 == null) {
            Log.w("MR2Provider", "onRemoveMemberRoute: Specified route not found. routeId=" + str);
            return;
        }
        this.g.deselectRoute(i2);
    }

    @Override // defpackage.AbstractC0202Dg0
    public void o(List list) {
        if (list == null || list.isEmpty()) {
            Log.w("MR2Provider", "onUpdateMemberRoutes: Ignoring null or empty routeIds.");
            return;
        }
        String str = (String) list.get(0);
        MediaRoute2Info i2 = this.o.i(str);
        if (i2 == null) {
            Log.w("MR2Provider", "onUpdateMemberRoutes: Specified route not found. routeId=" + str);
            return;
        }
        this.o.i.transferTo(i2);
    }
}
