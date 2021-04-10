package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;

/* renamed from: T11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T11 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C4848t21 t21 = (C4848t21) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC4507r21.f11179a;
        if (th0 == kh0) {
            ((TextView) t21.f11316a.findViewById(R.id.status_text)).setText((CharSequence) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC4507r21.b;
        if (th02 == kh0) {
            ((TextView) t21.f11316a.findViewById(R.id.status_text)).setCompoundDrawablesRelative((Drawable) uh0.g(th02), null, null, null);
            return;
        }
        QH0 qh0 = AbstractC4507r21.d;
        if (qh0 == kh0) {
            t21.b.f11247J = uh0.h(qh0);
            return;
        }
        SH0 sh0 = AbstractC4507r21.c;
        if (sh0 == kh0) {
            t21.f11316a.setVisibility(uh0.f(sh0));
            return;
        }
        SH0 sh02 = AbstractC4507r21.e;
        if (sh02 == kh0) {
            t21.f11316a.setBackgroundColor(uh0.f(sh02));
            return;
        }
        RH0 rh0 = AbstractC4507r21.f;
        if (rh0 == kh0) {
            t21.f11316a.findViewById(R.id.status_text).setAlpha(uh0.e(rh0));
            return;
        }
        SH0 sh03 = AbstractC4507r21.g;
        if (sh03 == kh0) {
            ((TextView) t21.f11316a.findViewById(R.id.status_text)).setTextColor(uh0.f(sh03));
            return;
        }
        SH0 sh04 = AbstractC4507r21.h;
        if (sh04 == kh0) {
            ((TextViewWithCompoundDrawables) t21.f11316a.findViewById(R.id.status_text)).h(ColorStateList.valueOf(uh0.f(sh04)));
            return;
        }
        SH0 sh05 = AbstractC4507r21.i;
        if (sh05 == kh0) {
            t21.f11316a.setTranslationY((float) (uh0.f(sh05) - t21.f11316a.getHeight()));
        }
    }
}
