package defpackage;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: Hx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0483Hx0 extends AbstractC5180v0 {
    public final int Z = this.G.getContext().getResources().getDimensionPixelSize(R.dimen.f20310_resource_name_obfuscated_RES_2131165650);
    public final int a0 = this.G.getContext().getResources().getDimensionPixelSize(R.dimen.f20290_resource_name_obfuscated_RES_2131165648);

    public C0483Hx0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f38980_resource_name_obfuscated_RES_2131624207);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C3319k50 k50 = (C3319k50) obj;
        LinearLayout linearLayout = (LinearLayout) view;
        TextView textView = (TextView) linearLayout.findViewById(R.id.suggestion_text);
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.password_text);
        z(textView, (UserInfoField) k50.b.get(0));
        z(textView2, (UserInfoField) k50.b.get(1));
        C3884nO nOVar = new C3884nO(textView.getContext());
        Drawable b = nOVar.b(k50.f10260a);
        int i = this.a0;
        b.setBounds(0, 0, i, i);
        textView.setCompoundDrawablePadding(this.Z);
        textView.setCompoundDrawablesRelative(b, null, null, null);
        nOVar.a(k50.f10260a, new C0361Fx0(this, textView));
        int i2 = this.Z;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        textView.setPaddingRelative(i2, 0, i2, 0);
        int i3 = this.Z;
        textView2.setPaddingRelative((i3 * 2) + this.a0, 0, i3, 0);
    }

    public final void z(TextView textView, UserInfoField userInfoField) {
        Drawable drawable = null;
        textView.setTransformationMethod(userInfoField.isObfuscated() ? new PasswordTransformationMethod() : null);
        textView.setGravity(16 | ((!LocalizationUtils.isLayoutRtl() || !userInfoField.isObfuscated()) ? 8388611 : 8388613));
        textView.setText(userInfoField.getDisplayText());
        textView.setContentDescription(userInfoField.getA11yDescription());
        textView.setOnClickListener(!userInfoField.isSelectable() ? null : new View$OnClickListenerC0422Gx0(userInfoField));
        textView.setClickable(true);
        textView.setEnabled(userInfoField.isSelectable());
        if (userInfoField.isSelectable()) {
            TypedArray obtainStyledAttributes = this.G.getContext().obtainStyledAttributes(new int[]{R.attr.f7490_resource_name_obfuscated_RES_2130969195});
            drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
        }
        textView.setBackground(drawable);
    }
}
