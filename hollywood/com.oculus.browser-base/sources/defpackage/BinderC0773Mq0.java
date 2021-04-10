package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Field;

/* renamed from: Mq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC0773Mq0 extends AbstractBinderC2658gC1 implements VY {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8506a;

    public BinderC0773Mq0(Object obj) {
        super("com.google.android.gms.dynamic.IObjectWrapper");
        this.f8506a = obj;
    }

    public static VY d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
        if (queryLocalInterface instanceof VY) {
            return (VY) queryLocalInterface;
        }
        return new UY(iBinder);
    }

    public static Object f(VY vy) {
        if (vy instanceof BinderC0773Mq0) {
            return ((BinderC0773Mq0) vy).f8506a;
        }
        IBinder asBinder = vy.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i++;
                field = field2;
            }
        }
        if (i != 1) {
            throw new IllegalArgumentException(AbstractC2531fV.s(64, "Unexpected number of IObjectWrapper declared fields: ", declaredFields.length));
        } else if (!field.isAccessible()) {
            field.setAccessible(true);
            try {
                return field.get(asBinder);
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("Binder object is null.", e);
            } catch (IllegalAccessException e2) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", e2);
            }
        } else {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
    }
}
