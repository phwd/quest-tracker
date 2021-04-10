package defpackage;

import android.view.View;
import com.oculus.browser.R;

/* renamed from: f00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2450f00 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        if (kh0 == AbstractC3475l00.f10321a) {
            view.findViewById(R.id.incognito_interstitial_learn_more).setOnClickListener(new View$OnClickListenerC3646m00(uh0));
        } else if (kh0 == AbstractC3475l00.b) {
            view.findViewById(R.id.incognito_interstitial_continue_button).setOnClickListener(new View$OnClickListenerC3817n00(uh0));
        } else {
            throw new IllegalArgumentException("Cannot update the view for propertyKey: " + kh0);
        }
    }
}
