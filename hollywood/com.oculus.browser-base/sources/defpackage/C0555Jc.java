package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Jc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0555Jc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5411wL.f11541a;
        if (th0 == kh0) {
            ((TextView) view.findViewById(R.id.entity_subject)).setText((CharSequence) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5411wL.b;
        if (th02 == kh0) {
            TextView textView = (TextView) view.findViewById(R.id.entity_description);
            if (TextUtils.isEmpty((String) uh0.g(th02))) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView.setText((CharSequence) uh0.g(th02));
        }
    }
}
