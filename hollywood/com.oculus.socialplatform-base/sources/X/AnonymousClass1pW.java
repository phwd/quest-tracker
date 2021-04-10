package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.WeakHashMap;

@SuppressLint({"RestrictedAPI"})
/* renamed from: X.1pW  reason: invalid class name */
public final class AnonymousClass1pW {
    public static final ThreadLocal<TypedValue> A00 = new ThreadLocal<>();
    public static final Object A01 = new Object();
    public static final WeakHashMap<Context, SparseArray<AppCompatResources.ColorStateListCacheEntry>> A02 = new WeakHashMap<>(0);

    @Nullable
    public static Drawable A00(@NonNull Context context, @DrawableRes int i) {
        return C10821pj.A01().A04(context, i);
    }
}
