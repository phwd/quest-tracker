package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* renamed from: q8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4353q8 extends ImageButton {
    public final K7 F;
    public final C4523r8 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4353q8(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        K7 k7 = new K7(this);
        this.F = k7;
        k7.d(attributeSet, i);
        C4523r8 r8Var = new C4523r8(this);
        this.G = r8Var;
        r8Var.b(attributeSet, i);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.F;
        if (k7 != null) {
            k7.a();
        }
        C4523r8 r8Var = this.G;
        if (r8Var != null) {
            r8Var.a();
        }
    }

    public boolean hasOverlappingRendering() {
        if (!(!(this.G.f11184a.getBackground() instanceof RippleDrawable)) || !super.hasOverlappingRendering()) {
            return false;
        }
        return true;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.f(i);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C4523r8 r8Var = this.G;
        if (r8Var != null) {
            r8Var.a();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C4523r8 r8Var = this.G;
        if (r8Var != null) {
            r8Var.a();
        }
    }

    public void setImageResource(int i) {
        this.G.c(i);
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C4523r8 r8Var = this.G;
        if (r8Var != null) {
            r8Var.a();
        }
    }
}
