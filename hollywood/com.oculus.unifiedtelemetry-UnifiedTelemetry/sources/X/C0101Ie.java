package X;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.Ie  reason: case insensitive filesystem */
public class C0101Ie extends Zr {
    public static C0101Ie A01;
    public Application A00;

    @Override // X.Ag, X.Zr
    @NonNull
    public final <T extends Af> T A1d(@NonNull Class<T> cls) {
        if (!Zz.class.isAssignableFrom(cls)) {
            return (T) super.A1d(cls);
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

    public C0101Ie(@NonNull Application application) {
        this.A00 = application;
    }
}
