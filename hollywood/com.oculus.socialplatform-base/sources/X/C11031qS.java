package X;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

/* renamed from: X.1qS  reason: invalid class name and case insensitive filesystem */
public class C11031qS extends Drawable.ConstantState {
    public int A00;
    public int A01;
    public ColorStateList A02;
    public ColorStateList A03;
    public Bitmap A04;
    public Paint A05;
    public PorterDuff.Mode A06;
    public PorterDuff.Mode A07;
    public C10941qH A08;
    public boolean A09;
    public boolean A0A;
    public boolean A0B;

    public final void A00(int i, int i2) {
        this.A04.eraseColor(0);
        Canvas canvas = new Canvas(this.A04);
        C10941qH r0 = this.A08;
        C10941qH.A00(r0, r0.A0F, C10941qH.A0G, canvas, i, i2);
    }

    public int getChangingConfigurations() {
        return this.A01;
    }

    public C11031qS() {
        this.A03 = null;
        this.A07 = C10921qD.A08;
        this.A08 = new C10941qH();
    }

    public C11031qS(C11031qS r4) {
        this.A03 = null;
        this.A07 = C10921qD.A08;
        if (r4 != null) {
            this.A01 = r4.A01;
            C10941qH r2 = new C10941qH(r4.A08);
            this.A08 = r2;
            Paint paint = r4.A08.A05;
            if (paint != null) {
                r2.A05 = new Paint(paint);
            }
            Paint paint2 = r4.A08.A06;
            if (paint2 != null) {
                this.A08.A06 = new Paint(paint2);
            }
            this.A03 = r4.A03;
            this.A07 = r4.A07;
            this.A09 = r4.A09;
        }
    }

    @NonNull
    public final Drawable newDrawable() {
        return new C10921qD(this);
    }

    @NonNull
    public final Drawable newDrawable(Resources resources) {
        return new C10921qD(this);
    }
}
