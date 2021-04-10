package X;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;

@RequiresApi(24)
/* renamed from: X.0HA  reason: invalid class name */
public class AnonymousClass0HA extends Drawable.ConstantState {
    public final Drawable.ConstantState A00;

    public final boolean canApplyTheme() {
        return this.A00.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.A00.getChangingConfigurations();
    }

    public AnonymousClass0HA(Drawable.ConstantState constantState) {
        this.A00 = constantState;
    }

    public final Drawable newDrawable() {
        AnonymousClass0Lz r1 = new AnonymousClass0Lz();
        ((AnonymousClass0c5) r1).A00 = this.A00.newDrawable();
        return r1;
    }

    public final Drawable newDrawable(Resources resources) {
        AnonymousClass0Lz r1 = new AnonymousClass0Lz();
        ((AnonymousClass0c5) r1).A00 = this.A00.newDrawable(resources);
        return r1;
    }

    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        AnonymousClass0Lz r1 = new AnonymousClass0Lz();
        ((AnonymousClass0c5) r1).A00 = this.A00.newDrawable(resources, theme);
        return r1;
    }
}
