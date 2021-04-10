package X;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: X.1jT  reason: invalid class name and case insensitive filesystem */
public final class C09671jT extends Drawable implements AbstractC10501n0 {
    public int A00;
    public int A01;
    public int A02;
    public int A03 = -1;
    public int A04;
    public int A05;
    public int A06 = 80;
    public int A07;
    @Nullable
    public AnonymousClass2eu A08;
    public String A09;
    @Nullable
    public String A0A;
    public int A0B;
    public int A0C;
    public long A0D;
    public HashMap<String, String> A0E = new HashMap<>();
    public final Matrix A0F = new Matrix();
    public final Paint A0G = new Paint(1);
    public final Rect A0H = new Rect();
    public final RectF A0I = new RectF();

    public final void A01() {
        this.A07 = -1;
        this.A00 = -1;
        this.A01 = -1;
        this.A0E = new HashMap<>();
        this.A09 = "none";
        invalidateSelf();
        this.A0D = -1;
        this.A0A = null;
        this.A03 = -1;
        invalidateSelf();
    }

    public final int getOpacity() {
        return -3;
    }

    public final void setAlpha(int i) {
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    private void A00(Canvas canvas, String str, String str2, int i) {
        String A072 = AnonymousClass006.A07(str, ": ");
        Paint paint = this.A0G;
        float measureText = paint.measureText(A072);
        float measureText2 = paint.measureText(str2);
        paint.setColor(1711276032);
        int i2 = this.A0B;
        int i3 = this.A0C;
        canvas.drawRect((float) (i2 - 4), (float) (i3 + 8), ((float) i2) + measureText + measureText2 + 4.0f, (float) (i3 + this.A02 + 8), paint);
        paint.setColor(-1);
        canvas.drawText(A072, (float) this.A0B, (float) this.A0C, paint);
        paint.setColor(i);
        canvas.drawText(str2, ((float) this.A0B) + measureText, (float) this.A0C, paint);
        this.A0C += this.A02;
    }

    @Override // X.AbstractC10501n0
    public final void A77(long j) {
        this.A0D = j;
        invalidateSelf();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0199, code lost:
        if (r0 >= 0) goto L_0x019b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x017a A[LOOP:0: B:22:0x0174->B:24:0x017a, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void draw(android.graphics.Canvas r21) {
        /*
        // Method dump skipped, instructions count: 416
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09671jT.draw(android.graphics.Canvas):void");
    }

    public C09671jT() {
        A01();
    }

    public final void onBoundsChange(Rect rect) {
        int i;
        super.onBoundsChange(rect);
        int min = Math.min(40, Math.max(10, Math.min(rect.width() / 8, rect.height() / 9)));
        this.A0G.setTextSize((float) min);
        int i2 = min + 8;
        this.A02 = i2;
        int i3 = this.A06;
        if (i3 == 80) {
            this.A02 = -i2;
        }
        this.A04 = rect.left + 10;
        if (i3 == 80) {
            i = rect.bottom - 10;
        } else {
            i = rect.top + 10 + 10;
        }
        this.A05 = i;
    }
}
