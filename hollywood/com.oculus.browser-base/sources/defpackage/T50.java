package defpackage;

import android.graphics.drawable.Drawable;
import org.chromium.chrome.browser.keyboard_accessory.tab_layout_component.KeyboardAccessoryTabLayoutView;

/* renamed from: T50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T50 implements AbstractC2504fI0 {
    public final U50 F;
    public final C1794b90 G;
    public final KeyboardAccessoryTabLayoutView H;
    public final int I;

    public T50(U50 u50, C1794b90 b90, KeyboardAccessoryTabLayoutView keyboardAccessoryTabLayoutView, int i) {
        this.F = u50;
        this.G = b90;
        this.H = keyboardAccessoryTabLayoutView;
        this.I = i;
    }

    @Override // defpackage.AbstractC2504fI0
    public void a(int i, Object obj) {
        Drawable drawable = (Drawable) obj;
        this.F.e(this.H, this.G);
    }
}
