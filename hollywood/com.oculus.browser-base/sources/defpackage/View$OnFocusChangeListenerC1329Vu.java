package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/* renamed from: Vu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnFocusChangeListenerC1329Vu implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1390Wu f9114a;

    public View$OnFocusChangeListenerC1329Vu(C1390Wu wu) {
        this.f9114a = wu;
    }

    public void onFocusChange(View view, boolean z) {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(((EditText) view).getText());
        C2092cv cvVar = this.f9114a.f9179a;
        if (!z3 || !z) {
            z2 = false;
        }
        cvVar.d(z2);
    }
}
