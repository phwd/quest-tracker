package androidx.lifecycle;

import java.util.HashMap;

public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    /* access modifiers changed from: package-private */
    public final void put(String key, ViewModel viewModel) {
        ViewModel oldViewModel = this.mMap.put(key, viewModel);
        if (oldViewModel != null) {
            oldViewModel.onCleared();
        }
    }

    /* access modifiers changed from: package-private */
    public final ViewModel get(String key) {
        return this.mMap.get(key);
    }

    public final void clear() {
        for (ViewModel vm : this.mMap.values()) {
            vm.clear();
        }
        this.mMap.clear();
    }
}
