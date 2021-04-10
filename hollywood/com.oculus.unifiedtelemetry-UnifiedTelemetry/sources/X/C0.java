package X;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry$1;
import java.util.Map;

public final class C0 {
    public final Bz A00 = new Bz();
    public final Zg A01;

    @MainThread
    public final void A00(@Nullable Bundle bundle) {
        String str;
        Zg zg = this.A01;
        AP lifecycle = zg.getLifecycle();
        if (lifecycle.A05() == AO.INITIALIZED) {
            lifecycle.A06(new Recreator(zg));
            Bz bz = this.A00;
            if (!bz.A03) {
                if (bundle != null) {
                    bz.A01 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                lifecycle.A06(new SavedStateRegistry$1(bz));
                bz.A03 = true;
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
        Bz bz = this.A00;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = bz.A01;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        AnonymousClass2U<String, AbstractC0067By> r0 = bz.A02;
        C0301aZ aZVar = new C0301aZ(r0);
        r0.A03.put(aZVar, false);
        while (aZVar.hasNext()) {
            Map.Entry entry = (Map.Entry) aZVar.next();
            bundle2.putBundle((String) entry.getKey(), ((AbstractC0067By) entry.getValue()).A4p());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public C0(Zg zg) {
        this.A01 = zg;
    }
}
