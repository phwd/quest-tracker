package com.facebook.common.util.context;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.facebook.common.util.context.wrapper.ContextWrapperUtils;
import com.facebook.debug.log.BLog;
import com.google.common.base.Optional;
import javax.annotation.Nullable;

public class ContextUtils {
    private static final String TAG = "ContextUtils";

    public static Context getRootBaseContext(Context context) {
        return ContextWrapperUtils.getRootBaseContext(context);
    }

    @Nullable
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> cls) {
        return (T) ContextWrapperUtils.findContextOfType(context, cls);
    }

    public static Context findContextForService(Context context) {
        return ContextWrapperUtils.findContextForService(context);
    }

    public static Context createThemeWrappedContext(Context context, int i, int i2) {
        return new ContextThemeWrapper(context, getResourceIdFromTheme(context, i, i2));
    }

    public static int getResourceIdFromTheme(Context context, int i, int i2) {
        Optional<Integer> resourceIdFromTheme = getResourceIdFromTheme(context, i);
        return resourceIdFromTheme.isPresent() ? resourceIdFromTheme.get().intValue() : i2;
    }

    public static Optional<Integer> getResourceIdFromTheme(@Nullable Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context != null && context.getTheme().resolveAttribute(i, typedValue, true)) {
            if (typedValue.resourceId != 0) {
                return Optional.of(Integer.valueOf(typedValue.resourceId));
            }
            BLog.e(TAG, "Theme.resolveAttribute resolves the attrId:%s to resId 0, type:%02X", context.getResources().getResourceName(i), Integer.valueOf(typedValue.type));
        }
        return Optional.absent();
    }

    public static int getColorFromTheme(Context context, int i, int i2) {
        Optional<Integer> colorFromTheme = getColorFromTheme(context, i);
        return colorFromTheme.isPresent() ? colorFromTheme.get().intValue() : i2;
    }

    public static Optional<Integer> getIntFromTheme(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return Optional.of(Integer.valueOf(typedValue.data));
        }
        return Optional.absent();
    }

    public static int getIntFromTheme(Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i, typedValue, true) ? typedValue.data : i2;
    }

    public static int getDimensionPixelSizeFromTheme(Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i, typedValue, true) ? TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics()) : i2;
    }

    public static Optional<Float> getFloatFromTheme(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return Optional.of(Float.valueOf(typedValue.getFloat()));
        }
        return Optional.absent();
    }

    public static Optional<Integer> getColorFromTheme(Context context, int i) {
        return getIntFromTheme(context, i);
    }

    public static Optional<Drawable> getDrawableFromTheme(Context context, int i) {
        Resources resources = context.getResources();
        Optional<Integer> resourceIdFromTheme = getResourceIdFromTheme(context, i);
        if (resourceIdFromTheme.isPresent()) {
            return Optional.of(resources.getDrawable(resourceIdFromTheme.get().intValue()));
        }
        return Optional.absent();
    }

    public static float getFloatFromTheme(Context context, int i, float f) {
        return getFloatFromTheme(context, i).or(Float.valueOf(f)).floatValue();
    }

    public static Drawable getDrawableFromTheme(Context context, int i, int i2) {
        Optional<Drawable> drawableFromTheme = getDrawableFromTheme(context, i);
        if (drawableFromTheme.isPresent()) {
            return drawableFromTheme.get();
        }
        return context.getResources().getDrawable(i2);
    }

    public static Optional<CharSequence> getCharSequenceFromTheme(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return Optional.of(typedValue.string);
        }
        return Optional.absent();
    }
}
