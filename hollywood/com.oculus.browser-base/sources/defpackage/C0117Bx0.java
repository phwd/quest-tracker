package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.PasswordAccessoryInfoView;
import org.chromium.ui.widget.ChipView;

/* renamed from: Bx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0117Bx0 extends AbstractC5180v0 {
    public String Z;

    public C0117Bx0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f39010_resource_name_obfuscated_RES_2131624210);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C3319k50 k50 = (C3319k50) obj;
        PasswordAccessoryInfoView passwordAccessoryInfoView = (PasswordAccessoryInfoView) view;
        int i = 0;
        z(passwordAccessoryInfoView.H, (UserInfoField) k50.b.get(0));
        z(passwordAccessoryInfoView.I, (UserInfoField) k50.b.get(1));
        TextView textView = passwordAccessoryInfoView.F;
        if (!k50.c) {
            i = 8;
        }
        textView.setVisibility(i);
        passwordAccessoryInfoView.F.setText(AbstractC5154ur1.k(k50.f10260a).replaceFirst("/$", ""));
        this.Z = k50.f10260a;
        C3884nO nOVar = new C3884nO(passwordAccessoryInfoView.getContext());
        passwordAccessoryInfoView.a(nOVar.b(k50.f10260a));
        nOVar.a(k50.f10260a, new C6021zx0(this, passwordAccessoryInfoView, k50));
    }

    public void z(ChipView chipView, UserInfoField userInfoField) {
        View$OnClickListenerC0056Ax0 ax0 = null;
        chipView.F.setTransformationMethod(userInfoField.isObfuscated() ? new PasswordTransformationMethod() : null);
        chipView.F.setText(userInfoField.getDisplayText());
        chipView.F.setContentDescription(userInfoField.getA11yDescription());
        if (userInfoField.isSelectable()) {
            ax0 = new View$OnClickListenerC0056Ax0(userInfoField);
        }
        chipView.setOnClickListener(ax0);
        chipView.setClickable(userInfoField.isSelectable());
        chipView.setEnabled(userInfoField.isSelectable());
    }
}
