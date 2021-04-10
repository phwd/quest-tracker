package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1pk  reason: invalid class name and case insensitive filesystem */
public interface AbstractC10831pk {
    Drawable A2R(@NonNull C10821pj v, @NonNull Context context, @DrawableRes int i);

    ColorStateList A51(@NonNull Context context, @DrawableRes int i);

    PorterDuff.Mode A52(int i);

    boolean AAh(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);

    boolean AAi(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);
}
