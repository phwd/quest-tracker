package androidx.savedstate;

import X.AN;
import X.AR;
import X.AbstractC0066Bx;
import X.AnonymousClass06;
import X.Zg;
import X.Zx;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
public final class Recreator implements Zx {
    public final Zg A00;

    @Override // X.Zx
    public final void A42(AR ar, AN an) {
        Throwable e;
        StringBuilder sb;
        Class cls;
        Constructor<? extends U> declaredConstructor;
        if (an == AN.ON_CREATE) {
            ar.getLifecycle().A07(this);
            Zg zg = this.A00;
            Bundle A002 = zg.getSavedStateRegistry().A00("androidx.savedstate.Restarter");
            if (A002 != null) {
                ArrayList<String> stringArrayList = A002.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(AbstractC0066Bx.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                            } catch (NoSuchMethodException e2) {
                                throw new IllegalStateException(AnonymousClass06.A05("Class", cls.getSimpleName(), " must have default constructor in order to be automatically recreated"), e2);
                            }
                            try {
                                ((AbstractC0066Bx) declaredConstructor.newInstance(new Object[0])).A3r(zg);
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

    public Recreator(Zg zg) {
        this.A00 = zg;
    }
}
