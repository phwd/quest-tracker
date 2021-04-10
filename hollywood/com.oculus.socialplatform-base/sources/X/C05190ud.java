package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.0ud  reason: invalid class name and case insensitive filesystem */
public final class C05190ud implements AnonymousClass0C3 {
    public final Set<String> A00 = new HashSet();

    @Override // X.AnonymousClass0C3
    @NonNull
    public final Bundle A9U() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.A00));
        return bundle;
    }

    public C05190ud(AnonymousClass0C4 r3) {
        if (r3.A02.A02("androidx.savedstate.Restarter", this) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }
}
