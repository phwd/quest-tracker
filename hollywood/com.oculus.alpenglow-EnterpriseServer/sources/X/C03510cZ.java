package X;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: X.0cZ  reason: invalid class name and case insensitive filesystem */
public final class C03510cZ implements AnonymousClass0GH {
    @Override // X.AnonymousClass0GH
    public final void A6R(@NonNull AbstractC03380cC r6) {
        if (r6 instanceof AbstractC01160Dt) {
            C01150Ds viewModelStore = ((AbstractC01160Dt) r6).getViewModelStore();
            AnonymousClass0GJ savedStateRegistry = r6.getSavedStateRegistry();
            HashMap<String, AnonymousClass0Do> hashMap = viewModelStore.A00;
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
