package X;

import android.app.Application;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0MB  reason: invalid class name */
public class AnonymousClass0MB extends C03490cX {
    public static AnonymousClass0MB A01;
    public Application A00;

    @Override // X.C03490cX, X.AbstractC01120Dp
    @NonNull
    public final <T extends AnonymousClass0Do> T A1t(@NonNull Class<T> cls) {
        if (!C03560cf.class.isAssignableFrom(cls)) {
            return (T) super.A1t(cls);
        }
        try {
            return cls.getConstructor(Application.class).newInstance(this.A00);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + cls, e);
        }
    }

    public AnonymousClass0MB(@NonNull Application application) {
        this.A00 = application;
    }
}
