package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import java.util.Objects;

/* renamed from: Za0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1525Za0 implements TextView.OnEditorActionListener {
    public final C2374eb0 F;

    public C1525Za0(C2374eb0 eb0) {
        this.F = eb0;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        C2374eb0 eb0 = this.F;
        Objects.requireNonNull(eb0);
        if (i != 6) {
            return false;
        }
        eb0.d.c(-1).performClick();
        return true;
    }
}
