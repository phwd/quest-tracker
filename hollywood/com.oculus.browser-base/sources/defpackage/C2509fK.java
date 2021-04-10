package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/* renamed from: fK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2509fK implements TextView.OnEditorActionListener {
    public final /* synthetic */ View$OnClickListenerC3876nK F;

    public C2509fK(View$OnClickListenerC3876nK nKVar) {
        this.F = nKVar;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        View focusSearch;
        if (i == 6) {
            this.F.S.performClick();
            return true;
        } else if (i != 5 || (focusSearch = textView.focusSearch(2)) == null) {
            return false;
        } else {
            focusSearch.requestFocus();
            return true;
        }
    }
}
