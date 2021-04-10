package X;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0Jk  reason: invalid class name and case insensitive filesystem */
public class C01050Jk extends C07240ri {
    public static C01050Jk A01;
    public Application A00;

    @Override // X.C07240ri, X.AnonymousClass0Ag
    @NonNull
    public final <T extends AnonymousClass0Af> T A1w(@NonNull Class<T> cls) {
        if (!AnonymousClass0rq.class.isAssignableFrom(cls)) {
            return (T) super.A1w(cls);
        }
        try {
            return cls.getConstructor(Application.class).newInstance(this.A00);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot create an instance of ");
            sb.append(cls);
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public C01050Jk(@NonNull Application application) {
        this.A00 = application;
    }
}
