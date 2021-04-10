package X;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: X.0cl  reason: invalid class name and case insensitive filesystem */
public final class C03590cl extends AnonymousClass0Do {
    public static final AbstractC01120Dp A05 = new C03600cm();
    public boolean A00 = false;
    public final HashMap<String, C03590cl> A01 = new HashMap<>();
    public final HashMap<String, AnonymousClass0MN> A02 = new HashMap<>();
    public final HashMap<String, C01150Ds> A03 = new HashMap<>();
    public final boolean A04;

    @Override // X.AnonymousClass0Do
    public final void A00() {
        this.A00 = true;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C03590cl r5 = (C03590cl) obj;
            if (!this.A02.equals(r5.A02) || !this.A01.equals(r5.A01) || !this.A03.equals(r5.A03)) {
                return false;
            }
        }
        return true;
    }

    public final boolean A01(@NonNull AnonymousClass0MN r3) {
        if (!this.A02.containsKey(r3.A0P) || !this.A04) {
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
        Iterator<AnonymousClass0MN> it = this.A02.values().iterator();
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

    public C03590cl(boolean z) {
        this.A04 = z;
    }
}
