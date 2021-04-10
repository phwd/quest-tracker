package X;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: X.0rk  reason: invalid class name and case insensitive filesystem */
public final class C07260rk implements AnonymousClass0By {
    @Override // X.AnonymousClass0By
    public final void A6h(@NonNull AbstractC07170rP r6) {
        if (r6 instanceof AbstractC00530Ak) {
            C00520Aj viewModelStore = ((AbstractC00530Ak) r6).getViewModelStore();
            AnonymousClass0C0 savedStateRegistry = r6.getSavedStateRegistry();
            HashMap<String, AnonymousClass0Af> hashMap = viewModelStore.A00;
            for (Object obj : new HashSet(hashMap.keySet())) {
                SavedStateHandleController.A01(hashMap.get(obj), savedStateRegistry, r6.getLifecycle());
            }
            if (!new HashSet(hashMap.keySet()).isEmpty()) {
                savedStateRegistry.A01();
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
    }
}
