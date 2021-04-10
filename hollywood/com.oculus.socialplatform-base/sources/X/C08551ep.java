package X;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: X.1ep  reason: invalid class name and case insensitive filesystem */
public final class C08551ep implements AbstractC08541eo {
    public final Set<AbstractC08781fH<?>> A00 = Collections.newSetFromMap(new WeakHashMap());

    @Override // X.AbstractC08541eo
    public final void onDestroy() {
        for (AbstractC08541eo r0 : C08381eW.A03(this.A00)) {
            r0.onDestroy();
        }
    }

    @Override // X.AbstractC08541eo
    public final void onStart() {
        for (AbstractC08541eo r0 : C08381eW.A03(this.A00)) {
            r0.onStart();
        }
    }

    @Override // X.AbstractC08541eo
    public final void onStop() {
        for (AbstractC08541eo r0 : C08381eW.A03(this.A00)) {
            r0.onStop();
        }
    }
}
