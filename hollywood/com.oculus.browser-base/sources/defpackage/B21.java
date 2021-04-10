package defpackage;

import android.view.View;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: B21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class B21 implements View.OnLayoutChangeListener {
    public final StatusView F;

    public B21(StatusView statusView) {
        this.F = statusView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.b();
    }
}
