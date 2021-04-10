package X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1q6  reason: invalid class name and case insensitive filesystem */
public final class C10861q6 extends SeekBar {
    public final C10851q5 A00;

    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        C10851q5 r5 = this.A00;
        if (r5.A00 != null) {
            SeekBar seekBar = r5.A05;
            int max = seekBar.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = r5.A00.getIntrinsicWidth();
                int intrinsicHeight = r5.A00.getIntrinsicHeight();
                int i2 = intrinsicWidth >> 1;
                if (intrinsicWidth < 0) {
                    i2 = 1;
                }
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight >> 1;
                }
                r5.A00.setBounds(-i2, -i, i2, i);
                float width = ((float) ((seekBar.getWidth() - seekBar.getPaddingLeft()) - seekBar.getPaddingRight())) / ((float) max);
                int save = canvas.save();
                canvas.translate((float) seekBar.getPaddingLeft(), (float) (seekBar.getHeight() >> 1));
                for (int i3 = 0; i3 <= max; i3++) {
                    r5.A00.draw(canvas);
                    canvas.translate(width, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    public C10861q6(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        C10891q9.A03(this, getContext());
        C10851q5 r0 = new C10851q5(this);
        this.A00 = r0;
        r0.A01(attributeSet, R.attr.seekBarStyle);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10851q5 r1 = this.A00;
        Drawable drawable = r1.A00;
        if (drawable != null && drawable.isStateful()) {
            SeekBar seekBar = r1.A05;
            if (drawable.setState(seekBar.getDrawableState())) {
                seekBar.invalidateDrawable(drawable);
            }
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.A00.A00;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }
}
