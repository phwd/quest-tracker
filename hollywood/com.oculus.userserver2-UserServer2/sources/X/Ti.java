package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Iterator;

public final class Ti extends C7 {
    public static final C8 A05 = new Tj();
    public boolean A00 = false;
    public final HashMap<String, Ti> A01 = new HashMap<>();
    public final HashMap<String, Fragment> A02 = new HashMap<>();
    public final HashMap<String, CB> A03 = new HashMap<>();
    public final boolean A04;

    @Override // X.C7
    public final void A00() {
        this.A00 = true;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Ti ti = (Ti) obj;
            if (!this.A02.equals(ti.A02) || !this.A01.equals(ti.A01) || !this.A03.equals(ti.A03)) {
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

    public Ti(boolean z) {
        this.A04 = z;
    }
}
