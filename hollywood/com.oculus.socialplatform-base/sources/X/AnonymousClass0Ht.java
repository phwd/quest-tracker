package X;

import android.annotation.SuppressLint;
import com.facebook.inject.AssistedProvider;
import com.google.common.base.Throwables;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

@SuppressLint({"ExplicitComplexProvider", "FbInjectorGet"})
/* renamed from: X.0Ht  reason: invalid class name */
public final class AnonymousClass0Ht<T> extends AnonymousClass0VG<AssistedProvider<T>> {
    public final Class<AssistedProvider<T>> A00;
    @Nullable
    public volatile Constructor<AssistedProvider<T>> A01;

    public final Object get() {
        try {
            Constructor<AssistedProvider<T>> constructor = this.A01;
            if (constructor == null) {
                try {
                    constructor = this.A00.getDeclaredConstructor(AnonymousClass0lg.class);
                    this.A01 = constructor;
                } catch (NoSuchMethodException e) {
                    StringBuilder sb = new StringBuilder("Assisted provider ");
                    sb.append(this.A00);
                    sb.append(" doesn't have default constructor.");
                    throw new IllegalArgumentException(sb.toString(), e);
                }
            }
            constructor.newInstance(getScopeAwareInjector());
            return null;
        } catch (InstantiationException e2) {
            Throwables.propagate(e2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (IllegalAccessException e3) {
            Throwables.propagate(e3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (InvocationTargetException e4) {
            Throwables.propagate(e4);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public AnonymousClass0Ht(Class<AssistedProvider<T>> cls) {
        this.A00 = cls;
    }
}
