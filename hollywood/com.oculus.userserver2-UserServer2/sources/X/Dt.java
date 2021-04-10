package X;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry$1;
import java.util.Map;

public final class Dt {
    public final Ds A00 = new Ds();
    public final TM A01;

    @MainThread
    public final void A00(@Nullable Bundle bundle) {
        String str;
        TM tm = this.A01;
        AbstractC0041Bq lifecycle = tm.getLifecycle();
        if (((Tc) lifecycle).A02 == EnumC0040Bp.INITIALIZED) {
            lifecycle.A05(new Recreator(tm));
            Ds ds = this.A00;
            if (!ds.A03) {
                if (bundle != null) {
                    ds.A01 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                lifecycle.A05(new SavedStateRegistry$1(ds));
                ds.A03 = true;
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
        Ds ds = this.A00;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = ds.A01;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        AnonymousClass2h<String, Dr> r0 = ds.A02;
        UE ue = new UE(r0);
        r0.A03.put(ue, false);
        while (ue.hasNext()) {
            Map.Entry entry = (Map.Entry) ue.next();
            bundle2.putBundle((String) entry.getKey(), ((Dr) entry.getValue()).A3Q());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public Dt(TM tm) {
        this.A01 = tm;
    }
}
