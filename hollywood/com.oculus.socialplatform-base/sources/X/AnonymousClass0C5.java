package X;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry$1;
import java.util.Map;

/* renamed from: X.0C5  reason: invalid class name */
public final class AnonymousClass0C5 {
    public final AnonymousClass0C4 A00 = new AnonymousClass0C4();
    public final AbstractC05180ub A01;

    @MainThread
    public final void A00(@Nullable Bundle bundle) {
        AbstractC05180ub r3 = this.A01;
        AnonymousClass0AQ lifecycle = r3.getLifecycle();
        if (lifecycle.A05() == AnonymousClass0AP.INITIALIZED) {
            lifecycle.A06(new Recreator(r3));
            AnonymousClass0C4 r1 = this.A00;
            if (!r1.A03) {
                if (bundle != null) {
                    r1.A01 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                lifecycle.A06(new SavedStateRegistry$1(r1));
                r1.A03 = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    @MainThread
    public final void A01(@NonNull Bundle bundle) {
        AnonymousClass0C4 r1 = this.A00;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = r1.A01;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        AnonymousClass02b<String, AnonymousClass0C3> r0 = r1.A02;
        AnonymousClass0wk r2 = new AnonymousClass0wk(r0);
        r0.A03.put(r2, false);
        while (r2.hasNext()) {
            Map.Entry entry = (Map.Entry) r2.next();
            bundle2.putBundle((String) entry.getKey(), ((AnonymousClass0C3) entry.getValue()).A9U());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public AnonymousClass0C5(AbstractC05180ub r2) {
        this.A01 = r2;
    }
}
