package defpackage;

import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: mK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3706mK0 implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final JO0 f10415a;

    public C3706mK0(JO0 jo0) {
        this.f10415a = jo0;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        Class cls;
        if (j80 == EnumC3157j80.ON_CREATE) {
            r80.Q().b(this);
            HO0 g = this.f10415a.g();
            if (g.c) {
                Bundle bundle = g.b;
                Bundle bundle2 = null;
                if (bundle != null) {
                    Bundle bundle3 = bundle.getBundle("androidx.savedstate.Restarter");
                    g.b.remove("androidx.savedstate.Restarter");
                    if (g.b.isEmpty()) {
                        g.b = null;
                    }
                    bundle2 = bundle3;
                }
                if (bundle2 != null) {
                    ArrayList<String> stringArrayList = bundle2.getStringArrayList("classes_to_restore");
                    if (stringArrayList != null) {
                        Iterator<String> it = stringArrayList.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            try {
                                try {
                                    Constructor<? extends U> declaredConstructor = Class.forName(next, false, C3706mK0.class.getClassLoader()).asSubclass(FO0.class).getDeclaredConstructor(new Class[0]);
                                    declaredConstructor.setAccessible(true);
                                    try {
                                        ((FO0) declaredConstructor.newInstance(new Object[0])).a(this.f10415a);
                                    } catch (Exception e) {
                                        throw new RuntimeException(AbstractC2531fV.f("Failed to instantiate ", next), e);
                                    }
                                } catch (NoSuchMethodException e2) {
                                    StringBuilder i = AbstractC2531fV.i("Class");
                                    i.append(cls.getSimpleName());
                                    i.append(" must have default constructor in order to be automatically recreated");
                                    throw new IllegalStateException(i.toString(), e2);
                                }
                            } catch (ClassNotFoundException e3) {
                                throw new RuntimeException(AbstractC2531fV.g("Class ", next, " wasn't found"), e3);
                            }
                        }
                        return;
                    }
                    throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                return;
            }
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }
}
