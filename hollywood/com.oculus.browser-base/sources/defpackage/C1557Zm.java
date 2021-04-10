package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.cast.framework.CastOptions;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.components.media_router.caf.CastOptionsProvider;

/* renamed from: Zm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1557Zm {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f9368a = new NF1("CastContext");
    public static C1557Zm b;
    public final Context c;
    public final AbstractC2502fH1 d;
    public final SS0 e;
    public final C5397wF1 f;
    public final CastOptions g;
    public TI1 h;
    public GG1 i;
    public final List j;

    public C1557Zm(Context context, CastOptions castOptions, List list) {
        AbstractC2502fH1 fh1;
        C3018iI1 ii1;
        C5397wF1 wf1;
        WI1 wi1;
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        this.g = castOptions;
        this.h = new TI1(C3246jh0.e(applicationContext));
        this.j = list;
        g();
        SS0 ss0 = null;
        try {
            fh1 = AbstractC2499fG1.a(applicationContext).y0(new BinderC0773Mq0(applicationContext.getApplicationContext()), castOptions, this.h, f());
        } catch (RemoteException unused) {
            NF1 nf1 = AbstractC2499fG1.f9911a;
            Object[] objArr = {"newCastContextImpl", OG1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
            fh1 = null;
        }
        this.d = fh1;
        try {
            MH1 mh1 = (MH1) fh1;
            Parcel d2 = mh1.d(6, mh1.c());
            IBinder readStrongBinder = d2.readStrongBinder();
            if (readStrongBinder == null) {
                ii1 = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager");
                if (queryLocalInterface instanceof C3018iI1) {
                    ii1 = (C3018iI1) queryLocalInterface;
                } else {
                    ii1 = new C3018iI1(readStrongBinder);
                }
            }
            d2.recycle();
        } catch (RemoteException unused2) {
            NF1 nf12 = f9368a;
            Object[] objArr2 = {"getDiscoveryManagerImpl", AbstractC2502fH1.class.getSimpleName()};
            if (nf12.d()) {
                nf12.c("Unable to call %s on %s.", objArr2);
            }
            ii1 = null;
        }
        if (ii1 == null) {
            wf1 = null;
        } else {
            wf1 = new C5397wF1(ii1);
        }
        this.f = wf1;
        try {
            MH1 mh12 = (MH1) this.d;
            Parcel d3 = mh12.d(5, mh12.c());
            IBinder readStrongBinder2 = d3.readStrongBinder();
            if (readStrongBinder2 == null) {
                wi1 = null;
            } else {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager");
                if (queryLocalInterface2 instanceof WI1) {
                    wi1 = (WI1) queryLocalInterface2;
                } else {
                    wi1 = new WI1(readStrongBinder2);
                }
            }
            d3.recycle();
        } catch (RemoteException unused3) {
            NF1 nf13 = f9368a;
            Object[] objArr3 = {"getSessionManagerImpl", AbstractC2502fH1.class.getSimpleName()};
            if (nf13.d()) {
                nf13.c("Unable to call %s on %s.", objArr3);
            }
            wi1 = null;
        }
        ss0 = wi1 != null ? new SS0(wi1, this.c) : ss0;
        this.e = ss0;
        if (ss0 != null) {
            new C2667gF1(this.c);
            SE0.g("PrecacheManager", "The log tag cannot be null or empty.");
        }
    }

    public static C1557Zm c(Context context) {
        SE0.e("Must be called from the main thread.");
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            try {
                Bundle bundle = C5858yz1.a(applicationContext).a(applicationContext.getPackageName(), 128).metaData;
                if (bundle == null) {
                    f9368a.a("Bundle is null", new Object[0]);
                }
                String string = bundle.getString("com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME");
                if (string != null) {
                    CastOptionsProvider castOptionsProvider = (CastOptionsProvider) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    context.getApplicationContext();
                    CastOptions b2 = castOptionsProvider.b();
                    context.getApplicationContext();
                    b = new C1557Zm(context, b2, castOptionsProvider.a());
                } else {
                    throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
                }
            } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e2) {
                throw new IllegalStateException("Failed to initialize CastContext.", e2);
            }
        }
        return b;
    }

    public static C1557Zm e(Context context) {
        SE0.e("Must be called from the main thread.");
        try {
            return c(context);
        } catch (RuntimeException e2) {
            NF1 nf1 = f9368a;
            Log.e(nf1.f8536a, nf1.c("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", e2));
            return null;
        }
    }

    public C0629Kg0 a() {
        SE0.e("Must be called from the main thread.");
        try {
            MH1 mh1 = (MH1) this.d;
            Parcel d2 = mh1.d(1, mh1.c());
            d2.recycle();
            return C0629Kg0.b((Bundle) AbstractC4376qF1.a(d2, Bundle.CREATOR));
        } catch (RemoteException unused) {
            NF1 nf1 = f9368a;
            Object[] objArr = {"getMergedSelectorAsBundle", AbstractC2502fH1.class.getSimpleName()};
            if (!nf1.d()) {
                return null;
            }
            nf1.c("Unable to call %s on %s.", objArr);
            return null;
        }
    }

    public SS0 b() {
        SE0.e("Must be called from the main thread.");
        return this.e;
    }

    public void d(String str) {
        SE0.e("Must be called from the main thread.");
        if (!TextUtils.equals(str, this.g.F)) {
            this.g.F = str;
            g();
            Map f2 = f();
            try {
                MH1 mh1 = (MH1) this.d;
                Parcel c2 = mh1.c();
                c2.writeString(str);
                c2.writeMap(f2);
                mh1.f(11, c2);
            } catch (RemoteException unused) {
                NF1 nf1 = f9368a;
                Object[] objArr = {"setReceiverApplicationId", AbstractC2502fH1.class.getSimpleName()};
                if (nf1.d()) {
                    nf1.c("Unable to call %s on %s.", objArr);
                }
            }
            Context context = this.c;
            for (WeakReference weakReference : AbstractC1496Ym.b) {
                try {
                    if (weakReference.get() != null) {
                        AbstractC1496Ym.a(context, (MenuItem) weakReference.get());
                    }
                } catch (IllegalArgumentException e2) {
                    NF1 nf12 = AbstractC1496Ym.f9296a;
                    Log.w(nf12.f8536a, nf12.c("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e2));
                }
            }
            for (WeakReference weakReference2 : AbstractC1496Ym.c) {
                if (weakReference2.get() != null) {
                    MediaRouteButton mediaRouteButton = (MediaRouteButton) weakReference2.get();
                    SE0.e("Must be called from the main thread.");
                    C1557Zm e3 = e(context);
                    if (e3 != null) {
                        mediaRouteButton.e(e3.a());
                    }
                }
            }
        }
    }

    public final Map f() {
        HashMap hashMap = new HashMap();
        GG1 gg1 = this.i;
        if (gg1 != null) {
            hashMap.put(gg1.b, gg1.c);
        }
        List<GG1> list = this.j;
        if (list != null) {
            for (GG1 gg12 : list) {
                SE0.i(gg12, "Additional SessionProvider must not be null.");
                String str = gg12.b;
                SE0.g(str, "Category for SessionProvider must not be null or empty string.");
                SE0.b(!hashMap.containsKey(str), String.format("SessionProvider for category %s already added", str));
                hashMap.put(str, gg12.c);
            }
        }
        return hashMap;
    }

    public final void g() {
        if (!TextUtils.isEmpty(this.g.F)) {
            this.i = new GG1(this.c, this.g, this.h);
        } else {
            this.i = null;
        }
    }
}
