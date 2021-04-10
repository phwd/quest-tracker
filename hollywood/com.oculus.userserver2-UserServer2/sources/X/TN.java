package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class TN implements Dr {
    public final Set<String> A00 = new HashSet();

    @Override // X.Dr
    @NonNull
    public final Bundle A3Q() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.A00));
        return bundle;
    }

    public TN(Ds ds) {
        if (ds.A02.A02("androidx.savedstate.Restarter", this) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }
}
