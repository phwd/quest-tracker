package com.facebook.inject;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.UL;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Ultralight {
    public static <T> T get(Class<? extends T> cls, Context context) {
        return (T) get(cls, null, context);
    }

    public static <T> T get(Class<? extends T> cls) {
        return (T) get(cls, (Class) null);
    }

    public static <T> T get(Class<? extends T> cls, @Nullable Class cls2, Context context) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return (T) get(RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2), context);
    }

    public static <T> T get(Class<? extends T> cls, @Nullable Class cls2) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return (T) get(RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2));
    }

    @SuppressLint({"BadArgument-FbInjector#get-0"})
    public static <T> T get(int i, Context context) {
        return (T) UL.factorymap.get(i, FbInjector.get(context));
    }

    @SuppressLint({"BadArgument-FbInjector#get-0"})
    public static <T> T get(int i) {
        return (T) UL.factorymap.get(i, FbInjector.get(FbInjectorImpl.getMainThreadInjectorThreadStack().getContext()));
    }

    public static <T> Lazy<T> lazy(Class cls) {
        return lazy(cls, null);
    }

    public static <T> Lazy<T> lazy(Class cls, @Nullable Class cls2) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return new Lazy(RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2));
    }

    public static <T> Lazy<T> lazy(int i) {
        return new Lazy(i);
    }

    /* access modifiers changed from: private */
    public static class Lazy<T> implements Lazy<T> {
        private final int mBindingId;
        private T mInstance;

        private Lazy(int i) {
            this.mBindingId = i;
        }

        @Override // com.facebook.inject.Lazy, javax.inject.Provider
        public T get() {
            if (this.mInstance == null) {
                this.mInstance = (T) Ultralight.get(this.mBindingId);
            }
            return this.mInstance;
        }
    }
}
