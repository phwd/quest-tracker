package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

/* renamed from: ZQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZQ0 implements TextView.OnEditorActionListener {
    public final /* synthetic */ SearchView F;

    public ZQ0(SearchView searchView) {
        this.F = searchView;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.F.v();
        return true;
    }
}
