package org.chromium.components.browser_ui.photo_picker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.oculus.browser.R;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PhotoPickerToolbar extends AbstractView$OnClickListenerC2014cS0 {
    public AbstractC4877tC0 b1;

    public PhotoPickerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void P(C3209jS0 js0, int i, int i2, int i3, boolean z) {
        super.P(js0, i, i2, i3, z);
        S(1);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void R() {
        super.R();
        ((DialogC4536rC0) this.b1).cancel();
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        super.b(list);
        int size = list.size();
        Button button = (Button) findViewById(R.id.done);
        button.setEnabled(list.size() > 0);
        if (size > 0) {
            button.setTextAppearance(button.getContext(), R.style.f71990_resource_name_obfuscated_RES_2132017772);
            return;
        }
        button.setTextAppearance(button.getContext(), R.style.f71950_resource_name_obfuscated_RES_2132017768);
        S(1);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void onFinishInflate() {
        super.onFinishInflate();
        B(R.string.f49160_resource_name_obfuscated_RES_2131952233);
    }
}
