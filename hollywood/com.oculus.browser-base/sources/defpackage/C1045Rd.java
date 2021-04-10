package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import java.util.Objects;

/* renamed from: Rd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1045Rd implements TextView.OnEditorActionListener {
    public final C1228Ud F;

    public C1045Rd(C1228Ud ud) {
        this.F = ud;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        C1228Ud ud = this.F;
        Objects.requireNonNull(ud);
        if (i != 6) {
            return false;
        }
        if (ud.I.getText().toString().trim().length() != 0) {
            ud.d(ud.G, 0);
        }
        return true;
    }
}
