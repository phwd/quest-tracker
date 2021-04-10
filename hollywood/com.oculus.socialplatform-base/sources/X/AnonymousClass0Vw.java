package X;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0Vw  reason: invalid class name */
public class AnonymousClass0Vw extends C05220uq {
    public static AnonymousClass0Vw A01;
    public Application A00;

    @Override // X.C05220uq, X.AnonymousClass0Ah
    @NonNull
    public final <T extends AnonymousClass0Ag> T A2L(@NonNull Class<T> cls) {
        if (!C05240uy.class.isAssignableFrom(cls)) {
            return (T) super.A2L(cls);
        }
        try {
            return cls.getConstructor(Application.class).newInstance(this.A00);
        } catch (NoSuchMethodException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot create an instance of ");
            sb.append(cls);
            throw new RuntimeException(sb.toString(), e);
        } catch (IllegalAccessException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot create an instance of ");
            sb2.append(cls);
            throw new RuntimeException(sb2.toString(), e2);
        } catch (InstantiationException e3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Cannot create an instance of ");
            sb3.append(cls);
            throw new RuntimeException(sb3.toString(), e3);
        } catch (InvocationTargetException e4) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Cannot create an instance of ");
            sb4.append(cls);
            throw new RuntimeException(sb4.toString(), e4);
        }
    }

    public AnonymousClass0Vw(@NonNull Application application) {
        this.A00 = application;
    }
}
