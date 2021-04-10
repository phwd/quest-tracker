package X;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* renamed from: X.6k  reason: invalid class name */
public final class AnonymousClass6k {
    public static AnonymousClass6k A07;
    public final Context A00;
    public final ArrayList A01 = new ArrayList();
    public final ArrayList A02 = new ArrayList();
    public final ArrayList A03 = new ArrayList();
    public final ArrayList A04 = new ArrayList();
    public final ArrayList A05 = new ArrayList();
    public final ArrayList A06 = new ArrayList();

    public static synchronized Object A01(AnonymousClass6k r5, ArrayList arrayList, Class cls) {
        synchronized (r5) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = arrayList.get(i);
                if (obj.getClass().equals(cls)) {
                    return obj;
                }
            }
            Object newInstance = cls.getConstructor(Context.class).newInstance(r5.A00);
            arrayList.add(newInstance);
            return newInstance;
        }
    }

    public static synchronized Object A02(AnonymousClass6k r5, ArrayList arrayList, String str) {
        Object obj;
        synchronized (r5) {
            try {
                obj = A01(r5, arrayList, Class.forName(str));
            } catch (ClassNotFoundException e) {
                C0139Dd.A0V("ContextConstructorHelper", e, "Cannot find class: %s", str);
            } catch (IllegalAccessException e2) {
                C0139Dd.A0U("ContextConstructorHelper", e2, "IllegalAccessException");
            } catch (InstantiationException e3) {
                C0139Dd.A0U("ContextConstructorHelper", e3, "InstantiationException");
            } catch (NoSuchMethodException e4) {
                C0139Dd.A0U("ContextConstructorHelper", e4, "NoSuchMethodException");
            } catch (InvocationTargetException e5) {
                C0139Dd.A0U("ContextConstructorHelper", e5, "InvocationTargetException");
            }
        }
        return obj;
        obj = null;
        return obj;
    }

    public static synchronized AnonymousClass6k A00(Context context) {
        AnonymousClass6k r1;
        synchronized (AnonymousClass6k.class) {
            r1 = A07;
            if (r1 == null) {
                r1 = new AnonymousClass6k(context.getApplicationContext());
                A07 = r1;
            }
        }
        return r1;
    }

    public AnonymousClass6k(Context context) {
        this.A00 = context;
    }
}
