package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

public final class AppCompatResources {
    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);

    private AppCompatResources() {
    }

    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList cachedColorStateList = getCachedColorStateList(context, i);
        if (cachedColorStateList != null) {
            return cachedColorStateList;
        }
        ColorStateList inflateColorStateList = inflateColorStateList(context, i);
        if (inflateColorStateList == null) {
            return ContextCompat.getColorStateList(context, i);
        }
        addColorStateListToCache(context, i, inflateColorStateList);
        return inflateColorStateList;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        return AppCompatDrawableManager.get().getDrawable(context, i);
    }

    @Nullable
    private static ColorStateList inflateColorStateList(Context context, int i) {
        if (isColorInt(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return AppCompatColorStateListInflater.createFromXml(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    @Nullable
    private static ColorStateList getCachedColorStateList(@NonNull Context context, @ColorRes int i) {
        ColorStateListCacheEntry colorStateListCacheEntry;
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> sparseArray = sColorStateCaches.get(context);
            if (!(sparseArray == null || sparseArray.size() <= 0 || (colorStateListCacheEntry = sparseArray.get(i)) == null)) {
                if (colorStateListCacheEntry.configuration.equals(context.getResources().getConfiguration())) {
                    return colorStateListCacheEntry.value;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    private static void addColorStateListToCache(@NonNull Context context, @ColorRes int i, @NonNull ColorStateList colorStateList) {
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> sparseArray = sColorStateCaches.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                sColorStateCaches.put(context, sparseArray);
            }
            sparseArray.append(i, new ColorStateListCacheEntry(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static boolean isColorInt(@NonNull Context context, @ColorRes int i) {
        Resources resources = context.getResources();
        TypedValue typedValue = getTypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type < 28 || typedValue.type > 31) {
            return false;
        }
        return true;
    }

    @NonNull
    private static TypedValue getTypedValue() {
        TypedValue typedValue = TL_TYPED_VALUE.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        TL_TYPED_VALUE.set(typedValue2);
        return typedValue2;
    }

    /* access modifiers changed from: private */
    public static class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(@NonNull ColorStateList colorStateList, @NonNull Configuration configuration2) {
            this.value = colorStateList;
            this.configuration = configuration2;
        }
    }
}
