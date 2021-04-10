package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.17H  reason: invalid class name */
public interface AnonymousClass17H {
    Drawable A1y(@NonNull AnonymousClass17F v, @NonNull Context context, @DrawableRes int i);

    ColorStateList A4f(@NonNull Context context, @DrawableRes int i);

    PorterDuff.Mode A4g(int i);

    boolean A8a(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);

    boolean A8b(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);
}
