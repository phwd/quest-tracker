package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

/* renamed from: X.1pV  reason: invalid class name */
public final class AnonymousClass1pV {
    public static volatile boolean A00 = true;

    public static Drawable A00(Context context, Context context2, @DrawableRes int i, @Nullable Resources.Theme theme) {
        try {
            if (A00) {
                Context context3 = context2;
                if (theme != null) {
                    context3 = new AnonymousClass1rZ(context2, theme);
                }
                return AnonymousClass1pW.A00(context3, i);
            }
        } catch (NoClassDefFoundError unused) {
            A00 = false;
        } catch (IllegalStateException e) {
            if (!context.getPackageName().equals(context2.getPackageName())) {
                return context2.getDrawable(i);
            }
            throw e;
        } catch (Resources.NotFoundException unused2) {
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return context2.getResources().getDrawable(i, theme);
    }
}
