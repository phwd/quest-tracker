package X;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;

/* renamed from: X.04C  reason: invalid class name */
public final class AnonymousClass04C {
    public ColorStateList A00 = null;
    public PorterDuff.Mode A01 = null;
    public boolean A02 = false;
    public boolean A03 = false;
    public boolean A04;
    @NonNull
    public final CompoundButton A05;

    public static final void A00(AnonymousClass04C r3) {
        CompoundButton compoundButton = r3.A05;
        Drawable buttonDrawable = compoundButton.getButtonDrawable();
        if (buttonDrawable == null) {
            return;
        }
        if (r3.A02 || r3.A03) {
            Drawable mutate = buttonDrawable.mutate();
            if (r3.A02) {
                mutate.setTintList(r3.A00);
            }
            if (r3.A03) {
                mutate.setTintMode(r3.A01);
            }
            if (mutate.isStateful()) {
                mutate.setState(compoundButton.getDrawableState());
            }
            compoundButton.setButtonDrawable(mutate);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(@androidx.annotation.Nullable android.util.AttributeSet r10, int r11) {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04C.A01(android.util.AttributeSet, int):void");
    }

    public AnonymousClass04C(@NonNull CompoundButton compoundButton) {
        this.A05 = compoundButton;
    }
}
