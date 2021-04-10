package androidx.savedstate;

import X.AbstractC01030Da;
import X.AbstractC03380cC;
import X.AbstractC03550cd;
import X.AnonymousClass006;
import X.AnonymousClass0DW;
import X.AnonymousClass0GH;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
public final class Recreator implements AbstractC03550cd {
    public final AbstractC03380cC A00;

    @Override // X.AbstractC03550cd
    public final void A6c(AbstractC01030Da r7, AnonymousClass0DW r8) {
        Throwable e;
        StringBuilder sb;
        Class cls;
        Constructor<? extends U> declaredConstructor;
        if (r8 == AnonymousClass0DW.ON_CREATE) {
            r7.getLifecycle().A07(this);
            AbstractC03380cC r5 = this.A00;
            Bundle A002 = r5.getSavedStateRegistry().A00("androidx.savedstate.Restarter");
            if (A002 != null) {
                ArrayList<String> stringArrayList = A002.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(AnonymousClass0GH.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                            } catch (NoSuchMethodException e2) {
                                throw new IllegalStateException(AnonymousClass006.A07("Class", cls.getSimpleName(), " must have default constructor in order to be automatically recreated"), e2);
                            }
                            try {
                                ((AnonymousClass0GH) declaredConstructor.newInstance(new Object[0])).A6R(r5);
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

    public Recreator(AbstractC03380cC r1) {
        this.A00 = r1;
    }
}
