package X;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

/* renamed from: X.1q7  reason: invalid class name and case insensitive filesystem */
public final class C10871q7 extends RatingBar {
    public final C10801ph A00;

    public final synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmap = this.A00.A00;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }

    public C10871q7(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ratingBarStyle);
        C10891q9.A03(this, getContext());
        C10801ph r0 = new C10801ph(this);
        this.A00 = r0;
        r0.A01(attributeSet, R.attr.ratingBarStyle);
    }
}
