package com.facebook.common.util.context.wrapper;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ContextWrapperUtils {
    public static Context getRootBaseContext(Context context) {
        Context baseContext = context;
        while (baseContext instanceof ContextWrapper) {
            baseContext = ((ContextWrapper) baseContext).getBaseContext();
        }
        return baseContext;
    }

    @Nullable
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> clazz) {
        while (!clazz.isInstance(context)) {
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (context == baseContext) {
                return null;
            }
            context = (T) baseContext;
        }
        return (T) context;
    }

    public static Context findContextForService(Context context) {
        if (!(context instanceof Activity)) {
            return context;
        }
        Activity activity = (Activity) context;
        if (activity.getParent() != null) {
            return activity.getParent();
        }
        return context;
    }
}
