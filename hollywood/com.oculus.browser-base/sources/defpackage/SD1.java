package defpackage;

import android.content.Context;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.internal.location.zzbf;
import com.google.android.gms.location.LocationRequest;
import java.util.HashMap;
import java.util.Map;

/* renamed from: SD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SD1 {

    /* renamed from: a  reason: collision with root package name */
    public final IH1 f8885a;
    public final Context b;
    public boolean c = false;
    public final Map d = new HashMap();
    public final Map e = new HashMap();
    public final Map f = new HashMap();

    public SD1(Context context, IH1 ih1) {
        this.b = context;
        this.f8885a = ih1;
    }

    public final void a() {
        synchronized (this.d) {
            for (BinderC2493fE1 fe1 : this.d.values()) {
                if (fe1 != null) {
                    ((JD1) this.f8885a.a()).f(zzbf.x(fe1, null));
                }
            }
            this.d.clear();
        }
        synchronized (this.f) {
            for (Object obj : this.f.values()) {
                C5859z.a(obj);
            }
            this.f.clear();
        }
        synchronized (this.e) {
            for (Object obj2 : this.e.values()) {
                C5859z.a(obj2);
            }
            this.e.clear();
        }
    }

    public final void b(LocationRequest locationRequest, C5718y90 y90, AbstractC3857nD1 nd1) {
        BinderC2493fE1 fe1;
        this.f8885a.f8215a.c();
        synchronized (this.d) {
            fe1 = (BinderC2493fE1) this.d.get(y90.c);
            if (fe1 == null) {
                fe1 = new BinderC2493fE1(y90);
            }
            this.d.put(y90.c, fe1);
        }
        ((JD1) this.f8885a.a()).f(new zzbf(1, new zzbd(locationRequest, zzbd.F, null, false, false, false, null), fe1, null, null, nd1 != null ? nd1.asBinder() : null));
    }

    public final void c() {
        if (this.c) {
            this.f8885a.f8215a.c();
            JD1 jd1 = (JD1) this.f8885a.a();
            Parcel c2 = jd1.c();
            int i = PE1.f8678a;
            c2.writeInt(0);
            jd1.d(12, c2);
            this.c = false;
        }
    }
}
