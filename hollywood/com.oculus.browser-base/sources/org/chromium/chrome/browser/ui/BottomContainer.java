package org.chromium.chrome.browser.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BottomContainer extends FrameLayout implements AbstractC4371qE, AbstractC2230dk {
    public final Callback F = new C1885bj(this);
    public AbstractC2400ek G;
    public C2712ga H;
    public float I;

    public BottomContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        ((C1551Zj) this.G).Y.c(this);
        C2712ga gaVar = this.H;
        gaVar.I.c(this.F);
    }

    public final /* synthetic */ void f() {
        setTranslationY(this.I);
    }

    @Override // defpackage.AbstractC2230dk
    public void h(int i, int i2) {
        setTranslationY(this.I);
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        setTranslationY(this.I);
    }

    @Override // defpackage.AbstractC2230dk
    public void k(int i, int i2) {
    }

    public void setTranslationY(float f) {
        this.I = f;
        super.setTranslationY(this.I + (((float) (((C1551Zj) this.G).b() - ((C1551Zj) this.G).O)) - ((float) ((Integer) this.H.H).intValue())));
    }
}
