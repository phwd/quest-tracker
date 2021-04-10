package a.c;

import a.a.g;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: chromium-webapk7.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public final g f9401a;
    public final g b;
    public final g c;

    public b(g gVar, g gVar2, g gVar3) {
        this.f9401a = gVar;
        this.b = gVar2;
        this.c = gVar3;
    }

    public abstract void a();

    public abstract b b();

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
        Method method = (Method) this.f9401a.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, b.class.getClassLoader()).getDeclaredMethod("read", b.class);
        this.f9401a.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method e(Class cls) {
        Method method = (Method) this.b.getOrDefault(cls.getName(), null);
        if (method != null) {
            return method;
        }
        Class c2 = c(cls);
        System.currentTimeMillis();
        Method declaredMethod = c2.getDeclaredMethod("write", cls, b.class);
        this.b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public boolean f(boolean z, int i) {
        if (!h(i)) {
            return z;
        }
        return ((c) this).e.readInt() != 0;
    }

    public CharSequence g(CharSequence charSequence, int i) {
        if (!h(i)) {
            return charSequence;
        }
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((c) this).e);
    }

    public abstract boolean h(int i);

    public int i(int i, int i2) {
        if (!h(i2)) {
            return i;
        }
        return ((c) this).e.readInt();
    }

    public Parcelable j(Parcelable parcelable, int i) {
        if (!h(i)) {
            return parcelable;
        }
        return ((c) this).e.readParcelable(c.class.getClassLoader());
    }

    public d k() {
        String readString = ((c) this).e.readString();
        if (readString == null) {
            return null;
        }
        b b2 = b();
        try {
            return (d) d(readString).invoke(null, b2);
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
        ((c) this).e.writeInt(i);
    }

    public void n(Parcelable parcelable, int i) {
        l(i);
        ((c) this).e.writeParcelable(parcelable, 0);
    }

    public void o(d dVar) {
        if (dVar == null) {
            ((c) this).e.writeString(null);
            return;
        }
        try {
            ((c) this).e.writeString(c(dVar.getClass()).getName());
            b b2 = b();
            try {
                e(dVar.getClass()).invoke(null, dVar, b2);
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
            throw new RuntimeException(dVar.getClass().getSimpleName() + " does not have a Parcelizer", e5);
        }
    }
}
