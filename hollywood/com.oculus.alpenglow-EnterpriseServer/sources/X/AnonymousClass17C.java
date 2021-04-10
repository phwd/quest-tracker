package X;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.17C  reason: invalid class name */
public final class AnonymousClass17C extends ContextWrapper {
    public static final Object A01 = new Object();
    public final Resources A00;

    public static Context A00(@NonNull Context context) {
        if (!(context instanceof AnonymousClass17C) && !(context.getResources() instanceof AnonymousClass17B)) {
            context.getResources();
        }
        return context;
    }

    public final AssetManager getAssets() {
        return this.A00.getAssets();
    }

    public final Resources.Theme getTheme() {
        return super.getTheme();
    }

    public final Resources getResources() {
        return this.A00;
    }

    public final void setTheme(int i) {
        super.setTheme(i);
    }
}
