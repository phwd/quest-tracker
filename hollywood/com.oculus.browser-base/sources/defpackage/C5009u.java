package defpackage;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* renamed from: u  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5009u extends ClickableSpan {
    public final int F;
    public final D G;
    public final int H;

    public C5009u(int i, D d, int i2) {
        this.F = i;
        this.G = d;
        this.H = i2;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.F);
        D d = this.G;
        d.b.performAction(this.H, bundle);
    }
}
