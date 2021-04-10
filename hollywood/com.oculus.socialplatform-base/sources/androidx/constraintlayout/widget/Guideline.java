package androidx.constraintlayout.widget;

import X.AnonymousClass2a8;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Guideline extends View {
    public final void draw(Canvas canvas) {
    }

    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setVisibility(int i) {
    }

    public void setGuidelineBegin(int i) {
        AnonymousClass2a8 r0 = (AnonymousClass2a8) getLayoutParams();
        r0.A0d = i;
        setLayoutParams(r0);
    }

    public void setGuidelineEnd(int i) {
        AnonymousClass2a8 r0 = (AnonymousClass2a8) getLayoutParams();
        r0.A0e = i;
        setLayoutParams(r0);
    }

    public void setGuidelinePercent(float f) {
        AnonymousClass2a8 r0 = (AnonymousClass2a8) getLayoutParams();
        r0.A0H = f;
        setLayoutParams(r0);
    }

    public Guideline(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
