package X;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;
import java.util.HashSet;

public final class TZ implements Dq {
    @Override // X.Dq
    public final void A2Z(@NonNull TM tm) {
        if (tm instanceof CC) {
            CB viewModelStore = ((CC) tm).getViewModelStore();
            Ds savedStateRegistry = tm.getSavedStateRegistry();
            HashMap<String, C7> hashMap = viewModelStore.A00;
            for (Object obj : new HashSet(hashMap.keySet())) {
                SavedStateHandleController.A01(hashMap.get(obj), savedStateRegistry, tm.getLifecycle());
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
