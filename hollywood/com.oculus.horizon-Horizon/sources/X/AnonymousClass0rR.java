package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.0rR  reason: invalid class name */
public final class AnonymousClass0rR implements AbstractC00650Bz {
    public final Set<String> A00 = new HashSet();

    @Override // X.AbstractC00650Bz
    @NonNull
    public final Bundle A8R() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.A00));
        return bundle;
    }

    public AnonymousClass0rR(AnonymousClass0C0 r3) {
        if (r3.A02.A02("androidx.savedstate.Restarter", this) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }
}
