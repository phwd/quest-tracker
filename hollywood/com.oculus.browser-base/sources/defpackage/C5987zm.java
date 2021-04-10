package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import java.util.Objects;

/* renamed from: zm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5987zm implements TextView.OnEditorActionListener {
    public final View$OnClickListenerC0521Im F;

    public C5987zm(View$OnClickListenerC0521Im im) {
        this.F = im;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        View$OnClickListenerC0521Im im = this.F;
        Objects.requireNonNull(im);
        if (i != 6) {
            return false;
        }
        if (!im.G.h(AbstractC3258jl0.i)) {
            im.d(im.G, 0);
        }
        return true;
    }
}
