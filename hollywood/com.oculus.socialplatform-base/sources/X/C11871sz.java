package X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.socialplatform.R;

/* renamed from: X.1sz  reason: invalid class name and case insensitive filesystem */
public class C11871sz extends C11011qQ implements AbstractC11951te {
    public final /* synthetic */ C11591sO A00;

    @Override // X.AbstractC11951te
    public final boolean A6O() {
        return false;
    }

    @Override // X.AbstractC11951te
    public final boolean A6P() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11871sz(C11591sO r3, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.A00 = r3;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        AnonymousClass1FF.A00(this, getContentDescription());
        setOnTouchListener(new AnonymousClass1t0(this, this, r3));
    }

    public final boolean performClick() {
        if (!super.performClick()) {
            playSoundEffect(0);
            this.A00.A05();
        }
        return true;
    }

    public final boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (!(drawable == null || background == null)) {
            int width = getWidth();
            int height = getHeight();
            int max = Math.max(width, height) >> 1;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) >> 1;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) >> 1;
            background.setHotspotBounds(paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
        }
        return frame;
    }
}
