package defpackage;

import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: Ls1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Ls1 {

    /* renamed from: a  reason: collision with root package name */
    public final C4931ta f8442a;
    public final C4931ta b;
    public final C4931ta c;

    public Ls1(C4931ta taVar, C4931ta taVar2, C4931ta taVar3) {
        this.f8442a = taVar;
        this.b = taVar2;
        this.c = taVar3;
    }

    public abstract void a();

    public abstract Ls1 b();

    public final Class c(Class cls) {
        Class cls2 = (Class) this.c.getOrDefault(cls.getName(), null);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.c.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method d(String str) {
        Method method = (Method) this.f8442a.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, Ls1.class.getClassLoader()).getDeclaredMethod("read", Ls1.class);
        this.f8442a.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method e(Class cls) {
        Method method = (Method) this.b.getOrDefault(cls.getName(), null);
        if (method != null) {
            return method;
        }
        Class c2 = c(cls);
        System.currentTimeMillis();
        Method declaredMethod = c2.getDeclaredMethod("write", cls, Ls1.class);
        this.b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public boolean f(boolean z, int i) {
        if (!h(i)) {
            return z;
        }
        return ((Ms1) this).e.readInt() != 0;
    }

    public CharSequence g(CharSequence charSequence, int i) {
        if (!h(i)) {
            return charSequence;
        }
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((Ms1) this).e);
    }

    public abstract boolean h(int i);

    public int i(int i, int i2) {
        if (!h(i2)) {
            return i;
        }
        return ((Ms1) this).e.readInt();
    }

    public Parcelable j(Parcelable parcelable, int i) {
        if (!h(i)) {
            return parcelable;
        }
        return ((Ms1) this).e.readParcelable(Ms1.class.getClassLoader());
    }

    public Ns1 k() {
        String readString = ((Ms1) this).e.readString();
        if (readString == null) {
            return null;
        }
        Ls1 b2 = b();
        try {
            return (Ns1) d(readString).invoke(null, b2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    public abstract void l(int i);

    public void m(int i, int i2) {
        l(i2);
        ((Ms1) this).e.writeInt(i);
    }

    public void n(Parcelable parcelable, int i) {
        l(i);
        ((Ms1) this).e.writeParcelable(parcelable, 0);
    }

    public void o(Ns1 ns1) {
        if (ns1 == null) {
            ((Ms1) this).e.writeString(null);
            return;
        }
        try {
            ((Ms1) this).e.writeString(c(ns1.getClass()).getName());
            Ls1 b2 = b();
            try {
                e(ns1.getClass()).invoke(null, ns1, b2);
                b2.a();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e2.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(ns1.getClass().getSimpleName() + " does not have a Parcelizer", e5);
        }
    }
}
