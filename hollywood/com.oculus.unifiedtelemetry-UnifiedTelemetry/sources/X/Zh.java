package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class Zh implements AbstractC0067By {
    public final Set<String> A00 = new HashSet();

    @Override // X.AbstractC0067By
    @NonNull
    public final Bundle A4p() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.A00));
        return bundle;
    }

    public Zh(Bz bz) {
        if (bz.A02.A02("androidx.savedstate.Restarter", this) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }
}
