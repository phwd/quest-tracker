package androidx.savedstate;

import X.AbstractC05180ub;
import X.AbstractC05230uw;
import X.AnonymousClass006;
import X.AnonymousClass0AO;
import X.AnonymousClass0AS;
import X.AnonymousClass0C2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
public final class Recreator implements AbstractC05230uw {
    public final AbstractC05180ub A00;

    @Override // X.AbstractC05230uw
    public final void A87(AnonymousClass0AS r7, AnonymousClass0AO r8) {
        Class cls;
        if (r8 == AnonymousClass0AO.ON_CREATE) {
            r7.getLifecycle().A07(this);
            AbstractC05180ub r5 = this.A00;
            Bundle A002 = r5.getSavedStateRegistry().A00("androidx.savedstate.Restarter");
            if (A002 != null) {
                ArrayList<String> stringArrayList = A002.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                Constructor<? extends U> declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(AnonymousClass0C2.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                                try {
                                    ((AnonymousClass0C2) declaredConstructor.newInstance(new Object[0])).A7j(r5);
                                } catch (Exception e) {
                                    throw new RuntimeException(AnonymousClass006.A07("Failed to instantiate ", next), e);
                                }
                            } catch (NoSuchMethodException e2) {
                                throw new IllegalStateException(AnonymousClass006.A09("Class", cls.getSimpleName(), " must have default constructor in order to be automatically recreated"), e2);
                            }
                        } catch (ClassNotFoundException e3) {
                            throw new RuntimeException(AnonymousClass006.A09("Class ", next, " wasn't found"), e3);
                        }
                    }
                    return;
                }
                throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            }
            return;
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }

    public Recreator(AbstractC05180ub r1) {
        this.A00 = r1;
    }
}
