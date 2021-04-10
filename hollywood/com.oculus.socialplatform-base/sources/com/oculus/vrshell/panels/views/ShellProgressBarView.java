package com.oculus.vrshell.panels.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

@Deprecated
public class ShellProgressBarView extends View {
    public final Paint mProgressPaint;
    public float mProgressRatio;

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.deprecated_oc_gray_e_fix_me));
        canvas.drawRect(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) ((int) (((float) canvas.getWidth()) * this.mProgressRatio)), (float) canvas.getHeight(), this.mProgressPaint);
    }

    public void setProgressRatio(float f) {
        if (f >= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z && f <= 1.0f) {
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

    public ShellProgressBarView(Context context) {
        this(context, null);
    }

    public ShellProgressBarView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShellProgressBarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgressRatio = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        Paint paint = new Paint();
        this.mProgressPaint = paint;
        paint.setColor(getResources().getColor(R.color.deprecated_oc_blue_2_fix_me));
    }
}
