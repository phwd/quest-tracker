package android.arch.lifecycle;

import android.arch.lifecycle.ClassesInfoCache;
import android.arch.lifecycle.Lifecycle;

/* access modifiers changed from: package-private */
public class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    private final ClassesInfoCache.CallbackInfo mInfo = ClassesInfoCache.sInstance.getInfo(this.mWrapped.getClass());
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object wrapped) {
        this.mWrapped = wrapped;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        this.mInfo.invokeCallbacks(source, event, this.mWrapped);
    }
}
