package X;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.3k  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00293k {
    public final AnonymousClass0m A00;
    public final AnonymousClass0m A01;
    public final AnonymousClass0m A02;

    private final AbstractC00293k A03() {
        C0665eH eHVar = (C0665eH) this;
        Parcel parcel = eHVar.A05;
        int dataPosition = parcel.dataPosition();
        int i = eHVar.A02;
        if (i == eHVar.A04) {
            i = eHVar.A03;
        }
        return new C0665eH(parcel, dataPosition, i, AnonymousClass08.A04(eHVar.A07, "  "), ((AbstractC00293k) eHVar).A01, ((AbstractC00293k) eHVar).A02, ((AbstractC00293k) eHVar).A00);
    }

    private final void A05() {
        C0665eH eHVar = (C0665eH) this;
        int i = eHVar.A00;
        if (i >= 0) {
            int i2 = eHVar.A06.get(i);
            Parcel parcel = eHVar.A05;
            int dataPosition = parcel.dataPosition();
            parcel.setDataPosition(i2);
            parcel.writeInt(dataPosition - i2);
            parcel.setDataPosition(dataPosition);
        }
    }

    public final Parcelable A02() {
        C0665eH eHVar = (C0665eH) this;
        return eHVar.A05.readParcelable(eHVar.getClass().getClassLoader());
    }

    public final AbstractC00303l A04() {
        String readString = ((C0665eH) this).A05.readString();
        if (readString == null) {
            return null;
        }
        AbstractC00293k A03 = A03();
        try {
            AnonymousClass0m r4 = this.A01;
            Method method = (Method) r4.get(readString);
            if (method == null) {
                method = Class.forName(readString, true, AbstractC00293k.class.getClassLoader()).getDeclaredMethod("read", AbstractC00293k.class);
                r4.put(readString, method);
            }
            return (AbstractC00303l) method.invoke(null, A03);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw cause;
            } else if (cause instanceof Error) {
                throw cause;
            } else {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException(e4);
        }
    }

    public final void A06(int i) {
        C0665eH eHVar = (C0665eH) this;
        eHVar.A05();
        eHVar.A00 = i;
        eHVar.A06.put(i, eHVar.A05.dataPosition());
        eHVar.A07(0);
        eHVar.A07(i);
    }

    public final void A07(int i) {
        ((C0665eH) this).A05.writeInt(i);
    }

    public final boolean A09(int i) {
        C0665eH eHVar = (C0665eH) this;
        while (true) {
            int i2 = eHVar.A02;
            if (i2 < eHVar.A03) {
                int i3 = eHVar.A01;
                if (i3 != i) {
                    if (String.valueOf(i3).compareTo(String.valueOf(i)) > 0) {
                        break;
                    }
                    Parcel parcel = eHVar.A05;
                    parcel.setDataPosition(i2);
                    int readInt = parcel.readInt();
                    eHVar.A01 = parcel.readInt();
                    eHVar.A02 += readInt;
                } else {
                    return true;
                }
            } else if (eHVar.A01 == i) {
                return true;
            }
        }
        return false;
    }

    private Class A00(Class cls) {
        AnonymousClass0m r5 = this.A00;
        String name = cls.getName();
        Class cls2 = (Class) r5.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        r5.put(name, cls3);
        return cls3;
    }

    public final void A08(AbstractC00303l r9) {
        if (r9 == null) {
            ((C0665eH) this).A05.writeString(null);
            return;
        }
        try {
            Class<?> cls = r9.getClass();
            ((C0665eH) this).A05.writeString(A00(cls).getName());
            AbstractC00293k A03 = A03();
            try {
                AnonymousClass0m r5 = this.A02;
                String name = cls.getName();
                Method method = (Method) r5.get(name);
                if (method == null) {
                    method = A00(cls).getDeclaredMethod("write", cls, AbstractC00293k.class);
                    r5.put(name, method);
                }
                method.invoke(null, r9, A03);
                A03.A05();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof RuntimeException) {
                    throw cause;
                } else if (cause instanceof Error) {
                    throw cause;
                } else {
                    throw new RuntimeException(e2);
                }
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException(e3);
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException(e4);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(AnonymousClass08.A04(r9.getClass().getSimpleName(), " does not have a Parcelizer"), e5);
        }
    }

    public AbstractC00293k(AnonymousClass0m r1, AnonymousClass0m r2, AnonymousClass0m r3) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
    }

    public final int A01(int i, int i2) {
        if (A09(i2)) {
            return ((C0665eH) this).A05.readInt();
        }
        return i;
    }
}
