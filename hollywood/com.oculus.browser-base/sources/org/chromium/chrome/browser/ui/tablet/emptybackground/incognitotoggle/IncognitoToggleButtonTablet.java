package org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle;

import android.content.Context;
import android.util.AttributeSet;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IncognitoToggleButtonTablet extends T00 {

    /* renamed from: J  reason: collision with root package name */
    public static final /* synthetic */ int f10794J = 0;
    public AbstractC5783ya1 K;

    public IncognitoToggleButtonTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void e() {
        AbstractC0124Ca1 ca1 = this.H;
        if (ca1 == null || ((AbstractC0246Ea1) ca1).i() == null) {
            setVisibility(8);
        } else {
            post(new W00(this));
        }
    }

    public void onAttachedToWindow() {
        AbstractC0124Ca1 ca1 = this.H;
        if (ca1 != null) {
            ((AbstractC0246Ea1) ca1).c(this.I);
            for (TabModel tabModel : ((AbstractC0246Ea1) this.H).f7969a) {
                tabModel.n(this.K);
            }
        }
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        AbstractC0124Ca1 ca1 = this.H;
        if (ca1 != null) {
            ((AbstractC0246Ea1) ca1).f.c(this.I);
            for (TabModel tabModel : ((AbstractC0246Ea1) this.H).f7969a) {
                tabModel.w(this.K);
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // defpackage.T00
    public void onFinishInflate() {
        super.onFinishInflate();
        setVisibility(8);
        setOnClickListener(new U00(this));
    }
}
