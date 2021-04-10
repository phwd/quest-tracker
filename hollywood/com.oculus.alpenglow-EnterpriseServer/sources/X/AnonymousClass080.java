package X;

import android.content.res.ColorStateList;
import android.graphics.Shader;
import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.080  reason: invalid class name */
public final class AnonymousClass080 {
    public int A00;
    public final ColorStateList A01;
    public final Shader A02;

    public final boolean A00() {
        ColorStateList colorStateList;
        if (this.A02 != null || (colorStateList = this.A01) == null || !colorStateList.isStateful()) {
            return false;
        }
        return true;
    }

    public AnonymousClass080(Shader shader, ColorStateList colorStateList, @ColorInt int i) {
        this.A02 = shader;
        this.A01 = colorStateList;
        this.A00 = i;
    }
}
