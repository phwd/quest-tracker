package defpackage;

import android.content.Context;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
/* renamed from: VV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VV {

    /* renamed from: a  reason: collision with root package name */
    public final Set f9088a = new HashSet();
    public final Set b = new HashSet();
    public String c;
    public String d;
    public final Map e = new C4931ta();
    public final Context f;
    public final Map g = new C4931ta();
    public int h = -1;
    public Looper i;
    public SV j;
    public Y6 k;
    public final ArrayList l;
    public final ArrayList m;

    public VV(Context context) {
        Object obj = SV.c;
        this.j = SV.d;
        this.k = ZA1.c;
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.f = context;
        this.i = context.getMainLooper();
        this.c = context.getPackageName();
        this.d = context.getClass().getName();
    }

    public final YV a() {
        SE0.b(!this.g.isEmpty(), "must call addApi() to add at least one API");
        C5092uV0 uv0 = C5092uV0.f11415a;
        Map map = this.g;
        C2470f7 f7Var = ZA1.e;
        if (map.containsKey(f7Var)) {
            uv0 = (C5092uV0) this.g.get(f7Var);
        }
        C3800mv mvVar = new C3800mv(null, this.f9088a, this.e, 0, null, this.c, this.d, uv0, false);
        Map map2 = mvVar.d;
        C4931ta taVar = new C4931ta();
        C4931ta taVar2 = new C4931ta();
        ArrayList arrayList = new ArrayList();
        for (C2470f7 f7Var2 : this.g.keySet()) {
            Object obj = this.g.get(f7Var2);
            boolean z = false;
            boolean z2 = map2.get(f7Var2) != null;
            taVar.put(f7Var2, Boolean.valueOf(z2));
            XB1 xb1 = new XB1(f7Var2, z2);
            arrayList.add(xb1);
            if (f7Var2.f9899a != null) {
                z = true;
            }
            SE0.k(z, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
            AbstractC2129d7 b2 = f7Var2.f9899a.b(this.f, this.i, mvVar, obj, xb1, xb1);
            taVar2.put(f7Var2.a(), b2);
            Objects.requireNonNull(b2);
        }
        VA1 va1 = new VA1(this.f, new ReentrantLock(), this.i, mvVar, this.j, this.k, taVar, this.l, this.m, taVar2, this.h, VA1.l(taVar2.values(), true), arrayList);
        Set set = YV.f9277a;
        synchronized (set) {
            set.add(va1);
        }
        if (this.h < 0) {
            return va1;
        }
        throw null;
    }
}
