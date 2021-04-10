package android.arch.lifecycle;

@Deprecated
public interface LifecycleRegistryOwner extends LifecycleOwner {
    @Override // android.arch.lifecycle.LifecycleOwner
    LifecycleRegistry getLifecycle();
}
