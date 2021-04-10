package com.oculus.panelapp.anytimeui.v2.layers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;

public class GuardianHandLayer extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(GuardianHandLayer.class);

    public enum Hand {
        Right,
        Left
    }

    public GuardianHandLayer(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void configure(Hand hand) {
        ((TextView) findViewById(R.id.guardian_hand_text)).setText(hand == Hand.Right ? R.string.guardian_hand_right : R.string.guardian_hand_left);
        constructDialogDrawables(hand);
    }

    private void constructDialogDrawables(Hand hand) {
        Resources resources = getResources();
        int[] iArr = {-65306, -8647169, -65306};
        GradientDrawable gradientDrawable = (GradientDrawable) ResourcesCompat.getDrawable(resources, R.drawable.guardian_controller_locator_outline_shape, null);
        if (gradientDrawable != null) {
            gradientDrawable.setGradientType(2);
            gradientDrawable.setColors(iArr);
            GradientDrawable gradientDrawable2 = (GradientDrawable) ResourcesCompat.getDrawable(resources, R.drawable.guardian_controller_locator_fill_shape, null);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.guardian_hand_dialog_circle_diameter);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.guardian_hand_dialog_line_thickness);
            int i = (dimensionPixelSize / 4) + dimensionPixelSize2;
            int i2 = ((dimensionPixelSize - i) / 2) + dimensionPixelSize2;
            int i3 = dimensionPixelSize / 2;
            int i4 = (dimensionPixelSize2 / 2) + i3;
            int i5 = hand == Hand.Right ? 1 : -1;
            GradientDrawable gradientDrawable3 = (GradientDrawable) ResourcesCompat.getDrawable(resources, R.drawable.guardian_controller_locator_arrow_shape, null).getConstantState().newDrawable().mutate();
            GradientDrawable gradientDrawable4 = (GradientDrawable) ResourcesCompat.getDrawable(resources, R.drawable.guardian_controller_locator_arrow_shape, null).getConstantState().newDrawable().mutate();
            if (gradientDrawable3 != null && gradientDrawable4 != null) {
                gradientDrawable3.setSize(dimensionPixelSize2, i);
                gradientDrawable4.setSize(i, dimensionPixelSize2);
                int[] iArr2 = {(-i4) * i5, i2};
                int[] iArr3 = {(-i2) * i5, i4};
                float f = (float) dimensionPixelSize2;
                float f2 = (float) i;
                float[] fArr = {(((float) (-iArr2[0])) / f) + 0.5f, (((float) (-iArr2[1])) / f2) + 0.5f};
                float[] fArr2 = {(((float) (-iArr3[0])) / f2) + 0.5f, (((float) (-iArr3[1])) / f) + 0.5f};
                gradientDrawable3.setGradientType(2);
                gradientDrawable3.setGradientCenter(fArr[0], fArr[1]);
                gradientDrawable3.setColors(iArr);
                gradientDrawable4.setGradientType(2);
                gradientDrawable4.setGradientCenter(fArr2[0], fArr2[1]);
                gradientDrawable4.setColors(iArr);
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable2, gradientDrawable, gradientDrawable3, gradientDrawable4});
                layerDrawable.setLayerGravity(0, 17);
                layerDrawable.setLayerGravity(1, 17);
                layerDrawable.setLayerGravity(2, 17);
                layerDrawable.setLayerInset(2, iArr2[0], iArr2[1], -iArr2[0], -iArr2[1]);
                layerDrawable.setLayerGravity(3, 17);
                layerDrawable.setLayerInset(3, iArr3[0], iArr3[1], -iArr3[0], -iArr3[1]);
                setBackground(layerDrawable);
                int i6 = i3 + dimensionPixelSize2;
                setY((float) (-i6));
                setX((float) (i6 * i5));
                setMinimumHeight((dimensionPixelSize * 2) + (dimensionPixelSize2 * 2));
            }
        }
    }
}
