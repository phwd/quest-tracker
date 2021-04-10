package defpackage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: eV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2360eV extends AbstractC2790h {
    public static Map b = new ConcurrentHashMap();
    public C5998zp1 c = C5998zp1.f11772a;
    public int d = -1;

    public static AbstractC2360eV f(Class cls) {
        AbstractC2360eV eVVar = (AbstractC2360eV) b.get(cls);
        if (eVVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                eVVar = (AbstractC2360eV) b.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (eVVar == null) {
            eVVar = (AbstractC2360eV) ((AbstractC2360eV) Op1.a(cls)).e(EnumC2190dV.GET_DEFAULT_INSTANCE, null, null);
            if (eVVar != null) {
                b.put(cls, eVVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return eVVar;
    }

    public static Object h(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static E30 j(E30 e30) {
        int size = e30.size();
        return e30.d(size == 0 ? 10 : size * 2);
    }

    public static AbstractC2360eV k(AbstractC2360eV eVVar, byte[] bArr) {
        int length = bArr.length;
        UM a2 = UM.a();
        AbstractC2360eV eVVar2 = (AbstractC2360eV) eVVar.e(EnumC2190dV.NEW_MUTABLE_INSTANCE, null, null);
        try {
            UO0 b2 = C2163dI0.f9768a.b(eVVar2);
            b2.e(eVVar2, bArr, 0, length + 0, new C3566la(a2));
            b2.c(eVVar2);
            if (eVVar2.f10040a != 0) {
                throw new RuntimeException();
            } else if (eVVar2.i()) {
                return eVVar2;
            } else {
                throw new L30(new C5488wp1().getMessage());
            }
        } catch (IOException e) {
            if (e.getCause() instanceof L30) {
                throw ((L30) e.getCause());
            }
            throw new L30(e.getMessage());
        } catch (IndexOutOfBoundsException unused) {
            throw L30.e();
        }
    }

    public Object d(EnumC2190dV dVVar) {
        return e(dVVar, null, null);
    }

    public abstract Object e(EnumC2190dV dVVar, Object obj, Object obj2);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return C2163dI0.f9768a.b(this).f(this, (AbstractC2360eV) obj);
        }
        return false;
    }

    public int g() {
        if (this.d == -1) {
            this.d = C2163dI0.f9768a.b(this).g(this);
        }
        return this.d;
    }

    public int hashCode() {
        int i = this.f10040a;
        if (i != 0) {
            return i;
        }
        int i2 = C2163dI0.f9768a.b(this).i(this);
        this.f10040a = i2;
        return i2;
    }

    public final boolean i() {
        byte byteValue = ((Byte) e(EnumC2190dV.GET_MEMOIZED_IS_INITIALIZED, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean d2 = C2163dI0.f9768a.b(this).d(this);
        e(EnumC2190dV.SET_MEMOIZED_IS_INITIALIZED, d2 ? this : null, null);
        return d2;
    }

    public void l(C6014zv zvVar) {
        UO0 b2 = C2163dI0.f9768a.b(this);
        C0112Bv bv = zvVar.b;
        if (bv == null) {
            bv = new C0112Bv(zvVar);
        }
        b2.a(this, bv);
    }

    public String toString() {
        String obj = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(obj);
        AbstractC1186Tj0.c(this, sb, 0);
        return sb.toString();
    }
}
