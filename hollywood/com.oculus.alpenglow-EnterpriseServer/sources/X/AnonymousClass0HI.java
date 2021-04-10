package X;

import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0HI  reason: invalid class name */
public abstract class AnonymousClass0HI {
    public final AnonymousClass06D<String, Class<?>> A00;
    public final AnonymousClass06D<String, Method> A01;
    public final AnonymousClass06D<String, Method> A02;

    public abstract int A01();

    public abstract <T extends Parcelable> T A03();

    public abstract AnonymousClass0HI A04();

    public abstract CharSequence A06();

    public abstract String A07();

    public abstract void A08();

    public abstract void A09(int i);

    public abstract void A0A(int i);

    public abstract void A0B(Parcelable parcelable);

    public abstract void A0D(CharSequence charSequence);

    public abstract void A0E(String str);

    public abstract void A0F(boolean z);

    public abstract void A0G(byte[] bArr);

    public abstract boolean A0H();

    public abstract boolean A0I(int i);

    public abstract byte[] A0J();

    private Class<?> A00(Class<?> cls) throws ClassNotFoundException {
        AnonymousClass06D<String, Class<?>> r4 = this.A00;
        String name = cls.getName();
        Class<?> cls2 = r4.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        r4.put(name, cls3);
        return cls3;
    }

    public final void A0C(AnonymousClass0HL r7) {
        if (r7 == null) {
            A0E(null);
            return;
        }
        try {
            Class<?> cls = r7.getClass();
            A0E(A00(cls).getName());
            AnonymousClass0HI A04 = A04();
            try {
                AnonymousClass06D<String, Method> r4 = this.A02;
                String name = cls.getName();
                Method method = r4.get(name);
                if (method == null) {
                    method = A00(cls).getDeclaredMethod("write", cls, AnonymousClass0HI.class);
                    r4.put(name, method);
                }
                method.invoke(null, r7, A04);
                A04.A08();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof RuntimeException) {
                    throw cause;
                } else if (!(cause instanceof Error)) {
                    throw new RuntimeException(e2);
                } else {
                    throw cause;
                }
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException(AnonymousClass006.A05(r7.getClass().getSimpleName(), " does not have a Parcelizer"), e3);
        }
    }

    public AnonymousClass0HI(AnonymousClass06D<String, Method> r1, AnonymousClass06D<String, Method> r2, AnonymousClass06D<String, Class<?>> r3) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
    }

    public final int A02(int i, int i2) {
        if (A0I(i2)) {
            return A01();
        }
        return i;
    }

    public final <T extends AnonymousClass0HL> T A05() {
        String A07 = A07();
        if (A07 == null) {
            return null;
        }
        AnonymousClass0HI A04 = A04();
        try {
            AnonymousClass06D<String, Method> r3 = this.A01;
            Method method = r3.get(A07);
            if (method == null) {
                method = Class.forName(A07, true, AnonymousClass0HI.class.getClassLoader()).getDeclaredMethod("read", AnonymousClass0HI.class);
                r3.put(A07, method);
            }
            return (T) ((AnonymousClass0HL) method.invoke(null, A04));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw cause;
            } else if (!(cause instanceof Error)) {
                throw new RuntimeException(e2);
            } else {
                throw cause;
            }
        }
    }
}
