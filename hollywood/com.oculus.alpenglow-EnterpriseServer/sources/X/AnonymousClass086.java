package X;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import java.util.List;

/* renamed from: X.086  reason: invalid class name */
public final class AnonymousClass086 {
    public final float[] A00;
    public final int[] A01;

    public AnonymousClass086(@ColorInt int i, @ColorInt int i2) {
        this.A01 = new int[]{i, i2};
        this.A00 = new float[]{0.0f, 1.0f};
    }

    public AnonymousClass086(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        this.A01 = new int[]{i, i2, i3};
        this.A00 = new float[]{0.0f, 0.5f, 1.0f};
    }

    public AnonymousClass086(@NonNull List<Integer> list, @NonNull List<Float> list2) {
        int size = list.size();
        this.A01 = new int[size];
        this.A00 = new float[size];
        for (int i = 0; i < size; i++) {
            this.A01[i] = list.get(i).intValue();
            this.A00[i] = list2.get(i).floatValue();
        }
    }
}
