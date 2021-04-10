package androidx.databinding.adapters;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil {
    private static final SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray<>();

    public static <T> T trackListener(View view, T t, int i) {
        WeakReference<?> weakReference;
        if (Build.VERSION.SDK_INT >= 14) {
            T t2 = (T) view.getTag(i);
            view.setTag(i, t);
            return t2;
        }
        synchronized (sListeners) {
            WeakHashMap<View, WeakReference<?>> weakHashMap = sListeners.get(i);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
                sListeners.put(i, weakHashMap);
            }
            if (t == null) {
                weakReference = weakHashMap.remove(view);
            } else {
                weakReference = weakHashMap.put(view, new WeakReference<>(t));
            }
            if (weakReference == null) {
                return null;
            }
            return (T) weakReference.get();
        }
    }

    public static <T> T getListener(View view, int i) {
        if (Build.VERSION.SDK_INT >= 14) {
            return (T) view.getTag(i);
        }
        synchronized (sListeners) {
            WeakHashMap<View, WeakReference<?>> weakHashMap = sListeners.get(i);
            if (weakHashMap == null) {
                return null;
            }
            WeakReference<?> weakReference = weakHashMap.get(view);
            if (weakReference == null) {
                return null;
            }
            return (T) weakReference.get();
        }
    }
}
