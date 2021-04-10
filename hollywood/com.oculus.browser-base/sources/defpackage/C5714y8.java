package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.oculus.browser.R;

/* renamed from: y8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5714y8 extends SeekBar {
    public final C5884z8 F;

    public C5714y8(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f7450_resource_name_obfuscated_RES_2130969191);
        AbstractC1361Wg1.a(this, getContext());
        C5884z8 z8Var = new C5884z8(this);
        this.F = z8Var;
        z8Var.a(attributeSet, R.attr.f7450_resource_name_obfuscated_RES_2130969191);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        C5884z8 z8Var = this.F;
        Drawable drawable = z8Var.e;
        if (drawable != null && drawable.isStateful() && drawable.setState(z8Var.d.getDrawableState())) {
            z8Var.d.invalidateDrawable(drawable);
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.F.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.F.d(canvas);
    }

    public C5714y8(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AbstractC1361Wg1.a(this, getContext());
        C5884z8 z8Var = new C5884z8(this);
        this.F = z8Var;
        z8Var.a(attributeSet, i);
    }
}
