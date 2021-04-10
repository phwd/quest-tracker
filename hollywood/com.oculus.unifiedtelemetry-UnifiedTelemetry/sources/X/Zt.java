package X;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;
import java.util.HashSet;

public final class Zt implements AbstractC0066Bx {
    @Override // X.AbstractC0066Bx
    public final void A3r(@NonNull Zg zg) {
        if (zg instanceof AbstractC0047Ak) {
            C0046Aj viewModelStore = ((AbstractC0047Ak) zg).getViewModelStore();
            Bz savedStateRegistry = zg.getSavedStateRegistry();
            HashMap<String, Af> hashMap = viewModelStore.A00;
            for (Object obj : new HashSet(hashMap.keySet())) {
                SavedStateHandleController.A01(hashMap.get(obj), savedStateRegistry, zg.getLifecycle());
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
