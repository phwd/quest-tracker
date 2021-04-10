package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: X.0vB  reason: invalid class name and case insensitive filesystem */
public final class C05280vB extends AnonymousClass0Ag {
    public static final AnonymousClass0Ah A05 = new C05290vC();
    public boolean A00 = false;
    public final HashMap<String, C05280vB> A01 = new HashMap<>();
    public final HashMap<String, Fragment> A02 = new HashMap<>();
    public final HashMap<String, C00470Ak> A03 = new HashMap<>();
    public final boolean A04;

    @Override // X.AnonymousClass0Ag
    public final void A00() {
        this.A00 = true;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C05280vB r5 = (C05280vB) obj;
            if (!this.A02.equals(r5.A02) || !this.A01.equals(r5.A01) || !this.A03.equals(r5.A03)) {
                return false;
            }
        }
        return true;
    }

    public final boolean A01(@NonNull Fragment fragment) {
        if (!this.A02.containsKey(fragment.A0P) || !this.A04) {
            return true;
        }
        return this.A00;
    }

    public final int hashCode() {
        return (((this.A02.hashCode() * 31) + this.A01.hashCode()) * 31) + this.A03.hashCode();
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.A02.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.A01.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.A03.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public C05280vB(boolean z) {
        this.A04 = z;
    }
}
