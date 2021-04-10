package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* renamed from: X.0e7  reason: invalid class name and case insensitive filesystem */
public final class C04140e7 extends AnonymousClass04I {
    public Drawable A00;
    public ColorStateList A01 = null;
    public PorterDuff.Mode A02 = null;
    public boolean A03 = false;
    public boolean A04 = false;
    public final SeekBar A05;

    @Override // X.AnonymousClass04I
    public final void A01(AttributeSet attributeSet, int i) {
        super.A01(attributeSet, i);
        SeekBar seekBar = this.A05;
        Context context = seekBar.getContext();
        int[] iArr = AnonymousClass18N.A06;
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(context, attributeSet, iArr, i, 0);
        seekBar.getContext();
        TypedArray typedArray = A002.A02;
        AnonymousClass0Aw.A01(seekBar, context, iArr, attributeSet, typedArray, i);
        Drawable A032 = A002.A03(0);
        if (A032 != null) {
            seekBar.setThumb(A032);
        }
        Drawable A022 = A002.A02(1);
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.A00 = A022;
        if (A022 != null) {
            A022.setCallback(seekBar);
            A022.setLayoutDirection(seekBar.getLayoutDirection());
            if (A022.isStateful()) {
                A022.setState(seekBar.getDrawableState());
            }
            A00();
        }
        seekBar.invalidate();
        if (typedArray.hasValue(3)) {
            this.A02 = C002704d.A00(typedArray.getInt(3, -1), this.A02);
            this.A04 = true;
        }
        if (typedArray.hasValue(2)) {
            this.A01 = A002.A01(2);
            this.A03 = true;
        }
        A002.A04();
        A00();
    }

    private void A00() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return;
        }
        if (this.A03 || this.A04) {
            Drawable mutate = drawable.mutate();
            this.A00 = mutate;
            if (this.A03) {
                mutate.setTintList(this.A01);
            }
            if (this.A04) {
                this.A00.setTintMode(this.A02);
            }
            if (this.A00.isStateful()) {
                this.A00.setState(this.A05.getDrawableState());
            }
        }
    }

    public C04140e7(SeekBar seekBar) {
        super(seekBar);
        this.A05 = seekBar;
    }
}
