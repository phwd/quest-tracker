package X;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: X.0us  reason: invalid class name */
public final class AnonymousClass0us implements AnonymousClass0C2 {
    @Override // X.AnonymousClass0C2
    public final void A7j(@NonNull AbstractC05180ub r6) {
        if (r6 instanceof AbstractC00480Al) {
            C00470Ak viewModelStore = ((AbstractC00480Al) r6).getViewModelStore();
            AnonymousClass0C4 savedStateRegistry = r6.getSavedStateRegistry();
            HashMap<String, AnonymousClass0Ag> hashMap = viewModelStore.A00;
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
