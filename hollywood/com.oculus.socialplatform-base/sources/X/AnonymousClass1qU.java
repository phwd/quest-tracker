package X;

import android.graphics.Paint;
import androidx.annotation.ColorInt;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1qU  reason: invalid class name */
public class AnonymousClass1qU extends AbstractC11071qY {
    public float A00 = 1.0f;
    public float A01 = 1.0f;
    public float A02 = 4.0f;
    public float A03 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A04 = 1.0f;
    public float A05 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A06 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public Paint.Cap A07 = Paint.Cap.BUTT;
    public Paint.Join A08 = Paint.Join.MITER;
    public C001204i A09;
    public C001204i A0A;
    public int[] A0B;

    public float getFillAlpha() {
        return this.A00;
    }

    @ColorInt
    public int getFillColor() {
        return this.A09.A00;
    }

    public float getStrokeAlpha() {
        return this.A01;
    }

    @ColorInt
    public int getStrokeColor() {
        return this.A0A.A00;
    }

    public float getStrokeWidth() {
        return this.A03;
    }

    public float getTrimPathEnd() {
        return this.A04;
    }

    public float getTrimPathOffset() {
        return this.A05;
    }

    public float getTrimPathStart() {
        return this.A06;
    }

    public void setFillColor(int i) {
        this.A09.A00 = i;
    }

    public void setStrokeColor(int i) {
        this.A0A.A00 = i;
    }

    public void setFillAlpha(float f) {
        this.A00 = f;
    }

    public void setStrokeAlpha(float f) {
        this.A01 = f;
    }

    public void setStrokeWidth(float f) {
        this.A03 = f;
    }

    public void setTrimPathEnd(float f) {
        this.A04 = f;
    }

    public void setTrimPathOffset(float f) {
        this.A05 = f;
    }

    public void setTrimPathStart(float f) {
        this.A06 = f;
    }

    public AnonymousClass1qU() {
    }

    public AnonymousClass1qU(AnonymousClass1qU r3) {
        super(r3);
        this.A0B = r3.A0B;
        this.A0A = r3.A0A;
        this.A03 = r3.A03;
        this.A01 = r3.A01;
        this.A09 = r3.A09;
        super.A01 = ((AbstractC11071qY) r3).A01;
        this.A00 = r3.A00;
        this.A06 = r3.A06;
        this.A04 = r3.A04;
        this.A05 = r3.A05;
        this.A07 = r3.A07;
        this.A08 = r3.A08;
        this.A02 = r3.A02;
    }
}
