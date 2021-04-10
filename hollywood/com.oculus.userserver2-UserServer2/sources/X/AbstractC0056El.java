package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
/* renamed from: X.El  reason: case insensitive filesystem */
public abstract class AbstractC0056El {
    public final AnonymousClass3C<String, Class<?>> A00;
    public final AnonymousClass3C<String, Method> A01;
    public final AnonymousClass3C<String, Method> A02;

    private final AbstractC0056El A03() {
        TK tk = (TK) this;
        Parcel parcel = tk.A05;
        int dataPosition = parcel.dataPosition();
        int i = tk.A02;
        if (i == tk.A04) {
            i = tk.A03;
        }
        return new TK(parcel, dataPosition, i, AnonymousClass06.A03(tk.A07, "  "), ((AbstractC0056El) tk).A01, ((AbstractC0056El) tk).A02, ((AbstractC0056El) tk).A00);
    }

    private final void A05() {
        TK tk = (TK) this;
        int i = tk.A00;
        if (i >= 0) {
            int i2 = tk.A06.get(i);
            Parcel parcel = tk.A05;
            int dataPosition = parcel.dataPosition();
            parcel.setDataPosition(i2);
            parcel.writeInt(dataPosition - i2);
            parcel.setDataPosition(dataPosition);
        }
    }

    public final <T extends Parcelable> T A02() {
        TK tk = (TK) this;
        return (T) tk.A05.readParcelable(tk.getClass().getClassLoader());
    }

    public final <T extends AbstractC0059Eo> T A04() {
        String readString = ((TK) this).A05.readString();
        if (readString == null) {
            return null;
        }
        AbstractC0056El A03 = A03();
        try {
            AnonymousClass3C<String, Method> r3 = this.A01;
            Method method = r3.get(readString);
            if (method == null) {
                method = Class.forName(readString, true, AbstractC0056El.class.getClassLoader()).getDeclaredMethod("read", AbstractC0056El.class);
                r3.put(readString, method);
            }
            return (T) ((AbstractC0059Eo) method.invoke(null, A03));
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

    public final void A06(int i) {
        TK tk = (TK) this;
        tk.A05();
        tk.A00 = i;
        tk.A06.put(i, tk.A05.dataPosition());
        tk.A07(0);
        tk.A07(i);
    }

    public final void A07(int i) {
        ((TK) this).A05.writeInt(i);
    }

    public final boolean A09(int i) {
        TK tk = (TK) this;
        while (true) {
            int i2 = tk.A02;
            if (i2 < tk.A03) {
                int i3 = tk.A01;
                if (i3 != i) {
                    if (String.valueOf(i3).compareTo(String.valueOf(i)) > 0) {
                        break;
                    }
                    Parcel parcel = tk.A05;
                    parcel.setDataPosition(i2);
                    int readInt = parcel.readInt();
                    tk.A01 = parcel.readInt();
                    tk.A02 += readInt;
                } else {
                    return true;
                }
            } else if (tk.A01 == i) {
                return true;
            }
        }
        return false;
    }

    private Class<?> A00(Class<?> cls) throws ClassNotFoundException {
        AnonymousClass3C<String, Class<?>> r4 = this.A00;
        String name = cls.getName();
        Class<?> cls2 = r4.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        r4.put(name, cls3);
        return cls3;
    }

    public final void A08(AbstractC0059Eo eo) {
        if (eo == null) {
            ((TK) this).A05.writeString(null);
            return;
        }
        try {
            Class<?> cls = eo.getClass();
            ((TK) this).A05.writeString(A00(cls).getName());
            AbstractC0056El A03 = A03();
            try {
                AnonymousClass3C<String, Method> r4 = this.A02;
                String name = cls.getName();
                Method method = r4.get(name);
                if (method == null) {
                    method = A00(cls).getDeclaredMethod("write", cls, AbstractC0056El.class);
                    r4.put(name, method);
                }
                method.invoke(null, eo, A03);
                A03.A05();
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
            throw new RuntimeException(AnonymousClass06.A03(eo.getClass().getSimpleName(), " does not have a Parcelizer"), e3);
        }
    }

    public AbstractC0056El(AnonymousClass3C<String, Method> r1, AnonymousClass3C<String, Method> r2, AnonymousClass3C<String, Class<?>> r3) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
    }

    public final int A01(int i, int i2) {
        if (A09(i2)) {
            return ((TK) this).A05.readInt();
        }
        return i;
    }
}
