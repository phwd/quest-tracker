package androidx.savedstate;

import X.AbstractC07170rP;
import X.AbstractC07290ro;
import X.AnonymousClass006;
import X.AnonymousClass0AN;
import X.AnonymousClass0AR;
import X.AnonymousClass0By;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
public final class Recreator implements AbstractC07290ro {
    public final AbstractC07170rP A00;

    @Override // X.AbstractC07290ro
    public final void A70(AnonymousClass0AR r7, AnonymousClass0AN r8) {
        Throwable e;
        StringBuilder sb;
        Class cls;
        Constructor<? extends U> declaredConstructor;
        if (r8 == AnonymousClass0AN.ON_CREATE) {
            r7.getLifecycle().A07(this);
            AbstractC07170rP r5 = this.A00;
            Bundle A002 = r5.getSavedStateRegistry().A00("androidx.savedstate.Restarter");
            if (A002 != null) {
                ArrayList<String> stringArrayList = A002.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(AnonymousClass0By.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                            } catch (NoSuchMethodException e2) {
                                throw new IllegalStateException(AnonymousClass006.A07("Class", cls.getSimpleName(), " must have default constructor in order to be automatically recreated"), e2);
                            }
                            try {
                                ((AnonymousClass0By) declaredConstructor.newInstance(new Object[0])).A6h(r5);
                            } catch (Exception e3) {
                                e = e3;
                                sb = new StringBuilder("Failed to instantiate ");
                                sb.append(next);
                                throw new RuntimeException(sb.toString(), e);
                            }
                        } catch (ClassNotFoundException e4) {
                            e = e4;
                            sb = new StringBuilder("Class ");
                            sb.append(next);
                            sb.append(" wasn't found");
                            throw new RuntimeException(sb.toString(), e);
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

    public Recreator(AbstractC07170rP r1) {
        this.A00 = r1;
    }
}
