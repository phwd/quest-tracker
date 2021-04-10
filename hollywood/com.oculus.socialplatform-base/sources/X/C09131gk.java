package X;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/* renamed from: X.1gk  reason: invalid class name and case insensitive filesystem */
public final class C09131gk extends Drawable.ConstantState {
    @VisibleForTesting
    public final AnonymousClass1g5 A00;

    public final int getChangingConfigurations() {
        return 0;
    }

    public C09131gk(AnonymousClass1g5 r1) {
        this.A00 = r1;
    }

    @NonNull
    public final Drawable newDrawable() {
        return new AnonymousClass1gA(this);
    }

    @NonNull
    public final Drawable newDrawable(Resources resources) {
        return newDrawable();
    }
}
