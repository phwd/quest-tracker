package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* renamed from: PJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class PJ0 implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final RadioButtonWithEditText f8684a;

    public PJ0(RadioButtonWithEditText radioButtonWithEditText) {
        this.f8684a = radioButtonWithEditText;
    }

    public void onFocusChange(View view, boolean z) {
        this.f8684a.k(z);
    }
}
