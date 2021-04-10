package X;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry$1;
import java.util.Map;

/* renamed from: X.0GK  reason: invalid class name */
public final class AnonymousClass0GK {
    public final AnonymousClass0GJ A00 = new AnonymousClass0GJ();
    public final AbstractC03380cC A01;

    @MainThread
    public final void A00(@Nullable Bundle bundle) {
        String str;
        AbstractC03380cC r3 = this.A01;
        AnonymousClass0DY lifecycle = r3.getLifecycle();
        if (lifecycle.A05() == AnonymousClass0DX.INITIALIZED) {
            lifecycle.A06(new Recreator(r3));
            AnonymousClass0GJ r1 = this.A00;
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
        AnonymousClass0GJ r1 = this.A00;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = r1.A01;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C005905t<String, AnonymousClass0GI> r0 = r1.A02;
        C03990dm r2 = new C03990dm(r0);
        r0.A03.put(r2, false);
        while (r2.hasNext()) {
            Map.Entry entry = (Map.Entry) r2.next();
            bundle2.putBundle((String) entry.getKey(), ((AnonymousClass0GI) entry.getValue()).A7X());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public AnonymousClass0GK(AbstractC03380cC r2) {
        this.A01 = r2;
    }
}
