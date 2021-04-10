package X;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class CO extends TX {
    public static CO A01;
    public Application A00;

    @Override // X.TX, X.C8
    @NonNull
    public final <T extends C7> T A1E(@NonNull Class<T> cls) {
        if (!Tf.class.isAssignableFrom(cls)) {
            return (T) super.A1E(cls);
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

    public CO(@NonNull Application application) {
        this.A00 = application;
    }
}
