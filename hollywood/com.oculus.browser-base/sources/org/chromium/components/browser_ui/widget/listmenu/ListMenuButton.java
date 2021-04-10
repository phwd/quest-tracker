package org.chromium.components.browser_ui.widget.listmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeImageButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ListMenuButton extends ChromeImageButton implements N4 {
    public final int H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f10825J;
    public Drawable K;
    public O4 L;
    public V80 M;
    public C1322Vq0 N = new C1322Vq0();
    public boolean O;

    public ListMenuButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.Z);
        this.H = obtainStyledAttributes.getDimensionPixelSize(2, getResources().getDimensionPixelSize(R.dimen.f20400_resource_name_obfuscated_RES_2131165659));
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        this.K = drawable;
        if (drawable == null) {
            this.K = AbstractC3153j7.c(getResources(), R.drawable.f34630_resource_name_obfuscated_RES_2131231503);
        }
        this.f10825J = obtainStyledAttributes.getBoolean(1, true);
        this.I = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
    }

    @Override // defpackage.N4
    public void a(boolean z, int i, int i2, int i3, int i4, Rect rect) {
        this.L.K.setAnimationStyle(z ? R.style.f69010_resource_name_obfuscated_RES_2132017474 : R.style.f69020_resource_name_obfuscated_RES_2132017475);
    }

    public void d() {
        O4 o4 = this.L;
        if (o4 != null) {
            o4.K.dismiss();
        }
    }

    public void f(String str) {
        if (str == null) {
            str = "";
        }
        setContentDescription(getContext().getResources().getString(R.string.f45520_resource_name_obfuscated_RES_2131951869, str));
    }

    /* renamed from: g */
    public void e() {
        V80 v80 = this.M;
        if (v80 != null) {
            C1237Ug a2 = v80.a();
            a2.I.add(new S80(this));
            O4 o4 = new O4(getContext(), this, this.K, a2.H, this.M.b(this));
            this.L = o4;
            o4.d0 = this.I;
            o4.e0 = this.f10825J;
            o4.W = this.H;
            if (this.O) {
                o4.X = AbstractC2417ep1.b(a2.G, a2.F);
            }
            this.L.K.setFocusable(true);
            O4 o42 = this.L;
            o42.Q = this;
            o42.P.b(new T80(this));
            this.L.d();
            C1261Uq0 uq0 = (C1261Uq0) this.N.iterator();
            if (uq0.hasNext()) {
                C5859z.a(uq0.next());
                throw null;
            }
            return;
        }
        throw new IllegalStateException("Delegate was not set.");
    }

    public void onDetachedFromWindow() {
        d();
        super.onDetachedFromWindow();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        if (TextUtils.isEmpty(getContentDescription())) {
            f("");
        }
        setOnClickListener(new U80(this));
    }
}
