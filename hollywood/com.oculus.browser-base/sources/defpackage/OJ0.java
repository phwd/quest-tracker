package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* renamed from: OJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OJ0 implements TextView.OnEditorActionListener {
    public final RadioButtonWithEditText F;

    public OJ0(RadioButtonWithEditText radioButtonWithEditText) {
        this.F = radioButtonWithEditText;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.F.j();
    }
}
