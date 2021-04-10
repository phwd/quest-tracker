package com.oculus.vrshell.panels.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.common.vrshellutil.R;

public class ShellProgressBarView extends View {
    private final Paint mProgressPaint;
    private float mProgressRatio;

    public ShellProgressBarView(Context context) {
        this(context, null);
    }

    public ShellProgressBarView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShellProgressBarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgressRatio = 0.0f;
        this.mProgressPaint = new Paint();
        this.mProgressPaint.setColor(getResources().getColor(R.color.deprecated_oc_blue_2_fix_me));
    }

    public void setProgressRatio(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.mProgressRatio = f;
        }
        invalidate();
    }

    public float getProgressRatio() {
        return this.mProgressRatio;
    }

    public void setPaintColor(int i) {
        this.mProgressPaint.setColor(i);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.deprecated_oc_gray_e_fix_me));
        canvas.drawRect(0.0f, 0.0f, (float) ((int) (((float) canvas.getWidth()) * this.mProgressRatio)), (float) canvas.getHeight(), this.mProgressPaint);
    }
}
