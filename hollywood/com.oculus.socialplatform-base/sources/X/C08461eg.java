package X;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: X.1eg  reason: invalid class name and case insensitive filesystem */
public final class C08461eg implements AnonymousClass1hH {
    public boolean A00;
    public boolean A01;
    public final Set<AbstractC08541eo> A02 = Collections.newSetFromMap(new WeakHashMap());

    public final void A00() {
        this.A00 = true;
        for (AbstractC08541eo r0 : C08381eW.A03(this.A02)) {
            r0.onDestroy();
        }
    }

    public final void A01() {
        this.A01 = true;
        for (AbstractC08541eo r0 : C08381eW.A03(this.A02)) {
            r0.onStart();
        }
    }

    public final void A02() {
        this.A01 = false;
        for (AbstractC08541eo r0 : C08381eW.A03(this.A02)) {
            r0.onStop();
        }
    }

    @Override // X.AnonymousClass1hH
    public final void A1H(@NonNull AbstractC08541eo r2) {
        this.A02.add(r2);
        if (this.A00) {
            r2.onDestroy();
        } else if (this.A01) {
            r2.onStart();
        } else {
            r2.onStop();
        }
    }

    @Override // X.AnonymousClass1hH
    public final void A9A(@NonNull AbstractC08541eo r2) {
        this.A02.remove(r2);
    }
}
