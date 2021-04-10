package X;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;

@RequiresApi(24)
/* renamed from: X.1pz  reason: invalid class name */
public class AnonymousClass1pz extends Drawable.ConstantState {
    public final Drawable.ConstantState A00;

    public final boolean canApplyTheme() {
        return this.A00.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.A00.getChangingConfigurations();
    }

    public AnonymousClass1pz(Drawable.ConstantState constantState) {
        this.A00 = constantState;
    }

    public final Drawable newDrawable() {
        C10921qD r1 = new C10921qD();
        ((AbstractC10301ln) r1).A00 = this.A00.newDrawable();
        return r1;
    }

    public final Drawable newDrawable(Resources resources) {
        C10921qD r1 = new C10921qD();
        ((AbstractC10301ln) r1).A00 = this.A00.newDrawable(resources);
        return r1;
    }

    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        C10921qD r1 = new C10921qD();
        ((AbstractC10301ln) r1).A00 = this.A00.newDrawable(resources, theme);
        return r1;
    }
}
