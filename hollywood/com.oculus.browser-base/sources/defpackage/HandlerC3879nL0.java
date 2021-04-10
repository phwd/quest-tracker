package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.oculus.os.Version;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: nL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC3879nL0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f10484a;

    public HandlerC3879nL0(C3366kL0 kl0) {
        this.f10484a = new WeakReference(kl0);
    }

    public void handleMessage(Message message) {
        C3366kL0 kl0 = (C3366kL0) this.f10484a.get();
        if (kl0 != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            Object obj = message.obj;
            Bundle peekData = message.peekData();
            String str = null;
            if (i != 0) {
                switch (i) {
                    case 2:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle = (Bundle) obj;
                            if (kl0.f == 0 && i2 == kl0.g && i3 >= 1) {
                                kl0.g = 0;
                                kl0.f = i3;
                                ServiceConnectionC4562rL0 rl0 = kl0.i;
                                C0507Ig0 a2 = C0507Ig0.a(bundle);
                                if (rl0.n == kl0) {
                                    rl0.g(a2);
                                }
                                ServiceConnectionC4562rL0 rl02 = kl0.i;
                                if (rl02.n == kl0) {
                                    rl02.o = true;
                                    int size = rl02.k.size();
                                    for (int i4 = 0; i4 < size; i4++) {
                                        ((AbstractC3537lL0) rl02.k.get(i4)).b(rl02.n);
                                    }
                                    C1052Rf0 rf0 = rl02.e;
                                    if (rf0 != null) {
                                        rl02.n.c(rf0);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    case 3:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle2 = (Bundle) obj;
                            C4050oL0 ol0 = (C4050oL0) kl0.h.get(i2);
                            if (ol0 != null) {
                                kl0.h.remove(i2);
                                ol0.b(bundle2);
                                return;
                            }
                            return;
                        }
                        return;
                    case 4:
                        if (obj == null || (obj instanceof Bundle)) {
                            if (peekData != null) {
                                str = peekData.getString("error");
                            }
                            Bundle bundle3 = (Bundle) obj;
                            C4050oL0 ol02 = (C4050oL0) kl0.h.get(i2);
                            if (ol02 != null) {
                                kl0.h.remove(i2);
                                ol02.a(str, bundle3);
                                return;
                            }
                            return;
                        }
                        return;
                    case 5:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle4 = (Bundle) obj;
                            if (kl0.f != 0) {
                                ServiceConnectionC4562rL0 rl03 = kl0.i;
                                C0507Ig0 a3 = C0507Ig0.a(bundle4);
                                if (rl03.n == kl0) {
                                    rl03.g(a3);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    case 6:
                        if (obj instanceof Bundle) {
                            Bundle bundle5 = (Bundle) obj;
                            C4050oL0 ol03 = (C4050oL0) kl0.h.get(i2);
                            if (bundle5 == null || !bundle5.containsKey("routeId")) {
                                ol03.a("DynamicGroupRouteController is created without valid route id.", bundle5);
                                return;
                            }
                            kl0.h.remove(i2);
                            ol03.b(bundle5);
                            return;
                        }
                        Log.w("MediaRouteProviderProxy", "No further information on the dynamic group controller");
                        return;
                    case Version.VERSION_7:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle6 = (Bundle) obj;
                            if (kl0.f != 0) {
                                Bundle bundle7 = (Bundle) bundle6.getParcelable("groupRoute");
                                C0869Of0 b = bundle7 != null ? C0869Of0.b(bundle7) : null;
                                ArrayList parcelableArrayList = bundle6.getParcelableArrayList("dynamicRoutes");
                                ArrayList arrayList = new ArrayList();
                                Iterator it = parcelableArrayList.iterator();
                                while (it.hasNext()) {
                                    Bundle bundle8 = (Bundle) it.next();
                                    arrayList.add(bundle8 == null ? null : new C0141Cg0(C0869Of0.b(bundle8.getBundle("mrDescriptor")), bundle8.getInt("selectionState", 1), bundle8.getBoolean("isUnselectable", false), bundle8.getBoolean("isGroupable", false), bundle8.getBoolean("isTransferable", false)));
                                }
                                ServiceConnectionC4562rL0 rl04 = kl0.i;
                                if (rl04.n == kl0) {
                                    AbstractC3537lL0 l = rl04.l(i3);
                                    if (l instanceof C4221pL0) {
                                        ((C4221pL0) l).l(b, arrayList);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    case Version.VERSION_8:
                        ServiceConnectionC4562rL0 rl05 = kl0.i;
                        if (rl05.n == kl0) {
                            AbstractC3537lL0 l2 = rl05.l(i3);
                            C4732sL0 sl0 = rl05.p;
                            if (sl0 != null && (l2 instanceof AbstractC0385Gg0)) {
                                AbstractC0385Gg0 gg0 = (AbstractC0385Gg0) l2;
                                C1543Zg0 zg0 = sl0.f11268a.b;
                                if (zg0.s == gg0) {
                                    zg0.k(zg0.c(), 2);
                                }
                            }
                            rl05.m(l2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                if (i2 == kl0.g) {
                    kl0.g = 0;
                    ServiceConnectionC4562rL0 rl06 = kl0.i;
                    if (rl06.n == kl0) {
                        rl06.o();
                    }
                }
                C4050oL0 ol04 = (C4050oL0) kl0.h.get(i2);
                if (ol04 != null) {
                    kl0.h.remove(i2);
                    ol04.a(null, null);
                }
            }
        }
    }
}
