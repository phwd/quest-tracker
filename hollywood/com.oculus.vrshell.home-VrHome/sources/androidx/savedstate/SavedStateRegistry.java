package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Iterator;
import java.util.Map;

@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {
    boolean mAllowingSavingState = true;
    private SafeIterableMap<String, SavedStateProvider> mComponents = new SafeIterableMap<>();
    private boolean mRestored;
    private Bundle mRestoredState;

    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    public interface SavedStateProvider {
        Bundle saveState();
    }

    SavedStateRegistry() {
    }

    public Bundle consumeRestoredStateForKey(String key) {
        if (!this.mRestored) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        } else if (this.mRestoredState == null) {
            return null;
        } else {
            Bundle result = this.mRestoredState.getBundle(key);
            this.mRestoredState.remove(key);
            if (!this.mRestoredState.isEmpty()) {
                return result;
            }
            this.mRestoredState = null;
            return result;
        }
    }

    /* access modifiers changed from: package-private */
    public void performRestore(Lifecycle lifecycle, Bundle savedState) {
        if (this.mRestored) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (savedState != null) {
            this.mRestoredState = savedState.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.addObserver(new GenericLifecycleObserver() {
            /* class androidx.savedstate.SavedStateRegistry.AnonymousClass1 */

            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.mAllowingSavingState = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.mAllowingSavingState = false;
                }
            }
        });
        this.mRestored = true;
    }

    /* access modifiers changed from: package-private */
    public void performSave(Bundle outBundle) {
        Bundle components = new Bundle();
        if (this.mRestoredState != null) {
            components.putAll(this.mRestoredState);
        }
        Iterator<Map.Entry<String, SavedStateProvider>> it = this.mComponents.iteratorWithAdditions();
        while (it.hasNext()) {
            Map.Entry<String, SavedStateProvider> entry1 = it.next();
            components.putBundle(entry1.getKey(), entry1.getValue().saveState());
        }
        outBundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", components);
    }
}
