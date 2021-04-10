package org.chromium.components.browser_ui.contacts_picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContactsPickerToolbar extends AbstractView$OnClickListenerC2014cS0 {
    public AbstractC0118By b1;
    public boolean c1 = true;

    public ContactsPickerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void P(C3209jS0 js0, int i, int i2, int i3, boolean z) {
        super.P(js0, i, i2, i3, z);
        S(1);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void R() {
        if (this.x0) {
            super.R();
        } else {
            ((DialogC6023zy) this.b1).cancel();
        }
    }

    public final void a0() {
        ColorStateList colorStateList;
        boolean z = !this.w0.c.isEmpty();
        boolean z2 = z && this.c1;
        ButtonCompat buttonCompat = (ButtonCompat) findViewById(R.id.done);
        buttonCompat.setEnabled(z2);
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById(R.id.search);
        if (!this.v0) {
            colorStateList = this.Q0;
        } else {
            colorStateList = this.R0;
        }
        appCompatImageView.setImageTintList(colorStateList);
        if (z2) {
            buttonCompat.setTextAppearance(buttonCompat.getContext(), R.style.f71990_resource_name_obfuscated_RES_2132017772);
            return;
        }
        buttonCompat.setTextAppearance(buttonCompat.getContext(), R.style.f71950_resource_name_obfuscated_RES_2132017768);
        if (z) {
            S(2);
        } else {
            S(1);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        super.b(list);
        a0();
    }
}
