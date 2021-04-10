package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

/* access modifiers changed from: package-private */
@SuppressLint({"RestrictedApi"})
public final class Recreator implements GenericLifecycleObserver {
    private final SavedStateRegistryOwner mOwner;

    Recreator(SavedStateRegistryOwner owner) {
        this.mOwner = owner;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        source.getLifecycle().removeObserver(this);
        Bundle bundle = this.mOwner.getSavedStateRegistry().consumeRestoredStateForKey("androidx.savedstate.Restarter");
        if (bundle != null) {
            ArrayList<String> classes = bundle.getStringArrayList("classes_to_restore");
            if (classes == null) {
                throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            }
            Iterator<String> it = classes.iterator();
            while (it.hasNext()) {
                reflectiveNew(it.next());
            }
        }
    }

    private void reflectiveNew(String className) {
        try {
            Class<? extends SavedStateRegistry.AutoRecreated> clazz = Class.forName(className, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class);
            try {
                Constructor<? extends SavedStateRegistry.AutoRecreated> constructor = clazz.getDeclaredConstructor(new Class[0]);
                constructor.setAccessible(true);
                try {
                    ((SavedStateRegistry.AutoRecreated) constructor.newInstance(new Object[0])).onRecreated(this.mOwner);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate " + className, e);
                }
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException("Class" + clazz.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("Class " + className + " wasn't found", e3);
        }
    }
}
