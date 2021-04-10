package defpackage;

import android.util.Pair;

/* renamed from: um  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5137um extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f11433a;
    public final /* synthetic */ Pair b;
    public final /* synthetic */ C5307vm c;

    public C5137um(C5307vm vmVar, boolean z, Pair pair) {
        this.c = vmVar;
        this.f11433a = z;
        this.b = pair;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2892hd hdVar = (C2892hd) obj;
        if (!hdVar.f9599a) {
            C5307vm vmVar = this.c;
            if (vmVar.c.t.g.contains(vmVar.f11496a.getBillingAddressId())) {
                C5307vm vmVar2 = this.c;
                vmVar2.c.t.s = vmVar2.f11496a.getBillingAddressId();
            } else {
                this.c.c.t.s = null;
            }
        } else {
            hdVar.l();
            if (this.f11433a) {
                this.c.c.e.remove(hdVar.m.getGUID());
                int i = 0;
                while (true) {
                    if (i >= this.c.b.size()) {
                        break;
                    } else if (((String) ((Pair) ((C3749me) this.c.b.get(i))).first).equals(hdVar.g)) {
                        this.c.b.remove(i);
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                this.c.c.d.add(hdVar.m);
            }
            this.c.b.add(0, new C3749me(hdVar.g, hdVar.c()));
            C5307vm vmVar3 = this.c;
            vmVar3.c.t.f(vmVar3.b);
            this.c.c.t.s = hdVar.g;
        }
        this.c.c.k.post((Runnable) this.b.second);
    }
}
