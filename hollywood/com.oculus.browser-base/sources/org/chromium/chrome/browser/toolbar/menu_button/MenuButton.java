package org.chromium.chrome.browser.toolbar.menu_button;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuButton extends FrameLayout implements AbstractC1056Rg1 {
    public ImageButton F;
    public ImageView G;
    public boolean H;
    public AbstractView$OnTouchListenerC4526r9 I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10787J;
    public HI0 K;
    public Drawable L;
    public AnimatorSet M;
    public boolean N;
    public BitmapDrawable O;
    public BitmapDrawable P;

    public MenuButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(boolean z) {
        ImageView imageView = this.G;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
            if (z) {
                b();
            }
        }
    }

    public final void b() {
        ImageView imageView;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.F.getDrawable().getConstantState().newDrawable().mutate();
        this.O = bitmapDrawable;
        bitmapDrawable.setBounds(this.F.getPaddingLeft(), this.F.getPaddingTop(), this.F.getWidth() - this.F.getPaddingRight(), this.F.getHeight() - this.F.getPaddingBottom());
        this.O.setGravity(17);
        this.O.setColorFilter(AbstractC1300Vg1.b(getContext(), this.H).getDefaultColor(), PorterDuff.Mode.SRC_IN);
        C1737aq1 aq1 = C2249dq1.a().f.b;
        if (aq1 != null && (imageView = this.G) != null) {
            imageView.setImageDrawable(AbstractC3153j7.c(getResources(), this.H ? aq1.c : aq1.b));
            BitmapDrawable bitmapDrawable2 = (BitmapDrawable) this.G.getDrawable().getConstantState().newDrawable().mutate();
            this.P = bitmapDrawable2;
            bitmapDrawable2.setBounds(this.G.getPaddingLeft(), this.G.getPaddingTop(), this.G.getWidth() - this.G.getPaddingRight(), this.G.getHeight() - this.G.getPaddingBottom());
            this.P.setGravity(17);
        }
    }

    @Override // defpackage.AbstractC1056Rg1
    public void c(ColorStateList colorStateList, boolean z) {
        this.F.setImageTintList(colorStateList);
        this.H = z;
        b();
        d();
    }

    public final void d() {
        if (this.F != null) {
            if (this.f10787J) {
                if (this.K == null) {
                    HI0 a2 = HI0.a(getContext());
                    this.K = a2;
                    ImageButton imageButton = this.F;
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    a2.H.set(imageButton.getPaddingStart(), this.F.getPaddingTop(), this.F.getPaddingEnd(), this.F.getPaddingBottom());
                    if (!a2.I.isEmpty()) {
                        a2.setBounds(a2.I);
                    }
                }
                this.K.d(getContext().getResources(), this.H);
                setBackground(this.K);
                this.K.start();
                return;
            }
            setBackground(this.L);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ImageButton) findViewById(R.id.menu_button);
        this.G = (ImageView) findViewById(R.id.menu_badge);
        this.L = getBackground();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            b();
        }
    }

    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        ImageButton imageButton = this.F;
        if (imageButton != null) {
            imageButton.setOnKeyListener(onKeyListener);
        }
    }
}
