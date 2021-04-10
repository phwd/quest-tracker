package X;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* renamed from: X.0Cr  reason: invalid class name */
public final class AnonymousClass0Cr {
    public final ArrayList<AnonymousClass0MN> A00 = new ArrayList<>();
    public final HashMap<String, AnonymousClass0Cq> A01 = new HashMap<>();

    @NonNull
    public final List<AnonymousClass0MN> A00() {
        AnonymousClass0MN r0;
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass0Cq r02 : this.A01.values()) {
            if (r02 != null) {
                r0 = r02.A01;
            } else {
                r0 = null;
            }
            arrayList.add(r0);
        }
        return arrayList;
    }

    @NonNull
    public final List<AnonymousClass0MN> A01() {
        ArrayList arrayList;
        ArrayList<AnonymousClass0MN> arrayList2 = this.A00;
        if (arrayList2.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (arrayList2) {
            arrayList = new ArrayList(arrayList2);
        }
        return arrayList;
    }

    public final void A02(@NonNull AnonymousClass0MN r3) {
        ArrayList<AnonymousClass0MN> arrayList = this.A00;
        if (!arrayList.contains(r3)) {
            synchronized (arrayList) {
                arrayList.add(r3);
            }
            r3.A0Q = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + r3);
    }
}
