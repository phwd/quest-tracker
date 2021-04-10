package X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.alpenglow.R;

/* renamed from: X.0Mp  reason: invalid class name and case insensitive filesystem */
public class C01850Mp extends AnonymousClass0eA implements AbstractC002203u {
    public final /* synthetic */ AnonymousClass0Mm A00;

    @Override // X.AbstractC002203u
    public final boolean A5j() {
        return false;
    }

    @Override // X.AbstractC002203u
    public final boolean A5k() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01850Mp(AnonymousClass0Mm r3, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.A00 = r3;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        C004805f.A00(this, getContentDescription());
        setOnTouchListener(new AnonymousClass0eM(this, this, r3));
    }

    public final boolean performClick() {
        if (!super.performClick()) {
            playSoundEffect(0);
            this.A00.A07();
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
