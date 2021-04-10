package defpackage;

import java.util.HashMap;
import java.util.Iterator;

/* renamed from: NS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NS extends AbstractC4312pu1 {
    public static final AbstractC4483qu1 b = new MS();
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public final boolean f;
    public boolean g = false;

    public NS(boolean z) {
        this.f = z;
    }

    @Override // defpackage.AbstractC4312pu1
    public void a() {
        if (KS.R(3)) {
            String str = "onCleared called for " + this;
        }
        this.g = true;
    }

    public boolean b(AbstractComponentCallbacksC3550lS lSVar) {
        if (this.c.containsKey(lSVar.f10345J) && this.f) {
            return this.g;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NS.class != obj.getClass()) {
            return false;
        }
        NS ns = (NS) obj;
        return this.c.equals(ns.c) && this.d.equals(ns.d) && this.e.equals(ns.e);
    }

    public int hashCode() {
        int hashCode = this.d.hashCode();
        return this.e.hashCode() + ((hashCode + (this.c.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
