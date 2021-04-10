package androidx.savedstate;

import X.AnonymousClass06;
import X.Bs;
import X.Dq;
import X.EnumC0039Bo;
import X.TM;
import X.Tc;
import X.Td;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
public final class Recreator implements Td {
    public final TM A00;

    @Override // X.Td
    public final void A2i(Bs bs, EnumC0039Bo bo) {
        Throwable e;
        StringBuilder sb;
        Class cls;
        Constructor<? extends U> declaredConstructor;
        if (bo == EnumC0039Bo.ON_CREATE) {
            ((Tc) bs.getLifecycle()).A01.A01(this);
            TM tm = this.A00;
            Bundle A002 = tm.getSavedStateRegistry().A00("androidx.savedstate.Restarter");
            if (A002 != null) {
                ArrayList<String> stringArrayList = A002.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(Dq.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                            } catch (NoSuchMethodException e2) {
                                throw new IllegalStateException(AnonymousClass06.A04("Class", cls.getSimpleName(), " must have default constructor in order to be automatically recreated"), e2);
                            }
                            try {
                                ((Dq) declaredConstructor.newInstance(new Object[0])).A2Z(tm);
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

    public Recreator(TM tm) {
        this.A00 = tm;
    }
}
