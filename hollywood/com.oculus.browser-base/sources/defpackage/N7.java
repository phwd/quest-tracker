package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* renamed from: N7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N7 extends CheckBox {
    public final P7 F;
    public final K7 G;
    public final M8 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public N7(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        P7 p7 = new P7(this);
        this.F = p7;
        p7.b(attributeSet, i);
        K7 k7 = new K7(this);
        this.G = k7;
        k7.d(attributeSet, i);
        M8 m8 = new M8(this);
        this.H = m8;
        m8.e(attributeSet, i);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.G;
        if (k7 != null) {
            k7.a();
        }
        M8 m8 = this.H;
        if (m8 != null) {
            m8.b();
        }
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        P7 p7 = this.F;
        return compoundPaddingLeft;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.f(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        P7 p7 = this.F;
        if (p7 == null) {
            return;
        }
        if (p7.f) {
            p7.f = false;
            return;
        }
        p7.f = true;
        p7.a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(AbstractC5544x8.a(getContext(), i));
    }
}
