package com.facebook.inject;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.UL;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Ultralight {
    public static <T> T get(Class<? extends T> clazz, Context context) {
        return (T) get(clazz, null, context);
    }

    public static <T> T get(Class<? extends T> clazz) {
        return (T) get(clazz, (Class) null);
    }

    public static <T> T get(Class<? extends T> clazz, @Nullable Class bindingAnnotation, Context context) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return (T) get(RuntimeBindingIdUtils.getBindingIdFromClasses(clazz, bindingAnnotation), context);
    }

    public static <T> T get(Class<? extends T> clazz, @Nullable Class bindingAnnotation) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return (T) get(RuntimeBindingIdUtils.getBindingIdFromClasses(clazz, bindingAnnotation));
    }

    @SuppressLint({"BadArgument-FbInjector#get-0"})
    public static <T> T get(int bindingId, Context context) {
        return (T) UL.factorymap.get(bindingId, FbInjector.get(context));
    }

    @SuppressLint({"BadArgument-FbInjector#get-0"})
    public static <T> T get(int bindingId) {
        return (T) UL.factorymap.get(bindingId, FbInjector.get(FbInjectorImpl.getMainThreadInjectorThreadStack().getContext()));
    }
}
