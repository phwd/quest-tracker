package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* renamed from: X.09j  reason: invalid class name */
public final class AnonymousClass09j {
    public final ArrayList<Fragment> A00 = new ArrayList<>();
    public final HashMap<String, AnonymousClass09i> A01 = new HashMap<>();

    @NonNull
    public final List<Fragment> A00() {
        Fragment fragment;
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass09i r0 : this.A01.values()) {
            if (r0 != null) {
                fragment = r0.A01;
            } else {
                fragment = null;
            }
            arrayList.add(fragment);
        }
        return arrayList;
    }

    @NonNull
    public final List<Fragment> A01() {
        ArrayList arrayList;
        ArrayList<Fragment> arrayList2 = this.A00;
        if (arrayList2.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (arrayList2) {
            arrayList = new ArrayList(arrayList2);
        }
        return arrayList;
    }

    public final void A02(@NonNull Fragment fragment) {
        ArrayList<Fragment> arrayList = this.A00;
        if (!arrayList.contains(fragment)) {
            synchronized (arrayList) {
                arrayList.add(fragment);
            }
            fragment.A0Q = true;
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment already added: ");
        sb.append(fragment);
        throw new IllegalStateException(sb.toString());
    }
}
