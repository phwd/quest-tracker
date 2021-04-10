package X;

import android.graphics.Paint;
import androidx.annotation.ColorInt;

/* renamed from: X.0M0  reason: invalid class name */
public class AnonymousClass0M0 extends AbstractC03330c3 {
    public float A00 = 1.0f;
    public float A01 = 1.0f;
    public float A02 = 4.0f;
    public float A03 = 0.0f;
    public float A04 = 1.0f;
    public float A05 = 0.0f;
    public float A06 = 0.0f;
    public Paint.Cap A07 = Paint.Cap.BUTT;
    public Paint.Join A08 = Paint.Join.MITER;
    public AnonymousClass080 A09;
    public AnonymousClass080 A0A;
    public int[] A0B;

    @Override // X.AnonymousClass0H7
    public final boolean A01() {
        if (this.A09.A00() || this.A0A.A00()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0021  */
    @Override // X.AnonymousClass0H7
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(int[] r5) {
        /*
            r4 = this;
            X.080 r2 = r4.A09
            boolean r0 = r2.A00()
            if (r0 == 0) goto L_0x0036
            android.content.res.ColorStateList r1 = r2.A01
            int r0 = r1.getDefaultColor()
            int r1 = r1.getColorForState(r5, r0)
            int r0 = r2.A00
            if (r1 == r0) goto L_0x0036
            r3 = 1
            r2.A00 = r1
        L_0x0019:
            X.080 r2 = r4.A0A
            boolean r0 = r2.A00()
            if (r0 == 0) goto L_0x0034
            android.content.res.ColorStateList r1 = r2.A01
            int r0 = r1.getDefaultColor()
            int r1 = r1.getColorForState(r5, r0)
            int r0 = r2.A00
            if (r1 == r0) goto L_0x0034
            r0 = 1
            r2.A00 = r1
        L_0x0032:
            r0 = r0 | r3
            return r0
        L_0x0034:
            r0 = 0
            goto L_0x0032
        L_0x0036:
            r3 = 0
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0M0.A02(int[]):boolean");
    }

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

    public AnonymousClass0M0() {
    }

    public AnonymousClass0M0(AnonymousClass0M0 r3) {
        super(r3);
        this.A0B = r3.A0B;
        this.A0A = r3.A0A;
        this.A03 = r3.A03;
        this.A01 = r3.A01;
        this.A09 = r3.A09;
        super.A01 = ((AbstractC03330c3) r3).A01;
        this.A00 = r3.A00;
        this.A06 = r3.A06;
        this.A04 = r3.A04;
        this.A05 = r3.A05;
        this.A07 = r3.A07;
        this.A08 = r3.A08;
        this.A02 = r3.A02;
    }
}
