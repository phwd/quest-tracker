package defpackage;

import android.widget.CompoundButton;
import android.widget.Switch;
import com.oculus.browser.R;

/* renamed from: E00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class E00 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        Switch r3 = (Switch) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = I00.c;
        if (th0 == kh0) {
            r3.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) uh0.g(th0));
            return;
        }
        QH0 qh0 = I00.b;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            r3.setChecked(h);
            r3.announceForAccessibility(r3.getResources().getString(h ? R.string.f45970_resource_name_obfuscated_RES_2131951914 : R.string.f45990_resource_name_obfuscated_RES_2131951916));
            r3.setContentDescription(r3.getResources().getString(h ? R.string.f46050_resource_name_obfuscated_RES_2131951922 : R.string.f46060_resource_name_obfuscated_RES_2131951923));
            return;
        }
        QH0 qh02 = I00.f8193a;
        if (qh02 == kh0) {
            r3.setVisibility(uh0.h(qh02) ? 0 : 8);
        }
    }
}
