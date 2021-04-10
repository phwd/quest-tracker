package X;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry$1;
import java.util.Map;

/* renamed from: X.0C1  reason: invalid class name */
public final class AnonymousClass0C1 {
    public final AnonymousClass0C0 A00 = new AnonymousClass0C0();
    public final AbstractC07170rP A01;

    @MainThread
    public final void A00(@Nullable Bundle bundle) {
        String str;
        AbstractC07170rP r3 = this.A01;
        AnonymousClass0AP lifecycle = r3.getLifecycle();
        if (lifecycle.A05() == AnonymousClass0AO.INITIALIZED) {
            lifecycle.A06(new Recreator(r3));
            AnonymousClass0C0 r1 = this.A00;
            if (!r1.A03) {
                if (bundle != null) {
                    r1.A01 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                lifecycle.A06(new SavedStateRegistry$1(r1));
                r1.A03 = true;
                return;
            }
            str = "SavedStateRegistry was already restored.";
        } else {
            str = "Restarter must be created only during owner's initialization stage";
        }
        throw new IllegalStateException(str);
    }

    @MainThread
    public final void A01(@NonNull Bundle bundle) {
        AnonymousClass0C0 r1 = this.A00;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = r1.A01;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        AnonymousClass02U<String, AbstractC00650Bz> r0 = r1.A02;
        C07520sv r2 = new C07520sv(r0);
        r0.A03.put(r2, false);
        while (r2.hasNext()) {
            Map.Entry entry = (Map.Entry) r2.next();
            bundle2.putBundle((String) entry.getKey(), ((AbstractC00650Bz) entry.getValue()).A8R());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public AnonymousClass0C1(AbstractC07170rP r2) {
        this.A01 = r2;
    }
}
