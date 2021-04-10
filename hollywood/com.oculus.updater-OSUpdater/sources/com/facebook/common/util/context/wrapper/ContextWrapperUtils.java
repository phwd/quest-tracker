package com.facebook.common.util.context.wrapper;

import android.content.Context;
import android.content.ContextWrapper;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ContextWrapperUtils {
    @Nullable
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> cls) {
        Context baseContext;
        while (!cls.isInstance(context)) {
            if (!(context instanceof ContextWrapper) || context == (baseContext = ((ContextWrapper) context).getBaseContext())) {
                return null;
            }
            context = (T) baseContext;
        }
        return (T) context;
    }
}
