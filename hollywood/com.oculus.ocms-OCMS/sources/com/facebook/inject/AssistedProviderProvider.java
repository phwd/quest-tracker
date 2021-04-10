package com.facebook.inject;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.build.BuildConstants;
import com.google.common.base.Throwables;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

@SuppressLint({"ExplicitComplexProvider", "FbInjectorGet"})
public class AssistedProviderProvider<T> extends AbstractProvider<AssistedProvider<T>> {
    private final Class<AssistedProvider<T>> mAssistedProviderClass;
    @Nullable
    private volatile Constructor<AssistedProvider<T>> mAssistedProviderConstructor;

    public AssistedProviderProvider(Class<AssistedProvider<T>> cls) {
        this.mAssistedProviderClass = cls;
        if (BuildConstants.isInternalBuild()) {
            getConstructor();
        }
    }

    @Override // javax.inject.Provider
    public AssistedProvider<T> get() {
        return createAssistedProvider();
    }

    private AssistedProvider<T> createAssistedProvider() {
        try {
            return getConstructor().newInstance((InjectorLike) getScopeAwareInjector());
        } catch (InstantiationException e) {
            throw Throwables.propagate(e);
        } catch (IllegalAccessException e2) {
            throw Throwables.propagate(e2);
        } catch (InvocationTargetException e3) {
            throw Throwables.propagate(e3);
        }
    }

    private Constructor<AssistedProvider<T>> getConstructor() {
        Constructor<AssistedProvider<T>> constructor = this.mAssistedProviderConstructor;
        if (constructor != null) {
            return constructor;
        }
        try {
            Constructor<AssistedProvider<T>> declaredConstructor = this.mAssistedProviderClass.getDeclaredConstructor(InjectorLike.class);
            this.mAssistedProviderConstructor = declaredConstructor;
            return declaredConstructor;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Assisted provider " + this.mAssistedProviderClass + " doesn't have default constructor.", e);
        }
    }

    @VisibleForTesting
    public Class<? extends AssistedProvider<T>> getAssistedProviderClass() {
        return this.mAssistedProviderClass;
    }
}
