package X;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.alpenglow.R;

/* renamed from: X.04J  reason: invalid class name */
public final class AnonymousClass04J extends RatingBar {
    public final AnonymousClass04I A00;

    public final synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmap = this.A00.A00;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }

    public AnonymousClass04J(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ratingBarStyle);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04I r0 = new AnonymousClass04I(this);
        this.A00 = r0;
        r0.A01(attributeSet, R.attr.ratingBarStyle);
    }
}
