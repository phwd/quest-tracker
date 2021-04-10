package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lm  reason: invalid class name */
public final class AnonymousClass1lm {
    public float A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public int A02 = 0;
    public int A03 = 0;
    public Integer A04 = AnonymousClass007.A01;
    public boolean A05 = false;
    @Nullable
    public float[] A06 = null;

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AnonymousClass1lm r4 = (AnonymousClass1lm) obj;
            if (this.A05 == r4.A05 && this.A03 == r4.A03 && Float.compare(r4.A00, this.A00) == 0 && this.A02 == r4.A02 && Float.compare(r4.A01, this.A01) == 0 && this.A04 == r4.A04) {
                return Arrays.equals(this.A06, r4.A06);
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        String str;
        Integer num = this.A04;
        int i4 = 0;
        if (num != null) {
            int intValue = num.intValue();
            if (1 != intValue) {
                str = "OVERLAY_COLOR";
            } else {
                str = "BITMAP_ONLY";
            }
            i = str.hashCode() + intValue;
        } else {
            i = 0;
        }
        int i5 = ((i * 31) + (this.A05 ? 1 : 0)) * 31;
        float[] fArr = this.A06;
        if (fArr != null) {
            i2 = Arrays.hashCode(fArr);
        } else {
            i2 = 0;
        }
        int i6 = (((i5 + i2) * 31) + this.A03) * 31;
        float f = this.A00;
        if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            i3 = Float.floatToIntBits(f);
        } else {
            i3 = 0;
        }
        int i7 = (((i6 + i3) * 31) + this.A02) * 31;
        float f2 = this.A01;
        if (f2 != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            i4 = Float.floatToIntBits(f2);
        }
        return ((((i7 + i4) * 31) + 0) * 31) + 0;
    }
}
