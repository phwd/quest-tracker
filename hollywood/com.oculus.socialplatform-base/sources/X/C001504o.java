package X;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.List;

/* renamed from: X.04o  reason: invalid class name and case insensitive filesystem */
public final class C001504o {
    public final float[] A00;
    public final int[] A01;

    public C001504o(@ColorInt int i, @ColorInt int i2) {
        this.A01 = new int[]{i, i2};
        this.A00 = new float[]{AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 1.0f};
    }

    public C001504o(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        this.A01 = new int[]{i, i2, i3};
        this.A00 = new float[]{AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 0.5f, 1.0f};
    }

    public C001504o(@NonNull List<Integer> list, @NonNull List<Float> list2) {
        int size = list.size();
        this.A01 = new int[size];
        this.A00 = new float[size];
        for (int i = 0; i < size; i++) {
            this.A01[i] = list.get(i).intValue();
            this.A00[i] = list2.get(i).floatValue();
        }
    }
}
