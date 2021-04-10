package com.facebook.common.util.context.wrapper;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ContextWrapperUtils {
    public static Context getRootBaseContext(Context context) {
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

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

    public static Context findContextForService(Context context) {
        if (!(context instanceof Activity)) {
            return context;
        }
        Activity activity = (Activity) context;
        return activity.getParent() != null ? activity.getParent() : context;
    }
}
